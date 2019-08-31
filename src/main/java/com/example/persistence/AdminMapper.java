package com.example.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.domain.OrderDetail;
import com.example.domain.Product;
import com.example.domain.User;
import com.example.web.form.AdminForm;
import com.example.web.form.ProductRegisterForm;
import com.example.web.form.SearchUserForm;

public interface AdminMapper {

	// Main画面　リスト
	public List<Product> productList();

	//Search
	public List<Product> search(AdminForm form);
	
	//Search
	public List<Product> searchsort(AdminForm form);
	
	// Main　削除
	public void deleteProduct(String id);

	//productDetail List
	public List<OrderDetail> productDetail(String id);

	//User情報画面　order List
	public List<OrderDetail> userDetail();
	
	//User情報画面　order List
	public List<OrderDetail> userSummary();
	
	//All　order List
	public List<OrderDetail> latestOrderD();
	
	//All　order List
	public List<OrderDetail> latestOrderS();
	//All　order List
	public List<OrderDetail> latestOrderSF(SearchUserForm form);
	
	//All　order List
	public void ModifyOrderShip(@Param("deliveryStatus")Integer deliveryStatus, @Param("orderId")Integer orderId);
	
	//----------------------------------------------------------------------------------------------------
	
	// Product　登録
	public void registerProduct(ProductRegisterForm form);
	
	// Modify　修正画面 ProductInfo
	public Product readProductInfo(String id);
	
	// Modify　修正完了
	public void ModifyProduct(ProductRegisterForm form);

	
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	
	//UserSearch画面　List All
	public List<User> userList();
	
	//UserSearch画面　SearchList
	public List<User> userSearch(SearchUserForm form);

	//UserSearch画面　List All Sort
	public List<User> userSearchSort(SearchUserForm form);
	
	//User情報画面
	public User readUserInfo(String id);
	
	//User情報画面　order List
	public List<OrderDetail> userDetail(String id);
	
	//User情報画面　order List
	public List<OrderDetail> userSummary(String id);
	
	
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
		
		public int countOrder0();
		//Chart UserCount
		public int countOrder1();
		//Chart UserCount
		public int countOrder2();

		
		
}
