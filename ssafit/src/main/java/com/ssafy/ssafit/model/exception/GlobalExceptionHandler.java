package com.ssafy.ssafit.model.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// 유저가 존재하지 않는 경우
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleUserNotFound(UserNotFoundException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", e.getMessage()));
	}

	// 비밀번호가 일치하지 않는 경우
	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<String> handleInvalidPassword(InvalidPasswordException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	// 플레이리스트가 삭제가 되지 않은 경우
	// id가 존재하지 않는 것이므로 404 not_found로 반환
	@ExceptionHandler(PlaylistNotDeletedException.class)
	public ResponseEntity<String> handlePlayListNotDeleted(PlaylistNotDeletedException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	// 플레이리스트가 생성이 되지 않은 경우
	@ExceptionHandler(PlaylistNotInsertedException.class)
	public ResponseEntity<String> handlePlayListNotInserted(PlaylistNotInsertedException e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}

	// 플레이리스트가 불려오지 않은 경우
	@ExceptionHandler(PlaylistNotSelectedException.class)
	public ResponseEntity<String> handlePlayListNotSelected(PlaylistNotSelectedException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	// 서버 오류가 발생한 경우
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllOtherExceptions(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}

}
