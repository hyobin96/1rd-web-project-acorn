package com.ssafy.ssafit.model.dao;

import java.util.Optional;

import com.ssafy.ssafit.model.dto.LoginRequest;
import com.ssafy.ssafit.model.dto.User;

public interface UserDao {
	/**
	 * users테이블에 새로운 행 삽입
	 * @param user
	 * @return 바뀐 행의 개수 반환
	 */
	int insertUser(User user);
	
	int updateIsDeleted(int id);
	
	int deleteUserById(int id);
	
	/**
	 * 
	 * @param request
	 * @return username, 암호화된 password, role을 담은 User dto를 반환
	 */
	Optional<User> selectUser(LoginRequest request);
}
