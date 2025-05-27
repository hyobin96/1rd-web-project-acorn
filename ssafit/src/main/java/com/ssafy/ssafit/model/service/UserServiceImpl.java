package com.ssafy.ssafit.model.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ssafit.model.dao.UserDao;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	// db에 접근하기 위한 dao
	private UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Value("${profile.upload.dir:/uploads}")
	private String uploadDir;

	// 암호화에 사용할 인코더
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 회원등록
	 */
	@Override
	public boolean signUp(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDao.insertUser(user) == 1;
	}

	/**
	 * 회원 소프트 삭제
	 */
	@Override
	public boolean withdrawUser(int id) {
		return userDao.updateIsDeleted(id) == 1;
	}

	/**
	 * 회원 하드 삭제
	 */
	@Override
	public boolean deleteUserPermanently(int id) {
		return userDao.deleteUserById(id) == 1;
	}

	/**
	 * username 중복검사
	 */
	@Override
	public boolean existsByUsername(String username) {
		return userDao.selectByUsername(username) == 0;
	}

	/**
	 * nickname 중복검사
	 */
	@Override
	public boolean existsByNickname(String nickname) {
		return userDao.selectByNickname(nickname) == 0;
	}

	/**
	 * email 중복검사
	 */
	@Override
	public boolean existsByEmail(String email) {
		return userDao.selectByEmail(email) == 0;
	}

	/**
	 * 회원정보 수정
	 */
	@Override
	public boolean updateUserInfo(String username, User user) {
		User existingUser = userDao.findByUsername(username);
		if (existingUser == null)
			return false;

		existingUser.setNickname(user.getNickname());
		existingUser.setGender(user.getGender());
		if (user.getBirthDate() != null && !user.getBirthDate().trim().isEmpty()) {
		    existingUser.setBirthDate(user.getBirthDate());
		} else {
		    existingUser.setBirthDate(null);
		}
		
		return userDao.updateUser(existingUser) >= 0;
	}

	/**
	 * 회원정보 조회
	 */
	@Override
	public User getMyInfo(String username) {
		User user = userDao.findByUsername(username);
		if (user == null)
			return null;

		User currentUser = new User();
		currentUser.setNickname(user.getNickname());
		currentUser.setEmail(user.getEmail());
		currentUser.setGender(user.getGender());
		currentUser.setProfileImage(user.getProfileImage());
		currentUser.setBirthDate(user.getBirthDate());

		return currentUser;
	}

	/**
	 * 사용자의 프로필 이미지 업로드 및 저장
	 */
	@Override
	public String updateProfileImage(String username, MultipartFile file) {
		User user = userDao.findByUsername(username);
		if (user == null)
			return null;

		String originalFilename = file.getOriginalFilename();
		String ext = "";
		if (originalFilename != null && originalFilename.contains(".")) {
			ext = originalFilename.substring(originalFilename.lastIndexOf("."));
		}
		String savedFileName = UUID.randomUUID().toString() + ext;

		File dir = new File(uploadDir);
		if (!dir.exists())
			dir.mkdirs();

		File dest = new File(dir, savedFileName);
		try {
			file.transferTo(dest);

			String profileImageUrl = "/uploads/" + savedFileName;

			user.setProfileImage(profileImageUrl);
			System.out.println(user);
			int tmp = userDao.updateUser(user);
			return profileImageUrl;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 전체 회원 조회
	 */
	@Override
	public List<User> getAllUsers() {
		return userDao.selectAllUsers();
	}
	
	/**
	 * 출석 체크 및 보상 지급
	 */
	 @Override
	    public ResponseEntity<?> checkAttendance(String username) {
	        User user = userDao.findByUsername(username);
	        System.out.println("userId:" + user.getId());
	        System.out.println("lastAttendanceDate: " + user.getLastAttendanceDate());
	        if (user == null) 
	        	throw new UserNotFoundException("유저가 DB에 없습니다.");

	        // 오늘 날짜 (yyyy-MM-dd)
	        String today = LocalDate.now().format(DateTimeFormatter.ISO_DATE);

	        String message = "오늘은 이미 출석했습니다.";
	        // 이미 오늘 출석했는지 확인
	        if (today.equals(user.getLastAttendanceDate())) {
	            return ResponseEntity.badRequest().body(
	            		Map.of("message", message,
	            				"rewardPoints", user.getRewardPoints())); // 이미 출석함
	        }

	        // 출석 처리
	        user.setLastAttendanceDate(today);

	        // 포인트 지급 (예: 10포인트)
	        int currentPoints = user.getRewardPoints();
	        user.setRewardPoints(currentPoints + 10);
	        
	        // DB 업데이트
	        boolean isUpdated = userDao.updateUser(user) > 0;
	        if (isUpdated) message = "출석 완료! 10포인트 지급!";
	        return ResponseEntity.ok(Map.of("message", message,
    				"rewardPoints", user.getRewardPoints()));
	    }
}
