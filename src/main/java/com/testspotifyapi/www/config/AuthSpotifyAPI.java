package com.testspotifyapi.www.config;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.apache.hc.core5.http.ParseException;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Configuration
public class AuthSpotifyAPI {
	
  private static final String clientId = "7018fc47d1cb49bba23cee1e5c702d60";
  
  private static final String clientSecret = "7c1006ea22e04ce0888c4bb616a68537";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setClientId(clientId)
    .setClientSecret(clientSecret)
    .build();
  
  private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
    .build();

  public static String accessToken() {
    try {
      final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

      // Set access token for further "spotifyApi" object usage
      spotifyApi.setAccessToken(clientCredentials.getAccessToken());
      System.out.println("Expires in: " + clientCredentials.getExpiresIn());
      System.out.println("spotifyApi.getAccessToken(): " + spotifyApi.getAccessToken());
      return spotifyApi.getAccessToken();
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
      return "error";
    }
  }
}
