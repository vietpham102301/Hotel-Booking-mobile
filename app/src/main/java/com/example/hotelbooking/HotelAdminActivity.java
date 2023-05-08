package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HotelAdminActivity extends AppCompatActivity {

    private TextView revenueTxtView;
    private TextView bookedRoomTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_admin);
        revenueTxtView = findViewById(R.id.revenueTxtView);
        bookedRoomTxtView = findViewById(R.id.bookedRoomTxtView);

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
    }

    public void openPieChart(){
        Intent intent = new Intent(this, PieChartActivity.class);
        startActivity(intent);
    }
    public void openBarChart(){
        Intent intent = new Intent(this, BarchartActivity.class);
        startActivity(intent);
    }
}