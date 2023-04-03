package com.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.project.MainActivity.*;

public class Signup extends AppCompatActivity {
    EditText username,email,password,confirm;
    Button btn;
    ProgressBar pb;
    int userid;

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        username = findViewById(R.id.enterUsername);
        email = findViewById(R.id.enterEmail2);
        password =findViewById(R.id.enterPassword);
        confirm = findViewById(R.id.enterConfirmPassword);
        btn = findViewById(R.id.create);
        pb = findViewById(R.id.progressBar);


        btn.setOnClickListener(view -> {
            String Username = username.getText().toString();
            String Email = email.getText().toString();
            String Password = password.getText().toString();
            String Confirm = confirm.getText().toString();

            if(Username.length()==0 || Email.length() ==0 || Password.length()==0){
                Toast.makeText(Signup.this, "Please fill all details", Toast.LENGTH_SHORT).show();
            }
            else {
                if(Password.compareTo(Confirm)==0){
                    if(isValid(Password)){

                        // Show the progress bar
                        pb.setVisibility(View.VISIBLE);
                        new Thread(() -> {
                            try {
                                // Load the MySQL JDBC driver class dynamically at runtime
                                Class.forName("com.mysql.jdbc.Driver");
                                // Establish a connection to a MySQL database using the JDBC driver.
                                Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                                // Check if the email is already in use by another user
                                String checkQuery = "SELECT * FROM User WHERE email = ?";
                                PreparedStatement checkStmt = con.prepareStatement(checkQuery);
                                checkStmt.setString(1, Email);
                                ResultSet rs = checkStmt.executeQuery();
                                if (rs.next()) {
                                    // Email is already in use by another user, show error message
                                    runOnUiThread(() -> {
                                        pb.setVisibility(View.INVISIBLE);
                                        Toast.makeText(Signup.this, "Email is already in used", Toast.LENGTH_SHORT).show();
                                    });
                                } else {
                                    // Email is not in use by another user, create new user account
                                    String insertQuery = "INSERT INTO User (name, email, password) VALUES (?, ?, ?)";
                                    PreparedStatement insertStmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                                    insertStmt.setString(1, Username);
                                    insertStmt.setString(2, Email);
                                    insertStmt.setString(3, Password);
                                    int rows = insertStmt.executeUpdate();
                                    if (rows > 0) {
                                        // Account created successfully, get the generated ID and store it in the global variable
                                        ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                                        if (generatedKeys.next()) {
                                            userid = generatedKeys.getInt(1);
                                        }

                                        // Show success message and go Home activity
                                        runOnUiThread(() -> {
                                            pb.setVisibility(View.INVISIBLE);
                                            Toast.makeText(Signup.this, "Account Created", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(Signup.this, Home.class);
                                            i.putExtra("userid", userid); // Pass the userid as an extra
                                            startActivity(i);
                                            finish();
                                        });
                                    } else {
                                        // Error occurred while creating account, show error message
                                        runOnUiThread(() -> {
                                            pb.setVisibility(View.INVISIBLE);
                                            Toast.makeText(getApplicationContext(), "Error occurred", Toast.LENGTH_SHORT).show();
                                        });
                                    }
                                }
                                con.close();
                            } catch (SQLException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }).start();

                    }
                    else{
                        Toast.makeText(Signup.this, "Password must contain at least 8 characters, having letter, digit and special symbol", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(Signup.this, "Password and Confirm password didn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static boolean isValid(String passwordCheck){
        int a=0;
        int b=0;
        int c=0;

        if (passwordCheck.length() >= 8) {
            for (int i = 0; i < passwordCheck.length(); i++) {
                if (Character.isLetter(passwordCheck.charAt(i))) {
                    a = 1;
                }
            }
            for (int k = 0; k < passwordCheck.length(); k++) {
                if (Character.isDigit(passwordCheck.charAt(k))) {
                    b = 1;
                }
            }
            for (int j = 0; j < passwordCheck.length(); j++) {
                char x = passwordCheck.charAt(j);
                if (x >= 33 && x <= 46 || x == 64) {
                    c = 1;
                }
            }
            return a == 1 && b == 1 & c == 1;
        }
        return false;
    }
    public void openSigning(View view) {

        startActivity(new Intent(Signup.this, SignIn.class ));
    }

}


