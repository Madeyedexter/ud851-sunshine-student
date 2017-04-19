package com.example.android.sunshine.sync;//  DONE (1) Create a class called SunshineSyncTask
//  DONE (2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather
//      DONE (3) Within syncWeather, fetch new weather data
//      DONE (4) If we have valid results, delete the old data and insert the new

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;
import com.example.android.sunshine.utilities.SunshineWeatherUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class SunshineSyncTask{

    synchronized public static void syncWeather(Context context){
        URL url = NetworkUtils.getUrl(context);

        try {
            String jsonResponse = NetworkUtils.getResponseFromHttpUrl(url);
            ContentValues[] contentValues = OpenWeatherJsonUtils.getWeatherContentValuesFromJson(context, jsonResponse);
            if(contentValues!=null && contentValues.length!=0){
                ContentResolver resolver = context.getContentResolver();
                //delete all old data
                resolver.delete(WeatherContract.WeatherEntry.CONTENT_URI,null,null);
                //insert new data
                resolver.bulkInsert(WeatherContract.WeatherEntry.CONTENT_URI,contentValues);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}