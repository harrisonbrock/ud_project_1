package com.harrisonbrock.movieapp.Network;

import android.net.Uri;
import android.util.Log;

import com.harrisonbrock.movieapp.Model.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by harry on 8/23/17.
 */

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    public NetworkUtils() {
    }

    public static List<Movie> fetchMovieData(String requestUrl) {

        URL url = buildUrl(requestUrl);

        String movieJson = null;

        try {

            movieJson = createHttpRequest(url);
        }
        catch (IOException e) {
            Log.e(LOG_TAG, "Problem creating HTTP request", e);
        }

        return MovieUtils.extractMovieDataFromJson(movieJson);
    }

    /**
     * buildUrl
     * This will movies by rating or popularity
     * @param getMoviesBy
     * @return URL
     */
    private static URL buildUrl(String getMoviesBy) {
        URL url = null;

        // Build Uri
        Uri newUri = Uri.parse(MovieUtils.getBaseMovieUrl()).buildUpon()
                // add the type of search
                .appendPath(getMoviesBy)
                // add the API Key
                .appendQueryParameter("api_key", MovieUtils.getApiKey())
                .build();
        try {
            url = new URL(newUri.toString());
        }
        catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error creating URL", e);
        }

        Log.v(LOG_TAG, "URL Created" + url);

        return url;
    }

    /**
     * @param url
     * @return
     * @throws IOException
     */
    public static String createHttpRequest (URL url) throws IOException {

        String jsonRepsone = "";

        if (url == null) return jsonRepsone;

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {

            httpURLConnection = (HttpURLConnection) url.openConnection();
            // Set read time on connection to 10 seconds
            httpURLConnection.setReadTimeout(10000);
            // Set connection to timeout after 15 seconds;
            httpURLConnection.setConnectTimeout(15000);
            // Set request to GET
            httpURLConnection.setRequestMethod("GET");
            // connect to url
            httpURLConnection.connect();

            // Make sure response code is 200
            if(httpURLConnection.getResponseCode() == 200) {

                // set input stream to the url input stream
                inputStream = httpURLConnection.getInputStream();

                // set jsonRepsone
                jsonRepsone = readFromStream(inputStream);
            }
            else {
                Log.e(LOG_TAG, "Error response code " + httpURLConnection.getResponseCode());
            }
        }
        catch (IOException e) {
            Log.e(LOG_TAG, "Eror getting data", e);
        }
        finally {
            if (httpURLConnection != null) httpURLConnection.disconnect();

            if (inputStream != null) inputStream.close();
        }
        return jsonRepsone;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();

        if (inputStream != null) {

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();

            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }

        }

        return output.toString();
    }

}