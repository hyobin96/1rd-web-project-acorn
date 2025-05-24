package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dao.EventPostDao;
import com.ssafy.ssafit.model.dto.EventFile;
import com.ssafy.ssafit.model.dto.EventPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * 이벤트 게시판 서비스 구현체
 */
@Service
public class EventPostServiceImpl implements EventPostService {

    @Autowired
    private EventPostDao eventPostDao;

    /**
     * 게시글 등록
     */
    @Override
    public void createPost(EventPost post, List<MultipartFile> files) {
        eventPostDao.insertPost(post);

        // 첨부파일이 있는 경우 파일 정보 저장
        if (files != null) {
            for (MultipartFile file : files) {
                String storedName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                String filePath = "/uploads/" + storedName;

                EventFile ef = new EventFile();
                ef.setPostId(post.getId());
                ef.setOriginalName(file.getOriginalFilename());
                ef.setStoredName(storedName);
                ef.setFilePath(filePath);

                eventPostDao.insertFile(ef);
            }
        }
    }

    /**
     * 게시글 조회
     */
    @Override
    public EventPost getPost(Long id) {
        EventPost post = eventPostDao.selectPost(id);
        post.setFileList(eventPostDao.selectFilesByPostId(id));
        return post;
    }

    /**
     * 모든 게시글 조회
     */
    @Override
    public List<EventPost> getAllPosts() {
        List<EventPost> posts = eventPostDao.selectAllPosts();
    	for (EventPost post : posts) {
            post.setFileList(eventPostDao.selectFilesByPostId(post.getId()));
        }
        return posts;
    }

    /**
     * 게시글 수정
     */
    @Override
    public void updatePost(EventPost post) {
        eventPostDao.updatePost(post);
    }

    /**
     * 게시글 삭제
     */
    @Override
    public void deletePost(Long id) {
        // 파일 정보 먼저 삭제
        eventPostDao.deleteFilesByPostId(id);
        eventPostDao.deletePost(id);
    }
}
