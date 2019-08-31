package com.example.web.form;

public class SearchUserForm {

	private String searchWord;
	private String selectCategory;

	private String id;
	private String userId;
	private String passwd;
	private String passwd2;
	private String name;
	private String address;
	private String tel;
	private String email;
	private String nickName;
	private String inpDate;
	private String authority;
	private String sortbtn;
	private Integer aa;
	private String orderDate;
	
	private Integer deliveryStatus;
	private Integer deliverySort;
	private Integer orderId;

	public SearchUserForm() {
	}


	public Integer getDeliverySort() {
		return deliverySort;
	}


	public void setDeliverySort(Integer deliverySort) {
		this.deliverySort = deliverySort;
	}


	public SearchUserForm(String searchWord, String selectCategory, String id, String userId, String passwd,
			String passwd2, String name, String address, String tel, String email, String nickName, String inpDate,
			String authority, String sortbtn, Integer aa, String orderDate, Integer deliveryStatus, int orderId) {
		super();
		this.searchWord = searchWord;
		this.selectCategory = selectCategory;
		this.id = id;
		this.userId = userId;
		this.passwd = passwd;
		this.passwd2 = passwd2;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.nickName = nickName;
		this.inpDate = inpDate;
		this.authority = authority;
		this.sortbtn = sortbtn;
		aa=1;
		this.orderDate = orderDate;
		deliveryStatus=null;
		this.orderId = orderId;
	}



	public String getSearchWord() {
		return searchWord;
	}


	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}


	public String getSelectCategory() {
		return selectCategory;
	}


	public void setSelectCategory(String selectCategory) {
		this.selectCategory = selectCategory;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPasswd() {
		return passwd;
	}


	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	public String getPasswd2() {
		return passwd2;
	}


	public void setPasswd2(String passwd2) {
		this.passwd2 = passwd2;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getInpDate() {
		return inpDate;
	}


	public void setInpDate(String inpDate) {
		this.inpDate = inpDate;
	}


	public String getAuthority() {
		return authority;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}


	public String getSortbtn() {
		return sortbtn;
	}


	public void setSortbtn(String sortbtn) {
		this.sortbtn = sortbtn;
	}


	public Integer getAa() {
		return aa;
	}


	public void setAa(Integer aa) {
		this.aa = aa;
	}


	public String getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}


	public Integer getDeliveryStatus() {
		return deliveryStatus;
	}


	public void setDeliveryStatus(Integer deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}


	public Integer getOrderId() {
		return orderId;
	}


	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}


	@Override
	public String toString() {
		return "SearchUserForm [searchWord=" + searchWord + ", selectCategory=" + selectCategory + ", id=" + id
				+ ", userId=" + userId + ", passwd=" + passwd + ", passwd2=" + passwd2 + ", name=" + name + ", address="
				+ address + ", tel=" + tel + ", email=" + email + ", nickName=" + nickName + ", inpDate=" + inpDate
				+ ", authority=" + authority + ", sortbtn=" + sortbtn + ", aa=" + aa + ", orderDate=" + orderDate
				+ ", deliveryStatus=" + deliveryStatus + ", orderId=" + orderId + "]";
	}

	
}
