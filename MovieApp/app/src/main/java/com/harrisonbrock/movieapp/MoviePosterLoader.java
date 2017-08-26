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
    private String mSortBy;

    public MoviePosterLoader(Context context, String sortBy) {
        super(context);

        mSortBy = sortBy;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {

        if (mSortBy == null) return null;

        return NetworkUtils.fetchMovieData(mSortBy);
    }
}
