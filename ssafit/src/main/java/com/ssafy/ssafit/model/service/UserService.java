package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.LoginRequest;
import com.ssafy.ssafit.model.dto.User;

public interface UserService {
	/**
	 * 회원등록
	 * @param user
	 * @return 등록이 되었는지
	 */
	boolean signUp(User user);
	
	/**
	 * 소프트 회원삭제
	 * @param id
	 * @return
	 */
	boolean withdrawUser(int id);
	
	/**
	 * 하드 회원삭제
	 * @param id
	 * @return
	 */
	boolean deleteUserPermanently(int id);

	/**
	 * username 중복검사
	 * @param username
	 * @return
	 */
	boolean existsByUsername(String username);

	/**
	 * nickname 중복검사
	 * @param nickname
	 * @return
	 */
	boolean existsByNickname(String nickname);

	/**
	 * email 중복검사
	 * @param email
	 * @return
	 */
	boolean existsByEmail(String email);
}
