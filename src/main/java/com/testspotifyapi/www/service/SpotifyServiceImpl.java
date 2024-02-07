package com.testspotifyapi.www.service;

import org.springframework.stereotype.Service;

import com.testspotifyapi.www.repository.SpotifyDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class SpotifyServiceImpl implements SpotifyService {
	
	private SpotifyDAO sdao;
	
}
