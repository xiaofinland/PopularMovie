package com.example.android.popularmovie;

import android.app.Activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.view.LayoutInflater;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiao on 19/11/2015.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {
    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();

    public MovieAdapter(Context context,  ArrayList<Movie> objects) {
        super(context,0,objects);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        //get Movie object from the ArrayAdapter at the appropriate position
        Movie current = getItem(position);
        String thumbUrl = current.thumb;


        if (convertView == null) {
           convertView = LayoutInflater.from(getContext()).inflate (R.layout.image_item,parent,false);
        }

        Picasso.with(getContext())
                .load(thumbUrl)
                .fit()
                .centerInside()
                .into((ImageView) convertView);
        return convertView;
    }
}
