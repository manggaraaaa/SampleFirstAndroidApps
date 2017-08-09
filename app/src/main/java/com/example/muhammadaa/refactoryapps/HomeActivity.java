package com.example.muhammadaa.refactoryapps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class HomeActivity extends FragmentActivity implements HomeFragment.Replaces, TampilFragment.Rep  {

    EditText show;
    Button dService, dATService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        startService(new Intent(HomeActivity.this,threadService.class));

        show = (EditText) findViewById(R.id.eShow);
        show.setVisibility(View.GONE);

        dService = (Button) findViewById(R.id.bService);
        dATService = (Button) findViewById(R.id.bATService);
        dATService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(HomeActivity.this,threadService.class));
                Intent i = new Intent(HomeActivity.this,ServiceActivity.class);
                startActivity(i);
            }
        });


        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction(fileService.TRANSACTION_DONE);

        registerReceiver(downloadReceiver, intentFilter);

        getSupportFragmentManager().beginTransaction().add(R.id.vFooter, new logoutFragment()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.vBody, new HomeFragment()).commit();
    }

    @Override
    public void ReplaceFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.vBody, new TampilFragment()).commit();
    }

    @Override
    public void RepFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.vBody, new HomeFragment()).commit();

    }

    public void startFileService(View view) {

        Intent intent = new Intent(this, fileService.class);
        intent.putExtra("url","http://www.sample-videos.com/text/Sample-text-file-10kb.txt");
        this.startService(intent);
        show.setVisibility(View.VISIBLE);
        dATService.setVisibility(View.GONE);
    }

    private BroadcastReceiver downloadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("File", "Service Received");

            showFileContents();
        }
    };

    public void showFileContents(){

        StringBuilder sb;

        try {
            FileInputStream fis = this.openFileInput("myFile");

            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");

            BufferedReader bufferedReader = new BufferedReader(isr);
            sb = new StringBuilder();

            String line;

            while((line = bufferedReader.readLine()) != null){
                sb.append(line).append("\n");
            }

            show.setText(sb.toString());

            Toast.makeText(this,"Sukses Download",Toast.LENGTH_SHORT).show();
            dService.setEnabled(false);
            dService.setVisibility(View.GONE);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
