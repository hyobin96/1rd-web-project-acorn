package com.ssafy.ssafit.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.LoginRequest;
import com.ssafy.ssafit.model.service.AuthService;

/**
 * 권한 작업을 처리하는 컨트롤러
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {
	@Autowired
	private AuthService authService;
	
	/**
	 * jwt 객체를 body에 담아 반환합니다.
	 * jwt는 토큰, username, role이 담겨 있습니다.
	 * @param request
	 * @return ResponseEntity
	 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request){
		return ResponseEntity.ok(authService.login(request));
	}
}
