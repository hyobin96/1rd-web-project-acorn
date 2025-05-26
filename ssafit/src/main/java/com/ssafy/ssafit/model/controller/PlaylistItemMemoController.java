package com.ssafy.ssafit.model.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.PlaylistItemMemo;
import com.ssafy.ssafit.model.service.PlaylistItemMemoService;

@RestController
@RequestMapping("/api/memo")
@CrossOrigin("*")
public class PlaylistItemMemoController {
	@Autowired
	private PlaylistItemMemoService memoService;

	/**
	 * playlistItemsId가 일치하는 memo들을 반환합니다.
	 * @param playlistItemsId
	 * @return
	 */
	@GetMapping("{playlistItemsId}")
	public ResponseEntity<?> getMemos(@PathVariable long playlistItemsId) {
		List<PlaylistItemMemo> list = memoService.getMemos(playlistItemsId);
		System.out.println(list);
		if (list.size() != 0) {
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.noContent().build();
	}

	/**
	 * memo를 등록합니다.
	 * @param memo
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> registMemo(@RequestBody PlaylistItemMemo memo) {
		return memoService.registMemo(memo) ? ResponseEntity.status(HttpStatus.CREATED).build()
				: ResponseEntity.internalServerError().body(Map.of("message", "등록이 되지 않았습니다."));
	}

	/**
	 * memo를 수정합니다.
	 * @param memo
	 * @return
	 */
	@PutMapping
	public ResponseEntity<?> modifyMemo(@RequestBody PlaylistItemMemo memo) {
		return memoService.modifyMemo(memo) ? ResponseEntity.ok(Map.of("message", "수정 완료"))
				: ResponseEntity.internalServerError().body(Map.of("message", "수정이 되지 않았습니다."));
	}

	/**
	 * id가 일치하는 memo를 삭제합니다.
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeMemo(@PathVariable long id) {
		return memoService.removeMemo(id) ? ResponseEntity.ok().build()
				: ResponseEntity.internalServerError().body(Map.of("message", "삭제가 되지 않았습니다."));
	}
}
