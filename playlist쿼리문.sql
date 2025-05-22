SELECT 
    p.id AS playlist_id,
    p.title AS playlist_title,
    pi.id AS item_id,
    pi.video_id,
    pi.thumbnails
FROM playlists p
LEFT JOIN playlist_items pi ON p.id = pi.playlist_id
WHERE p.user_id = 1 -- 또는 p.user_id = 1 와 같이 직접 ID 지정
ORDER BY pi.id ASC;