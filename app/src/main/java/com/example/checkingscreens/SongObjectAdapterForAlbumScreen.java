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

public class SongObjectAdapterForAlbumScreen extends ArrayAdapter<MusicObject> {

    public SongObjectAdapterForAlbumScreen(Activity context, ArrayList<MusicObject> musicObjects){
        super(context, 0, musicObjects);
    }


    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_song_item, parent, false);
        }

        MusicObject currentMusicObject = getItem(position);

        TextView songName = listItemView.findViewById(R.id.songName);
        TextView albumName = listItemView.findViewById(R.id.albumName);

        songName.setText(currentMusicObject.getSongName());
        albumName.setText(currentMusicObject.getAlbumName());


        return listItemView;
    }

}
