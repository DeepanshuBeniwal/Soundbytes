package com.example.checkingscreens;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.example.checkingscreens.MainActivity.LOG_TAG;

public class QueryUtilsForHomeScreen {

    private QueryUtilsForHomeScreen(){}

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<MusicObject> extractFeatureFromJson(String musicObjectJSON) {

        /*-----------------------------------------------------*/
        boolean flag = false;
        int counter = 0;
        int[] arr = new int[4];
        while(counter != 4){
        for (int i = 0; i < 4; i++) {
            int num = getRandomNumber(0,19);
                for (int j = 0; j < counter; j++) {
                    if(num == arr[j]){
                        flag = true;
                    }
                }
                if(!flag){
                    arr[i] = num;
                    counter++;
                }
        }
        }
        /*-------------------------------------------------------*/

        if (TextUtils.isEmpty(musicObjectJSON)) {
            return null;
        }

        List<MusicObject> musicObjects = new ArrayList<>();
        try {
            JSONArray root = new JSONArray(musicObjectJSON);

              for(int h=0; h<4; h++){
//            for (int h = 0; h < root.length(); h++) {
//                if (h == arr[0] || h == arr[1] || h == arr[2] || h == arr[3]) {
                    JSONObject rootObject = root.getJSONObject(arr[h]);

                    int artistId = rootObject.getInt("artist_id");
                    String artistName = rootObject.getString("artist_name");
                    String artistImageUrl = rootObject.getString("artist_image_url");
                    String artistBackground = rootObject.getString("artist_background");


                    JSONObject artistSocial = rootObject.getJSONObject("artist_social");

                    String youtubeUrl = artistSocial.getString("youtube_url");
                    String spotifyUrl = artistSocial.getString("spotify_url");
                    String appleMusicUrl = artistSocial.getString("apple_music_url");
                    String soundcloudUrl = artistSocial.getString("soundcloud_url");
                    String instagramUrl = artistSocial.getString("instagram_url");
                    String twitterUrl = artistSocial.getString("twitter_url");
                    String websiteUrl = artistSocial.getString("website_url");


                    JSONArray albums = rootObject.getJSONArray("albums");

                        JSONObject album = albums.getJSONObject(0);

                        int albumId = album.getInt("album_id");
                        String albumName = album.getString("album_name");
                        String albumArt = album.getString("album_art");
                        String albumBackground = album.getString("album_background");
                        String albumReleaseYear = album.getString("album_release_year");
                        String albumReleaseDate = album.getString("album_release_date");
                        String totalSongs = album.getString("total songs");
                        String albumPlayTime = album.getString("album_play_time");

                        JSONArray songs = album.getJSONArray("songs");
                        //for (int j = 0; j < songs.length(); j++) {
                            JSONObject song = songs.getJSONObject(0);

                            String songName = song.getString("song_name");
                            String songUrl = song.getString("song_url");
                            String geniusLyricsUrl = song.getString("genius_lyrics_url");

                            MusicObject musicObject = new MusicObject(artistId,
                                    artistName,
                                    artistImageUrl,
                                    artistBackground,
                                    youtubeUrl,
                                    spotifyUrl,
                                    appleMusicUrl,
                                    soundcloudUrl,
                                    instagramUrl,
                                    twitterUrl,
                                    websiteUrl,
                                    albumId,
                                    albumName,
                                    albumArt,
                                    albumBackground,
                                    albumReleaseYear,
                                    albumReleaseDate,
                                    totalSongs,
                                    albumPlayTime,
                                    songName,
                                    songUrl,
                                    geniusLyricsUrl);
                            musicObjects.add(musicObject);

                        //}
                }
//              }
            } catch(JSONException e){
                Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
            }
            return musicObjects;
        }


    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static List<MusicObject> fetchMusicObjectData(String requestUrl) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<MusicObject> musicObjects = extractFeatureFromJson(jsonResponse);
        return musicObjects;
    }
}
