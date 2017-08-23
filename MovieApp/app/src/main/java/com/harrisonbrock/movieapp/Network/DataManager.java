package com.harrisonbrock.movieapp.Network;

import com.harrisonbrock.movieapp.Model.Movie;

import java.util.List;

/**
 * Created by harry on 8/23/17.
 */

public class DataManager {

    private static final String LOG_TAG = DataManager.class.getSimpleName();

    private static DataManager instance = null;

    private List<Movie> mMovies;

    public static DataManager getInstance(String sortBy) {

        if(instance == null) {
            instance = new DataManager();
            instance.initializeMovie(sortBy);
        }

        return instance;
    }

    public static DataManager getInstance() {

        return instance;
    }

    public List<Movie> getMovies() {

        return mMovies;
    }

    private void initializeMovie(String sortBy) {

        mMovies = NetworkUtils.fetchMovieData(sortBy);
    }

}
