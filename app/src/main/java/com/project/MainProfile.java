package com.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.*;

public class MainProfile extends AppCompatActivity {

    String name;
    String email;
    String password;
    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);

        // Get the userid from the intent extra
        Intent intent = getIntent();
        // 0 is the default value if the extra is not found
        userid = intent.getIntExtra("userid", 0);


        TextView nameTextView = findViewById(R.id.name);

        new Thread(() -> {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/ucare_database", "ucare_database", "ucarepassword");
                String query = "SELECT name, email, password FROM User WHERE id = ?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, userid);
                ResultSet rs = stmt.executeQuery();
                boolean exists = rs.next();
                name = exists ? rs.getString("name").toUpperCase() : "No matching records found";
                email = exists ? rs.getString("email") : "No matching records found";
                password = exists ? rs.getString("password") : "No matching records found";

                runOnUiThread(() -> {
                    nameTextView.setText(name);
                    Toast.makeText(getApplicationContext(), exists ? "Record found " + name : "No records found", Toast.LENGTH_SHORT).show();
                });
                con.close();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    public void OpenFAQ(View view) {
        Intent i = new Intent(getApplicationContext(), FAQ.class);
        startActivity(i);
    }

    public void OpenPolicy(View view) {
        Intent i = new Intent(getApplicationContext(), PrivacyPolicy.class);
        startActivity(i);
    }

    public void OpenAboutUs(View view) {
        Intent i = new Intent(getApplicationContext(), AboutUs.class);
        startActivity(i);
    }

    public void LogOut(View view) {
        logoutMenu(MainProfile.this);
    }

    private void logoutMenu(MainProfile mainProfile){
        AlertDialog.Builder builder = new AlertDialog.Builder(mainProfile);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout ?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            Intent intent = new Intent(mainProfile, Signup.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear the back stack
            startActivity(intent);
            finish();
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
        builder.show();
    }

    public void OpenProfilePage(View view) {
        Intent i = new Intent(getApplicationContext(), ProfilePage.class);
        i.putExtra("name",name);
        i.putExtra("email",email);
        i.putExtra("password",password);
        startActivity(i);
    }
}