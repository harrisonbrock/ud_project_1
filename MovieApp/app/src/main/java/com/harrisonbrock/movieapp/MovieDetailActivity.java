package com.harrisonbrock.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.harrisonbrock.movieapp.Network.MovieUtils;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mPopText;
    private TextView mReleaseDate;
    private TextView mOverview;
    private ImageView mPoster;

    // String keys to use to get Intent values
    public static final String CURRENT_MOVIE_TITLE = "com.harrisonbrock.MovieAPP.CURRENT_MOVIE_TITLE";
    public static final String CURRENT_MOVIE_RELEASE_DATE = "com.harrisonbrock.MovieAPP.CURRENT_MOVIE_RELEASE_DATE";
    public static final String CURRENT_MOVIE_OVERVIEW = "com.harrisonbrock.MovieAPP.CURRENT_MOVIE_OVERVIEW";
    public static final String CURRENT_MOVIE_POPULARITY = "com.harrisonbrock.MovieAPP.CURRENT_MOIVE_POPULARITY";
    public static final String CURRENT_MOVIE_POSTER_URL = "com.harrisonbrock.MovieAPP.CURRENT_MOVIE_POSTER_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        createUI();

        setMovieDetails();

    }


    /**
     * setMovieDetails
     * This will get the movie details from the Intent
     */
    private void setMovieDetails() {

        Intent intent = getIntent();

        String title = intent.getStringExtra(CURRENT_MOVIE_TITLE);
        String releaseDate = intent.getStringExtra(CURRENT_MOVIE_RELEASE_DATE);
        String overview = intent.getStringExtra(CURRENT_MOVIE_OVERVIEW);
        double popularity = intent.getDoubleExtra(CURRENT_MOVIE_POPULARITY, -1);
        String posterUrl = intent.getStringExtra(CURRENT_MOVIE_POSTER_URL);

        mTitle.setText(title);

        mPopText.setText("Popularity: " + String.valueOf(popularity));

        mReleaseDate.setText("Release Date: " + releaseDate);

        mOverview.setText(overview);

        Picasso.with(this)
                .load(MovieUtils.getBasePosterUrl()
                + "w185"
                + posterUrl)
                .into(mPoster);
    }


    /**
     * CreateUI
     * This will create the UI for the screen
     */
    private void createUI() {


        mTitle = (TextView) findViewById(R.id.tv_movie_detail_title);
        mPopText = (TextView) findViewById(R.id.tv_movie_detail_pop);
        mReleaseDate = (TextView) findViewById(R.id.tv_movie_detail_rd);
        mOverview = (TextView) findViewById(R.id.tv_movie_detail_overview);
        mPoster = (ImageView) findViewById(R.id.img_movie_detail_poster);

    }
}
