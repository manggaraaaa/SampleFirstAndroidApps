package com.example.muhammadaa.refactoryapps;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button login;
    TextView pesan;
    EditText username, password;

    int counter = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopService(new Intent(this,threadService.class));

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        pesan = (TextView) findViewById(R.id.tMessage);
        pesan.setVisibility(View.GONE);

        login = (Button) findViewById(R.id.bLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();

            }
        });
    }

    public void login(){
        if(username.getText().toString().equals("ari") &&
                password.getText().toString().equals("ari")) {

            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        else {

            Toast.makeText(getApplicationContext(), "Username dan Password Salah", Toast.LENGTH_SHORT).show();
            Log.d("ssss", "else");

            counter--;


            if(counter == 0){
                pesan.setVisibility(View.VISIBLE);
                pesan.setBackgroundColor(Color.RED);
                pesan.setText("Anda sudah tiga kali salah. Silahkan hub. Admin");
                login.setEnabled(false);

            }


        }


    }
}
