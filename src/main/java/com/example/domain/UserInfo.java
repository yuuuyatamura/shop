package com.example.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserInfo {

	  private int id;
	  @NotEmpty(message="{error.user.login.id.required}")
	  private String userId;
	  @NotEmpty(message="{error.user.login.pw.required}")
	  private String passwd;
	  @NotEmpty(message="{error.user.register.name.required}")
	  private String name;
	  @NotEmpty(message="{error.user.register.nickName.required}")
	  private String nickName;
	  public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	public String getInp_date() {
		return inp_date;
	}
	public void setInp_date(String inp_date) {
		this.inp_date = inp_date;
	}
	public String getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	private String address;
	@NotEmpty(message="{error.user.register.tel.required}")
	  private String tel;
	@NotEmpty(message="{error.user.register.email.required}")
	@Email(message="{error.user.register.email.form}")
	  private String email;
	
	  private String inp_date;
	  private String upd_date;
	  private int authority;
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", userId=" + userId + ", passwd=" + passwd + ", name=" + name + ", nickName="
				+ nickName + ", address=" + address + ", tel=" + tel + ", email=" + email + ", inp_date=" + inp_date
				+ ", upd_date=" + upd_date + ", authority=" + authority + "]";
	}
	  
	  
}