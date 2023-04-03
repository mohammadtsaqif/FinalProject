package com.project;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

public class GetstartedCleaning extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getstartedcleaning);
    }

    public void openCleaningPage(View view){
        Intent i = new Intent(this, Cleaning.class);
        startActivity(i);
    }
}