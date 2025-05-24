package com.ssafy.ssafit.model.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.UserService;

import jakarta.validation.Valid;



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
	@PutMapping("{userId}")
	public ResponseEntity<?> withdrawUser(@PathVariable("userId") int id) {
		return userService.withdrawUser(id) ? ResponseEntity.ok().build()
				: ResponseEntity.internalServerError().body("처리 중 오류가 발생했습니다.");
	}

	/**
	 * 회원탈퇴, 영구 삭제
	 * 
	 * @param id
	 * @return 성공 시 200, 실패 시 500
	 */
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
    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        return userService.existsByEmail(email)
        		? ResponseEntity.ok(Map.of("message", "사용 가능한 이메일입니다."))
        		: ResponseEntity.badRequest().body(Map.of("message", "중복된 이메일입니다."));
    }


}
