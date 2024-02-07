const token = 'BQArHjd1lR8YQAWmaJAb-FtXo26n5ETCqpogvOIfZqKfKUkmn8F768d36DACR7sQdGU6j31VxVyP-gNocW1_X5L34-3P1q41cua2Y1spyesTE6RAdt3GIcxpCeM9tsgBXqSPWUj7ntvZsojieSfH173HVNt5GRnFYw8GM_RoDFoEYc4olg3MdFK9DHFLWbfvhAiz0ec2dltxs7JVhOBAgQcG191YaeNe2orxmkdnFk3kdF2Tpj0N2Rksvnlv7K4UH8v3Wzbch1Up9ylzHjhAnN3dnr8e';
async function fetchWebApi(endpoint, method, body) {
  const res = await fetch(`https://api.spotify.com/${endpoint}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
    method,
    body:JSON.stringify(body)
  });
  return await res.json();
}

async function getTopTracks(){
  // Endpoint reference : https://developer.spotify.com/documentation/web-api/reference/get-users-top-artists-and-tracks
  return (await fetchWebApi(
    'v1/me/top/tracks?time_range=long_term&limit=5', 'GET'
  )).items;
}

const topTracks = await getTopTracks();
console.log(
  topTracks?.map(
    ({name, artists}) =>
      `${name} by ${artists.map(artist => artist.name).join(', ')}`
  )
);