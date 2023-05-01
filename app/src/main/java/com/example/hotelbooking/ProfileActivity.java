package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView orderHistoryTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        orderHistoryTxtView = findViewById(R.id.bookingHistoryTxtView);
        orderHistoryTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOrderHistory();
            }
        });
    }

    public void openOrderHistory(){
        Intent intent = new Intent(this, OrderHistory.class);
        startActivity(intent);
    }
}