package com.ssafy.ssafit.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.PlaylistDao;
import com.ssafy.ssafit.model.dao.UserDao;
import com.ssafy.ssafit.model.dto.LoadingPlaylist;
import com.ssafy.ssafit.model.dto.Playlist;
import com.ssafy.ssafit.model.dto.User;
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

	@Autowired
	private UserDao userDao;

	/**
	 * id를 받아서 일치하는 playlist 삭제 삭제가 되지 않을 경우 {@link PlaylistNotDeletedException} 던지기
	 */
	@Override
	public void deletePlaylistById(long id) {
		if (playlistDao.deletePlaylistById(id) == 0) {
			throw new PlaylistNotDeletedException("playlist가 삭제되지 않았습니다.");
		}
	}

	/**
	 * playlist를 playlists 테이블에 새로 만들기 생성이 되지 않았다면
	 * {@link PlaylistNotInsertedException} 던지기
	 */
	@Override
	public void createPlaylist(Playlist playlist) {
		System.out.println("service 도착");
		if (playlistDao.insertPlaylist(playlist) == 0) {
			throw new PlaylistNotInsertedException("playlist가 생성되지 않았습니다.");
		}
	}

	/**
	 * id가 일치하는 playlist 가져오기 id와 일치하는 playlist가 없다면
	 * {@link PlaylistNotSelectedException} 던지기 playlist가 존재한다면 반환
	 */
	@Override
	public Map<Long, List<LoadingPlaylist>> getPlaylist() {
		System.out.println("getPlaylist service까지는 도착");
		// 토큰을 등록한 SecurityContext에서 username 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication);
		System.out.println(authentication.getPrincipal());
		String username = authentication.getPrincipal().toString();
		System.out.println(username);

		// users 테이블에서 username이 일치하는 user 가져오기
		User user = userDao.selectUser(username).get();
		// userId가 일치하는 playlist중에서 playlistId가 일치하는 item들을 가져옵니다.
		List<LoadingPlaylist> playlist = playlistDao.selectPlaylist(user.getId());
		if (playlist.isEmpty()) {
			throw new PlaylistNotSelectedException("playlist가 선택되지 않았습니다.");
		}
		
		Map<Long, List<LoadingPlaylist>> map= new HashMap<>();
		for (LoadingPlaylist lp : playlist) {
			long id = lp.getPlaylistId();
			if(map.containsKey(id)) {
				map.get(id).add(lp);
			}
			else {
				List<LoadingPlaylist> list = new ArrayList<>();
				list.add(lp);
				map.put(id, list);
			}
		}
		
		return map;
	}

}
