package com.example.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.OrderDetail;
import com.example.domain.Product;
import com.example.domain.User;
import com.example.persistence.AdminMapper;
import com.example.web.form.AdminForm;
import com.example.web.form.ProductRegisterForm;
import com.example.web.form.SearchUserForm;

@SuppressWarnings("unused")
@Service
public class AdminService {

	@Autowired
	AdminMapper mapper;

	// Main画面 リスト
	public List<Product> productList() {
		List<Product> productList = mapper.productList();
		return productList;
	}

	// Search
	public List<Product> search(AdminForm form) {
		List<Product> productList = mapper.search(form);
		return productList;
	}

	// Searchsort
	public List<Product> searchsort(AdminForm form) {
		List<Product> productList = mapper.searchsort(form);
		return productList;
	}

	// Main 削除
	public void deleteProduct(String id) {
		mapper.deleteProduct(id);
	}

	// Product Detail
	public List<OrderDetail> productDetail(String id) {
		List<OrderDetail> productDetail = mapper.productDetail(id);
		return productDetail;
	}
	
	// Order Detail
	public List<OrderDetail> latestOrderD() {
		List<OrderDetail> latestOrderD = mapper.latestOrderD();
		return latestOrderD;
	}
	public List<OrderDetail> latesetOrderS() {
		List<OrderDetail> latesetOrderS = mapper.latestOrderS();
		return latesetOrderS;
	}
	public List<OrderDetail> latesetOrderSF(SearchUserForm form) {
		List<OrderDetail> latesetOrderSF = mapper.latestOrderSF(form);
		return latesetOrderSF;
	}
	public void ModifyOrderShip(int deliveryStatus,int orderId) {
		mapper.ModifyOrderShip(deliveryStatus,orderId);
	}

	// ----------------------------------------------------------------------------------------------------
	// Product 登録
	//public void registerProduct(ProductRegisterForm form, String FileName) {
	public void registerProduct(ProductRegisterForm form, String FileName2) {

		// image name 設定 & Product 登録
		//form.setImage(FileName);
		form.setImage(FileName2);
		mapper.registerProduct(form);
	}

	// Modify 修正画面 ProductInfo
	public Product readProductInfo(String id) {
		Product productInfo = mapper.readProductInfo(id);
		return productInfo;
	}

	// Modify 修正完了
	//public void ModifyProduct(ProductRegisterForm form, String FileName) {
	public void ModifyProduct(ProductRegisterForm form, String FileName2) {
		//form.setImage(FileName);
		form.setImage(FileName2);
		mapper.ModifyProduct(form);
	}
	public void ModifyProduct(ProductRegisterForm form) {
		//form.setImage(FileName);
		mapper.ModifyProduct(form);
	}

	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------

	// UserSearch画面 List All
	public List<User> userList() {
		List<User> userList = mapper.userList();
		return userList;
	}

	// UserSearch画面 SearchList
	public List<User> userSearch(SearchUserForm form) {
		List<User> userList = mapper.userSearch(form);
		return userList;
	}

	// UserSearch画面 List All Sort
	public List<User> userSearchSort(SearchUserForm form) {
		List<User> userList = mapper.userSearchSort(form);
		return userList;
	}

	// User情報画面
	public User readUserInfo(String id) {
		User userInfo = mapper.readUserInfo(id);
		return userInfo;
	}

	// User情報画面 order List
	public List<OrderDetail> userDetail(String id) {
		List<OrderDetail> userDetail = mapper.userDetail(id);
		return userDetail;
	}

	// User情報画面 order List
	public List<OrderDetail> userSummary(String id) {
		List<OrderDetail> userSummary = mapper.userSummary(id);
		return userSummary;
	}

	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------

	// chart
	public int countOrder0() {
		int countOrder0 = mapper.countOrder0();
		return countOrder0;
	}
	public int countOrder1() {
		int countOrder1 = mapper.countOrder1();
		return countOrder1;
	}
	public int countOrder2() {
		int countOrder2 = mapper.countOrder2();
		return countOrder2;
	}

}
