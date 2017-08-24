package com.harrisonbrock.movieapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import com.harrisonbrock.movieapp.Model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviePosterListActivity extends AppCompatActivity
implements LoaderManager.LoaderCallbacks<List<Movie>>{

    private GridView mGridView;
    private Spinner mSpinner;
    private MoviePosterAdapter mAdapter;
    private String mQuery;
    private List<String> mSortByList;
    private ArrayAdapter<String> mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_poster_list);


        createUI();

        mSortByList = new ArrayList<>();

        mSortByList.add("By popular");
        mSortByList.add("By top_rated");

        mArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mSortByList);
        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mArrayAdapter);

        getQuery();

        mAdapter = new MoviePosterAdapter(this, new ArrayList<Movie>());

        mGridView.setAdapter(mAdapter);

        final LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null, this);

        // onItemClick
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MoviePosterListActivity.this, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.MOVIE_POSITION, position);
                startActivity(intent);
            }
        });

    }

    private void createUI() {

        mGridView = (GridView) findViewById(R.id.grid);

        mSpinner = (Spinner) findViewById(R.id.sp_sortBy);

    }

    private String getQuery() {

        String tempSortBy = mSpinner.getSelectedItem().toString();

        if (tempSortBy.equals(mArrayAdapter.getItem(0))) mQuery = "popular";
        else mQuery = "top_rated";

        return mQuery;
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {
        return new MoviePosterLoader(this, mQuery);
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
