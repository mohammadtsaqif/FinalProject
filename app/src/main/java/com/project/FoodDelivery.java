package com.project;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class FoodDelivery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fooddelivery);
    }
    public void openFoodDeliveryNextPage(View view){
        Intent i = new Intent(this, Menu.class );
        startActivity(i);
    }
}