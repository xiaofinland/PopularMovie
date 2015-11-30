package com.example.android.popularmovie;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
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
        updateMovies();
                return rootView;
    }
    public void updateMovies (){
        FetchMovieTask fetchMovieTask = new FetchMovieTask(getActivity(), this);
        String sort =Utility.getPreferredSort(getContext());
        fetchMovieTask.execute(sort);
    }

    @Override
    public void onTaskComplete (final ArrayList<Movie> movies){
        if (mMovieAdapter == null){
            mMovieAdapter = new MovieAdapter(getContext(), R.layout.image_item, movies);
            mGridView.setAdapter(mMovieAdapter);
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                    ((FragmentCallback)getActivity().onItemClick(moviee.get(position)));
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
