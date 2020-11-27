package com.example.checkingscreens;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class AlbumAndArtistObjectLoaderForHomeScreen extends AsyncTaskLoader<List<MusicObject>> {

    /** Tag for log messages */
    private static final String LOG_TAG = AlbumAndArtistObjectLoaderForHomeScreen.class.getName();

    /** Query URL */
    private String mUrl;


    public AlbumAndArtistObjectLoaderForHomeScreen(Context context, String url) {
        super(context);
        mUrl = url;
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
        List<MusicObject> musicObjects = QueryUtilsForHomeScreen.fetchMusicObjectData(mUrl);
        return musicObjects;
    }

}
