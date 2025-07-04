package com.ssafy.ssafit.model.dto;

/**
 * 이벤트 게시글에 첨부된 파일 정보를 담는 DTO
 */
public class EventFile {
    private Long id;
    private Long postId;
    private String originalName;
    private String storedName;
    private String filePath;
    private String uploadedAt;
    private String fileType; // 썸네일인지 컨텐츠이미지인지 역할 구분위함
    private boolean isThumbnail; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getStoredName() {
        return storedName;
    }

    public void setStoredName(String storedName) {
        this.storedName = storedName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(String uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public boolean isThumbnail() {
		return isThumbnail;
	}

	public void setThumbnail(boolean isThumbnail) {
		this.isThumbnail = isThumbnail;
	}

	@Override
	public String toString() {
		return "EventFile [id=" + id + ", postId=" + postId + ", originalName=" + originalName + ", storedName="
				+ storedName + ", filePath=" + filePath + ", uploadedAt=" + uploadedAt + ", fileType=" + fileType
				+ ", isThumbnail=" + isThumbnail + "]";
	}
	
}
