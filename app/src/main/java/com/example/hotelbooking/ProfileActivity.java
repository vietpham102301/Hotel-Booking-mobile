package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private ImageView removeHomePage;
    private TextView orderHistoryTxtView;
    private TextView logOutTxtView;
    private TextView cusnameTxtView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        removeHomePage=findViewById(R.id.btnRemoveHomePage);
        orderHistoryTxtView = findViewById(R.id.bookingHistoryTxtView);
        logOutTxtView = findViewById(R.id.logout);
        orderHistoryTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOrderHistory();
            }
        });

        logOutTxtView.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences(PaymentActivity.SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            openSigIn();
        });
        removeHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeHomePage();
            }
        });

        cusnameTxtView = findViewById(R.id.nameTxtView);
        SharedPreferences sharedPreferences = getSharedPreferences(PaymentActivity.SHARED_PREFS,MODE_PRIVATE);
        String cusname = sharedPreferences.getString("customer_name", "");
        cusnameTxtView.setText(cusname);
    }
    public void openOrderHistory(){
        Intent intent = new Intent(this, OrderHistory.class);
        startActivity(intent);
    }
    public void openSigIn(){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
    public void removeHomePage(){
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }
}