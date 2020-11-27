package com.example.checkingscreens;

public class MusicObject {

    public int mArtistId;
    public String mArtistName;
    public String mArtistImageUrl;
    public String mArtistBackground;
    public String mYoutubeUrl;
    public String mSpotifyUrl;
    public String mAppleMusicUrl;
    public String mSoundCloudUrl;
    public String mInstagramUrl;
    public String mTwitterUrl;
    public String mWebsiteUrl;
    public int mAlbumId;
    public String mAlbumName;
    public String mAlbumArt;
    public String mAlbumBackground;
    public String mAlbumReleaseYear;
    public String mAlbumReleaseDate;
    public String mTotalSongs;
    public String mAlbumPlayTime;
    public String mSongName;
    public String mSongUrl;
    public String mGeniusLyricsUrl;

    public MusicObject(int artistId,
                       String artistName,
                       String artistImageUrl,
                       String artistBackground,
                       String youtubeUrl,
                       String spotifyUrl,
                       String appleMusicUrl,
                       String soundcloudUrl,
                       String instagramUrl,
                       String twitterUrl,
                       String websiteUrl,
                       int albumId,
                       String albumName,
                       String albumArt,
                       String albumBackground,
                       String albumReleaseYear,
                       String albumReleaseDate,
                       String totalSongs,
                       String albumPlayTime,
                       String songName,
                       String songUrl,
                       String geniusLyricsUrl){

        mArtistId = artistId;
        mArtistName = artistName;
        mArtistImageUrl = artistImageUrl;
        mArtistBackground = artistBackground;
        mYoutubeUrl = youtubeUrl;
        mSpotifyUrl = spotifyUrl;
        mAppleMusicUrl = appleMusicUrl;
        mSoundCloudUrl = soundcloudUrl;
        mInstagramUrl = instagramUrl;
        mTwitterUrl = twitterUrl;
        mWebsiteUrl = websiteUrl;
        mAlbumId = albumId;
        mAlbumName = albumName;
        mAlbumArt = albumArt;
        mAlbumBackground = albumBackground;
        mAlbumReleaseYear = albumReleaseYear;
        mAlbumReleaseDate = albumReleaseDate;
        mTotalSongs = totalSongs;
        mAlbumPlayTime = albumPlayTime;
        mSongName = songName;
        mSongUrl = songUrl;
        mGeniusLyricsUrl = geniusLyricsUrl;
    }

    public MusicObject(int artistId,
                       String artistName,
                       String artistImageUrl,
                       String artistBackground,
                       String youtubeUrl,
                       String spotifyUrl,
                       String appleMusicUrl,
                       String soundcloudUrl,
                       String instagramUrl,
                       String twitterUrl,
                       String websiteUrl,
                       int albumId,
                       String albumName,
                       String albumArt,
                       String albumBackground,
                       String albumReleaseYear,
                       String albumReleaseDate,
                       String totalSongs,
                       String albumPlayTime
                       ){

        mArtistId = artistId;
        mArtistName = artistName;
        mArtistImageUrl = artistImageUrl;
        mArtistBackground = artistBackground;
        mYoutubeUrl = youtubeUrl;
        mSpotifyUrl = spotifyUrl;
        mAppleMusicUrl = appleMusicUrl;
        mSoundCloudUrl = soundcloudUrl;
        mInstagramUrl = instagramUrl;
        mTwitterUrl = twitterUrl;
        mWebsiteUrl = websiteUrl;
        mAlbumId = albumId;
        mAlbumName = albumName;
        mAlbumArt = albumArt;
        mAlbumBackground = albumBackground;
        mAlbumReleaseYear = albumReleaseYear;
        mAlbumReleaseDate = albumReleaseDate;
        mTotalSongs = totalSongs;
        mAlbumPlayTime = albumPlayTime;
    }

    public MusicObject(int artistId,
                       String artistName,
                       String artistImageUrl,
                       String artistBackground,
                       String youtubeUrl,
                       String spotifyUrl,
                       String appleMusicUrl,
                       String soundcloudUrl,
                       String instagramUrl,
                       String twitterUrl,
                       String websiteUrl){

        mArtistId = artistId;
        mArtistName = artistName;
        mArtistImageUrl = artistImageUrl;
        mArtistBackground = artistBackground;
        mYoutubeUrl = youtubeUrl;
        mSpotifyUrl = spotifyUrl;
        mAppleMusicUrl = appleMusicUrl;
        mSoundCloudUrl = soundcloudUrl;
        mInstagramUrl = instagramUrl;
        mTwitterUrl = twitterUrl;
        mWebsiteUrl = websiteUrl;
    }








    public String getArtistName(){
        return mArtistName;
    }
    public String getSongName(){
        return mSongName;
    }
    public String getAlbumName(){
        return mAlbumName;
    }
    public String getSongUrl(){
        return mSongUrl;
    }
    public String getAlbumArt(){
        return mAlbumArt;
    }
    public String getArtistImageUrl(){
        return mArtistImageUrl;
    }
    public int getArtistId(){
        return mArtistId;
    }
    public int getAlbumId(){
        return mAlbumId;
    }
    public String getAlbumReleaseYear(){ return mAlbumReleaseYear; }
    public String getYoutubeUrl(){ return mYoutubeUrl;}
    public String getSpotifyUrl(){ return mSpotifyUrl;}
    public String getAppleMusicUrl(){ return mAppleMusicUrl;}
    public String getSoundCloudUrl(){ return mSoundCloudUrl;}
    public String getInstagramUrl(){ return mInstagramUrl;}
    public String getTwitterUrl(){ return mTwitterUrl;}
    public String getWebsiteUrl(){ return mWebsiteUrl;}
    public String getTotalSongs(){ return mTotalSongs;}
    public String getAlbumPlayTime(){ return mAlbumPlayTime;}
    public String getGeniusLyricsUrl(){return mGeniusLyricsUrl;}

}
