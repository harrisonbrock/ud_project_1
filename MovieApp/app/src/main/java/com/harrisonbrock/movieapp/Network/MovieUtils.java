package com.harrisonbrock.movieapp.Network;

import android.text.TextUtils;

import com.harrisonbrock.movieapp.Model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harry on 8/23/17.
 */

public class MovieUtils {

    private static final String LOG_TAG = MovieUtils.class.getSimpleName();

    private static final String BASE_MOVIE_URL =
            "http://api.themoviedb.org/3/movie";

    private static final String BASE_FORWARD = "/";

    public static String getBaseForward() {
        return BASE_FORWARD;
    }

    private static final String BASE_POSTER_URL =
            "http://image.tmdb.org/t/p/";

    private static final String POSTER_SIZE = "";

    private static final String API_KEY =
            "6e91a6633ce2abd6d09f7c1f3d72ca32";


    public static String getApiKey() {
        return API_KEY;
    }

    public static String getLogTag() {
        return LOG_TAG;
    }

    public static String getBaseMovieUrl() {
        return BASE_MOVIE_URL;
    }

    public static String getBasePosterUrl() {
        return BASE_POSTER_URL;
    }

    public static String getPosterSize() {
        return POSTER_SIZE;
    }



    protected static List<Movie> extractMovieDataFromJson(String json) {

        if (TextUtils.isEmpty(json)) return null;

        List<Movie> movies = new ArrayList<>();

        // try and read json
        try {

            // create json object
            JSONObject baseJsonResponse = new JSONObject(json);

            // Get array of movie results
            JSONArray movieArray = baseJsonResponse.getJSONArray("results");

            for (int index = 0; index < movieArray.length(); index++) {

                JSONObject currentMovie = movieArray.getJSONObject(index);

                String title = currentMovie.getString("title");
                String posterUrl = currentMovie.getString("poster_path");
                String overview = currentMovie.getString("overview");
                Double popularity = currentMovie.getDouble("popularity");

                Movie newMovie = new Movie(title, posterUrl, overview, popularity);

                movies.add(newMovie);
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }


        return  movies;
    }
}
