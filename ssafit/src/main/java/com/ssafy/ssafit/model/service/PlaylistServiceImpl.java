package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.PlaylistDao;
import com.ssafy.ssafit.model.dto.Playlist;
import com.ssafy.ssafit.model.exception.PlaylistNotDeletedException;
import com.ssafy.ssafit.model.exception.PlaylistNotInsertedException;
import com.ssafy.ssafit.model.exception.PlaylistNotSelectedException;

/**
 * playlist에 관한 서비스를 담당
 */
@Service
public class PlaylistServiceImpl implements PlaylistService {
	@Autowired
	private PlaylistDao playlistDao;

	/**
	 * id를 받아서 일치하는 playlist 삭제
	 * 삭제가 되지 않을 경우 {@link PlaylistNotDeletedException} 던지기
	 */
	@Override
	public void deletePlaylistById(int id) {
		if(playlistDao.deletePlaylistById(id) == 0) {
			throw new PlaylistNotDeletedException("playlist가 삭제되지 않았습니다.");
		}
	}

	/**
	 * playlist를 playlists 테이블에 새로 만들기
	 * 생성이 되지 않았다면 {@link PlaylistNotInsertedException} 던지기
	 */
	@Override
	public void createPlaylist(Playlist playlist) {
		System.out.println("service 도착");
		if(playlistDao.insertPlaylist(playlist) == 0) {
			throw new PlaylistNotInsertedException("playlist가 생성되지 않았습니다.");
		}
	}

	/**
	 * id가 일치하는 playlist 가져오기
	 * id와 일치하는 playlist가 없다면 {@link PlaylistNotSelectedException} 던지기
	 * playlist가 존재한다면 반환
	 */
	@Override
	public List<Playlist> getPlaylist(int id) {
		List<Playlist> playlist = playlistDao.selectPlaylist(id);
		if(playlist.isEmpty()) {
			throw new PlaylistNotSelectedException("playlist가 선택되지 않았습니다.");
		}
		return playlist;
	}

}
