package com.example.web.form;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ModifyForm implements Serializable {

    private static final long serialVersionUID = 1L;

  

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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
	
	public ModifyForm() {
	}

	public ModifyForm(String nickName, String name, String address, String tel,
			String email) {
		super();
		
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.nickName = nickName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	@Override
	public String toString() {
		return "RegisterForm [name=" + name
				+ ", address=" + address + ", tel=" + tel + ", email=" + email + "]";
	}
	
}
