package com.example.domain;

public class Product {

	private String id;
	private String categoryId;
	private String category;
	private String name;
	private String image;
	private int price;
	private int stockQuantity;
	private String details;
	private int deleteFlag;
	private double average;
	private String inpDate;

	public Product() {
	}

	public Product(String id, String categoryId, String category, String name, String image, int price,
			int stockQuantity, String details, int deleteFlag, double average, String inpDate) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.category = category;
		this.name = name;
		this.image = image;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.details = details;
		this.deleteFlag = deleteFlag;
		this.average = average;
		this.inpDate = inpDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public String getInpDate() {
		return inpDate;
	}

	public void setInpDate(String inpDate) {
		this.inpDate = inpDate;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", categoryId=" + categoryId + ", category=" + category + ", name=" + name
				+ ", image=" + image + ", price=" + price + ", stockQuantity=" + stockQuantity + ", details=" + details
				+ ", deleteFlag=" + deleteFlag + ", average=" + average + ", inpDate=" + inpDate + "]";
	}



}
