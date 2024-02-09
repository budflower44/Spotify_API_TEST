package com.testspotifyapi.www.controller;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.testspotifyapi.www.albums.AlbumsVO;
import com.testspotifyapi.www.auth.Authorization;
import com.testspotifyapi.www.auth.AuthorizationRefresh;
import com.testspotifyapi.www.auth.AuthorizationUri;
import com.testspotifyapi.www.config.AuthSpotifyAPI;
import com.testspotifyapi.www.domain.ArtistsVO;
import com.testspotifyapi.www.handler.MusicSearch;
import com.testspotifyapi.www.service.SpotifyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import se.michaelthelin.spotify.SpotifyApi;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/spotify/**")
@Controller
public class SpotifyApiController {
	
	private final Logger log = LoggerFactory.getLogger(SpotifyApiController.class);
	
	@Autowired
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
	        JsonNode tracksNode = objectMapper.readTree(search(accessToken, searchQuery))
	        		.get("tracks")
	        		.get("items");

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
        ResponseEntity<String> responseEntity = 
        		rest.exchange("https://api.spotify.com/v1/search?type=track&q=" + q, 
        				HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value(); //상태 코드가 들어갈 status 변수
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);

        return response;
    }

	@GetMapping("/getSearch")
	public void searchPage() {}
	
	@PostMapping("/search/albums")
	public String searchData(Model m, @RequestBody String albumsData) {
		System.out.print("in");
		log.info("log in");
		System.out.print("@@@@@@@@@@@@@@@@ albumsData >> "+albumsData);

		// Jackson ObjectMapper 생성
        ObjectMapper objectMapper = new ObjectMapper();
        
        try {
            // JSON 문자열을 Java 객체로 파싱
        	Map<String, Object> albumsDataJsonMap = objectMapper.readValue(albumsData, new TypeReference<Map<String, Object>>() {});
                    
           List<Map<String, Object>> alVOList = (List<Map<String, Object>>) albumsDataJsonMap.get("items");
           
           for (Map<String, Object> alVOMap : alVOList) {
               // 각 오브젝트에서 필요한 값 가져오기
               String id = (String) alVOMap.get("id");
               String albumType = (String) alVOMap.get("album_type");
               
               //오브젝트 안의 오브젝트 값 가져오기
               Object externalUrlsObj = (Object) alVOMap.get("external_urls");
               Map<String, String> externalUrlsMap = (Map<String, String>) externalUrlsObj;
               String externalUrls = externalUrlsMap.get("spotify");
               
               // 배열 안의 오브젝트 가져오기
               List<Object> artistsObjList = new ArrayList<Object> ((List<Object>) alVOMap.get("artists"));
               String artistsExternalUrls = null;
               String artistsHref = null;
               String artistsId = null;
               String artistsName = null;
               String artistsType = null;
               String artistsUri = null;
               for(Object artistObj : artistsObjList) {
            	   // 배열 안의 오브젝트 안의 오브젝트 가져오기
            	   Map<String, Object> artistMap = (Map<String, Object>) artistObj;
            	    // "external_urls" 오브젝트 가져오기
            	    Map<String, String> artistsExternalUrlsMap = (Map<String, String>) artistMap.get("external_urls");
            	    // "spotify" 값 가져오기
            	    artistsExternalUrls = artistsExternalUrlsMap.get("spotify");
            	    artistsHref = (String) artistMap.get("href");
            	    artistsId = (String) artistMap.get("id");
            	    artistsName = (String) artistMap.get("name");
            	    artistsType = (String) artistMap.get("type");
            	    artistsUri = (String) artistMap.get("uri");
               }
               
               
               //오브젝트 안의 배열 안의 오브젝트 값 가져오기
               List<Object> imagesObjList = new ArrayList<Object> ((List<Object>) alVOMap.get("images"));
               int imagesHeight = 0;
               int imagesWidth = 0;
               String imagesUrl = null;
               for(Object imagesObj : imagesObjList) {
            	   Map<String, Object> imagesMap = (Map<String, Object>) imagesObj;
            	    // height와 width 값 가져오기
            	    imagesHeight = (int) imagesMap.get("height");
            	    imagesWidth = (int) imagesMap.get("width");
            	    // height와 width가 640이면 데이터 출력
            	    if (imagesHeight == 640 && imagesWidth == 640) {
            	        imagesUrl = (String) imagesMap.get("url");
            	        // 가져온 데이터 출력
            	        
            	    }
               }
               String href = (String) alVOMap.get("href");
               String name = (String) alVOMap.get("name");
               String releaseDate = (String) alVOMap.get("release_date");
               String releaseDatePrecision = (String) alVOMap.get("release_date_precision");
               int totalTracks = (int) alVOMap.get("total_tracks");
               String type = (String) alVOMap.get("type");
               String uri = (String) alVOMap.get("uri");

               
               // 가져온 데이터 출력 확인용
               System.out.println("ID: " + id);
               System.out.println("external_urls: " + externalUrls);
               System.out.println("album_type: " + albumType);
               System.out.println("artistsExternalUrls: " + artistsExternalUrls);
               System.out.println("artistsHref: " + artistsHref);
               System.out.println("artistsId: " + artistsId);
               System.out.println("artistsName: " + artistsName);
               System.out.println("artistsType: " + artistsType);
               System.out.println("artistsUri: " + artistsUri);
               System.out.println("imagesUrl: " + imagesUrl);
   	           System.out.println("imagesHeight: " + imagesHeight);
   	           System.out.println("imagesWidth: " + imagesWidth);
               System.out.println("href: " + href);
               System.out.println("name: " + name);
               System.out.println("releaseDate: " + releaseDate);
               System.out.println("releaseDatePrecision: " + releaseDatePrecision);
               System.out.println("total_tracks: " + totalTracks);
               System.out.println("type: " + type);
               System.out.println("uri: " + uri);
               
               AlbumsVO albumsVO = new AlbumsVO(id, externalUrls, albumType, artistsExternalUrls, 
            		   artistsHref, artistsId, artistsName, artistsType, artistsUri, href, imagesUrl, 
            		   imagesHeight, imagesWidth, name, releaseDate, releaseDatePrecision, totalTracks, 
            		   type, uri);
               
               System.out.println(albumsVO);
              
               if(ssv != null) {
            	   ssv.insertDBAlbums(albumsVO);            	   
               }
               
           }
           }catch (Exception e) {
			// TODO: handle exception
        	   e.printStackTrace();
		}
	
		return "Data received successfully";
	} 
	
	
	
	
}
