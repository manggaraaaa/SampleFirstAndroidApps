package com.example.muhammadaa.refactoryapps;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class HomeActivity extends FragmentActivity implements HomeFragment.Replaces {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportFragmentManager().beginTransaction().add(R.id.vFooter, new logoutFragment()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.vBody, new HomeFragment()).commit();
    }

    @Override
    public void ReplaceFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.vBody, new TampilFragment()).commit();
    }
}
