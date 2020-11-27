package com.example.checkingscreens;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class ArtistObjectLoaderForAllArtistsScreen extends AsyncTaskLoader<List<MusicObject>> {

    /** Tag for log messages */
    private static final String LOG_TAG = ArtistObjectLoaderForAllArtistsScreen.class.getName();

    /** Query URL */
    private String mUrl;

    public ArtistObjectLoaderForAllArtistsScreen(Context context, String url) {
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
        List<MusicObject> musicObjects = QueryUtilsForAllArtistsScreen.fetchMusicObjectData(mUrl);
        return musicObjects;
    }

}
