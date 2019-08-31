package com.example.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Comment;
import com.example.domain.Page;
import com.example.domain.Product;
import com.example.domain.User;
import com.example.persistence.ProductMapper;
import com.example.web.form.CommentForm;
import com.example.web.form.SearchForm;


@SuppressWarnings("unused")
@Service
public class ProductService {

	@Autowired
	ProductMapper mapper;
	
	// 最初の画面に表示させる最新商品のリスト取得
	public List<Product> getFirstList(){
		List<Product> firstList = mapper.getFirstList(); 
		return firstList;
	}
	
	public void firstdbsetting() {
		FirstDBSetting first = new FirstDBSetting();
		first.firstInsert(mapper);
	}
	
	//　選択された商品のIDから詳細情報を取得
	public Product productDetail(String id) {
		Product p = mapper.getProduct(id);
		return p;
	}
	
	// 条件検索を行い
	public List<Product> search(SearchForm option, Page page){
		
		// 検索条件の基本値を設定
		option = checkDefault(option);
		
		// 設定した条件からDB検索
		List<Product> list = mapper.getList(option, page);

		// 検索結果返還
		return list;
	}
	
	public Page paging(SearchForm option, Page page) {
		// 検索条件の基本値を設定
		option = checkDefault(option);
		String tc = mapper.getTotalCount(option);
		// 総商品数
		int totalCount = 0;
		if (tc != null) {
			totalCount = Integer.parseInt(tc);
		} else {
			totalCount = 0;
		}
		page.setTotalCount(totalCount);
		// 一ページに表示させる商品数
		int pageSize = page.getPageSize();
		// 総ページ数
		int totalPage = totalCount/pageSize;
		// 最後のページにpagesizeより少ない数の商品がある場合
		if (totalCount%pageSize > 0) {
			totalPage++;
		}
		page.setTotalPage(totalPage);

		// 現在のページ
		int pageNo = (page.getPageNo()*page.getPageSize())/page.getPageSize();
		
		if(pageNo == 1) { 
			page.setPageNo(0);
		} else {
			page.setPageNo((pageNo-1)*page.getPageSize());
		}
		
		int m = pageNo/page.getBtnSize();
		if (pageNo%page.getBtnSize() != 0) {
			m++;
		}
		
		page.setEndPage(page.getBtnSize()*m);
		page.setStartPage(page.getEndPage()-4);
		
		if (page.getEndPage() > page.getTotalPage()) {
			page.setEndPage(page.getTotalPage());
		}
		
		return page;
	}
	
	
	// 検索条件をチェック
	public SearchForm checkDefault(SearchForm option) {
		// 値がない場合の基本値設定
		if (option.getKeyword().equals("")) {
			option.setKeywords(new String[0]);
		} else {
			String[] keys = option.getKeyword().split(" ");
			option.setKeywords(keys);
		}
		if (option.getSort() == null || option.getSort().equals("")) {
			option.setSort("p.name");
		}
		if (option.getSortFormat() == null || option.getSortFormat().equals("")) {
			option.setSortFormat("ASC");
		}
		
		return option;
	}
	
	
	public List<Comment> getCommentList(String pid){
		List<Comment> clist = mapper.getCommentList(pid);
		return clist;
	}
	
	public List<String> getOrderDetailList(String pid){
		List<String> userOrder = mapper.getOrderDetailList(pid);
		return userOrder;
	}
	
	public String commentCheck(HttpSession session, List<Comment> clist, String pid) {
		
		User user = (User) session.getAttribute("user");
		String cmflag = null;
		if (user == null) { cmflag = "false";
		} else {
			for (Comment cm : clist) {
				if (cm.getUserId().equals(user.getId())) { cmflag="false"; }
			}
			List<String> orderdetail = mapper.getOrderDetailList(pid);
			if (cmflag == null) {
				for (String od : orderdetail) {
					if (user.getId().equals(od)) { cmflag = "true"; }
				}
			}
			if (cmflag == null) { cmflag = "false"; }
		}
		return cmflag;
	}
	
	@Transactional
	public void insertComment(CommentForm comment) {
		mapper.insertComment(comment);
	}
	
	@Transactional
	public void deleteComment(String cid) {
		mapper.deleteComment(cid);
	}
	
	public void modifyComment(Comment comment) {
		mapper.modifyComment(comment);
	}
	
	
}
