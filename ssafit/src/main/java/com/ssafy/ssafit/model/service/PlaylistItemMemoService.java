package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.PlaylistItemMemo;

public interface PlaylistItemMemoService {
	
	/**
	 * playlistItemsId가 일치하는 메모들 모두 리스트에 담아 반환
	 * @param playlistItemsId
	 * @return memo객체들이 담긴 리스트
	 */
	public List<PlaylistItemMemo> getMemos(long playlistItemsId);
	
	/**
	 * memo 새로 등록
	 * @param memo
	 * @return 등록이 되었으면 true 반환
	 */
	public boolean registMemo(PlaylistItemMemo memo);
	
	/**
	 * memo에 담긴 id가 일치하는 memo 업데이트
	 * @param memo
	 * @return 수정이 되었으면 true 반환
	 */
	public boolean modifyMemo(PlaylistItemMemo memo);
	
	/**
	 * id가 일치하는 memo 삭제
	 * @param id
	 * @return 삭제가 되었으면 true 반환
	 */
	public boolean removeMemo(long id);
}
