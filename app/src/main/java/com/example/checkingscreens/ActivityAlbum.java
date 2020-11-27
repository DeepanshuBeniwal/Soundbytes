package com.example.checkingscreens;

import androidx.activity.OnBackPressedCallback;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.jgabrielfreitas.core.BlurImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ActivityAlbum extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<MusicObject>> {

    private View decorView;
    ImageView albumArt, playerAlbumArt;
    BlurImageView blurImageView, playerBlurImage;
    TextView albumName, artistName, albumReleaseYear, noOfSongs, albumPlayTime;
    JcPlayerView jcPlayerView;
    int artistId;
    int albumId;
    boolean isArtistActivityPreviousActivity, isAllAlbumsActivityPreviousActivity, isMainActivityPreviousActivity;
    RelativeLayout mainlayout, playerLayout;
    Button backFromPlayer, backToPlayer;
    Button genius;
    String mAlbumArt;
    int number_of_songs, positionForGenius;
    // here it is
    Button backToPreviousScreen;
    ArrayList<String> geniusUrl;

    SongObjectAdapterForAlbumScreen adapter;

    public static final String LOG_TAG = MusicObject.class.getName();
    private static final String REQUEST_URL = "https://gist.githubusercontent.com/DeepanshuBeniwal/03d508365d0344dd93259fcf5d01d49e/raw/ac47a89baa72ea89cd360d097ee23dad0965dad4/resized_final.json";
    private static final int MUSIC_OBJECT_LOADER_ID = 1;

    final ArrayList<JcAudio> jcAudios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        Intent intent = getIntent();
        artistId = intent.getIntExtra("artist_id", 0);
        albumId = intent.getIntExtra("album_id", 0);
        isMainActivityPreviousActivity = intent.getBooleanExtra("main_activity_is_previous_activity", false);
        isAllAlbumsActivityPreviousActivity = intent.getBooleanExtra("all_albums_activity_is_previous_activity", false);
        isArtistActivityPreviousActivity = intent.getBooleanExtra("artist_activity_is_previous_activity", false);

        albumArt = findViewById(R.id.albumArt);
        playerAlbumArt = findViewById(R.id.playerAlbumArt);
        albumName = findViewById(R.id.albumName);
        artistName = findViewById(R.id.artistName);
        albumReleaseYear = findViewById(R.id.albumReleaseYear);
        noOfSongs = findViewById(R.id.noOfSongs);
        albumPlayTime = findViewById(R.id.albumPlayTime);

        blurImageView = findViewById(R.id.BlurImageView);
        playerBlurImage = findViewById(R.id.playerBlurImage);


        genius = findViewById(R.id.genius);
        geniusUrl = new ArrayList<>();

        jcPlayerView = findViewById(R.id.jcplayer);

        mainlayout = findViewById(R.id.mainlayout);
        playerLayout = findViewById(R.id.playerLayout);

        playerLayout.setVisibility(View.GONE);

        backToPlayer = findViewById(R.id.backToPlayer);
        backFromPlayer = findViewById(R.id.backFromPlayer);


        final ListView songObjectListView = findViewById(R.id.songsListView);
        adapter = new SongObjectAdapterForAlbumScreen(this, new ArrayList<MusicObject>());
        songObjectListView.setAdapter(adapter);

        backToPreviousScreen = findViewById(R.id.backToPreviousScreen);


        /*-----------------------------------------------------------------------------------------*/
        backToPreviousScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (isMainActivityPreviousActivity) {
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                if (isAllAlbumsActivityPreviousActivity) {
                    intent = new Intent(getApplicationContext(), ActivityAllAlbums.class);
                    startActivity(intent);
                }
                if (isArtistActivityPreviousActivity) {
                    intent = new Intent(getApplicationContext(), ActivityArtist.class);
                    startActivity(intent);
                }
            }
        });
        /*------------------------------------------------------------------------------------------*/

        songObjectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                positionForGenius = i;
                MusicObject currentMusicObject = adapter.getItem(i);
                //songImage = currentMusicObject.getAlbumArt();
                jcPlayerView.playAudio(jcAudios.get(i));
                jcPlayerView.createNotification();
                mainlayout.setVisibility(View.GONE);
                playerLayout.setVisibility(View.VISIBLE);
            }
        });

        backFromPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerLayout.setVisibility(View.GONE);
                mainlayout.setVisibility(View.VISIBLE);
                backToPlayer.setVisibility(View.VISIBLE);
            }
        });

        backToPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainlayout.setVisibility(View.GONE);
                playerLayout.setVisibility(View.VISIBLE);
            }
        });


//        genius.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(geniusUrl));
//                startActivity(websiteIntent);
//            }
//        });

        genius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(geniusUrl.get(positionForGenius)));
                startActivity(websiteIntent);
            }
        });



















        /*--------------------------------------------------------------------------------------------------*/
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int i) {
                if (i == 0) {
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
        /*-----------------------------------------------------------------------------------------------------*/


        getSupportActionBar().setTitle("Album");
    }


    @Override
    public Loader<List<MusicObject>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        Uri baseUri = Uri.parse(REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        return new SongObjectLoaderForAlbumScreen(this, uriBuilder.toString(), artistId, albumId);
    }


    @Override
    public void onLoadFinished(Loader<List<MusicObject>> loader, List<MusicObject> musicObjects) {
        View loadingLayout = findViewById(R.id.loading_layout);
        loadingLayout.setVisibility(View.GONE);
        mainlayout.setVisibility(View.VISIBLE);

        adapter.clear();

        if (musicObjects != null && !musicObjects.isEmpty()) {
            for (MusicObject currentMusicObject : musicObjects) {
                if (currentMusicObject.getArtistId() == artistId) {
                    if (currentMusicObject.getAlbumId() == albumId) {
                        mAlbumArt = currentMusicObject.getAlbumArt();
                        Picasso.with(this).load(mAlbumArt).error(R.drawable.ic_launcher_background).into(albumArt);
                        Picasso.with(this).load(mAlbumArt).error(R.drawable.ic_launcher_background).into(playerAlbumArt);
                        Picasso.with(this).load(mAlbumArt).error(R.drawable.ic_launcher_background).into(blurImageView);
                        blurImageView.setBlur(5);
                        Picasso.with(this).load(mAlbumArt).error(R.drawable.ic_launcher_background).into(playerBlurImage);
                        playerBlurImage.setBlur(5);
                        albumName.setText(currentMusicObject.getAlbumName());
                        artistName.setText(currentMusicObject.getArtistName());
                        albumReleaseYear.setText(currentMusicObject.getAlbumReleaseYear());
                        number_of_songs = Integer.parseInt(currentMusicObject.getTotalSongs());
                        noOfSongs.setText(currentMusicObject.getTotalSongs());
                        albumPlayTime.setText(currentMusicObject.getAlbumPlayTime());

                    }
                }
            }
            for (MusicObject currentMusicObject : musicObjects) {

                geniusUrl.add(currentMusicObject.getGeniusLyricsUrl());
                jcAudios.add(JcAudio.createFromURL(currentMusicObject.getSongName(), currentMusicObject.getSongUrl()));
            }
            jcPlayerView.initPlaylist(jcAudios, null);
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
        if (hasFocus) {
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    private int hideSystemBars() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

}
