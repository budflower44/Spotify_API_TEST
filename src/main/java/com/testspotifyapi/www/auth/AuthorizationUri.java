package com.testspotifyapi.www.auth;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.net.URI;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class AuthorizationUri {
  private static final String clientId = "7018fc47d1cb49bba23cee1e5c702d60";
  private static final String clientSecret = "7c1006ea22e04ce0888c4bb616a68537";
  private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8089/callback");

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setClientId(clientId)
    .setClientSecret(clientSecret)
    .setRedirectUri(redirectUri)
    .build();
  private static final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
//          .state("x4xkmn9pu3j6ukrs8n")
//          .scope("user-read-birthdate,user-read-email")
//          .show_dialog(true)
    .build();

  public static void authorizationCodeUri_Sync() {
    final URI uri = authorizationCodeUriRequest.execute();

    System.out.println("URI: " + uri.toString());
  }

  public static void authorizationCodeUri_Async() {
    try {
      final CompletableFuture<URI> uriFuture = authorizationCodeUriRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final URI uri = uriFuture.join();

      System.out.println("URI: " + uri.toString());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    authorizationCodeUri_Sync();
    authorizationCodeUri_Async();
    
  }
}