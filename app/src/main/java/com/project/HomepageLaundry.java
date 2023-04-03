package com.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class HomepageLaundry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_laundry);
    }

    public void openWashing(View view) {
        Intent i = new Intent(getApplicationContext(), Washing.class);
        startActivity(i);

    }

    public void openDrying(View view) {
        Intent i = new Intent(getApplicationContext(), Drying.class);
        startActivity(i);

    }













}