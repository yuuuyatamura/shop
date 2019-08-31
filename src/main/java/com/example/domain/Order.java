package com.example.domain;

public class Order {
	private String uid;
	private String orderId;
	private int totalPrice;
	private String orderDate;
	private String deliveryStatus;
	
	public Order() {
	}

	public Order(String uid, int totalPrice, String orderDate, String deliveryStatus) {
		super();
		this.uid = uid;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.deliveryStatus = deliveryStatus;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	@Override
	public String toString() {
		return "Order [uid=" + uid + ", orderId=" + orderId + ", totalPrice=" + totalPrice + ", orderDate=" + orderDate
				+ ", deliveryStatus=" + deliveryStatus + "]";
	}

}
