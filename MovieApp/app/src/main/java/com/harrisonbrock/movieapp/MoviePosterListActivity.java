package com.harrisonbrock.movieapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.harrisonbrock.movieapp.Model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviePosterListActivity extends AppCompatActivity
implements LoaderManager.LoaderCallbacks<List<Movie>>{

    private GridView mGridView;
    private MoviePosterAdapter mAdapter;
    private String mSortMoviesBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_poster_list);

        mSortMoviesBy = "popular";
        // create UI
        createUI();
        // setup adapter
        setupAdapter();
        // start Loader Manager
        startLoader();

        // set on click listener
        // when a user clicks on a poster the user
        // will be sent to a detail screen for that movie
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = createMovieDetail(position);
                startActivity(intent);
            }
        });

    }


    /**
     * startLoader
     * This will start the Loader Manager
     */
    private void startLoader() {
        final LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null, this);
    }


    /**
     * setup Adapter
     * This will create and setup Adapter
     */
    private void setupAdapter() {

        mAdapter = new MoviePosterAdapter(this, new ArrayList<Movie>());

        mGridView.setAdapter(mAdapter);
    }


    /**
     * createMovieDetail
     * This will pass the movie's data to the detail screen
     * @param position this is the position of the item in the list
     * @return a new Intent
     */
    private Intent createMovieDetail(int position) {

        Intent intent = new Intent(MoviePosterListActivity.this, MovieDetailActivity.class);

        intent.putExtra(MovieDetailActivity.CURRENT_MOVIE_TITLE, mAdapter
                .getItem(position).getTitle());

        intent.putExtra(MovieDetailActivity.CURRENT_MOVIE_RELEASE_DATE, mAdapter
                .getItem(position).getRelaseDate());

        intent.putExtra(MovieDetailActivity.CURRENT_MOVIE_OVERVIEW, mAdapter
                .getItem(position).getOverview());

        intent.putExtra(MovieDetailActivity.CURRENT_MOVIE_POPULARITY, mAdapter
                .getItem(position).getPopularity());

        intent.putExtra(MovieDetailActivity.CURRENT_MOVIE_POSTER_URL, mAdapter
                .getItem(position).getPosterUrl());

        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        // if top top rated selected
        if (id == R.id.mm_top_rated) {
            Log.v("Options Item", "Clicked");
           mSortMoviesBy = "top_rated";
            restartLoader();
        }

        // if popular is selected
        if (id == R.id.mm_popular) {
            mSortMoviesBy = "popular";
            restartLoader();
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * restartLoader
     * This will clear the adapter and restart the loader
     */
    private void restartLoader() {
        mAdapter.clear();
        getLoaderManager()
                .restartLoader(1, null, MoviePosterListActivity.this);
    }


    /**
     * createUI
     * This will create the UI for the screen
     */
    private void createUI() {

        mGridView = (GridView) findViewById(R.id.grid);

    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {
        return new MoviePosterLoader(this, mSortMoviesBy);
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
