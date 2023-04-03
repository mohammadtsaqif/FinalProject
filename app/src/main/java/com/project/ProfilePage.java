package com.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class ProfilePage extends AppCompatActivity {

    String name;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        TextView tvName = (TextView)findViewById(R.id.Username) ;
        TextView tvEmail = (TextView)findViewById(R.id.Email);
        TextView tvPassword = (TextView)findViewById(R.id.passwordAnswer);

        //TextView tvNoTel = (TextView)findViewById(R.id.mobileAnswer);
        //TextView tvVillage = (TextView)findViewById(R.id.villageAnswer);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");

        //noTel = intent.getStringExtra("noTel");
        //village = intent.getStringExtra("village");

        tvName.setText(name);
        tvEmail.setText(email);
        tvPassword.setText(password);

        //tvNoTel.setText(noTel);
        //tvVillage.setText(village);
    }

    public void openBusinessProfile(View view) {
        Intent i = new Intent(getApplicationContext(), BusinessProfilePage.class);
        i.putExtra("name",name);
        i.putExtra("email",email);
        startActivity(i);
    }

    public void openEdit(View view){
        Intent i = new Intent(getApplicationContext(), EditDetails.class);
        i.putExtra("name",name);
        i.putExtra("email",email);
        i.putExtra("password",password);
        //i.putExtra("village",village);
        startActivity(i);
    }
}
