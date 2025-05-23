DROP DATABASE IF EXISTS ssafit;

CREATE DATABASE IF NOT EXISTS ssafit;

USE ssafit;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    nickname VARCHAR(50) NOT NULL UNIQUE,
    is_admin BOOLEAN DEFAULT FALSE, 
    is_deleted BOOLEAN DEFAULT FALSE, -- 탈퇴 회원 관리용
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS playlists;

CREATE TABLE playlists (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(100),
    start_date DATE, -- 운동 시작일
    end_date DATE, -- 운동 종료일
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS playlist_items;

CREATE TABLE playlist_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    playlist_id BIGINT NOT NULL,
    video_id VARCHAR(20) NOT NULL, -- 동영상의 고유 식별자
    thumbnails VARCHAR(100) NOT NULL,
    memo TEXT, 
    FOREIGN KEY (playlist_id) REFERENCES playlists(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS workout_memos;

CREATE TABLE workout_memos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    content TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS workout_logs;

CREATE TABLE workout_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    playlist_id BIGINT,
    total_time_seconds INT, -- 동영상 총 길이 
    completed BOOLEAN DEFAULT FALSE,
    shared_link TEXT, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS community_posts;

CREATE TABLE community_posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(200),
    content TEXT,
    type ENUM('정보공유', '공지', '기타') DEFAULT '정보공유',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS event_posts;

CREATE TABLE event_posts (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '게시글 고유 ID',
  title VARCHAR(200) NOT NULL COMMENT '게시글 제목',
  content TEXT COMMENT '게시글 내용',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시각',
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각',
  is_deleted BOOLEAN DEFAULT FALSE COMMENT '삭제 여부 (소프트 삭제용)',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS event_files;

CREATE TABLE event_files (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '파일 고유 ID',
  post_id BIGINT NOT NULL COMMENT '첨부된 게시글 ID',
  original_name VARCHAR(255) COMMENT '사용자가 업로드한 원래 이름',
  stored_name VARCHAR(255) COMMENT '서버에 저장된 파일 이름 (UUID 포함)',
  file_path VARCHAR(500) COMMENT '서버 내 저장 경로',
  uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '업로드 시각',
  PRIMARY KEY (id),
  FOREIGN KEY (post_id) REFERENCES event_posts(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


DROP TABLE IF EXISTS shopping_posts;

CREATE TABLE shopping_posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200),
    content TEXT,
    category ENUM('용품', '식품') DEFAULT '용품',
    is_cash_only BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

use ssafit;

ALTER TABLE users
ADD COLUMN profile_image VARCHAR(500) COMMENT '유저 프로필 이미지 경로 또는 파일명';

ALTER TABLE users
ADD COLUMN gender ENUM('M', 'F', 'O') DEFAULT NULL COMMENT '성별(M:남성, F:여성, O:선택하지 않음)',
ADD COLUMN birth_date DATE DEFAULT NULL COMMENT '생년월일';
