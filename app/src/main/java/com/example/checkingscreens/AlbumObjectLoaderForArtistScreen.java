package com.example.checkingscreens;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;

import java.util.List;

public class AlbumObjectLoaderForArtistScreen extends AsyncTaskLoader<List<MusicObject>> {



    /** Tag for log messages */
    private static final String LOG_TAG = AlbumObjectLoaderForArtistScreen.class.getName();

    /** Query URL */
    private String mUrl;
    int num;



    public AlbumObjectLoaderForArtistScreen(Context context, String url, int num) {
        super(context);
        mUrl = url;
        this.num = num;
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
        List<MusicObject> musicObjects = QueryUtilsForArtistScreen.fetchMusicObjectData(mUrl, num);
        return musicObjects;
    }

}
