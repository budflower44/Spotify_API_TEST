package com.testspotifyapi.www.repository;

import com.testspotifyapi.www.albums.AlbumsVO;

public interface SpotifyDAO {

	String compareToDBDataAlbums(String id);

	void insertDBAlbums(AlbumsVO albumsVO);

}
