package com.example.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Comment;
import com.example.domain.Page;
import com.example.domain.Product;
import com.example.domain.User;
import com.example.service.ProductService;
import com.example.web.form.CommentForm;
import com.example.web.form.SearchForm;

@SuppressWarnings("unused")
@Controller
public class ProductController {

	@Autowired
	ProductService service;
	
	@RequestMapping("/list")
	public String listPage(@ModelAttribute("option")SearchForm option, Model model, @ModelAttribute("page")Page page, HttpSession session) {
		if (page.getPageNo() == 0) {
			page.setPageNo(1);
		}
		int pageNo = page.getPageNo();
		page = service.paging(option, page);
		
		// DBから検索条件に該当するproductのlist取得
		List<Product> list = service.search(option, page);
		page.setTotalCount(list.size());
		page.setPageNo(page.getPageNo()/page.getPageSize());
		if (page.getPageNo()==0) {
			page.setPageNo(1);
		}else {
			page.setPageNo(pageNo);
		}
		if (list == null) {
			list = new ArrayList<>();
		}
		session.setAttribute("option", option);
		session.setAttribute("list", list);
		
		// HTMLページへ伝達
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "list";
	}

	@RequestMapping(value="/detail", params="id")
	public String detailPage(@Param("id")int id, Model model, ModelMap modelmap, HttpSession session,
			@ModelAttribute("comment")CommentForm form, @ModelAttribute("cm")Comment comment) {
		
		// 渡されたidを利用してDBから
		Product product = service.productDetail(id+"");
		// HTMLページへ伝達
		model.addAttribute("product", product);
		
		List<Comment> clist = service.getCommentList(id+"");
		model.addAttribute("clist", clist);
		
		String cmflag = service.commentCheck(session, clist, id+"");
		model.addAttribute("cmflag", cmflag);
		
		String mflag = (String) modelmap.getOrDefault("mflag", "false");
		model.addAttribute("mflag", mflag);
		
		return "productDetail";
	}

	@RequestMapping("/ajaxComment")
	public @ResponseBody List<Comment> ajaxComment(@RequestParam("pid") String id) {
		List<Comment> clist = service.getCommentList(id+"");
		return clist;
	}

	@RequestMapping("/ajaxList")
	public @ResponseBody List<Product> ajaxList(HttpSession session) {
		@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>) session.getAttribute("list");
		return list;
	}

	@RequestMapping("/ajaxFirstList")
	public @ResponseBody List<Product> ajaxFirstList(HttpSession session) {
		@SuppressWarnings("unchecked")
		List<Product> firstList = (List<Product>) session.getAttribute("firstList");
		return firstList;
	}
	
	@RequestMapping("/insertComment")
	public String insertComment(@ModelAttribute("comment")CommentForm form) {
		System.out.println("insert comment "+form.toString());
		service.insertComment(form);
		
		return "redirect:/detail?id="+form.getProductId();
	}
	@RequestMapping("/modifyComment")
	public String modifyComment(@ModelAttribute("comment")CommentForm form, @Param("cid")String cid, RedirectAttributes ratt) {
		ratt.addFlashAttribute("mflag", cid);
		return "redirect:/detail?id="+form.getProductId();
	}
	@RequestMapping("/modifyCommentdo")
	public String modifyCommentdo(@ModelAttribute("comment")Comment form, @Param("cid")String cid) {
		service.modifyComment(form);
		return "redirect:/detail?id="+form.getProductId();
	}

	@RequestMapping("/deleteComment")
	public String deleteComment(@ModelAttribute("comment")CommentForm form, @Param("cid")String cid) {
		service.deleteComment(cid);
		return "redirect:/detail?id="+form.getProductId();
	}
	
	
}
