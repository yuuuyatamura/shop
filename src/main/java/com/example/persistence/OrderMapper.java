package com.example.persistence;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.domain.Order;
import com.example.domain.OrderHistory;
import com.example.domain.Product;
import com.example.web.form.OrderForm;

public interface OrderMapper {

	public List<Product> getPnames(String[] keys);
		
	public void insertOrder(Order order);
	
	public void insertOrderDetail(@Param("list") ArrayList<OrderForm> list);
	
	public String getOrderId(@Param("uid")String uid, @Param("thedate")String thedate);
	
	public List<Order> purchaseHistory(@Param("uid")String uid);
	
	public List<OrderHistory> getHistoryDetail(String id);
	
}
