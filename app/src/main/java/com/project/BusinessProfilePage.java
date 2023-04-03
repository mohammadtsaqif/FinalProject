    package com.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BusinessProfilePage extends AppCompatActivity {

    String name;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_profile);

        TextView tvName = (TextView)findViewById(R.id.Username) ;
        TextView tvEmail =(TextView)findViewById(R.id.Email);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");

        tvName.setText(name);
        tvEmail.setText(email);
    }

    public void openUserProfile(View view) {
        finish();
    }
}
