package com.example.android.popularmovie;

import android.content.Context;
//import android.graphics.Movie;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by xiao on 19/11/2015.
 */
public class FetchMovieTask extends AsyncTask<String, Void, ArrayList<Movie>> {
    private static final String LOG_TAG = FetchMovieTask.class.getSimpleName();
    private ArrayAdapter<String> mMoviewAdapater;

    private Context mContext;
    private TaskCallback  mCallback;

    public FetchMovieTask(Context context, TaskCallback callback){
        mContext = context;
        mCallback = callback;
    }

    private ArrayList<Movie> getMovieDataFromJason(String MovieJasonStr) throws JSONException{
        final String JON_RESULTS = "results";

        // velues we are going to fetch from JSON.
        final String JON_ID = "id";
        final String JON_OVERVIEW = "overview";
        final String JON_RELEASE_DATE ="release_date";
        final String JON_POSTER_PATH = "poster_path";
        final String JON_TITLE = "title";
        final String JON_VOTE_AVERAGE = "vote_average";

        JSONObject moviewJson = new JSONObject(MovieJasonStr);
        JSONArray movieArray = moviewJson.getJSONArray(JON_RESULTS);

        //Loog JSON Array and fetch JSON movie objects
        ArrayList<Movie> movies = new ArrayList<>(movieArray.length());
        for (int i=0; i<movieArray.length();i++){
            JSONObject currentMovie = movieArray.getJSONObject(i);

            //get needed data from JSON text
            String id =currentMovie.getString(JON_ID);
            String overview = currentMovie.getString(JON_OVERVIEW);
            String release_date = currentMovie.getString(JON_RELEASE_DATE);
            String poster_path = currentMovie.getString(JON_POSTER_PATH);
            String title = currentMovie.getString(JON_TITLE);
            float vote_average = (float) currentMovie.getDouble(JON_VOTE_AVERAGE);

            // Make a movie object and add to movie list
            Movie movie = new Movie(id,title,poster_path,overview,release_date,vote_average);
            movies.add(movie);
        }
        return movies;
    }
@Override
protected ArrayList<Movie> doInBackground (String...params){
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String movieJsonStr = null;

    try{
        //building bloacks of  URL to to be built
        final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
        final String SORT_PARAM = "sort_by";
        final String API_PARAM = "api_key";

        String sort = params[0];


        //build URI
        Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendQueryParameter(SORT_PARAM,sort)
                .appendQueryParameter(API_PARAM, BuildConfig.MOVIE_DATABASE_API_KEY)
                .build();
        URL url = new URL(builtUri.toString());
        Log.d(LOG_TAG, "URL: "+builtUri.toString());
        //Create the request to Moviedb, and open the connection
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();
        //Reading input stream into a String
        InputStream inputStream = urlConnection.getInputStream();
        StringBuffer buffer = new StringBuffer();
        if (inputStream == null){
            //nothing to do
            return null;
        }
        reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine())!=null){
            buffer.append(line+"\n");
        }
        if (buffer.length()==0){
            //empty stream. No parsing
            return null;
        }
        movieJsonStr=buffer.toString();
    }catch (IOException e){
        e.printStackTrace();
    }finally {
        if(urlConnection !=null){
            urlConnection.disconnect();
        }
        if (reader !=null){
            try {
                reader.close();
            } catch (final IOException e){
                e.printStackTrace();
            }
        }
    }
    try {
        return getMovieDataFromJason(movieJsonStr);
    }catch (JSONException e){
        e.printStackTrace();
    }
    return null;
}
@Override
    public void onPostExecute (ArrayList<Movie> movies){
    super.onPostExecute(movies);
    mCallback.onTaskComplete(movies);

}
 public interface TaskCallback {
     void onTaskComplete (ArrayList<Movie> movies);
 }
}
