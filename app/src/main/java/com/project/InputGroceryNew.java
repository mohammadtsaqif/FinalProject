package com.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class InputGroceryNew extends AppCompatActivity {

    private TextView item1, item2, item3, item4, item5, item6, item7, item8;
    private Button duitNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_grocery_new);

        item1 = findViewById(R.id.brg1);
        item2 = findViewById(R.id.brg2);
        item3 = findViewById(R.id.brg3);
        item4 = findViewById(R.id.brg4);
        item5 = findViewById(R.id.brg5);
        item6 = findViewById(R.id.brg6);
        item7 = findViewById(R.id.brg7);
        item8 = findViewById(R.id.brg8);


        String dispt1 = getIntent().getStringExtra("item1");
        String dispt2 = getIntent().getStringExtra("item2");
        String dispt3 = getIntent().getStringExtra("item3");
        String dispt4 = getIntent().getStringExtra("item4");
        String dispt5 = getIntent().getStringExtra("item5");
        String dispt6 = getIntent().getStringExtra("item6");
        String dispt7 = getIntent().getStringExtra("item7");
        String dispt8 = getIntent().getStringExtra("item8");


        item1.setText(dispt1);
        item2.setText(dispt2);
        item3.setText(dispt3);
        item4.setText(dispt4);
        item5.setText(dispt5);
        item6.setText(dispt6);
        item7.setText(dispt7);
        item8.setText(dispt8);

        duitNow = findViewById(R.id.openqr);
        duitNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQR();
            }
        });

    }

    public void openQR(){
        Intent i = new Intent(InputGroceryNew.this, qrpay.class);
        startActivity(i);
    }

    public void btnCOD(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Thank you for using our services!", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
}