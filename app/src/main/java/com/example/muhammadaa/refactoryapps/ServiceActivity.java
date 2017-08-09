package com.example.muhammadaa.refactoryapps;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ServiceActivity extends FragmentActivity {

    ListView mainList;
    private String[] texts = {"Hallo","Perkenalkan","Nama","Saya","Adalah","Muhammad", "Ari", "Anggara"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_service);
        mainList = (ListView) findViewById(R.id.lView);
        mainList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new ArrayList<String>()));
        new MyTask().execute();
    }

    class MyTask extends AsyncTask<Void, String, Void>{

        private ArrayAdapter<String> adapter;
        private int count=0;

        @Override
        protected void onPreExecute() {
            adapter=(ArrayAdapter<String>) mainList.getAdapter();
            setProgressBarIndeterminate(false);
            setProgressBarVisibility(true);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            for (String item:texts){
                publishProgress(item);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            count++;
            setProgress((int)(((double)count/texts.length)*10000));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            setProgressBarVisibility(false);
            Toast.makeText(getApplicationContext(),"All item added succesfull",Toast.LENGTH_SHORT).show();
        }
    }
}
