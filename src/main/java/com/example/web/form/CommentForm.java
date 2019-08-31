package com.example.web.form;

public class CommentForm {

	private String productId;
	private String userId;
	private String orderDetailId;
	private String score;
	private String comment;
	
	public CommentForm() {
	}

	public CommentForm(String productId, String userId, String orderDetailId, String score, String comment) {
		super();
		this.productId = productId;
		this.userId = userId;
		this.orderDetailId = orderDetailId;
		this.score = score;
		this.comment = comment;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "CommentForm [productId=" + productId + ", userId=" + userId + ", orderDetailId=" + orderDetailId
				+ ", score=" + score + ", comment=" + comment + "]";
	}
	
}
