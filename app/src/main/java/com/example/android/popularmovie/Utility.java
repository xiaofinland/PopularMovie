package com.example.android.popularmovie;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by xiao on 19/11/2015.
 */
public class Utility {
    private static final String posterUrl = "http://image.tmdb.org/t/p/";
    // List of the different poster sizes, in case I want to dynamically change the size.
         private static final String imgXXS = "w92";
         private static final String imgXS = "w154";
         private static final String imgS = "w185";
         private static final String imgM = "w342";
         private static final String imgL = "w500";
         private static final String imgXL = "w780";
         private static final String imgOrg = "original";

    public static String getKey(Context context) {
        // GitHub ignores the file that the key is stored in,
        // so I don't have to delete any code before committing.
        return context.getString(R.string.api_key);
    }
    public static String getPreferredSort(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(context.getString(R.string.pref_sorting_key), context.getString(R.string.pref_sort_popularity)) + ".desc";
        }
    public static String getSort(Context context) {
        // TODO: Once SettingsActivity is working, pull from there.
        return context.getString(R.string.pref_sort_popularity);
        }
    public static String getPosterUrl(String urlEnd) {
        return posterUrl + imgM + urlEnd;
       }

    //// TODO: add viedo player here in stage 2 


}
