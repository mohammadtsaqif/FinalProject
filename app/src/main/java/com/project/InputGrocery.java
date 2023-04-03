package com.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class InputGrocery extends AppCompatActivity {
    private EditText item1, item2, item3, item4, item5, item6, item7, item8;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_grocery);

        item1 = findViewById(R.id.Item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item5 = findViewById(R.id.item5);
        item6 = findViewById(R.id.item6);
        item7 = findViewById(R.id.item7);
        item8 = findViewById(R.id.item8);
        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });
    }

    public void sendData(){

        String t1 = item1.getText().toString();
        String t2 = item2.getText().toString();
        String t3 = item3.getText().toString();
        String t4 = item4.getText().toString();
        String t5 = item5.getText().toString();
        String t6 = item6.getText().toString();
        String t7 = item7.getText().toString();
        String t8 = item8.getText().toString();


        Intent i = new Intent(InputGrocery.this, InputGroceryNew.class);

        i.putExtra("item1",t1);
        i.putExtra("item2",t2);
        i.putExtra("item3",t3);
        i.putExtra("item4",t4);
        i.putExtra("item5",t5);
        i.putExtra("item6",t6);
        i.putExtra("item7",t7);
        i.putExtra("item8",t8);

        startActivity(i);
    }

}