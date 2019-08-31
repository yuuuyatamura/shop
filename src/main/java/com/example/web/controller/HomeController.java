package com.example.web.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Product;
import com.example.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	ProductService service;
	
	@RequestMapping({"/index", "/main", "/menu"})
	public String indexPage(Model model, HttpSession session) {
		List<Product> firstList = service.getFirstList();
		model.addAttribute("firstList", firstList);
		session.setAttribute("firstList", firstList);
		// 初めてdbにデータを入力する時
//		service.firstdbsetting();
		return "index";
	}
	
	@RequestMapping("/file")
	public String filePage() {
		return "file-upload";
	}

	@RequestMapping("/fileupload")
	public String fileupload(@RequestParam("profile")MultipartFile file) {
		// 一時的に保存したい場合のパース
//		String absolutePath = context.getRealPath("resources/static/uploaded");

		// プロジェクトの内のupload　folderのパース
		String uploadfolder = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\uploaded";
		
		// アップロードされたファイルがない場合
		if (file.isEmpty()) {
			System.out.println("file is empty");
			return "redirect:/file";
        }

        try {
        	// fileを保存する経路
            Path path = Paths.get(uploadfolder+ File.separator + file.getOriginalFilename());
            // 経路にフォルダがない場合新しいフォルダを生成
            Files.createDirectories(path.getParent());
            // fileコピー＝生成
            Files.copy(file.getInputStream(), path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		return "file-upload";
	}
	
	
	 
}
