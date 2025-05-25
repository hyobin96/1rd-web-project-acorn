package com.ssafy.ssafit.model.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.ssafy.ssafit.model.dto.LoginRequest;

/**
 * 권한서비스에 대한 인터페이스 로그인, 로그아웃을 처리
 */
public interface AuthService {
	/**
	 * 로그인 토큰 발급
	 * @param request
	 * @return Cookie를 담은 HttpHeaders를 반환합니다.
	 */
	public ResponseEntity login(LoginRequest request);
	
	/**
	 * 쿠키의 수명을 0으로 하고 헤더에 담아 반환합니다.
	 * @return 헤더에 쿠키가 담긴 ResponseEntity 반환
	 */
	public ResponseEntity logout();
}
