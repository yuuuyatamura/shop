package com.example.web.form;

import org.springframework.web.multipart.MultipartFile;

public class ProductRegisterForm {

	private String id;
	private MultipartFile image2;
	private String image;
	private String photo;
	private String categoryId;
	private String name;
	private Integer price;
	private Integer stockQuantity;
	private String details;
	private Integer deleteFlag;

	public MultipartFile getImage2() {
		return image2;
	}

	public String getImage() {
		return image;
	}

	public void setImage2(MultipartFile image2) {
		this.image2 = image2;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public ProductRegisterForm() {
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	@Override
	public String toString() {
		return "ProductRegisterForm [id=" + id + ", image2=" + image2 + ", image=" + image + ", photo=" + photo
				+ ", categoryId=" + categoryId + ", name=" + name + ", price=" + price + ", stockQuantity="
				+ stockQuantity + ", details=" + details + ", deleteFlag=" + deleteFlag + "]";
	}

}
