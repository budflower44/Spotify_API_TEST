console.log("spotify Auth ajax test in");

const tokenText = document.getElementById('tokenText');

async function getTokenToServer(){
    try {
        const url = "/spotify/token";
        const config = {
            method:'post'
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        console.log("getTokenToServer result >> "+result);
        tokenText.value = result;
        return result;
    } catch (error) {
        console.log(error);
    }
}

// getTokenToServer().then(result =>{
//     window.onSpotifyWebPlaybackSDKReady = () => {
//         const player = new Spotify.Player({
//             name: 'Web Playback SDK Quick Start Player',
//             getOAuthToken: cb => { cb(result); },
//             volume: 0.5
//         });

//         // Ready
//         player.addListener('ready', ({ device_id }) => {
//             console.log('Ready with Device ID', device_id);
//         });

//         // Not Ready
//         player.addListener('not_ready', ({ device_id }) => {
//             console.log('Device ID has gone offline', device_id);
//         });

//         player.addListener('player_state_changed', (state) => {
//             if(!state){
//                 return;
//             }
//             console.log("state changed", state);
//         })
//           player.addListener('initialization_error', ({ message }) => {
//             console.error(message);
//         });

//         player.addListener('authentication_error', ({ message }) => {
//             console.error(message);
//         });

//         player.addListener('account_error', ({ message }) => {
//             console.error(message);
//         });

//         document.getElementById('togglePlay').onclick = function() {
//           player.togglePlay();
//         };
//         player.connect();
//     }
// })


// window.onSpotifyWebPlaybackSDKReady = () => {
//     getTokenToServer().then(result =>{
//         const player = new Spotify.Player({
//             name: 'Web Playback SDK Quick Start Player',
//             getOAuthToken: cb => { cb(result); },
//             volume: 0.5
//         });

//         // Ready
//         player.addListener('ready', ({ device_id }) => {
//             console.log('Ready with Device ID', device_id);
//         });

//         // Not Ready
//         player.addListener('not_ready', ({ device_id }) => {
//             console.log('Device ID has gone offline', device_id);
//         });

//         player.addListener('player_state_changed', (state) => {
//             if(!state){
//                 return;
//             }
//             console.log("state changed", state);
//         })
//           player.addListener('initialization_error', ({ message }) => {
//             console.error(message);
//         });

//         player.addListener('authentication_error', ({ message }) => {
//             console.error(message);
//         });

//         player.addListener('account_error', ({ message }) => {
//             console.error(message);
//         });

//         document.getElementById('togglePlay').onclick = function() {
//           player.togglePlay();
//         };
//         player.connect();
//     }
// )}

// async function initializeSpotifyPlayer() {
//     try {
//         // 토큰을 가져오는 비동기 함수 호출
//         const token = await getTokenToServer();

//         // 토큰을 사용하여 Spotify 플레이어 초기화
//         const player = new Spotify.Player({
//             name: 'Web Playback SDK Quick Start Player',
//             getOAuthToken: cb => { cb(token); },
//             volume: 0.5
//         });

//         // 플레이어 이벤트 리스너 등록
//         player.addListener('ready', ({ device_id }) => {
//             console.log('Ready with Device ID', device_id);
//         });

//         player.addListener('not_ready', ({ device_id }) => {
//             console.log('Device ID has gone offline', device_id);
//         });

//         player.addListener('player_state_changed', (state) => {
//             if (!state) {
//                 return;
//             }
//             console.log("state changed", state);
//         });

//         player.addListener('initialization_error', ({ message }) => {
//             console.error(message);
//         });

//         player.addListener('authentication_error', ({ message }) => {
//             console.error(message);
//         });

//         player.addListener('account_error', ({ message }) => {
//             console.error(message);
//         });

//         document.getElementById('togglePlay').onclick = function() {
//             player.togglePlay();
//         };

//         // Spotify 플레이어 연결
//         player.connect();
//     } catch (error) {
//         console.error("Error initializing Spotify player:", error);
//     }
// }

// 기능 실행 시점에서 플레이어 초기화 함수 호출
initializeSpotifyPlayer();

//https://api.spotify.com/v1/artists/4Z8W4fKeB5YxbusRsdQVPb

async function getArtists2(){
    return (await fetchWebApi(
        'v1/artists/4Z8W4fKeB5YxbusRsdQVPb', 'GET'
    ))
}

async function fetchWebApi(endpoint, method, body) {
    try {
        const token = await getTokenToServer();
        const res = await fetch(`https://api.spotify.com/${endpoint}`, {
            method,
            headers: {
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify(body)
        });
        return await res.json();
    } catch (error) {
        console.log('Error fetching data:', error);
    }
}

async function getArtistsList2(){
    const Artist = await getArtists2();
    console.log(`2 : ${Artist.name} (${Artist.type})`);
    const div = document.getElementById('div1');
    let li = `<p>2 : ${Artist.name} (${Artist.type})</p>`
    div.innerHTML += li;
}

// getArtistsList2();

// async function initializeSpotifyPlayer() {
//     const token = await getTokenToServer();

//     window.onSpotifyWebPlayback = () => {
//         const player = new Spotify.Player({
//             name: 'Web Playback SDK Quick Start Player',
//             getOAuthToken: cb => {cb(token); },
//             volume: 0.5
//         });
//     };

//     // Ready
//     player.addListener('ready', ({ device_id }) => {
//         console.log('Ready with Device ID', device_id);
//     });
//     // Not Ready
//     player.addListener('not_ready', ({ device_id }) => {
//         console.log('Device ID has gone offline', device_id);
//     });
//     player.addListener('initialization_error', ({ message }) => {
//         console.error(message);
//     });
//     player.addListener('authentication_error', ({ message }) => {
//         console.error(message);
//     });
//     player.addListener('account_error', ({ message }) => {
//         console.error(message);
//     });
//     document.getElementById('togglePlay').onclick = function() {
//       player.togglePlay();
//     };
//     player.connect();

// }
// initializeSpotifyPlayer();


//   async function getArtists1(){
//     const token = await getTokenToServer(); // 토큰 가져오기
//     try {
//         const res = await fetch(`https://api.spotify.com/v1/artists/4Z8W4fKeB5YxbusRsdQVPb`, {
//             headers: {
//                 Authorization: `Bearer ${token}`,
//             }
//         });
//         if (!res.ok) {
//             throw new Error('Failed to fetch artist');
//         }
//         return await res.json();
//     } catch (error) {
//         console.error('Error fetching artist:', error);
//     }
// }

// async function getArtistsList1(){
//     const artist = await getArtists1();
//     console.log(`1 : ${artist.name} (${artist.type})`);
//     const div = document.getElementById('div1');
//     let li = `<p>1 : ${artist.name} (${artist.type})</p>`
//     div.innerHTML += li;
// }

// getArtistsList1();
  
//   async function getTopTracks(){
//     // Endpoint reference : https://developer.spotify.com/documentation/web-api/reference/get-users-top-artists-and-tracks
//     return (await fetchWebApi(
//       'v1/me/top/tracks?time_range=long_term&limit=5', 'GET'
//     )).items;
//   }
  
//   const topTracks = await getTopTracks();
//   console.log(
//     topTracks.map(
//       ({name, artists}) =>
//         `${name} by ${artists.map(artist => artist.name).join(', ')}`
//     )
//   );


// const clientData = "grant_type=client_credentials&client_id=7018fc47d1cb49bba23cee1e5c702d60&client_secret=7c1006ea22e04ce0888c4bb616a68537";
//     'grant_type':'client_credentials',
//     'client_id':'7018fc47d1cb49bba23cee1e5c702d60',
//     'client_secret':'7c1006ea22e04ce0888c4bb616a68537'
// }

// const clientData = new URLSearchParams();
// clientData.append('grant_type', 'client_credentials');
// clientData.append('client_id', '7018fc47d1cb49bba23cee1e5c702d60');
// clientData.append('client_secret', '7c1006ea22e04ce0888c4bb616a68537');

// async function fetchSpotifyApiGetToken(clientData){
//     try {
//         const url = `https://accounts.spotify.com/api/token/`;
//         const config = {
//             method:'post',
//             headers:{
//                 'Content-Type' : 'application/x-www-form-urlencoded'
//             },
//             body: clientData
//         };
//         const resp = await fetch(url, config);
//         const result = await resp.json();
//         console.log(result);
//         return result;  
//     } catch (error) {
//         console.log(error);
//     }
// }

// fetchSpotifyApiGetToken(clientData);

// fetch('https://accounts.spotify.com/api/token')
//     .then(AthuSpotifyAPI => AthuSpotifyAPI.json)
//     .then(token=> {
//         const tokenKey = token.data[0];
//         console.log("token >> "+token);
//     })

//     const token = 'BQArHjd1lR8YQAWmaJAb-FtXo26n5ETCqpogvOIfZqKfKUkmn8F768d36DACR7sQdGU6j31VxVyP-gNocW1_X5L34-3P1q41cua2Y1spyesTE6RAdt3GIcxpCeM9tsgBXqSPWUj7ntvZsojieSfH173HVNt5GRnFYw8GM_RoDFoEYc4olg3MdFK9DHFLWbfvhAiz0ec2dltxs7JVhOBAgQcG191YaeNe2orxmkdnFk3kdF2Tpj0N2Rksvnlv7K4UH8v3Wzbch1Up9ylzHjhAnN3dnr8e';
//     async function fetchWebApi(endpoint, method, body) {
//       const res = await fetch(`https://api.spotify.com/${endpoint}`, {
//         headers: {
//           Authorization: `Bearer ${token}`,
//         },
//         method,
//         body:JSON.stringify(body)
//       });
//       return await res.json();
//     }