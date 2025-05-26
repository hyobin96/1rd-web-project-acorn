package com.ssafy.ssafit.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.LoginRequest;
import com.ssafy.ssafit.model.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * 권한 작업을 처리하는 컨트롤러
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "https://localhost:5173", allowCredentials = "true")
public class AuthController {
	@Autowired
	private AuthService authService;
	
	/**
	 * ResponseEntity 헤더에 토큰을 쿠키로 담아 반환합니다.
	 * 자동으로 브라우저에 등록됩니다.
	 * @param request
	 * @return ResponseEntity
	 */
	@Operation(summary = "로그인", description = "회원 ID와 비밀번호로 로그인합니다.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "로그인 성공"),
			@ApiResponse(responseCode = "401", description = "아이디나 비밀번호가 일치하지 않음"),
			@ApiResponse(responseCode = "500", description = "서버 에러 발생")
	})
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request){
		return authService.login(request);
	}
	
	/**
	 * ResponseEntity 헤더에 쿠키를 즉시만료로 하여 반환합니다.
	 * @return ResponseEntity
	 */
	@Operation(summary = "로그아웃", description = "쿠키를 만료시켜 로그아웃시킵니다.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "로그아웃 성공"),
			@ApiResponse(responseCode = "500", description = "서버 에러 발생")
	})
	@PostMapping("/logout")
	public ResponseEntity<?> logout(){
		return authService.logout();
	}
	
	/**
	 * 로그인이 되어있는지 체크합니다.
	 * @return
	 */
	@Operation(summary = "로그인 체크", description = "일반 유저 로그인이 되어있는지 체크합니다.")
	@GetMapping
	public ResponseEntity<?> loginCheck(){
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 권한이 admin인지 체크합니다.
	 * @return
	 */
	@Operation(summary = "관리자 로그인 체크", description = "권한이 관리자인지 체크합니다.")
	@GetMapping("/admin")
	public ResponseEntity<?> adminCheck(){
		return ResponseEntity.ok().build();
	}
}
