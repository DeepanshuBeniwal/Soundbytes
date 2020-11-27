package com.example.checkingscreens;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class AlbumObjectLoaderForAllAlbumsScreen extends AsyncTaskLoader<List<MusicObject>> {

    /** Tag for log messages */
    private static final String LOG_TAG = AlbumObjectLoaderForAllAlbumsScreen.class.getName();

    /** Query URL */
    private String mUrl;

    public AlbumObjectLoaderForAllAlbumsScreen(Context context, String url) {
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
        List<MusicObject> musicObjects = QueryUtilsForAllAlbumsScreen.fetchMusicObjectData(mUrl);
        return musicObjects;
    }

}
