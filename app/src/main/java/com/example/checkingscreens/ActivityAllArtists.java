package com.example.checkingscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class ActivityAllArtists extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<MusicObject>>{

//    Button allArtistsToArtist;
    private View decorView;
    ArtistObjectAdapterForAllArtistsScreen adapter;
    Button backToPreviousScreen;

    RelativeLayout mainLayout;

    public static final String LOG_TAG = MusicObject.class.getName();
    private static final String REQUEST_URL = "https://gist.githubusercontent.com/DeepanshuBeniwal/03d508365d0344dd93259fcf5d01d49e/raw/ac47a89baa72ea89cd360d097ee23dad0965dad4/resized_final.json";
    private static final int MUSIC_OBJECT_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_artists);


        mainLayout = findViewById(R.id.main_layout);

        final ListView artistObjectListView = findViewById(R.id.allArtistsListView);
        adapter = new ArtistObjectAdapterForAllArtistsScreen(this, new ArrayList<MusicObject>());
        artistObjectListView.setAdapter(adapter);

        backToPreviousScreen = findViewById(R.id.backToPreviousScreen);

        /*----------------------------------------------------------------------------------------*/
        backToPreviousScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        /*----------------------------------------------------------------------------------------*/

        artistObjectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MusicObject currentMusicObject = adapter.getItem(i);
                Intent intent = new Intent(getApplicationContext(), ActivityArtist.class);
                intent.putExtra("artist_id", currentMusicObject.getArtistId());
                intent.putExtra("all_artists_activity_is_previous_activity", true);
                startActivity(intent);
            }
        });

        /*--------------------------------------------------------------------------------------------------*/
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int i) {
                if(i == 0){
                    decorView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(MUSIC_OBJECT_LOADER_ID, null, this);
        }else{
            View loadingLayout = findViewById(R.id.loading_layout);
            View networkErrorLayout = findViewById(R.id.network_error_layout);

            loadingLayout.setVisibility(View.GONE);
            networkErrorLayout.setVisibility(View.VISIBLE);

        }





























        getSupportActionBar().setTitle("All Artists");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        allArtistsToArtist = findViewById(R.id.AllArtistsToArtist);
//        allArtistsToArtist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), ActivityArtist.class));
//            }
//        });
    }









    @Override
    public Loader<List<MusicObject>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        Uri baseUri = Uri.parse(REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        return new ArtistObjectLoaderForAllArtistsScreen(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<MusicObject>> loader, List<MusicObject> musicObjects) {
        View loadingLayout = findViewById(R.id.loading_layout);
        loadingLayout.setVisibility(View.GONE);
        mainLayout.setVisibility(View.VISIBLE);
        adapter.clear();

        if (musicObjects != null && !musicObjects.isEmpty()) {
            adapter.addAll(musicObjects);
        }
    }
    @Override
    public void onLoaderReset(Loader<List<MusicObject>> loader) {
        adapter.clear();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    private int hideSystemBars(){
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

}
