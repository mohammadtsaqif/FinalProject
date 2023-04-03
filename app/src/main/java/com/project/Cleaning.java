package com.project;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.project.MainActivity.*;

public class Cleaning extends AppCompatActivity {

    int userid;
    final static int service_cleaning = 1;

    //Rate for cleaning service category
    int rate1 = 10;
    int rate2 = 20;
    int rate3 = 15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cleaning);

        Button calcRC = findViewById(R.id.btn_cleaning_room);
        Button calcCW = findViewById(R.id.btn_car_washing);
        Button calcMW = findViewById(R.id.btn_motor_washing);
        EditText hours = findViewById(R.id.edittextHours);
        TextView result = findViewById(R.id.totalCost);

        // Get the userid from the intent extra
        Intent intent = getIntent();
        // 0 is the default value if the extra is not found
        userid = intent.getIntExtra("userid", 0);


            calcRC.setOnClickListener(view -> {
                String Hours = hours.getText().toString();
                if (TextUtils.isEmpty(Hours)) {
                    Toast.makeText(Cleaning.this, "Please fill the hours", Toast.LENGTH_SHORT).show();
                }else {
                    final float totalCost = calculateCost(rate1, Hours);
                    String msg = "" + totalCost;
                    result.setText(msg);
                    // placeOrder(totalCost);
                }
            });

            calcCW.setOnClickListener(view -> {
                String Hours = hours.getText().toString();
                if (TextUtils.isEmpty(Hours)) {
                    Toast.makeText(Cleaning.this, "Please fill the hours", Toast.LENGTH_SHORT).show();
                }else {
                    final float totalCost = calculateCost(rate2, Hours);
                    String msg = "" + totalCost;
                    result.setText(msg);
                    //placeOrder(totalCost);
                }
            });

            calcMW.setOnClickListener(view -> {
                String Hours = hours.getText().toString();
                if (TextUtils.isEmpty(Hours)) {
                    Toast.makeText(Cleaning.this, "Please fill the hours", Toast.LENGTH_SHORT).show();
                }else {
                    final float totalCost = calculateCost(rate3, Hours);
                    String msg = "" + totalCost;
                    result.setText(msg);
                    //placeOrder(totalCost);
                }
            });
        }


    public void placeOrder(double totalCost){
        try {
            // Connect to the MySQL database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create a new order
            String query1 = "INSERT INTO orders (date, status, total_cost, serviceId, customerId) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);

            stmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            stmt.setString(2, "Pending");
            stmt.setDouble(3, totalCost);
            stmt.setInt(4, service_cleaning);
            stmt.setNull(5, userid);

            // Execute the query and retrieve the auto-generated order ID
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);
                Toast.makeText(Cleaning.this, "Created order with ID:" + orderId, Toast.LENGTH_SHORT).show();
            } else {
                throw new SQLException("Creating order failed, no ID obtained.");
            }
            // Close the database connection
            conn.close();
        } catch (SQLException e) {
            System.err.println("SQL error occurred: " + e.getMessage());
        }
    }
    public void cancelOrder(){}

    public float calculateCost(int rate, String hours){
        int Hours = Integer.parseInt(hours);
        return (float)(rate * Hours);
    }
}