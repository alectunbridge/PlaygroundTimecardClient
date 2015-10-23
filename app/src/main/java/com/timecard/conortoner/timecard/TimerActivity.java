package com.timecard.conortoner.timecard;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.transform.Result;

public class TimerActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.textView);
        new MakeHttpCallTask().execute("http://www.google.co.uk");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class MakeHttpCallTask extends AsyncTask<String, Void, String> {

        private Exception exception;

        protected String doInBackground(String... urls) {
            try {
                return "" + testHttpGet(urls[0]);
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }


        public int testHttpGet(String urlAsString) throws IOException {
            URL url = new URL(urlAsString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            } finally {
                urlConnection.disconnect();
            }
            return responseCode;
        }

        private int testHttpPost(String urlAsString) throws IOException {
            URL url = new URL(urlAsString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            try {
                urlConnection.setDoOutput(true);
                urlConnection.setChunkedStreamingMode(0);

                OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
                //writeStream(out);

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                //readStream(in);
            } finally {
                urlConnection.disconnect();
            }
            return responseCode;
        }

        protected void onPostExecute(String result) {
            //showDialog("Downloaded " + result + " bytes");
            textView.setText(result);
        }
    }
}
