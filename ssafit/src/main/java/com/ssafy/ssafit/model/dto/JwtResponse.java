package com.ssafy.ssafit.model.dto;

import java.util.List;

/**
 * 토큰과 유저 이름, 권한을 함께 담는 dto 로그인 요청에서 응답
 */
public class JwtResponse {
	private String token;
	private String username;
	private String role; // "ADMIN" or "USER"

	public JwtResponse(String token, String username, String role) {
		this.token = token;
		this.username = username;
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "JwtResponse [token=" + token + ", username=" + username + ", role=" + role + "]";
	}

}
