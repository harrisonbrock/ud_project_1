package com.harrisonbrock.movieapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.harrisonbrock.movieapp.Model.Movie;
import com.harrisonbrock.movieapp.Network.NetworkUtils;

import java.util.List;

/**
 * Created by harry on 8/23/17.
 */

public class MoviePosterLoader extends AsyncTaskLoader<List<Movie>> {

    private static final String LOG_TAG = MoviePosterLoader.class.getSimpleName();
    private String mSortMoviesBy;

    public MoviePosterLoader(Context context, String sortMoviesBy) {
        super(context);

        mSortMoviesBy = sortMoviesBy;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {

        if (mSortMoviesBy == null) return null;

        return NetworkUtils.fetchMovieData(mSortMoviesBy);
    }
}
