package com.example.web.form;

import javax.validation.constraints.NotEmpty;

public class LoginForm {

	@NotEmpty(message="{error.user.login.id.required}")
	private String userId;
	@NotEmpty(message="{error.user.login.pw.required}")
	private String passwd;
	
	

	public LoginForm() {
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

	@Override
	public String toString() {
		return "LoginForm [userId=" + userId + ", passwd=" + passwd + "]";
	}
	
	
	
}
