package com.harrisonbrock.movieapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.harrisonbrock.movieapp.Model.Movie;
import com.harrisonbrock.movieapp.Network.MovieUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by harry on 8/23/17.
 */

public class MoviePosterAdapter extends ArrayAdapter<Movie> {

    public MoviePosterAdapter(Context context, List<Movie> movies) {

        super(context, 0, movies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View imageItem = convertView;

        if (imageItem == null) {

            imageItem = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_poster, parent, false);
        }

        Movie currentMovie = getItem(position);
        ImageView moviePoster =imageItem.findViewById(R.id.img_movie_poster);

        Picasso.with(getContext()).load(MovieUtils.getBasePosterUrl()
                + "w500"
                + currentMovie.getPosterUrl())
                .into(moviePoster);

        return imageItem;
    }
}
