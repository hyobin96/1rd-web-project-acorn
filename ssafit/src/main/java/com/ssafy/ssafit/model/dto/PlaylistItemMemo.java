package com.ssafy.ssafit.model.dto;

public class PlaylistItemMemo {
	private long id;
	private long playlistItemsId;
	private String memo;
	private String createdAt;
	private String updatedAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPlaylistItemsId() {
		return playlistItemsId;
	}

	public void setPlaylistItemsId(long playlistItemsId) {
		this.playlistItemsId = playlistItemsId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "PlaylistItemMemo [id=" + id + ", playlistItemsId=" + playlistItemsId + ", memo=" + memo + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
