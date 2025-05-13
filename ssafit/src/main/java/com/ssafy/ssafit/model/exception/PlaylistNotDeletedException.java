package com.ssafy.ssafit.model.exception;

public class PlaylistNotDeletedException extends RuntimeException {
	public PlaylistNotDeletedException(String message) {
        super(message);
    }
}
