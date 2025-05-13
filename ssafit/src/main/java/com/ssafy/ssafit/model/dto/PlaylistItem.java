package com.ssafy.ssafit.model.dto;

public class PlaylistItem {
    private Long id;
    private Long playlistId;
    private String videoId;
    private String memo;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPlaylistId() { return playlistId; }
    public void setPlaylistId(Long playlistId) { this.playlistId = playlistId; }

    public String getVideoId() { return videoId; }
    public void setVideoId(String videoId) { this.videoId = videoId; }

    public String getMemo() { return memo; }
    public void setMemo(String memo) { this.memo = memo; }
} 