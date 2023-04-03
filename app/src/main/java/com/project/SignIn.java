package com.project;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.project.MainActivity.*;

public class SignIn extends AppCompatActivity {

    EditText email, password;
    Button btn;
    ProgressBar pb;
     int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        btn = findViewById(R.id.login_button);
        pb = findViewById(R.id.progressBarSignin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();


                if(Email.length() == 0 || Password.length() == 0){
                    Toast.makeText(SignIn.this,"Please fill all details", Toast.LENGTH_SHORT).show();
                }
                if(isValidEmail(Email) ) {
                        // Show the progress bar
                        pb.setVisibility(View.VISIBLE);

                        new Thread(() -> {
                            try {
                                //load the MySQL JDBC driver class dynamically at runtime
                                Class.forName("com.mysql.jdbc.Driver");
                                //establish a connection to a MySQL database using the JDBC driver
                                Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                                String query = "SELECT * FROM User WHERE email = ? AND password = ?";
                                PreparedStatement stmt = con.prepareStatement(query);
                                stmt.setString(1, Email);
                                stmt.setString(2, Password);

                                ResultSet rs = stmt.executeQuery();
                                boolean exists = rs.next();
                                if (exists) {
                                    // assign the user ID to the global variable
                                    userid = rs.getInt("id");
                                }
                                runOnUiThread(() -> {
                                    //Hide the progress bar
                                    pb.setVisibility(View.INVISIBLE);
                                    if (exists) {
                                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(SignIn.this, Home.class);
                                        i.putExtra("userid", userid); // Pass the userid as an extra
                                        startActivity(i);
                                        finish();

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                con.close();
                            } catch (SQLException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }).start();
                }
            }
        });
    }
    private boolean isValidEmail(String Email) {
        // Add your own email validation logic here
        return Email.contains("@");
    }

}