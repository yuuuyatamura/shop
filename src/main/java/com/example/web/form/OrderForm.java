package com.example.web.form;

public class OrderForm {

	private String pid;
	private String pname;
	private String image;
	private String num;
	private int	price;
	private int total;
	
	public OrderForm() {
	}

	public OrderForm(String pid, String pname, String image, String num, int price, int total) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.image = image;
		this.num = num;
		this.price = price;
		this.total = total;
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

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "OrderForm [pid=" + pid + ", pname=" + pname + ", image=" + image + ", num=" + num + ", price=" + price
				+ ", total=" + total + "]";
	}


	
}
