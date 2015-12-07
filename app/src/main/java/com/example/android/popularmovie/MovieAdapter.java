package com.example.android.popularmovie;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by xiao on 19/11/2015.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {
    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();

    public MovieAdapter(Activity context,  List<Movie> objects) {
        super(context,0,objects);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        //get Movie object from the ArrayAdapter at the appropriate position
        Movie movie = getItem(position);
        ImageView imageView;
        if (convertView == null) {
           imageView = new ImageView(getContext());
        }else {
            imageView=(ImageView)convertView;
        }


        Picasso.with(getContext())
                .load(movie.getPosterPath())
                .fit()
                .centerInside()
                .into(imageView);
        return imageView;
    }
}
