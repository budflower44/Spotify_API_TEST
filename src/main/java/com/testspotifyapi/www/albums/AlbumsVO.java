package com.testspotifyapi.www.albums;

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
public class AlbumsVO {
	
    private String id;
    private String externalUrls;
    private String albumType;
    private String artistsExternalUrls;
    private String artistsHref;
    private String artistsId;
    private String artistsName;
    private String artistsType;
    private String artistsUri;
    private String href;
    private String imagesUrl;
    private Integer imagesHeight;
    private Integer imagesWidth;
    private String name;
    private String releaseDate;
    private String releaseDatePrecision;
    private int totalTracks;
    private String type;
    private String uri;
    private long likes;
    private long playCount;
    private int rankMonth;
    private int rankWeek;
    private int rankDay;
    
	public AlbumsVO(String id, String externalUrls, String albumType, String artistsExternalUrls, String artistsHref,
			String artistsId, String artistsName, String artistsType, String artistsUri, String href, String imagesUrl,
			int imagesHeight, int imagesWidth, String name, String releaseDate, String releaseDatePrecision,
			int totalTracks, String type, String uri) {
        this.id = id != null ? id : "";
        this.externalUrls = externalUrls != null ? externalUrls : "";
        this.albumType = albumType != null ? albumType : "";
        this.artistsExternalUrls = artistsExternalUrls != null ? artistsExternalUrls : "";
        this.artistsHref = artistsHref != null ? artistsHref : "";
        this.artistsId = artistsId != null ? artistsId : "";
        this.artistsName = artistsName != null ? artistsName : "";
        this.artistsType = artistsType != null ? artistsType : "";
        this.artistsUri = artistsUri != null ? artistsUri : "";
        this.href = href != null ? href : "";
        this.imagesUrl = imagesUrl != null ? imagesUrl : "";
        this.imagesHeight = imagesHeight < 640 ? imagesHeight : 640;
        this.imagesWidth = imagesWidth < 640 ? imagesWidth : 640;
        this.name = name != null ? name : "";
        this.releaseDate = releaseDate != null ? releaseDate : "";
        this.releaseDatePrecision = releaseDatePrecision != null ? releaseDatePrecision : "";
        this.totalTracks = totalTracks;
        this.type = type != null ? type : "";
        this.uri = uri != null ? uri : "";
	}
    
    
    
}
