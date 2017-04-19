package com.example.android.sunshine.sync;/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// DONE (2) Make sure you've imported the jobdispatcher.JobService, not job.JobService

// DONE (3) Add a class called SunshineFirebaseJobService that extends jobdispatcher.JobService

import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class SunshineFirebaseJobService extends JobService{

    private AsyncTask mAsyncTask;


    @Override
    public boolean onStartJob(@NonNull final JobParameters job) {
        mAsyncTask=new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                SunshineSyncTask.syncWeather(getApplicationContext());
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(job,false);
            }
        };
        mAsyncTask.execute();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        if(mAsyncTask!=null) mAsyncTask.cancel(true);

        return true;
    }
}

//  DONE (4) Declare an ASyncTask field called mFetchWeatherTask

//  DONE (5) Override onStartJob and within it, spawn off a separate ASyncTask to sync weather data
//              DONE (6) Once the weather data is sync'd, call jobFinished with the appropriate arguments

//  DONE (7) Override onStopJob, cancel the ASyncTask if it's not null and return true
