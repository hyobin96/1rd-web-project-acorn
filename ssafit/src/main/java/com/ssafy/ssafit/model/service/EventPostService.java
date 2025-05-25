package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.EventPost;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 이벤트 게시판 관련 서비스 인터페이스
 */
public interface EventPostService {

	// 게시글 등록
	void createPost(EventPost post, MultipartFile thumbnail, List<MultipartFile> files);

	// 게시글 조회
	EventPost getPost(Long id);

	// 전체 게시글 조회
	List<EventPost> getAllPosts();

	// 게시글 수정
	void updatePost(EventPost post);

	// 게시글 삭제
	void deletePost(Long id);
}
