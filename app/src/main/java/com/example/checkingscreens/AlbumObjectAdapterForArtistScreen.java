package com.example.checkingscreens;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumObjectAdapterForArtistScreen extends ArrayAdapter<MusicObject> {

    public AlbumObjectAdapterForArtistScreen(Activity context, ArrayList<MusicObject> musicObjects){
        super(context, 0, musicObjects);
    }


    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.artist_screen_album_item, parent, false);
        }

        MusicObject currentMusicObject = getItem(position);

        ImageView albumArt = listItemView.findViewById(R.id.albumArt);
        TextView albumName = listItemView.findViewById(R.id.albumName);
        TextView albumReleaseYear = listItemView.findViewById(R.id.albumReleaseYear);

        Picasso.with(getContext()).load(currentMusicObject.getAlbumArt()).error(R.drawable.default_album).into(albumArt);
        albumName.setText(currentMusicObject.getAlbumName());
        albumReleaseYear.setText(currentMusicObject.getAlbumReleaseYear());


        return listItemView;
    }

}
