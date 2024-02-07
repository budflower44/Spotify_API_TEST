<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp"></jsp:include>

<h1>Token</h1>

<div class="div1" id="div1">
    <c:out value="${token}" />
</div>

<h1>Spotify Web Playback SDK Quick Start</h1>
    <button id="togglePlay">Toggle Play</button>
<input type="text" id="tokenText">

    <script src="https://sdk.scdn.co/spotify-player.js"></script>
	<script src="/resources/js/spotifyAPI_test3.js"></script>
     <script>
     window.onSpotifyWebPlaybackSDKReady = () => {
    	    getTokenToServer().then(result =>{
    	    	const token = document.getElementById('tokenText').value;
    	    	console.log("result >> "+token);
    	        const player = new Spotify.Player({
    	            name: 'Web Playback SDK Quick Start Player',
    	            getOAuthToken: cb => { cb(token); },
    	            volume: 0.5
    	        });

    	        // Ready
    	        player.addListener('ready', ({ device_id }) => {
    	            console.log('Ready with Device ID', device_id);
    	        });

    	        // Not Ready
    	        player.addListener('not_ready', ({ device_id }) => {
    	            console.log('Device ID has gone offline', device_id);
    	        });

    	        player.addListener('player_state_changed', (state) => {
    	            if(!state){
    	                return;
    	            }
    	            console.log("state changed", state);
    	        })
    	          player.addListener('initialization_error', ({ message }) => {
    	            console.error(message);
    	        });

    	        player.addListener('authentication_error', ({ message }) => {
    	            console.error(message);
    	        });

    	        player.addListener('account_error', ({ message }) => {
    	            console.error(message);
    	        });

    	        document.getElementById('togglePlay').onclick = function() {
    	          player.togglePlay();
    	        };
    	        player.connect();
    	    }
    	)}
    </script> 


<!-- <script src="https://sdk.scdn.co/spotify-player.js"></script> -->



<jsp:include page="../layout/footer.jsp"></jsp:include>