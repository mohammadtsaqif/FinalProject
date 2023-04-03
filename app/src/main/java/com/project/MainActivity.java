package com.project;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    static final String DB_URL = "jdbc:mysql://db4free.net:3306/ucare_database";
    static final String DB_USER = "ucare_database";
    static final String DB_PASSWORD = "ucarepassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void openApp(View view) {
        Intent i =new Intent(getApplicationContext(), Signup.class );
        startActivity(i);
        finish();
    }
}