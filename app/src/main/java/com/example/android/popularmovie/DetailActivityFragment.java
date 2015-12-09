package com.example.android.popularmovie;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View detailView= inflater.inflate(R.layout.fragment_detail, container, false);
        Intent intent = getActivity().getIntent();
        //pass title
        if (intent != null && intent.hasExtra("MOVIE_TITLE")){
            String movie_title = intent.getStringExtra("MOVIE_TITLE");
            ((TextView) detailView.findViewById(R.id.movie_title_text))
                    .setText(movie_title);

            // set activity title
            getActivity().setTitle(movie_title);

            //pass poster image
            String movie_poster = intent.getStringExtra("MOVIE_POSTER");
            ImageView poster = (ImageView) detailView.findViewById(R.id.poster_image_view);
                    Picasso
                            .with(getActivity())
                            .load(movie_poster)
                            .fit()
                            .into(poster);
        //pass thumb image
            String movie_thumb = intent.getStringExtra("MOVIE_THUMB");
            ImageView thumb = (ImageView)detailView.findViewById(R.id.thumb_image_view);
                Picasso
                        .with(getContext())
                        .load(movie_thumb)
                        .fit()
                        .into(thumb);

            //pass release date
            String movie_release = intent.getStringExtra("MOVIE_RELEASE");
            ((TextView) detailView.findViewById(R.id.movie_releas_date_text))
                    .setText(movie_release);
            //pass rating
            String movie_rating = intent.getStringExtra("MOVIE_RATING");
            ((TextView) detailView.findViewById(R.id.movie_rating_text))
                    .setText( movie_rating);
            //pass overview
            String movie_overview = intent.getStringExtra("MOVIE_OVERVIEW");
            ((TextView) detailView.findViewById(R.id.movie_overview_text))
                    .setText( movie_overview);
        }
        return detailView;
    }

}

