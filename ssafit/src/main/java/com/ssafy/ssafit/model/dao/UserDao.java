package com.ssafy.ssafit.model.dao;

import java.util.List;
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
	
	int updateUser(User user); //회원정보 수정
	
	int updateIsDeleted(int id);
	
	int deleteUserById(int id);
	
	
	/**
	 * 
	 * @param request
	 * @return username, 암호화된 password, role을 담은 User dto를 반환
	 */
	Optional<User> selectUser(String username);

	int selectByUsername(String username);

	int selectByNickname(String nickname);

	int selectByEmail(String email);

	// id(username)으로 회원 조회
	User findByUsername(String username);

	// 전체 회원 조회
	List<User> selectAllUsers();
}
