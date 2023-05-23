package com.example.hotelbooking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView orderHistoryTxtView;
    private TextView logOutTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
    }

    public void openOrderHistory(){
        Intent intent = new Intent(this, OrderHistory.class);
        startActivity(intent);
    }
    public void openSigIn(){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}