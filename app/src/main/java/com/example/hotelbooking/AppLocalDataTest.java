package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class AppLocalDataTest extends AppCompatActivity {
    public static final String SHARED_PREFS = "bookingApp";
    public static final String CHECK_IN = "checkin";
    public static final String CHECK_OUT = "checkout";
    public static final String TRAVELLER = "traveller";
    public static final String HOTEL_NAME = "hotel_name";

    private String checkIn;
    private String checkOut;
    private Integer traveller;
    private String hotelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
//        saveData();
        showDataToConsole();

    }
    public void saveData(){
        android.content.SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(CHECK_IN, "2023-04-16");
        editor.putString(CHECK_OUT, "2023-04-20");
        editor.putInt(TRAVELLER, 1);
        editor.putString(HOTEL_NAME, "LOTUS RESIDENCE - Landmark 81 Vinhomes Central Park");
        editor.apply();
    }

    public void showDataToConsole(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        checkIn = sharedPreferences.getString(CHECK_IN, "");
        checkOut = sharedPreferences.getString(CHECK_OUT, "");
        traveller = sharedPreferences.getInt(TRAVELLER, 0);
        hotelName = sharedPreferences.getString(HOTEL_NAME, "");

        Log.d("checkIn: ", checkIn);
        Log.d("checkout: ", checkOut);
        Log.d("traveller: ", String.valueOf(traveller));
        Log.d("hotelName: ", hotelName);

    }


}