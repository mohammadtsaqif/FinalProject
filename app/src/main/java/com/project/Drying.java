package com.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Drying extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drying);
    }
    public void onClickDryingNext(View view){
        Button btnNext = (Button) findViewById(R.id.btnNext);
        Intent intent = new Intent(getApplicationContext(), HomepageLaundry.class);
        Toast toast = Toast.makeText(getApplicationContext(), "Thankyou for using our services! ", Toast.LENGTH_SHORT);
        toast.show();
        startActivity(intent);
    }
}