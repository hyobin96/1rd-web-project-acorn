package com.ssafy.ssafit.model.dto;

public class PlaylistItem {
	private Long id;
	private Long playlistId; // playlist의 id
	private String videoId;
	private String thumbnails;
	private String memo;

	public PlaylistItem() {
		// TODO Auto-generated constructor stub
	}

	public PlaylistItem(Long id, Long playlistId, String videoId, String thumbnails, String memo) {
		super();
		this.id = id;
		this.playlistId = playlistId;
		this.videoId = videoId;
		this.thumbnails = thumbnails;
		this.memo = memo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(Long playlistId) {
		this.playlistId = playlistId;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "PlaylistItem [id=" + id + ", playlistId=" + playlistId + ", videoId=" + videoId + ", thumbnails="
				+ thumbnails + ", memo=" + memo + "]";
	}

}