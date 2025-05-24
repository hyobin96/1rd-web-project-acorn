package com.ssafy.ssafit.model.dto;

import java.util.List;

/**
 * 이벤트 게시판 게시글 정보를 담는 DTO
 */
public class EventPost {
	private Long id;
	private String title;
	private String content; // Optional, null 가능
	private String startDate; //행사 시작일
	private String endDate; //행사 종료일
	private String createdAt; // DB의 TIMESTAMP -> String으로 조회
	private String updatedAt;
	private List<EventFile> fileList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<EventFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<EventFile> fileList) {
		this.fileList = fileList;
	}

	@Override
	public String toString() {
		return "EventPost [id=" + id + ", title=" + title + ", content=" + content + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", fileList="
				+ fileList + "]";
	}

}
