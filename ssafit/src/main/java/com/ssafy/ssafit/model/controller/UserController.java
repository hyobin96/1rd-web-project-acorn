package com.ssafy.ssafit.model.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.websocket.OnOpen;



/**
 * 회원 정보를 관리하는 컨트롤러
 * 회원가입, 회원탈퇴, 정보 수정 기능을 처리
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 회원가입
	 * 
	 * @param user
	 * @return 성공 시 200, 실패 시 500
	 */
	@Operation(summary = "회원가입", description = "정보를 검증한 후에 회원을 등록합니다.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "회원등록 성공"),
			@ApiResponse(responseCode = "400", description = "회원정보가 규칙에 맞지 않습니다."),
			@ApiResponse(responseCode = "500", description = "아이디가 중복입니다.")
	})
	@PostMapping("")
	public ResponseEntity<?> regist(@Valid @RequestBody User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String errorMessage = bindingResult.getFieldError().getDefaultMessage();
	        return ResponseEntity.badRequest().body(Map.of("error", errorMessage));
		}
		
		if(userService.signUp(user)) {
			Map<String, String> info = new HashMap<>();
			info.put("id", user.getId() + "");
			info.put("username", user.getUsername());
			
			return ResponseEntity.ok(info);
		}
		
		return ResponseEntity.internalServerError().body(Map.of("error", "아이디가 중복입니다."));
	}
	
	@Operation(summary = "회원 정보 조회", description = "쿠키에 저장된 내용을 바탕으로 해당 유저의 정보를 반환합니다.")
    @GetMapping("/me")
    public ResponseEntity<User> getMyInfo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getMyInfo(username);
        return ResponseEntity.ok(user);
    }
	
    
    /**
     * 회원정보 수정
     * @param user
     * @return 성공 시 200, 실패 시 500
     */
	 @Operation(summary = "회원정보 수정", description = "회원정보를 수정합니다.")
     @PatchMapping("/me")
     public ResponseEntity<?> updateMyInfo(@RequestBody User user){
    	 //1. 인증된 사용자 정보 추출
    	 String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	 //2. Service에 업데이트 요청
    	 boolean isUpdated = userService.updateUserInfo(username, user);
    	 
    	 if(isUpdated) {
    		 return ResponseEntity.ok("회원정보가 수정되었습니다.");
    	 } else {
    		 return ResponseEntity.internalServerError().body("회원정보 수정에 실패했습니다.");
    	 }
     }
     
     /**
      * 프로필 이미지 저장 
      */
	 @Operation(summary = "프로필 이미지 저장", description = "프로필 이미지 파일을 저장하고 경로를 db에 저장합니다.")
     @PostMapping("/me/profile-image")
     public ResponseEntity<?> uploadProfileImage(@RequestParam("file") MultipartFile file){
    	 String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	 
    	 String profileImageUrl = userService.updateProfileImage(username, file);
    	 
    	 if (profileImageUrl != null) {
    	        return ResponseEntity.ok(Map.of("profileImageUrl", profileImageUrl));
    	        
    	    } else {
    	        return ResponseEntity.internalServerError().body(Map.of("error", "이미지 업로드 실패"));
    	    }
     }

	/**
	 * 회원탈퇴, 소프트 삭제
	 * 
	 * @param user
	 * @return 성공 시 200, 실패 시 500
	 */
	@Operation(summary = "소프트회원탈퇴")
 	@PutMapping("{userId}")
 	public ResponseEntity<?> withdrawUser(@PathVariable("userId") int id) {
 		return userService.withdrawUser(id) ? ResponseEntity.ok().build()
 				: ResponseEntity.internalServerError().body("처리 중 오류가 발생했습니다.");
 	}
 	
//	@PutMapping("{userId}")
//	public ResponseEntity<?> withdrawUser(@PathVariable("userId") int id) {
//		return userService.withdrawUser(id) ? ResponseEntity.ok().build()
//				: ResponseEntity.internalServerError().body("처리 중 오류가 발생했습니다.");
//	}

	/**
	 * 회원탈퇴, 영구 삭제
	 * 
	 * @param id
	 * @return 성공 시 200, 실패 시 500
	 */
	@Operation(summary = "영구탈퇴", description = "유저정보를 db에서 삭제합니다.")
	@DeleteMapping("{userId}")
	public ResponseEntity<?> deleteUserPermanently(@PathVariable("userId") int id) {
		return userService.deleteUserPermanently(id) ? ResponseEntity.ok().build()
				: ResponseEntity.internalServerError().body("처리 중 오류가 발생했습니다.");
	}
	
	/**
	 * username 중복검사
	 * @param username
	 * @return
	 */
	@Operation(summary = "아이디 중복검사")
	@GetMapping("/check-username")
    public ResponseEntity<?> checkUsername(@RequestParam String username) {
		System.out.println(username);
        return userService.existsByUsername(username)
        		? ResponseEntity.ok(Map.of("message", "사용 가능한 아이디입니다."))
        		: ResponseEntity.badRequest().body(Map.of("message", "중복된 아이디입니다."));
    }

	/**
	 * nickname 중복검사
	 * @param nickname
	 * @return
	 */
	@Operation(summary = "닉네임 중복검사")
    @GetMapping("/check-nickname")
    public ResponseEntity<?> checkNickname(@RequestParam String nickname) {
        return userService.existsByNickname(nickname)
        		? ResponseEntity.ok(Map.of("message", "사용 가능한 닉네임입니다."))
        		: ResponseEntity.badRequest().body(Map.of("message", "중복된 닉네임입니다."));
    }

    /**
     * email 중복검사
     * @param email
     * @return
     */
	@Operation(summary = "email 중복검사")
    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        return userService.existsByEmail(email)
        		? ResponseEntity.ok(Map.of("message", "사용 가능한 이메일입니다."))
        		: ResponseEntity.badRequest().body(Map.of("message", "중복된 이메일입니다."));
    }

    /**
     * 전체 회원 조회
     */
	@Operation(summary = "전체 회원 조회")
    @GetMapping("")
    public ResponseEntity<?> getAllUsers(){
    	return ResponseEntity.ok(userService.getAllUsers());
    }
}
