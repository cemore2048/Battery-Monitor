package com.demoji.rafa.demoji;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by rafa on 7/23/16.
 */
public class FlickrApi {
    String key = FlickerApiKey.key;

    public static URL composeFlickrUrl(String filter, String key, int limit) {
        URL url = null;
        try {
            url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&text="
                    + filter + "&api_key=" + key + "&per_page=" + limit + "&format=json");

            Log.d("composed url", url.toString());
        }
        catch(MalformedURLException e) {
            Log.d("Flickr Api", "malformed url");
        }

        return url;
    }


}
