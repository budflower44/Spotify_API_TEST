package com.testspotifyapi.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testspotifyapi.www.albums.AlbumsVO;
import com.testspotifyapi.www.repository.SpotifyDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class SpotifyServiceImpl implements SpotifyService {
	
	@Autowired
	private SpotifyDAO sdao;

	@Override
	public void insertDBAlbums(AlbumsVO albumsVO) {
		System.out.println("serviceImpl in");
		// TODO Auto-generated method stub
		try {
			 if (sdao != null) {
		            String isExistingAlbums = sdao.compareToDBDataAlbums(albumsVO.getId());
		            System.out.println("비교 완료 : "+isExistingAlbums);
		            if(isExistingAlbums == null) {
		                sdao.insertDBAlbums(albumsVO);
		            } else {
		                System.out.println("DB 넣기 실패");
		            }
		        } else {
		            System.out.println("sdao 객체가 null입니다.");
		        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
