package com.project;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
public class Ehailing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ehailing);
    }
    public void openDestination(View view){
        Intent i = new Intent( getApplicationContext(), Destination.class);
        startActivity(i);
    }
}