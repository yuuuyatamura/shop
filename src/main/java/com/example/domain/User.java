package com.example.domain;

public class User {

	private String id;
	private String userId;
	private String passwd;
	private String passwd2;
	private String name;
	private String address;
	private String tel;
	private String email;
	private String nickName;
	private String inp_date;
	private String inpDate;
	private String updDate;
	private String authority;

	public User() {
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

	public String getInp_date() {
		return inp_date;
	}

	public void setInp_date(String inp_date) {
		this.inp_date = inp_date;
	}

	public String getInpDate() {
		return inpDate;
	}

	public void setInpDate(String inpDate) {
		this.inpDate = inpDate;
	}

	public String getUpdDate() {
		return updDate;
	}

	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", passwd=" + passwd + ", passwd2=" + passwd2 + ", name="
				+ name + ", address=" + address + ", tel=" + tel + ", email=" + email + ", nickName=" + nickName
				+ ", inp_date=" + inp_date + ", inpDate=" + inpDate + ", updDate=" + updDate + ", authority="
				+ authority + "]";
	}

}
