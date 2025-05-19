package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Playlist;

/**
 * playlists에 접근하는 dao
 * playlist 삭제, 삽입, 선택을 제공합니다.
 */
public interface PlaylistDao {
	/**
	 * id를 받아서 해당하는 playlist를 delete
	 * @param id
	 * @return 변경된 행의 개수
	 */
	int deletePlaylistById(int id);
	
	/**
	 * playList를 playlists에 삽입
	 * @param playList
	 * @return 변경된 행의 개수
	 */
	int insertPlaylist(Playlist playList);
	
	/**
	 * id를 받아서 해당하는 playlist를 선택해서 dto에 담아서 반환
	 * @param id
	 * @return PlayList가 들어간 Optional 객체를 반환
	 */
	List<Playlist> selectPlaylist(int id);
}
