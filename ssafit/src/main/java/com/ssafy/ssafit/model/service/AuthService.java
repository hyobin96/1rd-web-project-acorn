package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.JwtResponse;
import com.ssafy.ssafit.model.dto.LoginRequest;

/**
 * 권한서비스에 대한 인터페이스 로그인, 로그아웃을 처리
 */
public interface AuthService {
	/**
	 * 로그인 토큰 발급
	 * @param request
	 * @return 로그인 응답 객체를 반환
	 */
	public JwtResponse login(LoginRequest request);
}
