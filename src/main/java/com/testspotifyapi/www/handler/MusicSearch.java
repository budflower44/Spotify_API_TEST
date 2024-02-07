package com.testspotifyapi.www.handler;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.testspotifyapi.www.config.AuthSpotifyAPI;

import se.michaelthelin.spotify.SpotifyApi;

public class MusicSearch {
	
	private String clientId = "7018fc47d1cb49bba23cee1e5c702d60";

	private String clientSecret = "7c1006ea22e04ce0888c4bb616a68537";

	private String redirectUri = "http://localhost:8089/callback";

	private SpotifyApi spotifyApi;
	
	private AuthSpotifyAPI authSpotifyAPI;
	
	private MusicSearch ms;
	
	public void initialize() {
		// SpotifyApi 객체를 생성하고 클라이언트 ID, 리디렉션 URI를 설정합니다.
		spotifyApi = new SpotifyApi.Builder()
				.setClientId(clientId)
				.setClientSecret(clientSecret)
				.setRedirectUri(URI.create(redirectUri))
				.build();
	}
	
    public String search(String accessToken, String q){ //q는 검색어

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);;
        headers.add("Host", "api.spotify.com");
        headers.add("Content-type", "application/json");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://api.spotify.com/v1/search?type=track&q=" + q, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value(); //상태 코드가 들어갈 status 변수
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);

        return response;
    }
}