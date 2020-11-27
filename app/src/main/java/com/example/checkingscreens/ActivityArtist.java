package com.example.checkingscreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Color;
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
import android.widget.Toast;

import com.jgabrielfreitas.core.BlurImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ActivityArtist extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<MusicObject>>{

    String artistImageUrl, artistName, youtubeUrl, spotifyUrl, appleMusicUrl, soundcloudUrl, instagramUrl, twitterUrl, websiteUrl;
    ImageView artist_image;
    TextView artist_name;
    Button youtube, spotify, appleMusic, soundcloud, instagram, twitter, website;
    RelativeLayout mainLayout;

    // here it is
    Button backToPreviousScreen;
    boolean isMainActivityPreviousActivity, isAllArtistsActivityPreviousActivity;

    private View decorView;
    Button artistToAlbum;
    Button back;
    BlurImageView wholeLayoutImage;

    int num;

    AlbumObjectAdapterForArtistScreen adapter;

    public static final String LOG_TAG = MusicObject.class.getName();
    private static final String REQUEST_URL = "https://gist.githubusercontent.com/DeepanshuBeniwal/03d508365d0344dd93259fcf5d01d49e/raw/ac47a89baa72ea89cd360d097ee23dad0965dad4/resized_final.json";
    private static final int MUSIC_OBJECT_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        /*-----------------testing starts-------------------*/
        /*
        RelativeLayout testing = findViewById(R.id.testing);
        testing.setBackgroundColor(Color.);
        */
        /*---------------- testing ends---------------------*/

        youtube = findViewById(R.id.youtube);
        spotify = findViewById(R.id.spotify);
        appleMusic = findViewById(R.id.apple);
        soundcloud = findViewById(R.id.soundcloud);
        instagram = findViewById(R.id.instagram);
        twitter = findViewById(R.id.twitter);
        website = findViewById(R.id.website);

        mainLayout = findViewById(R.id.main_layout);

        wholeLayoutImage = findViewById(R.id.wholeLayoutImage);

        artist_image = findViewById(R.id.artistImage);
        artist_name = findViewById(R.id.artistName);

        backToPreviousScreen = findViewById(R.id.backToPreviousScreen);

        Intent intent = getIntent();
        num = intent.getIntExtra("artist_id",0);
        isMainActivityPreviousActivity = intent.getBooleanExtra("main_activity_is_previous_activity", false);
        isAllArtistsActivityPreviousActivity = intent.getBooleanExtra("all_artists_activity_is_previous_activity", false);


        final ListView albumObjectListView = findViewById(R.id.album_list_view);
        adapter = new AlbumObjectAdapterForArtistScreen(this, new ArrayList<MusicObject>());
        albumObjectListView.setAdapter(adapter);

        /*----------------------------------------------------------------------------------------*/
        backToPreviousScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(isMainActivityPreviousActivity){
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                if(isAllArtistsActivityPreviousActivity){
                    intent = new Intent(getApplicationContext(), ActivityAllArtists.class);
                    startActivity(intent);
                }
            }
        });
        /*----------------------------------------------------------------------------------------*/

        albumObjectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MusicObject currentMusicObject = adapter.getItem(i);
                Intent intent = new Intent(getApplicationContext(), ActivityAlbum.class);
                intent.putExtra("artist_id", currentMusicObject.getArtistId());
                intent.putExtra("album_id", currentMusicObject.getAlbumId());
                intent.putExtra("artist_activity_is_previous_activity", true);
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
        /*-----------------------------------------------------------------------------------------------------*/


        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
                startActivity(websiteIntent);
            }
        });

        spotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(spotifyUrl));
                startActivity(websiteIntent);
            }
        });

        appleMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(appleMusicUrl));
                startActivity(websiteIntent);
            }
        });

        soundcloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(soundcloudUrl));
                startActivity(websiteIntent);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
                startActivity(websiteIntent);
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl));
                startActivity(websiteIntent);
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
                startActivity(websiteIntent);
            }
        });

















        /*-----------------------------------------------------------------------------------------*/
        getSupportActionBar().setTitle("Artist");

        artistToAlbum = findViewById(R.id.ArtistToAlbum);
        artistToAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivityAlbum.class));
            }
        });

        back = findViewById(R.id.BackFromArtist);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /*-------------------------------------------------------------------------------------------*/


    }


    @Override
    public Loader<List<MusicObject>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        Uri baseUri = Uri.parse(REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        return new AlbumObjectLoaderForArtistScreen(this, uriBuilder.toString(), num);
    }



    @Override
    public void onLoadFinished(Loader<List<MusicObject>> loader, List<MusicObject> musicObjects) {
        View loadingLayout = findViewById(R.id.loading_layout);
        loadingLayout.setVisibility(View.GONE);
        mainLayout.setVisibility(View.VISIBLE);
        adapter.clear();

        if (musicObjects != null && !musicObjects.isEmpty()) {
            for(MusicObject currentMusicObject : musicObjects){
                if(currentMusicObject.getArtistId() == num){
                    artistImageUrl = currentMusicObject.getArtistImageUrl();
                    artistName = currentMusicObject.getArtistName();
                    youtubeUrl = currentMusicObject.getYoutubeUrl();
                    spotifyUrl = currentMusicObject.getSpotifyUrl();
                    appleMusicUrl = currentMusicObject.getAppleMusicUrl();
                    soundcloudUrl = currentMusicObject.getSoundCloudUrl();
                    instagramUrl = currentMusicObject.getInstagramUrl();
                    twitterUrl = currentMusicObject.getTwitterUrl();
                    websiteUrl = currentMusicObject.getWebsiteUrl();

                    artist_name.setText(artistName);
                    Picasso.with(this).load(artistImageUrl).error(R.drawable.ic_launcher_background).into(artist_image);
                    Picasso.with(this).load(artistImageUrl).error(R.drawable.ic_launcher_background).into(wholeLayoutImage);
                    wholeLayoutImage.setBlur(6);
                }
            }

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
