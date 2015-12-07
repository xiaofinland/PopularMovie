package com.example.android.popularmovie;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements FetchMovieTask.TaskCallback {
    private GridView mGridView;
    private MovieAdapter mMovieAdapter;

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

            }
    public MainActivityFragment() {
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
       // outState.putParcelableArrayList("flavors", flavorList);
        super.onSaveInstanceState(outState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mGridView = (GridView)rootView.findViewById(R.id.movies_gridView);
        //mMovieAdapter = new MovieAdapter(getActivity(), Arrays.asList(movie));
       // mGridView.setAdapter(mMovieAdapter);
        //updateMovies();
                return rootView;
    }
    //public void updateMovies (){
    //    FetchMovieTask fetchMovieTask = new FetchMovieTask(getActivity(), this);
     //   String sort =Utility.getPreferredSort(getContext());
     //   fetchMovieTask.execute(sort);
    //}

@Override
    public void onTaskComplete (final ArrayList<Movie> movies){
        if (mMovieAdapter == null){
            mMovieAdapter = new MovieAdapter(getActivity(), movies);
            mGridView.setAdapter(mMovieAdapter);
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                    Intent intent = new Intent(getActivity(),DetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, movies);
                    startActivity(intent);
                }
            });

            }else {
            mMovieAdapter.clear();
            mMovieAdapter.addAll(movies);
        }

         mMovieAdapter.notifyDataSetChanged();

    }
    public interface FragmentCallback {
        void onItemClick(Movie movie);
    }
}
