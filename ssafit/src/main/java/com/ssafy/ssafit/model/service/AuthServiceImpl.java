package com.ssafy.ssafit.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.config.JwtUtil;
import com.ssafy.ssafit.model.dao.UserDao;
import com.ssafy.ssafit.model.dto.JwtResponse;
import com.ssafy.ssafit.model.dto.LoginRequest;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.exception.InvalidPasswordException;
import com.ssafy.ssafit.model.exception.UserNotFoundException;

/**
 * 권한 과정을 처리하는 서비스
 * 로그인 과정을 처리한다.
 */
@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserDao userDao; // DB에 접근하기 위한 DAO
	
	@Autowired
	private PasswordEncoder passwordEncoder; // password 일치 과정에 사용
	
	@Autowired
	private JwtUtil jwtUtil; // 토큰을 생성하기 위한 유틸클래스
	
	/**
	 * 아이디, 비밀번호 일치 여부를 판단하고 일치한다면
	 * 토큰을 생성해 HttpHeader에 쿠키로 담고 HttpHeader를 반환합니다.
	 */
	@Override
	public HttpHeaders login(LoginRequest request) {
	    Optional<User> optUser = userDao.selectUser(request);

	    // 유저가 존재하지 않는다면 UserNotFoundException 발생
	    if (optUser.isEmpty()) {
	        throw new UserNotFoundException("존재하지 않는 사용자입니다.");
	    }

	    User user = optUser.get();

	    // 비밀번호가 일치하지 않는다면 InvalidPasswordException 발생
	    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
	        throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
	    }

	    String role = user.isAdmin() ? "ADMIN" : "USER";
	    String token = jwtUtil.generateToken(user.getUsername(), role);
	    
	    ResponseCookie cookie = ResponseCookie.from("accessToken", token)
	    		.httpOnly(true)
	    		.secure(false)
	    		.path("/")
	    		.maxAge(3600)
	    		.sameSite("None")
	    		.build();
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
	    
	    return headers;
	}


}
