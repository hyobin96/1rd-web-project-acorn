package com.ssafy.ssafit.model.dao;

import java.util.List;
import com.ssafy.ssafit.model.dto.PlaylistItem;


/**
 * playlist-items에 접근하는 dao
 * playlist-item 선택, 삽입, 삭제를 제공합니다.
 */
public interface PlaylistItemDao {
	/**
	 * playlistId가 일치하는 playlistItem을 조회해 리스트로 반환합니다.
	 * @param playlistId
	 * @return List<PlaylistItem>
	 */
    List<PlaylistItem> selectByPlaylistId(Long playlistId);
    
    /**
     * playlist-items에 item을 삽입합니다.
     * @param items
     * @return 삽입한 행의 개수를 반환합니다.
     */
    int insert(List<PlaylistItem> items);
    
    /**
     * playlist-items에서 id가 일치하는 item을 삭제합니다.
     * @param id
     * @return 삭제한 행의 개수를 반환합니다.
     */
    int delete(Long id);
}