package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HotelAdminActivity extends AppCompatActivity {

    private TextView revenueTxtView;
    private TextView bookedRoomTxtView;
    private TextView logOutTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_admin);
        revenueTxtView = findViewById(R.id.revenueTxtView);
        bookedRoomTxtView = findViewById(R.id.bookedRoomTxtView);
        logOutTxtView = findViewById(R.id.logout);



        revenueTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBarChart();
            }
        });
        bookedRoomTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPieChart();
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

    public void openPieChart(){
        Intent intent = new Intent(this, PieChartActivity.class);
        startActivity(intent);
    }
    public void openBarChart(){
        Intent intent = new Intent(this, BarchartActivity.class);
        startActivity(intent);
    }

    public void openSigIn(){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}