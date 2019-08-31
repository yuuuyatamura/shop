package com.example.domain;

public class OrderHistory {

	private String orderId;
	private String pid;
	private String pname;
	private String image;
	private String price;
	private String num;
	private String total;
	private String inpDate;
	
	public OrderHistory() {
	}

	public OrderHistory(String orderId, String pid, String pname, String image, String price, String num, String total,
			String inpDate) {
		super();
		this.orderId = orderId;
		this.pid = pid;
		this.pname = pname;
		this.image = image;
		this.price = price;
		this.num = num;
		this.total = total;
		this.inpDate = inpDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getInpDate() {
		return inpDate;
	}

	public void setInpDate(String inpDate) {
		this.inpDate = inpDate;
	}

	@Override
	public String toString() {
		return "OrderHistory [orderId=" + orderId + ", pid=" + pid + ", pname=" + pname + ", image=" + image
				+ ", price=" + price + ", num=" + num + ", total=" + total + ", inpDate=" + inpDate + "]";
	}

	
}
