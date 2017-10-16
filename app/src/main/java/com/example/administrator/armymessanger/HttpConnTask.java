package com.example.administrator.armymessanger;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpConnTask extends AsyncTask<String, String, Long>{
    HttpURLConnection client;
    URL url = null;

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    protected Long doInBackground(String... args){
        HttpURLConnection client = null;
        if(args[0] == "GET")
            getHttpRequest(args[1]);
        if(args[0] == "GET")
            postHttpRequest(args[1]);
        return null;
    };

    protected void onProgressUpdate(String... progress){

    }

    protected void onPostExecute(Long result) {
    }

    public void setUrl(String strURL){
        try {
            url = new URL(strURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void getHttpRequest(String strURL){
        try {
            url = new URL(strURL);
            client = (HttpURLConnection) url.openConnection();
            client.setRequestProperty("User-Agent", "my-rest-app-v0.1");
            client.setRequestMethod("GET");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    private void postHttpRequest(String strURL){
        try {
            url = new URL(strURL);
            client = (HttpURLConnection) url.openConnection();
            client.setRequestProperty("User-Agent", "my-rest-app-v0.1");
            client.setRequestMethod("POST");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };


}

