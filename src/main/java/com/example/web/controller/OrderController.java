package com.example.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;

import com.example.domain.Mail;
import com.example.domain.Order;
import com.example.domain.OrderHistory;
import com.example.domain.User;
import com.example.service.OrderService;
import com.example.service.mail.EmailHtmlSender;
import com.example.service.mail.EmailSender;
import com.example.service.mail.EmailStatus;
import com.example.web.form.OrderForm;

@SuppressWarnings("unused")
@Controller
public class OrderController {

	@Autowired
	OrderService service;
	
	@Autowired
	HttpSession session;
	
	// cartに商品追加
	@RequestMapping("/cartPut")
	public String cart(@Param("pid") String pid, @Param("num")String num) {
		// sessionにあるcart取得
		@SuppressWarnings("unchecked")
		HashMap<String, Integer> map = (HashMap<String, Integer>) session.getAttribute("map");
		// セッションにcartのマップがない場合
		if (map == null) {
			// 新しいマップ生成
			map = new HashMap<>();
		}
		// 選択された商品がすでにcartにある場合
		if (map.get(pid) != null) {
			// 商品の数量を増やす
			map.put(pid, map.get(pid)+Integer.parseInt(num));
		} else {
			// 新しい商品が選択された場合は選択された商品番号と数量を追加
			map.put(pid, Integer.parseInt(num));
		}
		// セッションにcart保存
		session.setAttribute("map", map);
		// cartの商品リストを利用して注文リストを生成
		return "redirect:/orderlist";
	}
	
	// cartの商品で注文リスト作る
	@SuppressWarnings("unchecked")
	@RequestMapping("/orderlist")
	public String orderlist(@ModelAttribute("order")OrderForm form, Model model) {
		// cart取得
		HashMap<String, Integer> map = (HashMap<String, Integer>) session.getAttribute("map");
		// 新しい注文リスト宣言
		ArrayList<OrderForm> orderList = new ArrayList<>();
		// cartの商品番号を基にしてDBから商品の注文リスト作る
		orderList = service.getPnames(map, orderList);
		// セッションに注文リスト保存
		session.setAttribute("orderList", orderList);
		// cartページ表示させるサーブレットにredirect
		return "redirect:/cart";
	}
	
	// cartのページに遷移
	@RequestMapping("/cart")
	public String cartPage(Model model) {
		// sessionにあるlist取得
		@SuppressWarnings("unchecked")
		ArrayList<OrderForm> orderList = (ArrayList<OrderForm>) session.getAttribute("orderList");
		if (orderList == null) {
			orderList = new ArrayList<>();
		}
		int sum = 0;
		for (OrderForm of : orderList) {
			sum += of.getTotal();
		}
		model.addAttribute("orderTotalPrice", sum);
		// ページに値伝える
		model.addAttribute("list", orderList);
		// ページに遷移
		return "cart";
	}

	@RequestMapping("/cartDelete")
	public String cartDelete(@Param("pid") String pid) {
		// sessionにあるcart取得
		@SuppressWarnings("unchecked")
		HashMap<String, Integer> map = (HashMap<String, Integer>) session.getAttribute("map");
		// 選択された商品番号の削除
		map.remove(pid);
		// 削除したcartをまたセッションに保存
		session.setAttribute("map", map);
		// 新しい注文リストを作ってcartページに表示
		return "redirect:/orderlist";
	}

/*	@RequestMapping("/cartModify")
	public String cartModify(@Param("pid") String pid) {
		HashMap<String, Integer> map = (HashMap<String, Integer>) session.getAttribute("map");
		session.setAttribute("map", map);
		return "redirect:/detail?id="+pid;
	}*/

	// 注文ページにカート目録表示
	@RequestMapping("/order")
	public String orderPage(Model model) {
		// セッションにある注文リストを取得
		@SuppressWarnings("unchecked")
		ArrayList<OrderForm> orderList = (ArrayList<OrderForm>) session.getAttribute("orderList");
		int sum = 0;
		for (OrderForm of : orderList) {
			sum += of.getTotal();
		}
		model.addAttribute("orderTotalPrice", sum);
		//　orderページに送る
		model.addAttribute("list", orderList);
		// 注文確認ページに遷移
		return "order";
	}

	// 注文登録
	@RequestMapping("/orderEnd")
	public String orderEnd(RedirectAttributes ratt) {
		// セッションにある注文リストを取得
		@SuppressWarnings("unchecked")
		ArrayList<OrderForm> orderList = (ArrayList<OrderForm>) session.getAttribute("orderList");
		// loginしているユーザの情報取得
		User user = (User) session.getAttribute("user");
		// login判別
		if (user == null) {
			session.setAttribute("orderFlag", "1");
			// loginしてない場合はlogin画面に遷移
			return "redirect:/login";
		}
		
		// loginしている場合は注文リストをDBに登録
		String orderId = service.insertOrder(orderList, user.getId());
		ratt.addAttribute("orderId", orderId);
		
		// 注文完了メール送信する
		return "redirect:/mail";
	}
	
	@Autowired
	EmailHtmlSender emailHtmlSender;
	
	// メール送信
	@RequestMapping("/mail")
	public String mailsend(@ModelAttribute("mail")Mail mail, @RequestParam("orderId")String orderId) {
		
		//　loginしているユーザの情報取得
		User user = (User) session.getAttribute("user");
		
		// セッションにある注文リストを取得
		@SuppressWarnings("unchecked")
		ArrayList<OrderForm> orderList = (ArrayList<OrderForm>) session.getAttribute("orderList");
		
		Context context = new Context();
		context.setVariable("orderId", orderId);
		context.setVariable("list", orderList);
		
		String mailTo = user.getEmail();
		String mailTitle = "[THP] ご注文ありがとうございます。";
		
		EmailStatus emailStatus = emailHtmlSender.send(mailTo, mailTitle, "mail", context);
		// 注文が完了されたらセッションにあるカートのリストと注文リスト削除
		session.removeAttribute("orderList");
		session.removeAttribute("map");
        return "orderEnd";
	}
	
	@RequestMapping("/purchaseHistory")
	public String purchaseHistory(@ModelAttribute("order")Order order, Model model) {
		User user = (User) session.getAttribute("user");
		String uid = user.getId();
		List<Order> historyList = service.purchaseHistory(uid);
		
		model.addAttribute("historyList", historyList);
		return "history";
	}
	
	@RequestMapping("/historydetail")
	public String historydetail(@ModelAttribute("order")Order order, Model model) {
		
		List<OrderHistory> hdetailList = service.getHistoryDetail(order.getOrderId());
		int sum = 0;
		for (OrderHistory of : hdetailList) {
			sum += Integer.parseInt(of.getTotal());
		}
		model.addAttribute("orderTotalPrice", sum);
		
		model.addAttribute("order", order);
		model.addAttribute("hdetailList", hdetailList);
		
		return "historyDetail";
	}
	
	

}
