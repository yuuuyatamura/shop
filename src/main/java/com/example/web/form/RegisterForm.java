package com.example.web.form;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class RegisterForm implements Serializable {

    private static final long serialVersionUID = 1L;

	@NotEmpty(message="{error.user.register.id.required}")
	private String userId;
	@NotEmpty(message="{error.user.register.pw.required}")
	private String passwd;
	private String passwd2;
	@NotEmpty(message="{error.user.register.name.required}")
	private String name;
	@NotEmpty(message="{error.user.register.address.required}")
	private String address;
	@NotEmpty(message="{error.user.register.tel.required}")
	private String tel;
	@NotEmpty(message="{error.user.register.email.required}")
	@Email(message="{error.user.register.email.form}")
	private String email;
	@NotEmpty(message="{error.user.register.nickName.form}")
	private String nickName;
	
	public RegisterForm() {
	}

	public RegisterForm(@NotEmpty(message = "{error.user.register.id.required}") String userId,
			@NotEmpty(message = "{error.user.register.pw.required}") String passwd, String passwd2,
			@NotEmpty(message = "{error.user.register.name.required}") String name,
			@NotEmpty(message = "{error.user.register.address.required}") String address,
			@NotEmpty(message = "{error.user.register.tel.required}") String tel,
			@NotEmpty(message = "{error.user.register.email.required}") @Email(message = "{error.user.register.email.form}") String email,
			@NotEmpty(message = "{error.user.register.nickName.form}") String nickName) {
		super();
		this.userId = userId;
		this.passwd = passwd;
		this.passwd2 = passwd2;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.nickName = nickName;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "RegisterForm [userId=" + userId + ", passwd=" + passwd + ", passwd2=" + passwd2 + ", name=" + name
				+ ", address=" + address + ", tel=" + tel + ", email=" + email + ", nickName=" + nickName + "]";
	}

	
}
