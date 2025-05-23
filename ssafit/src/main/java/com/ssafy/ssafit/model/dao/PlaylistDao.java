package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.LoadingPlaylist;
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
	int deletePlaylistById(long id);
	
	/**
	 * playList를 playlists에 삽입
	 * @param playList
	 * @return 변경된 행의 개수
	 */
	int insertPlaylist(Playlist playList);
	
	/**
	 * id를 받아서 해당하는 playlist, item을 선택해서 dto에 담아서 반환
	 * 유저의 플레이리스트와 아이템들을 로딩하는데 사용
	 * @param id
	 * @return 
	 */
	List<LoadingPlaylist> selectPlaylist(long id);

}
