package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.PlaylistItemMemo;

public interface PlaylistItemMemoDao {
	
	public List<PlaylistItemMemo> selectAllMemo(long playlistItemsId);
	
	public int insertMemo(PlaylistItemMemo memo);
	
	public int updateMemo(PlaylistItemMemo memo);
	
	public int deleteMemo(long id);
}
