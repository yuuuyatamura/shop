package com.example.domain;

public class Comment {

	private String id;
	private String productId;
	private String userId;
	private String nickName;
	private String orderDetailId;
	private String score;
	private String comment;
	private String inpDate;
	
	public Comment() {
	}

	public Comment(String id, String productId, String userId, String nickName, String orderDetailId, String score,
			String comment, String inpDate) {
		super();
		this.id = id;
		this.productId = productId;
		this.userId = userId;
		this.nickName = nickName;
		this.orderDetailId = orderDetailId;
		this.score = score;
		this.comment = comment;
		this.inpDate = inpDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getInpDate() {
		return inpDate;
	}

	public void setInpDate(String inpDate) {
		this.inpDate = inpDate;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", productId=" + productId + ", userId=" + userId + ", nickName=" + nickName
				+ ", orderDetailId=" + orderDetailId + ", score=" + score + ", comment=" + comment + ", inpDate="
				+ inpDate + "]";
	}

	
}
