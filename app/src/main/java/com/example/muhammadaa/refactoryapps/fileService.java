package com.example.muhammadaa.refactoryapps;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by muhammadaa on 09/08/17.
 */

public class fileService extends IntentService {

    public static final String TRANSACTION_DONE = "com.example.muhammadaa.refactoryapps";

    public fileService(){
        super(fileService.class.getName());
    }


    public fileService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.e("File", "Service Started");

        String ambilURL = intent.getStringExtra("url");

        downloadFile(ambilURL);

        Log.e("File", "Service Stopped");

        Intent i = new Intent(TRANSACTION_DONE);
        fileService.this.sendBroadcast(i);

    }

    protected void downloadFile(String theURL){

        String fileName = "myFile";

        try {

            FileOutputStream outputStream =
                    openFileOutput(fileName, Context.MODE_PRIVATE);

            URL fileURL = new URL(theURL);

            HttpURLConnection urlConnection =
                    (HttpURLConnection) fileURL.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            while ((bufferLength = inputStream.read(buffer)) > 0){
                outputStream.write(buffer, 0, bufferLength);
            }

            outputStream.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
