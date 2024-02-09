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
public class TracksVO {
	
    private String id;
    private String albumId;
    private String artistsId;
    private String externalUrl;
    private long discNumber;
    private long durationMs;
    private Boolean explicit;
    private String externalIds;
    private String href;
    private Boolean isLocal;
    private String name;
    private long popularity;
    private String previewUrl;
    private int trackNumber;
    private String type;
    private String uri;
    private long likes;
    private long playCount;
    private int rankMonth;
    private int rankWeek;
    private int rankDay;
	
}
