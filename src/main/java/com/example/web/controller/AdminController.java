package com.example.web.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.OrderDetail;
import com.example.domain.Product;
import com.example.domain.User;
import com.example.service.AdminService;
import com.example.service.mail.EmailSender;
import com.example.web.form.AdminForm;
import com.example.web.form.MailForm;
import com.example.web.form.ProductRegisterForm;
import com.example.web.form.SearchUserForm;

@Controller
@SessionAttributes({ "adForm", "pdForm", "suForm", "MForm" })
public class AdminController {

	@Autowired
	AdminService service;

	@ModelAttribute("adForm")
	public AdminForm setForm1() {
		return new AdminForm();
	}

	@ModelAttribute("pdForm")
	public ProductRegisterForm setForm2() {
		return new ProductRegisterForm();
	}

	@ModelAttribute("suForm")
	public SearchUserForm setForm3() {
		return new SearchUserForm();
	}

	@ModelAttribute("MForm")
	public MailForm setForm4() {
		return new MailForm();
	}

	// Today
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
	Date d = new Date();
	String today = format.format(d);

	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// -----------------------------------Main
	// Product検索画面-----------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// Main画面
	@RequestMapping("/admin")
	public String AdminPage(@ModelAttribute("adForm") AdminForm form, Model model) {
		// remove form
		AdminForm adForm = new AdminForm();
		model.addAttribute("adForm", adForm);
		// remove form
		ProductRegisterForm pdForm = new ProductRegisterForm();
		model.addAttribute("pdForm", pdForm);
		// remove form
		SearchUserForm suForm = new SearchUserForm();
		model.addAttribute("suForm", suForm);
		// remove form
		MailForm MForm = new MailForm();
		model.addAttribute("MForm", MForm);

		// read All list
		List<Product> productList = service.productList();
		model.addAttribute("productList", productList);

		return "admin";
	}

	@RequestMapping("/adminSearch")
	public String AdminPage2(@ModelAttribute("adForm") AdminForm form, Model model, @Param("btn") String btn) {
		if (form.getCategoryId() == null && form.getDeleteFlag() == null && form.getDetails() == null
				&& form.getId() == null && form.getName() == null && form.getSearchWord() == null
				&& form.getSelectCategory() == null) {
			if (btn != null) {
				form.setSortbtn(btn);
				List<Product> productList = service.searchsort(form);
				model.addAttribute("productList", productList);
				return "admin";
			} else {
				List<Product> productList = service.productList();
				model.addAttribute("productList", productList);
				return "admin";
			}

		} else {
			form.setSortbtn(btn);
			List<Product> productList = service.search(form);
			model.addAttribute("productList", productList);
			return "admin";
		}
	}

	// ----------------------------------------------------------------------------------------------------
	// Main 削除
	@RequestMapping(value = "/adminDelete")
	public String Admin2(@Param("id") String id, @Param("btn") String btn) {
		service.deleteProduct(id);
		return "redirect:/adminSearch?btn="+btn;
	}

	// ----------------------------------------------------------------------------------------------------
	// Main 修正画面に行く
	@RequestMapping("/adminModify")
	public String AdminModify(@ModelAttribute("pdForm") ProductRegisterForm form, Model model, @Param("id") String id, @Param("btn") String btn) {
		Product productInfo = service.readProductInfo(id);
		model.addAttribute("productInfo", productInfo);
		return "adminModify";
	}
	// 修正
	@RequestMapping("/adminModify2")
	public String AdminModify2(@ModelAttribute("pdForm") ProductRegisterForm form, Model model,
			@RequestParam("image2") MultipartFile file, @Param("sortbtn") String sortbtn) {

		//既存のファイル名　todayl.jpg --> today1  /  jpg
//		String fileName = file.getOriginalFilename();
//		String[] image=fileName.split("\\.");
//		String imageName=image[0];
//		String imageExtension=image[1];
//		// Set FileName 
//		String FileName=today+"_"+form.getName()+"."+imageExtension;
		String FileName2=today+"_"+form.getName();
		// 写真登録
		String uploadfolder = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img";
		
		
		if(!file.isEmpty()) {
		try {
			// fileを保存する経路
			Path path = Paths.get(uploadfolder + File.separator + FileName2+".jpg");
			// 経路にフォルダがない場合新しいフォルダを生成
			Files.createDirectories(path.getParent());
				// fileコピー＝生成
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			// 修正実行
			service.ModifyProduct(form, FileName2);
			//service.ModifyProduct(form,FileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		else{// 修正実行
		service.ModifyProduct(form);
		//service.ModifyProduct(form,FileName);
		}
		// remove form
		ProductRegisterForm pdForm = new ProductRegisterForm();
		model.addAttribute("pdForm", pdForm);

		return "redirect:/adminSearch?btn="+sortbtn;
	}
	
	// ORDER 画面に行く
	@RequestMapping("/adminOrder")
	public String AdminOrder(@ModelAttribute("suForm") SearchUserForm form, Model model) {
		//remove form
		SearchUserForm suForm = new SearchUserForm();
		model.addAttribute("suForm", suForm);
		//read order table & shippment info
		int countOrder0 = service.countOrder0();
		model.addAttribute("countOrder0",countOrder0);
		int countOrder1 = service.countOrder1();
		model.addAttribute("countOrder1",countOrder1);
		int countOrder2 = service.countOrder2();
		model.addAttribute("countOrder2",countOrder2);
		List<OrderDetail> latestOrderD=service.latestOrderD();
		model.addAttribute("latestOrderD", latestOrderD);
		List<OrderDetail> latestOrderS=service.latesetOrderS();
		model.addAttribute("latestOrderS", latestOrderS);
		return "adminOrder";
	}
	
	// ORDER 画面に行く
	@RequestMapping("/adminOrder2")
	public String AdminOrder2(@ModelAttribute("suForm") SearchUserForm form, Model model,@Param("deliveryStatus") Integer deliveryStatus, @Param("orderId") Integer orderId,@Param("deliverySort") Integer deliverySort) {
		service.ModifyOrderShip(deliveryStatus,orderId);
		if(deliverySort!=null) {
			return "redirect:adminOrder3?deliveryStatus="+deliverySort;
		}
		else {
			form.setDeliveryStatus(null);
			return "redirect:adminOrder3";
		}
	}
	
	
	@RequestMapping("/adminOrder3")
	public String AdminOrder3(@ModelAttribute("suForm") SearchUserForm form, Model model,@Param("deliveryStatus") Integer deliveryStatus,@Param("selectCategory") String selectCategory,@Param("searchWord") String searchWord) {
		
		//read order table & shippment info
		int countOrder0 = service.countOrder0();
		model.addAttribute("countOrder0",countOrder0);
		int countOrder1 = service.countOrder1();
		model.addAttribute("countOrder1",countOrder1);
		int countOrder2 = service.countOrder2();
		model.addAttribute("countOrder2",countOrder2);
		List<OrderDetail> latestOrderD=service.latestOrderD();
		model.addAttribute("latestOrderD", latestOrderD);
		List<OrderDetail> latestOrderSF=service.latesetOrderSF(form);
		model.addAttribute("latestOrderS", latestOrderSF);
		model.addAttribute("deliverySort",deliveryStatus);
		
		return "adminOrder";
	}



	// Name Click ---> Product Detail 画面
	@RequestMapping("/adminProductDetail")
	public String adminProductDetail1(@ModelAttribute("pdForm") ProductRegisterForm form, Model model,
			@Param("id") String id) {

		// read product Info
		Product productInfo = service.readProductInfo(id);
		model.addAttribute("productInfo", productInfo);

		// read product Info order detail
		List<OrderDetail> productDetail = service.productDetail(id);
		model.addAttribute("productDetail", productDetail);

		return "adminProductDetail";
	}

	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// -----------------------------------Regist Product商品登録画面-----------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// Regist Product Screen
	@RequestMapping("/adminRegister")
	public String adminRegister1(@ModelAttribute("pdForm") ProductRegisterForm form, Model model) {
		// remove form
		ProductRegisterForm pdForm = new ProductRegisterForm();
		model.addAttribute("pdForm", pdForm);
		return "adminRegister";
	}

	// Regist Product Action
	@RequestMapping("/adminRegister2")
	public String adminRegister2(@Validated @ModelAttribute("pdForm") ProductRegisterForm form, BindingResult result,
			Model model, @RequestParam("image2") MultipartFile file) {

		//既存のファイル名　todayl.jpg --> today1  /  jpg
		String fileName = file.getOriginalFilename();
		String[] image=fileName.split("\\.");
		@SuppressWarnings("unused")
		String imageName=image[0];
		String imageExtension=image[1];
		// Set FileName 
		@SuppressWarnings("unused")
		String FileName=today+"_"+form.getName()+"."+imageExtension;
		String FileName2=today+"_"+form.getName();
		// Regist Image
		// upload file path
		String uploadfolder = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img";
		try {
			// fileを保存する経路
			//Path path = Paths.get(uploadfolder + File.separator + FileName);
			Path path = Paths.get(uploadfolder + File.separator + FileName2 +".jpg");
	//以前のコード  --> Path path = Paths.get(uploadfolder + File.separator + today + "_" + form.getName() + ".jpg");
			// 経路にフォルダがない場合新しいフォルダを生成
			Files.createDirectories(path.getParent());
			// fileコピー＝生成
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Regist Info Action
		//service.registerProduct(form,FileName);
		service.registerProduct(form, FileName2);

		// remove form
		ProductRegisterForm pdForm = new ProductRegisterForm();
		model.addAttribute("pdForm", pdForm);
		return "redirect:adminRegister";
	}

	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// ------------------------------------User検索画面------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// User List 画面
	@RequestMapping("/adminUser")
	public String adminUser1(@ModelAttribute("suForm") SearchUserForm form, Model model) {
		// remove form
		SearchUserForm suForm = new SearchUserForm();
		model.addAttribute("suForm", suForm);
		// read All User list
		List<User> userList = service.userList();
		model.addAttribute("userList", userList);
		return "adminUserSearch";
	}

	// User List 画面 Search
	@RequestMapping("/adminUserSearch")
	public String adminUserSearch1(@ModelAttribute("suForm") SearchUserForm form, Model model,
			@Param("btn") String btn) {

		if (form.getSearchWord() == null && form.getSelectCategory() == null) {
			if (btn != null) {
				form.setSortbtn(btn);
				List<User> userList = service.userSearchSort(form);
				model.addAttribute("userList", userList);
				return "adminUserSearch";
			} else {
				form.setSortbtn(btn);
				List<User> userList = service.userList();
				model.addAttribute("userList", userList);
				return "adminUserSearch";
			}
		} else {
			form.setSortbtn(btn);
			List<User> userList = service.userSearch(form);
			model.addAttribute("userList", userList);
			return "adminUserSearch";
		}
	}

	// User Detail 画面
	@RequestMapping("/adminUserDetail")
	public String adminUserDetail1(@ModelAttribute("suForm") SearchUserForm form, Model model, @Param("id") String id) {
		User userInfo = service.readUserInfo(id);
		model.addAttribute("userInfo", userInfo);
		List<OrderDetail> userDetail = service.userDetail(id);
		model.addAttribute("userDetail", userDetail);
		List<OrderDetail> userSummary = service.userSummary(id);
		model.addAttribute("userSummary", userSummary);
		return "adminUserDetail";
	}

	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------Mail
	// 画面----------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// Mail Form 画面
	@RequestMapping("/adminMail")
	public String adminMail1(@ModelAttribute("MForm") MailForm form, Model model, @Param("mail") String mail) {
		// remove form
		MailForm MForm = new MailForm();
		model.addAttribute("MForm", MForm);
		// Admin Mail Set
		MForm.setTo(mail);
		return "adminMail";
	}

	@Autowired
	EmailSender emailSender;

	// Mail Form 画面 Action
	@RequestMapping("/adminMail2")
	public String adminMail2(@ModelAttribute("MForm") MailForm form, Model model) {
		// Mail Action
		emailSender.sendPlainText(form.getTo(), form.getSubject(), form.getContent());
		// remove form
		MailForm MForm = new MailForm();
		model.addAttribute("MForm", MForm);
		return "redirect:adminMail";
	}

	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// -----------------------------------Chart画面---------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// User画面 List All
	// @RequestMapping("/adminChart")
	// public String adminChart1(@ModelAttribute("adForm") AdminForm form, Model
	// model) {
	// // session remove
	// AdminForm adForm = new AdminForm();
	// model.addAttribute("adForm", adForm);
	// int countProduct = service.chartProductCount();
	// model.addAttribute("countProduct", countProduct);
	// int countUser = service.chartUserCount();
	// model.addAttribute("countUser", countUser);
	// return "adminChart";
	// }
	//
	// @RequestMapping("/adminChartSearch")
	// public String adminChart2(@ModelAttribute("adForm") AdminForm form, Model
	// model) {
	// return "adminChart";
	// }

}
