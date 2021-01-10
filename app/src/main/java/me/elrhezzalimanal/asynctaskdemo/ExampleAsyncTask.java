package me.elrhezzalimanal.asynctaskdemo;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

//use this approach of creating a AsyncTask class if you don't want to interact with your UI elements

public class ExampleAsyncTask extends AsyncTask<Integer, Void, Void> {
    private static final String TAG = "ExampleAsyncTask";

    //this method runs in the worker thread,perform long task here
    @Override
    protected Void doInBackground(Integer... integers) {
        for (int i=0; i<integers[0]; i++) {
            Log.d(TAG, "doInBackground: i was: " + i);
            SystemClock.sleep(1000);
        }
        return null;
    }
}
