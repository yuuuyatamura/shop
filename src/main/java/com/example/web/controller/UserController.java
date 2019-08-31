package com.example.web.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;

import com.example.domain.Mail;
import com.example.domain.User;
import com.example.domain.UserInfo;
import com.example.service.UserService;
import com.example.service.OrderService;
import com.example.web.form.LoginForm;
import com.example.web.form.ModifyForm;
import com.example.web.form.OrderForm;
import com.example.web.form.RegisterForm;

@SuppressWarnings("unused")
@Controller
public class UserController {

	@Autowired
	UserService service;

	@ModelAttribute("loginForm")
	public LoginForm setForm() {
		return new LoginForm();
	}

	@Autowired
	HttpSession session;

	// loginページに遷移
	@RequestMapping("/login")
	public String loginPage(@ModelAttribute("loginForm") LoginForm form, Model model) {
		return "login";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/loginCheck")
	public String loginCheck(@Validated @ModelAttribute("loginForm") LoginForm form, BindingResult result,
			HttpSession session, Model model) {
		// DBからlogin情報取得
		User user = service.loginCheck(form.getUserId());

		// login情報がある場合
		if (user != null) {
			// 入力したパスワードとIDで検索したUser情報のパスワード比較
			if (user.getPasswd().equals(form.getPasswd())) {
				
				// loginに成功したユーザ情報をsessionに保存
				session.setAttribute("user", user);
				// user cart 生成
				HashMap<String, Integer> map = new HashMap<>();
				if (session.getAttribute("map") != null) {
					map = (HashMap<String, Integer>) session.getAttribute("map");
				}
				session.setAttribute("map", map);
			} else {
				// パスワードが間違っている場合error追加
				result.reject("error.user.login.pw.wrong");
			}
		} else {
			// IDで検索した結果がない場合error追加
			result.reject("error.user.login.no.account");
		}
		// errorがある場合はlogin画面に戻す
		if (result.hasErrors()) {
			return "login";
		}
		String flag = (String) session.getAttribute("orderFlag");
		// 注文を申請中だったら戻る
		if (flag!=null && flag.equals("1")) {
			return "redirect:/order";
		}
		if (form.getUserId().equals("admin")) {
			// model.addAttribute("userId", user);
			return "redirect:adminOrder";
		}
		// index画面に遷移
		return "redirect:/index";
	}

	// registerページに遷移
	@RequestMapping("/register")
	public String registerPage(@ModelAttribute("registerForm") RegisterForm form) {
		return "register";
	}

	// registerページに遷移
	@RequestMapping("/registerCheck")
	public String registerCheck(@Validated @ModelAttribute("registerForm") RegisterForm form, BindingResult result,
			@RequestParam("profile") MultipartFile file) {

		// IDが入力された場合
		if (result.getFieldError("userId") == null) {
			// IDの重複チェック
			Boolean idcheck = service.idCheck(form.getUserId());
			// 重複エラー
			if (!idcheck) {
				result.rejectValue("userId", "error.user.register.id.exist");
			}
		}
		// パスワードが入力された場合
		if (result.getFieldError("passwd") == null) {
			// passwordエラー処理
			if (form.getPasswd2() == null || !form.getPasswd().equals(form.getPasswd2())) {
				result.rejectValue("passwd2", "error.user.register.pw.unmatch");
			}
		}
		// errorがある場合はlogin画面に戻す
		if (result.hasErrors()) {
			return "register";
		}
		// DBにユーザ登録
		service.insertUser(form);
		// プロジェクトの内のupload folderのパース
		String uploadfolder = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\user";
		// アップロードされたファイルがない場合
		if (file.isEmpty()) {
			System.out.println("file is empty");
			return "redirect:/userModify";
		}
		try {
			// fileを保存する経路
			Path path = Paths.get(uploadfolder + File.separator + form.getUserId()+".jpg");
			// 経路にフォルダがない場合新しいフォルダを生成
			Files.createDirectories(path.getParent());
			// fileコピー＝生成
			Files.copy(file.getInputStream(), path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// login画面に遷移
		return "redirect:/login?userId=" + form.getUserId();

	}

	// myPageに遷移
	@RequestMapping("/myPage")
	public String myPage() {
		return "myPage";
	}

	// logout
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}

	// ユーザ情報変更ページに遷移
	@RequestMapping("/userModify")
	public String userModify(@ModelAttribute("modifyForm") ModifyForm form, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		UserInfo user2 = new UserInfo();
		BeanUtils.copyProperties(user, form);
		user2.setNickName(user.getNickName());
		model.addAttribute("nickName", user2);
		model.addAttribute("modifyForm", form);
		return "userModify";
	}

	// ユーザ情報変更（Modifyボタン押下）
	@RequestMapping("/userModifying")
	public String userModifying(@Validated @ModelAttribute("modifyForm") ModifyForm form,
			@RequestParam("pic") MultipartFile file, BindingResult result, Model model, HttpSession session) {

		if (result.hasErrors()) {
			model.addAttribute("modifyForm", form);
			System.out.println("エラー");
			return "userModify";
		} else {
			User login = (User) session.getAttribute("user");
			String userId = login.getUserId();

			// データ登録に利用するドメインクラスのインスタンス化
			UserInfo userInfo = new UserInfo();

			// Formクラスの値をドメインクラスにコピー
			BeanUtils.copyProperties(form, userInfo);
			userInfo.setUserId(userId);

			// データ登録を行うためのサービス処理呼び出し
			service.insertUserInfo(userInfo);

			User user = (User) session.getAttribute("user");

			String uploadfolder = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\user";

			// アップロードされたファイルがない場合
			if (file.isEmpty()) {
				System.out.println("file is empty");
				return "redirect:/userModify";
			}

			try {
				// fileを保存する経路
				Path path = Paths.get(uploadfolder + File.separator + user.getUserId()+".jpg");
				// 経路にフォルダがない場合新しいフォルダを生成
				Files.createDirectories(path.getParent());
				// fileコピー＝生成
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			} catch (Exception e) {
				e.printStackTrace();
			}
			user = service.loginCheck(user.getUserId());
			session.setAttribute("user", user);
			
			// 完了画面へのリダイレクト
			return "redirect:/index";
		}

	}
}
