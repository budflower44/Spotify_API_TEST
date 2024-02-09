package com.testspotifyapi.www.domain;

import com.testspotifyapi.www.albums.AlbumsVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SearchDTO {
	
	private AlbumsVO albumsVO;
	private ArtistsVO artistsVO;
	private TracksVO tracksVO;
	
}
