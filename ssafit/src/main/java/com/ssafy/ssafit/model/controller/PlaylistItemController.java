package com.ssafy.ssafit.model.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ssafy.ssafit.model.dto.PlaylistItem;
import com.ssafy.ssafit.model.service.PlaylistItemService;

import io.swagger.v3.oas.annotations.Operation;


/**
 * playlist-item에 접근하기 위한 controller
 * 아이템 가져오기, 추가, 삭제를 제공합니다.
 */
@RestController
@RequestMapping("/api/playlistItem")
@CrossOrigin("*")
public class PlaylistItemController {

    @Autowired
    private PlaylistItemService service;

    /**
     * playlistId가 일치하는 items를 조회합니다.
     * @param playlistId
     * @return 200코드와 List<PlaylistItem>
     */
    @Operation(summary = "영상 목록 조회", description = "playlist에 담긴 영상들을 조회합니다.")
    @GetMapping("/{playlistId}")
    public ResponseEntity<List<PlaylistItem>> getItems(@PathVariable Long playlistId) {
        return ResponseEntity.ok(service.getItems(playlistId));
    }

    /**
     * item을 playlist_items 테이블에 추가합니다.
     * @param items
     * @return 201 코드
     */
    @Operation(summary = "영상 등록", description = "playlistItem들을 db에 등록합니다.")
    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody List<PlaylistItem> items) {
        service.addItem(items);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * id가 일치하는 item을 playlist_items 테이블에서 삭제합니다.
     * @param id
     * @return 204 코드
     */
    @Operation(summary = "영상 삭제", description = "id가 일치하는 playlistItem을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
} 