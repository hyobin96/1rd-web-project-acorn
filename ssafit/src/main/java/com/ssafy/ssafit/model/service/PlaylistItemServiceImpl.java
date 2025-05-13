package com.ssafy.ssafit.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.ssafit.model.dao.PlaylistItemDao;
import com.ssafy.ssafit.model.dto.PlaylistItem;
import com.ssafy.ssafit.model.exception.PlaylistItemNotInsertedException;
import com.ssafy.ssafit.model.exception.PlaylistItemNotDeletedException;

/**
 * playlistItem에 관한 서비스를 제공하는 구현체
 * 아이템 가져오기, 추가, 삭제를 제공
 */
@Service
public class PlaylistItemServiceImpl implements PlaylistItemService {

    @Autowired
    private PlaylistItemDao dao;

    /**
     * playlistId가 일치하는 item들을 가져온다.
     */
    public List<PlaylistItem> getItems(Long playlistId) {
        return dao.selectByPlaylistId(playlistId);
    }

    /**
     * item을 playlist_items 테이블에 추가한다.
     * 만약 추가가 실패한다면 {@link PlaylistItemNotInsertedException}를 던진다.
     */
    public void addItem(PlaylistItem item) {
        if (dao.insert(item) == 0) {
            throw new PlaylistItemNotInsertedException("플레이리스트 아이템 추가 실패");
        }
    }

    /**
     * id가 일치하는 item을 삭제한다.
     * 만약 삭제가 실패한다면 {@link PlaylistItemNotDeletedException}를 던진다.
     */
    public void deleteItem(Long id) {
        if (dao.delete(id) == 0) {
            throw new PlaylistItemNotDeletedException("플레이리스트 아이템 삭제 실패");
        }
    }
}