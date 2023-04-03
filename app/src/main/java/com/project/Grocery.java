package com.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Grocery extends AppCompatActivity {
    private EditText name, mobile, village;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);

        button = (Button) findViewById(R.id.buttonStartGrocery);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                openLocationSelect();
            }
        });
    }

    public void openLocationSelect(){

        Intent intent = new Intent(Grocery.this, InputGrocery.class);
        startActivity(intent);
    }
}