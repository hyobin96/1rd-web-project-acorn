package com.ssafy.ssafit.model.dto;

public class LoadingPlaylist {
	private long playlistId;
	private String playlistTitle;
	private long playlistItemId;
	private String playlistItemTitle;
	private String videoId;
	private String thumbnails;
	private String createdAt;
	
	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public LoadingPlaylist() {
		// TODO Auto-generated constructor stub
	}

	public long getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(long playlistId) {
		this.playlistId = playlistId;
	}

	public String getPlaylistTitle() {
		return playlistTitle;
	}

	public void setPlaylistTitle(String playlistTitle) {
		this.playlistTitle = playlistTitle;
	}

	public long getPlaylistItemId() {
		return playlistItemId;
	}

	public void setPlaylistItemId(long playlistItemId) {
		this.playlistItemId = playlistItemId;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(String thumbnails) {
		this.thumbnails = thumbnails;
	}

	public String getPlaylistItemTitle() {
		return playlistItemTitle;
	}

	public void setPlaylistItemTitle(String playlistItemTitle) {
		this.playlistItemTitle = playlistItemTitle;
	}

	@Override
	public String toString() {
		return "LoadingPlaylist [playlistId=" + playlistId + ", playlistTitle=" + playlistTitle + ", playlistItemId="
				+ playlistItemId + ", playlistItemTitle=" + playlistItemTitle + ", videoId=" + videoId + ", thumbnails="
				+ thumbnails + "]";
	}

}
