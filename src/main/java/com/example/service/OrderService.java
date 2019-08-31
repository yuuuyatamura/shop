package com.example.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderHistory;
import com.example.domain.Product;
import com.example.persistence.OrderMapper;
import com.example.web.form.OrderForm;

@SuppressWarnings("unused")
@Service
public class OrderService {

	@Autowired
	OrderMapper mapper;
	
	// cartに入れている商品のidのマップを利用して商品リストを取得
	public ArrayList<OrderForm> getPnames(HashMap<String, Integer> map, ArrayList<OrderForm> olist){
		
		// productのidの配列
		String[] keys = map.keySet().toArray(new String[map.size()]);
		
		// DBから商品のリスト取得
		List<Product> list = mapper.getPnames(keys);
		
		for (String key : map.keySet()) {
			int num = map.get(key);
			
			for (Product p : list) {
				if (p.getId().equals(key)) {
					
				int price = p.getPrice();
				int total = price*num;
				
				OrderForm order = new OrderForm(key, p.getName(), p.getImage(), num+"", p.getPrice(), total);
				olist.add(order);
				}
			}
		}
		return olist;
	}
	
	@Transactional
	public String insertOrder(ArrayList<OrderForm> list, String uid) {
		// 総金額
		int totalprice = 0;
		for (OrderForm o : list) {
			totalprice += o.getTotal();
		}
		// 注文日
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String thedate = simple.format(new Date());
		// 新しいorderオブジェクト生成
		Order order = new Order(uid, totalprice, thedate, "1");
		
		// order生成
		mapper.insertOrder(order);
		// order detail　生成
		mapper.insertOrderDetail(list);
		
		String orderId = mapper.getOrderId(uid, thedate);
		
		return orderId;
	}
	
	public List<Order> purchaseHistory(String uid){
		List<Order> list = mapper.purchaseHistory(uid);
		for (Order order : list) {
			if (order.getDeliveryStatus().equals("1")) {
				order.setDeliveryStatus("配送準備中");
			}
			if (order.getDeliveryStatus().equals("2")) {
				order.setDeliveryStatus("配送中");
			}
			if (order.getDeliveryStatus().equals("0")) {
				order.setDeliveryStatus("配送完了");
			}
		}
		return list;
	}

	public List<OrderHistory> getHistoryDetail(String id){
		List<OrderHistory> historyList = mapper.getHistoryDetail(id);
		return historyList;
	}
	
}
