package com.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class EditDetails extends AppCompatActivity {
    EditText editName,editEmail,editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        editName = findViewById(R.id.editTextName) ;
        editEmail = findViewById(R.id.editTextEmail);
        editPassword = findViewById(R.id.editPasswordEdit);


        Intent intent = getIntent();

        editName.setHint(getIntent().getStringExtra("name"));
        editEmail.setHint(getIntent().getStringExtra("email"));
        editPassword.setHint(getIntent().getStringExtra("password"));

        //editNoTel.setHint(getIntent().getStringExtra("noTel"));
        //editVillage.setHint(getIntent().getStringExtra("village"));
    }

    public void save(View view) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/ucare_database", "ucare_database", "ucarepassword");
            String insertQuery = "UPDATE `User` SET `name` = '?',`email` = '?',`password` = '?' WHERE id = ?";
            PreparedStatement updateStmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            updateStmt.setString(1, editName.getText().toString());
            updateStmt.setString(2, editEmail.getText().toString());
            updateStmt.setString(3, editPassword.getText().toString());
            updateStmt.setInt(4,1);

            updateStmt.executeUpdate();
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        Intent i = new Intent(getApplicationContext(), MainProfile.class);
        startActivity(i);
    }
}