package com.harrisonbrock.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.harrisonbrock.movieapp.Model.Movie;
import com.harrisonbrock.movieapp.Network.DataManager;
import com.harrisonbrock.movieapp.Network.MovieUtils;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mPopText;
    private TextView mReleaseDate;
    private TextView mOverview;
    private ImageView mPoster;
    public static final String MOVIE_POSITION = "com.harrisonbrokc.moveApp.MOVIE_PORTION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        createUI();

        Intent intent = getIntent();

        int index = intent.getIntExtra(MOVIE_POSITION, -1);

        Movie movie = DataManager.getInstance().getMovies().get(index);

        mTitle.setText(movie.getTitle());

        mPopText.setText(String.valueOf(movie.getPopularity()));

       mReleaseDate.setText(movie.getRelaseDate());

        mOverview.setText(movie.getOverview());

        Picasso.with(this)
                .load(MovieUtils.getBasePosterUrl()
                + "w185"
                + movie.getPosterUrl())
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
