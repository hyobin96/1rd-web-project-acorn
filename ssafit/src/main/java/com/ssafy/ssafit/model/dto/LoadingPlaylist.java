package com.ssafy.ssafit.model.dto;

public class LoadingPlaylist {
	private long playlistId;
	private String playlistTitle;
	private long playlistItemId;
	private String videoId;
	private String thumbnails;

	public LoadingPlaylist() {
		// TODO Auto-generated constructor stub
	}

	public LoadingPlaylist(long playlistId, String playlistTitle, long playlistItemId, String videoId,
			String thumbnails) {
		super();
		this.playlistId = playlistId;
		this.playlistTitle = playlistTitle;
		this.playlistItemId = playlistItemId;
		this.videoId = videoId;
		this.thumbnails = thumbnails;
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

	@Override
	public String toString() {
		return "LoadingPlaylist [playlistId=" + playlistId + ", playlistTitle=" + playlistTitle + ", playlistItemId="
				+ playlistItemId + ", videoId=" + videoId + ", thumbnails=" + thumbnails + "]";
	}

}
