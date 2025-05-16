package com.ssafy.ssafit.model.controller;

import com.ssafy.ssafit.model.dto.EventPost;
import com.ssafy.ssafit.model.service.EventPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 이벤트 게시판 API를 처리하는 컨트롤러
 */
@RestController
@RequestMapping("/api/events")
@CrossOrigin("*")
public class EventPostController {

    @Autowired
    private EventPostService eventPostService;

    /**
     * 이벤트 게시글 등록
     */
    @PostMapping
    public ResponseEntity<?> create(@ModelAttribute EventPost post,
                                    @RequestParam(value = "files", required = false) List<MultipartFile> files) {
        System.out.println("도착");
        System.out.println(post);
    	eventPostService.createPost(post, files);
        return ResponseEntity.ok().build();
    }

    /**
     * 게시글 단건 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<EventPost> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(eventPostService.getPost(id));
    }

    /**
     * 전체 게시글 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<EventPost>> getAll() {
        return ResponseEntity.ok(eventPostService.getAllPosts());
    }

    /**
     * 게시글 수정
     */
    @PutMapping
    public ResponseEntity<?> update(@RequestBody EventPost post) {
        eventPostService.updatePost(post);
        return ResponseEntity.ok().build();
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        eventPostService.deletePost(id);
        return ResponseEntity.ok().build();
    }
}
