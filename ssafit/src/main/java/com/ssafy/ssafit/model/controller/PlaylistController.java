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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.LoadingPlaylist;
import com.ssafy.ssafit.model.dto.Playlist;
import com.ssafy.ssafit.model.service.PlaylistService;

@RestController
@RequestMapping("/api/playlist")
@CrossOrigin(origins = "https://localhost:5173", allowCredentials = "true")
public class PlaylistController {
	@Autowired
	private PlaylistService playlistService;

	/**
	 * 유저의 playlist와 해당하는 item들을 가져옵니다.
	 * @return 각 playlist에 해당하는 item들을 map에 담아 반환합니다.
	 * 		   key는 playlistId이고 value는 List<LoadingPlaylist> 입니다.
	 */
	@GetMapping
	public ResponseEntity<Map<Long, List<LoadingPlaylist>>> readPlaylist() {
		return ResponseEntity.ok(playlistService.getPlaylist());
	}

	@DeleteMapping("{playlistId}")
	public ResponseEntity<?> deletePlaylist(@PathVariable("playlistId") long id) {
		playlistService.deletePlaylistById(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("")
	public ResponseEntity<?> addPlaylist(@RequestBody Playlist playlist) {
		System.out.println(playlist);
		playlistService.createPlaylist(playlist);
		return ResponseEntity.status(HttpStatus.CREATED).body(playlist);
	}
}
