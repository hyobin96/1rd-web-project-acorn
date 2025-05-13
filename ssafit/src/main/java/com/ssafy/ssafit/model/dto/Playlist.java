package com.ssafy.ssafit.model.dto;

public class Playlist {
	private long id;
	private long userId;
	private String title;
	private String startDate;
	private String endDate;
	private String createdAt;

	public Playlist() {
	}

	public Playlist(long id, long userId, String title, String startDate, String endDate, String createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdAt = createdAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "PlayList [id=" + id + ", userId=" + userId + ", title=" + title + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", createdAt=" + createdAt + "]";
	}

}
