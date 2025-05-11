package com.ssafy.ssafit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.UserDao;
import com.ssafy.ssafit.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	// db에 접근하기 위한 dao
	private UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	// 암호화에 사용할 인코더
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * 회원등록
	 */
	@Override
	public boolean signUp(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDao.insertUser(user) == 1;
	}

	/**
	 * 회원 소프트 삭제
	 */
	@Override
	public boolean withdrawUser(int id) {
		return userDao.updateIsDeleted(id) == 1;
	}

	/**
	 * 회원 하드 삭제
	 */
	@Override
	public boolean deleteUserPermanently(int id) {
		return userDao.deleteUserById(id) == 1;
	}

}
