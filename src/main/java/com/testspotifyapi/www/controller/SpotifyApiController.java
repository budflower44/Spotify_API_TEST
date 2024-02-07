package com.testspotifyapi.www.controller;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testspotifyapi.www.auth.Authorization;
import com.testspotifyapi.www.auth.AuthorizationRefresh;
import com.testspotifyapi.www.auth.AuthorizationUri;
import com.testspotifyapi.www.config.AuthSpotifyAPI;
import com.testspotifyapi.www.handler.MusicSearch;
import com.testspotifyapi.www.service.SpotifyService;

import se.michaelthelin.spotify.SpotifyApi;

@RequestMapping("/spotify/**")
@Controller
public class SpotifyApiController {
	
	private final Logger log = LoggerFactory.getLogger(SpotifyApiController.class);
	
	private SpotifyService ssv;
	
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
	
//	@GetMapping("/musicPlayer")
//	public ResponseEntity<String> musicPlayer() {
//		
//		
//		return "";
//	}
	
	@GetMapping("/getToken")
	public void tokenPage() {}
	
	@PostMapping("/token")
	public ResponseEntity<String> getAuthSpotifyApiToken(Model m) {
	    String accessToken = AuthSpotifyAPI.accessToken().toString();
	    log.info(accessToken);
	    return new ResponseEntity<String>(accessToken, HttpStatus.OK);
	}

	@GetMapping("/getTracksLogs")
	public void getTracksLogs(Model m) {
	    String accessToken = AuthSpotifyAPI.accessToken();
	    log.info("accessToken : "+accessToken);
	    String searchQuery = "Charlie Puth";

	    log.info(search(accessToken, searchQuery));

	    List<String> resultList = new ArrayList<>();

	    try {
	        // JSON 파싱
	        ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode tracksNode = objectMapper.readTree(search(accessToken, searchQuery)).get("tracks").get("items");

	        for (JsonNode trackNode : tracksNode) {
	            resultList.add(trackNode.get("name").toString());
	        }

	        m.addAttribute("list", resultList);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
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
