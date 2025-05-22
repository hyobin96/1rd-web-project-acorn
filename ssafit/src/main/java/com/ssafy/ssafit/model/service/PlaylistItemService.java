package com.ssafy.ssafit.model.service;

import java.util.List;
import com.ssafy.ssafit.model.dto.PlaylistItem;

/**
 * playlistItem을 담당하는 서비스
 * item을 얻고 추가하고 삭제하는 기능 제공
 */
public interface PlaylistItemService {
	/**
	 * playlistId와 일치하는 아아템들을 가져온다.
	 * @param playlistId
	 * @return List<PlaylistItem>
	 */
    List<PlaylistItem> getItems(Long playlistId);
    
    /**
     * item을 playlist_items 테이블에 추가한다.
     * @param items
     */
    void addItem(List<PlaylistItem> items);
    
    /**
     * id가 일치하는 item을 삭제한다.
     * @param id
     */
    void deleteItem(Long id);
}