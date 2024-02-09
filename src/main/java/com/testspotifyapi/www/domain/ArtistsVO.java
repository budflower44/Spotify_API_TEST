package com.testspotifyapi.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ArtistsVO {

    private String id;
    private String externalUrls;
    private String followersHref;
    private long followersTotal;
    private String genres;
    private String href;
    private String imagesUrl;
    private int imagesHeight;
    private int imagesWidth;
    private String name;
    private long popularity;
    private String type;
    private String uri;
    private long likes;
    private long playCount;
    private int rankMonth;
    private int rankWeek;
    private int rankDay;
    
	public ArtistsVO(String id, String externalUrls, String followersHref, long followersTotal, String genres,
			String href, String imagesUrl, int imagesHeight, int imagesWidth, String name, long popularity, String type,
			String uri) {
		this.id = id;
		this.externalUrls = externalUrls;
		this.followersHref = followersHref;
		this.followersTotal = followersTotal;
		this.genres = genres;
		this.href = href;
		this.imagesUrl = imagesUrl;
		this.imagesHeight = imagesHeight;
		this.imagesWidth = imagesWidth;
		this.name = name;
		this.popularity = popularity;
		this.type = type;
		this.uri = uri;
	}
    
    
    
}
