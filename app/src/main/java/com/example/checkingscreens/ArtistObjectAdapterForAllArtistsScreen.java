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

public class ArtistObjectAdapterForAllArtistsScreen extends ArrayAdapter<MusicObject> {

    public ArtistObjectAdapterForAllArtistsScreen(Activity context, ArrayList<MusicObject> musicObjects){
        super(context, 0, musicObjects);
    }


    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.all_artists_screen_artist_item, parent, false);
        }

        MusicObject currentMusicObject = getItem(position);

        ImageView artistImage = listItemView.findViewById(R.id.artistImage);
        TextView artistName = listItemView.findViewById(R.id.artistName);

        Picasso.with(getContext()).load(currentMusicObject.getArtistImageUrl()).error(R.drawable.default_artist).into(artistImage);
        artistName.setText(currentMusicObject.getArtistName());


        return listItemView;
    }

}
