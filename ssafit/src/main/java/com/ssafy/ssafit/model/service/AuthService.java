package com.ssafy.ssafit.model.service;

import org.springframework.http.HttpHeaders;

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
	public HttpHeaders login(LoginRequest request);
}
