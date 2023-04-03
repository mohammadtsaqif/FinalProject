package com.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class Laundry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry);
    }

    public void openLaundry(View view) {
        Intent i = new Intent(getApplicationContext(), HomepageLaundry.class);
        startActivity(i);
    }
}