package com.example.ponderwonder.clock;

import android.content.Context;
import android.net.ConnectivityManager;
import android.telecom.ConnectionRequest;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchWeather {

    private static final String OPEN_WEATHER_MAP_API = "0bd127354646aa1dfbeb1db2892f291d";
    static final String USER_AGENT = "Weather Notification (Linux; Android)";

    public static boolean checkInternetStatus(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo() != null;
    }

    public static String getWeatherResponse(Context context, String city){
        URL url;
        HttpURLConnection connection = null;
        try {

            url = new URL(String.format(OPEN_WEATHER_MAP_API, city));
            connection = (HttpURLConnection) url.openConnection();

            // TODO: Need to request connection
            // connection.Request;
            connection.setRequestProperty("content-type", "application/json;  charset=utf-8");
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(false);

            int code = connection.getResponseCode();
            InputStream is;

            // Check if response code returns with Error;
            if (code != HttpURLConnection.HTTP_OK) {
                is = connection.getErrorStream();
            } else {
                is = connection.getInputStream();
            }

            // Create Buffered Reader with the InputStream
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            // Setup response for data that we will use
            String line;
            StringBuffer response = new StringBuffer();

            // Add content to response
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }

            rd.close();
            Log.d("FetchWeather", "Response is = " + response.toString());
            return response.toString();
        } catch (Exception e) {
            return null;
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
    }

}

