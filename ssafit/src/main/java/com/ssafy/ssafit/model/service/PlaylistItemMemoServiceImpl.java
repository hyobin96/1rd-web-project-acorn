package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.PlaylistItemMemoDao;
import com.ssafy.ssafit.model.dto.PlaylistItemMemo;

@Service
public class PlaylistItemMemoServiceImpl implements PlaylistItemMemoService{
	@Autowired
	private PlaylistItemMemoDao memoDao;
	
	/**
	 * playlistItemId 가 일치하는 memo들을 리스트에 담아 반환
	 */
	@Override
	public List<PlaylistItemMemo> getMemos(long playlistItemsId) {
		return memoDao.selectAllMemo(playlistItemsId);
	}

	/**
	 * 삽입이 성공했으면 true 반환
	 */
	@Override
	public boolean registMemo(PlaylistItemMemo memo) {
		return memoDao.insertMemo(memo) == 1;
	}

	/**
	 * 수정이 성공했으면 true 반환
	 */
	@Override
	public boolean modifyMemo(PlaylistItemMemo memo) {
		return memoDao.updateMemo(memo) == 1;
	}

	/**
	 * 삭제가 성공했으면 true 반환
	 */
	@Override
	public boolean removeMemo(long id) {
		return memoDao.deleteMemo(id) == 1;
	}

}
