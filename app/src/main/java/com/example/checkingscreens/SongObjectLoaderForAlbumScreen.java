package com.example.checkingscreens;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class SongObjectLoaderForAlbumScreen extends AsyncTaskLoader<List<MusicObject>> {

    /** Tag for log messages */
    private static final String LOG_TAG = SongObjectLoaderForAlbumScreen.class.getName();

    /** Query URL */
    private String mUrl;
    int mArtistId, mAlbumId;


    public SongObjectLoaderForAlbumScreen(Context context, String url, int artistId, int albumId) {
        super(context);
        mUrl = url;
        mArtistId = artistId;
        mAlbumId = albumId;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<MusicObject> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<MusicObject> musicObjects = QueryUtilsForAlbumScreen.fetchMusicObjectData(mUrl, mArtistId, mAlbumId);
        return musicObjects;
    }

}
