package com.ssafy.ssafit.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class User {
	private Long id;
	
	@NotBlank(message = "5~20자의 영문 소문자와 특수기호(_)만 사용 가능합니다.")
	@Size(min = 5, max = 15, message = "5~20자의 영문 소문자와 특수기호(_)만 사용 가능합니다.")
	@Pattern(regexp = "^[a-z0-9_]+$", message = "5~20자의 영문 소문자와 특수기호(_)만 사용 가능합니다.")
	private String username;
	
	@NotBlank(message = "8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.")
	@Size(min = 8, max = 16, message = "8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.")
	private String password;
	
	@NotBlank(message = "이메일 형식이 올바르지 않습니다.")
	@Email(message = "이메일 형식이 올바르지 않습니다.")
	private String email;
	
	@NotBlank(message = "2자 이상 10자 이하로 입력해주세요.")
	@Size(min = 2, max = 10, message = "2자 이상 10자 이하로 입력해주세요.")
	private String nickname;
	
	private boolean isAdmin;
	
	@JsonProperty("isDeleted")
	private boolean isDeleted;
	private String createdAt;
	private String updatedAt; 
	private String profileImage; // 프로필 이미지 파일명 또는 경로
	private String gender;    // 성별
	private String birthDate; // 생년월일

	public User() {
	}
	
	//회원가입용 생성자
	public User(Long id, String username, String password, String email, String nickname, boolean isAdmin,
			boolean isDeleted, String createdAt) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.isAdmin = isAdmin;
		this.isDeleted = isDeleted;
		this.createdAt = createdAt;
	}
	
	//전체 생성자
	public User(Long id, String username, String password, String email, String nickname, boolean isAdmin,
			boolean isDeleted, String createdAt, String updatedAt, String profileImage, String gender, String birthDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.isAdmin = isAdmin;
		this.isDeleted = isDeleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.profileImage =profileImage;
		this.gender = gender;
		this.birthDate = birthDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public String getProfileImage() {
	    return profileImage;
	}

	public void setProfileImage(String profileImage) {
	    this.profileImage = profileImage;
	}
	
	public String getGender() {
	    return gender;
	}
	public void setGender(String gender) {
	    this.gender = gender;
	}
	public String getBirthDate() {
	    return birthDate;
	}
	public void setBirthDate(String birthDate) {
	    this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", nickname=" + nickname + ", isAdmin=" + isAdmin + ", isDeleted=" + isDeleted + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", profileImage=" + profileImage + ", gender=" + gender
				+ ", birthDate=" + birthDate + "]";
	}


}
