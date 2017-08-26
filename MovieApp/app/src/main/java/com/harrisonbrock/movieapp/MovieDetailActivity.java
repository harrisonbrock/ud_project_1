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

    public static final String CURRENT_MOVIE_TITEL = "com.harrisonbrock.com.CURRENT_MOVIE_TITEL";
    public static final String CURRENT_MOVIE_RELEASE_DATE = "com.harrisonbrock.com.CURRENT_MOVIE_RELEASE_DATE";
    public static final String CURRENT_MOVIE_OVERVIEW = "com.harrisonbrock.CURRENT_MOVIE_OVERVIEW";
    public static final String CURRENT_MOVIE_POPULARITY = "com.harrisonbrock.CURRENT_MOIVE_POPULARITY";
    public static final String CURRENT_MOVIE_POSTER_URL = "com.harrisonbrock.CURRENT_MOVIE_POSTER_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        createUI();

        getMovieDetails();

    }

    private void getMovieDetails() {
        Intent intent = getIntent();


        String title = intent.getStringExtra(CURRENT_MOVIE_TITEL);
        String releaseDate = intent.getStringExtra(CURRENT_MOVIE_RELEASE_DATE);
        String overview = intent.getStringExtra(CURRENT_MOVIE_OVERVIEW);
        double popularity = intent.getDoubleExtra(CURRENT_MOVIE_POPULARITY, -1);
        String posterUrl = intent.getStringExtra(CURRENT_MOVIE_POSTER_URL);

        mTitle.setText(title);

        mPopText.setText(String.valueOf(popularity));

        mReleaseDate.setText(releaseDate);

        mOverview.setText(overview);

        Picasso.with(this)
                .load(MovieUtils.getBasePosterUrl()
                + "w185"
                + posterUrl)
                .into(mPoster);
    }

    private void createUI() {


        mTitle = (TextView) findViewById(R.id.tv_movie_detail_title);
        mPopText = (TextView) findViewById(R.id.tv_movie_detail_pop);
        mReleaseDate = (TextView) findViewById(R.id.tv_movie_detail_rd);
        mOverview = (TextView) findViewById(R.id.tv_movie_detail_overview);
        mPoster = (ImageView) findViewById(R.id.img_movie_detail_poster);

    }
}
