package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.EventFile;
import com.ssafy.ssafit.model.dto.EventPost;
import java.util.List;

/**
 * 이벤트 게시판 DAO
 */
public interface EventPostDao {
	/**
	 * 게시글 등록
	 * @param post
	 */
    void insertPost(EventPost post);
    
    /**
     * id가 일치하는 게시글 조회
     * @param id
     * @return EventPost
     */
    EventPost selectPost(Long id);
    
    /**
     * 모든 게시글 조회
     * @return List<EventPost>
     */
    List<EventPost> selectAllPosts();
    
    /**
     * 게시글 수정
     * @param post
     */
    void updatePost(EventPost post);
    
    /**
     * id가 일치하는 게시글 삭제
     * @param id
     */
    void deletePost(Long id);

    /**
     * file 등록
     * @param file
     */
    void insertFile(EventFile file);
    
    /**
     * postId가 일치하는 파일들 모두 조회
     * @param postId
     * @return List<EventFile>
     */
    List<EventFile> selectFilesByPostId(Long postId);
    
    /**
     * postId가 일치하는 파일들 모두 삭제
     * @param postId
     */
    void deleteFilesByPostId(Long postId);
}
