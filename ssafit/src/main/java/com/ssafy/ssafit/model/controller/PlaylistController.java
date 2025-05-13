package com.ssafy.ssafit.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Playlist;
import com.ssafy.ssafit.model.service.PlaylistService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/playlist")
@CrossOrigin("*")
public class PlaylistController {
	@Autowired
	private PlaylistService playlistService;
	
	@GetMapping("{userId}")
	public ResponseEntity<Playlist> readPlaylist(@PathVariable("userId") int userId){
		return ResponseEntity.ok(playlistService.getPlaylist(userId));
	}
	
	@DeleteMapping("{playlistId}")
	public ResponseEntity<?> deletePlaylist(@PathVariable("playlistId") int id){
		playlistService.deletePlaylistById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("")
	public ResponseEntity<?> addPlaylist(@RequestBody Playlist playlist){
		playlistService.createPlaylist(playlist);
		return ResponseEntity.status(HttpStatus.CREATED).body(playlist);
	}
}
