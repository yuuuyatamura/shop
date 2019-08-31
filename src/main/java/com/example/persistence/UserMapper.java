package com.example.persistence;

import com.example.domain.User;
import com.example.domain.UserInfo;
import com.example.web.form.LoginForm;
import com.example.web.form.RegisterForm;

@SuppressWarnings("unused")
public interface UserMapper {

	public User loginCheck(String userId);

	public void insertUser(RegisterForm register);
	public void insertUser(UserInfo userInfo);

	public void insertUserInfo(UserInfo userInfo);
}
