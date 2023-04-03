package com.project;

import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    public void openMenuorderFull(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Thankyou for using our services! ", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
}