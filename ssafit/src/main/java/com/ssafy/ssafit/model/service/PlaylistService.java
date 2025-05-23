package com.ssafy.ssafit.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.dto.LoadingPlaylist;
import com.ssafy.ssafit.model.dto.Playlist;

public interface PlaylistService {
	
	/**
	 * playlists에서 id가 일치하는 playlist를 db에서 삭제한다.
	 * 삭제가 되지 않는다면 에러를 던진다.
	 * @param id
	 */
	void deletePlaylistById(long id);
	
	/**
	 * 새로운 playlist를 만든다
	 * 생성되지 않는다면 에러를 발생한다
	 * @param playlist
	 */
	void createPlaylist(Playlist playlist);
	
	/**
	 * id가 일치하는 playlist를 가져온다.
	 * 일치하는 playlist가 없다면 에러를 발생한다.
	 */
	Map<Long, List<LoadingPlaylist>> getPlaylist();
	
}
