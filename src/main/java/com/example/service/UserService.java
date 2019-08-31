package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.domain.UserInfo;
import com.example.persistence.OrderMapper;
import com.example.persistence.UserMapper;
import com.example.web.form.LoginForm;
import com.example.web.form.OrderForm;
import com.example.web.form.RegisterForm;

@SuppressWarnings("unused")
@Service
public class UserService {

	@Autowired
	UserMapper mapper;
	
	// 入力したIDでDB検索
	public User loginCheck(String userId) {
		User user = mapper.loginCheck(userId);
		// 検索結果を返還
		return user;
	}
	
	// 入力したIDでDB検索
		public User myPage(RegisterForm form) {
			User user = mapper.loginCheck(form.getUserId());
			// 検索結果を返還
			return user;
		}
	
	// 入力したIDでDB検索
	public boolean idCheck(String userId) {
		Boolean result = false;
		User user = mapper.loginCheck(userId);
		// 検索結果が存在しない場合
		if (user == null) {
			// このIDは使える
			result = true;
		}
		return result;
	}
	
	// User登録
	@Transactional
	public void insertUser(RegisterForm register) {
		mapper.insertUser(register);
	}
	
	// User修正登録
	@Transactional
	public void insertUserInfo(UserInfo userInfo) {
		mapper.insertUserInfo(userInfo);
	}
	
	/*// cartに入れている商品のidのマップを利用して商品リストを取得
	public ArrayList<OrderForm> getPnames(HashMap<String, Integer> map, ArrayList<OrderForm> olist){
		return olist;
	}*/
	
}
