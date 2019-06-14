package com.example.ponderwonder.clock;

import android.content.Context;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.HttpUrl;

public class FetchWeather {

    private static final String OPEN_WEATHER_MAP_API = "0bd127354646aa1dfbeb1db2892f291d";

    public static JSONObject getJSON(Context context, String city) {
        try{
            URL url = new URL(String.format(OPEN_WEATHER_MAP_API, city));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // TODO: Need to request connection
            // connection.Request;
            return null;
        } catch(Exception e) {
            return null;
        }
    }
}
