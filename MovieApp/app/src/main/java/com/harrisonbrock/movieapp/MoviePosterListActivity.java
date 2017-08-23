package com.harrisonbrock.movieapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.harrisonbrock.movieapp.Model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviePosterListActivity extends AppCompatActivity
implements LoaderManager.LoaderCallbacks<List<Movie>>{

    private GridView mGridView;
    private MoviePosterAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_poster_list);

        mGridView = (GridView) findViewById(R.id.grid);

        mAdapter = new MoviePosterAdapter(this, new ArrayList<Movie>());

        mGridView.setAdapter(mAdapter);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null, this);
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {
        return new MoviePosterLoader(this, "popular");
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
        mAdapter.addAll(movies);

    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        mAdapter.clear();
    }
}
