package com.example.android.popularmovie;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiao on 19/11/2015.
 */
public class Movie implements Parcelable {

    public static final String PARCEL_TAG = "movie_tag";

    private String id;
    private String title;
    private String posterUrl;
    private String overview;
    private String release_date;
    private float vote_average;
    private boolean favorite;
    private String runtime;

    public Movie(){

    }

    public Movie (String id, String title,String posterUrl,String overview,String release_date,float vote_average){
        this.id=id;
        this.title = title;
        this.posterUrl = posterUrl;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_average=vote_average;
    }

    private Movie (Parcel in){
        id = in.readString();
        title = in.readString();
        posterUrl = in.readString();
        overview = in.readString();
        release_date =in.readString();
        vote_average = in.readFloat();
        favorite =in.readInt()==0;
    }
    @Override
    public int describeContents(){
        return  0;
    }
    @Override
    public void writeToParcel (Parcel parcel, int i){
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(posterUrl);
        parcel.writeString(overview);
        parcel.writeString(release_date);
        parcel.writeFloat(vote_average);
        parcel.writeInt(favorite ? 0:1);
    }
   public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>(){
    @Override
   public  Movie createFromParcel(Parcel parcel) {
        return new Movie(parcel);
    }
        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
   };

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPosterUrl() {
        return posterUrl;
    }
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getRelease_date() {
        return release_date;
    }
    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
    public float getVote_average() {
        return vote_average;
    }
    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }
}
