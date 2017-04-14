package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private static final String TAG  = DetailActivity.class.getSimpleName();


    TextView detailsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailsTV = (TextView)findViewById(R.id.tv_weather_details);

        Intent intent = getIntent();
        Log.d(TAG,""+intent.hasExtra(Intent.EXTRA_TEXT));
            String text = intent.getStringExtra("weather");
            detailsTV.setText(text);


    }
}