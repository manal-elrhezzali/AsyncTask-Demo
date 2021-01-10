package me.elrhezzalimanal.asynctaskdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtNumber;

    private SecondAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        ExampleAsyncTask asyncTask = new ExampleAsyncTask();
//        asyncTask.execute(10);

        asyncTask = new SecondAsyncTask();
        asyncTask.execute(10);

    }

    //frees the resources used by the AsyncTask when the activity gets destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != asyncTask) {
            if (!asyncTask.isCancelled()) {
                asyncTask.cancel(true);//cancels the task even if it hasn't finished
            }
        }
    }

    private class SecondAsyncTask extends AsyncTask<Integer, Integer, String> {

        //gets executed before doInbackground and runs in Main thread
        // use it to initialize your UI elements or so
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtNumber = findViewById(R.id.txtNumber);
        }

        //runs in the worker thread
        @Override
        protected String doInBackground(Integer... integers) {
            for (int i=0; i<integers[0]; i++) {
                publishProgress(i);//sends i to onProgressUpdate method
                SystemClock.sleep(1000); //equivalent to Thread.sleep(1000)
            }
            return "Finished!";// the string Finished gets passed to onPostExecute as a parameter
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            txtNumber.setText(String.valueOf(values[0]));
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtNumber.setText(s);
        }
    }
}
