package com.timecard.conortoner.timecard;

import android.os.AsyncTask;

import org.xml.sax.XMLReader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by alec on 23/10/15.
 */
public class MakeHttpCallTask extends AsyncTask<String, Void, String> {

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
    }
}
