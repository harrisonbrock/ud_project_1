package com.harrisonbrock.movieapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by harry on 8/23/17.
 */

public class Movie implements Parcelable {

    private static final String LOG_TAG = Movie.class.getSimpleName();
    private String mTitle;
    private String mPosterUrl;
    private String mOverview;
    private Double mPopularity;
    private String mRelaseDate;

    public Movie(String title, String posterUrl, String overview, Double popularity, String relaseDate) {
        mTitle = title;
        mPosterUrl = posterUrl;
        mOverview = overview;
        mPopularity = popularity;
        mRelaseDate = relaseDate;
    }

    protected Movie(Parcel in) {
        mTitle = in.readString();
        mPosterUrl = in.readString();
        mOverview = in.readString();
        mPopularity = in.readDouble();
        mRelaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mPosterUrl);
        parcel.writeString(mOverview);
        parcel.writeDouble(mPopularity);
        parcel.writeString(mRelaseDate);
    }

    public static String getLogTag() {
        return LOG_TAG;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPosterUrl() {
        return mPosterUrl;
    }

    public String getOverview() {
        return mOverview;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public String getRelaseDate() {
        return mRelaseDate;
    }
}