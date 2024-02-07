<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>get Tracks</h1>
	
		<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">#</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<th>${bvo}</th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<!-- <script src="/resources/js/spotifyAPI_test2.js"></script>
 -->	
	<%
        String playlistId = "30VMGPBFuRbpy2zFPllOwB";
    %>

<script src="https://open.spotify.com/embed/iframe-api/v1" async></script>

<div id="embed-iframe"></div>

<script>
window.onSpotifyIframeApiReady = (IFrameAPI) => {
	  const element = document.getElementById('embed-iframe');
	  const options = {
	      uri: 'spotify:episode:7makk4oTQel546B0PZlDM5'
	    };
	  const callback = (EmbedController) => {};
	  IFrameAPI.createController(element, options, callback);
	};
</script>

<script>
function openWindowPop(url, name){
    var options = 'top=10, left=10, width=500, height=600, status=no, menubar=no, toolbar=no, resizable=no';
    window.open(url, name, options);
}
</script>

<a href="javascript:openWindowPop('https://open.spotify.com/track/3nqQXoyQOWXiESFLlDF1hG', 'Play');">팝업창 열기</a>

<iframe style="border-radius:12px" src="https://open.spotify.com/embed/artist/6VuMaDnrHyPL1p4EHjYLi7?utm_source=generator" width="100%" height="352" frameBorder="0" allowfullscreen="" allow="autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture" loading="lazy"></iframe>

<iframe style="border-radius:12px" 
	src="https://open.spotify.com/embed/track/3nqQXoyQOWXiESFLlDF1hG?utm_source=generator" 
	width="100%" height="352" frameBorder="0" allowfullscreen="" allow="autoplay; 
	clipboard-write; encrypted-media; fullscreen; picture-in-picture" loading="lazy" >
	</iframe>

<script>
console.log("test2 in~");

var playlistId = "<%= playlistId %>";

/* https://open.spotify.com/embed/[artist]/[137W8MRPWKqSmrBGDBFSop]?utm_source=generator */

document.body.innerHTML += `
	<iframe style="border-radius:12px" 
	src="https://open.spotify.com/embed/artist/137W8MRPWKqSmrBGDBFSop?utm_source=generator" 
	width="80%" height="352" frameBorder="0" allowfullscreen="" allow="autoplay; 
	clipboard-write; encrypted-media; fullscreen; picture-in-picture" loading="lazy" >
	</iframe>
`;
</script>

</body>
</html>