package com.project;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.sql.*;
import android.widget.TextView;
import static com.project.MainActivity.*;

public class Home extends AppCompatActivity {

    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Get the userid from the intent extra
        Intent intent = getIntent();
        // 0 is the default value if the extra is not found
        userid = intent.getIntExtra("userid", 0);


        Button cleaning = (Button) findViewById(R.id.buttoncleaning);
        Button ehailing = (Button) findViewById(R.id.buttonehailing);
        Button grocery = (Button) findViewById(R.id.buttongrocery);
        Button laundry = (Button) findViewById(R.id.buttonlaundry);
        Button fooddelivery = (Button) findViewById(R.id.buttonfooddelivery);
        TextView nameTextView = (TextView) findViewById(R.id.hello_username);

        new Thread(() -> {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                String query = "SELECT name FROM User WHERE id = ?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, userid);

                ResultSet rs = stmt.executeQuery();
                boolean exists = rs.next();
                String name = exists ? rs.getString("name").toUpperCase() : "No matching records found";
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

        cleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openCleaningPage();}
         });
         ehailing.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 openEhailingPage();
             }
         });
         grocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGroceryPage();
            }
         });
         laundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLaundryPage();
            }
         });
         fooddelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFoodDeliveryPage();
            }
         });
    }

    public void openCleaningPage(){
        Intent i = new Intent(this, GetstartedCleaning.class);
        startActivity(i);
    }

    public void openEhailingPage(){
        Intent i = new Intent(this, Ehailing.class);
        startActivity(i);
    }

    public void openGroceryPage(){
        Intent i = new Intent(this, Grocery.class);
        startActivity(i);
    }

    public void openLaundryPage(){
        Intent i = new Intent(this, Laundry.class);
        startActivity(i);
    }
    public void openFoodDeliveryPage(){
        Intent i = new Intent(this, FoodDelivery.class);
        startActivity(i);
    }

    public void openProfileAccountPage(View view){
        Intent i = new Intent(this, MainProfile.class);
        i.putExtra("userid",userid);
        startActivity(i);
    }
}
