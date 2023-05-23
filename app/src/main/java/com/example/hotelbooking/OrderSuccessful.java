package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class OrderSuccessful extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private String hotelName;
    private Float rating;
    private String checkIn;
    private String checkOut;
    private int traveller;
    private Float tax;
    private float serviceFee;
    private float price;
    private String roomType;
    private String hotelImgUrl;
    private String customerName;



    private TextView hotelNameTxtView;
    private TextView ratingTxtView;
    private TextView roomTypeTxtView;
    private TextView checkInTxtView;
    private TextView checkOutTxtView;
    private TextView guestTxtView;
    private TextView priceTxtView;
    private TextView calPriceTxtView;
    private TextView taxTxtView;
    private TextView serviceFeeTxtView;
    private TextView totalTxtView;
    private ImageView hotelImgView;
    private TextView customerNameTxtView;
    private TextView removeHomePage;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_successful);
        hotelNameTxtView = findViewById(R.id.hotelNameTxtView);
        ratingTxtView = findViewById(R.id.ratingTxtView);
        roomTypeTxtView = findViewById(R.id.roomTypeTxtView);
        checkInTxtView = findViewById(R.id.checkInTxtView);
        checkOutTxtView = findViewById(R.id.checkoutTxtview);
        guestTxtView = findViewById(R.id.guestTxtView);
        priceTxtView = findViewById(R.id.priceTxtView);
        calPriceTxtView = findViewById(R.id.calPriceTxtView);
        taxTxtView = findViewById(R.id.taxTxtView);
        serviceFeeTxtView = findViewById(R.id.serviceFeeTxtView);
        totalTxtView = findViewById(R.id.totalFeeTxtView);
        hotelImgView = findViewById(R.id.hotelImgView);
        customerNameTxtView = findViewById(R.id.customerNameTxtView);
//        removeHomePage=findViewById(R.id.txtTripGuide);

        setData();

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lang, R.layout.payment_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelected(false);
        spinner.setSelection(0,true);
        spinner.setOnItemSelectedListener(this);

        hotelNameTxtView.setText(hotelName);
        ratingTxtView.setText(rating.toString() + " rating");

        //handle date format
        String[] receiveDate1 = checkIn.split("-");
        String monthName1 = new String();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            monthName1 = Month.of(Integer.parseInt(receiveDate1[1])).name();
            monthName1 = monthName1.substring(0, 1).toUpperCase() + monthName1.substring(1).toLowerCase();
        }

        String[] receiveDate2 = checkOut.split("-");
        String monthName2 ="";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            monthName2 = Month.of(Integer.parseInt(receiveDate2[1])).name();
            monthName2 = monthName2.substring(0, 1).toUpperCase() + monthName2.substring(1).toLowerCase();
        }
        String checkInFormatted = monthName1 + " "+receiveDate1[2]+ ", "+receiveDate1[0];
        String checkOutFormatted = monthName2+" "+receiveDate2[2]+", "+receiveDate2[0];

        checkInTxtView.setText(checkInFormatted);
        checkOutTxtView.setText(checkOutFormatted);


        guestTxtView.setText(traveller +" Passenger");
        taxTxtView.setText("$"+tax.toString());
        serviceFeeTxtView.setText("$"+serviceFee);

        LocalDate fromDate = null; // January 1st, 2023
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            fromDate = LocalDate.of(Integer.parseInt(receiveDate1[0]), Integer.parseInt(receiveDate1[1]), Integer.parseInt(receiveDate1[2]));
        }
        LocalDate toDate = null; // April 17th, 2023
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            toDate = LocalDate.of(Integer.parseInt(receiveDate2[0]), Integer.parseInt(receiveDate2[1]), Integer.parseInt(receiveDate2[2]));
        }

        long stayedDays = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            stayedDays = ChronoUnit.DAYS.between(fromDate, toDate);
        }
        String priceDisplay = stayedDays+" nights";
        priceTxtView.setText(priceDisplay);
        String calPrice = "$"+ price * stayedDays;
        calPriceTxtView.setText(calPrice);
        roomTypeTxtView.setText(roomType);

        Float total = tax+serviceFee+price*stayedDays;
        totalTxtView.setText("$"+total);

        Glide.with(this).asBitmap().load(hotelImgUrl).into(hotelImgView);


        //cusname
        customerNameTxtView.setText(customerName);

        customerNameTxtView.setOnClickListener(view -> {
            openProfile();
        });
//        //Btn-RemoveHomePage
//        removeHomePage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                removeHomePage();
//            }
//        });
    }

    public void openProfile(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }


    public void setData(){
        SharedPreferences sharedPreferences = getSharedPreferences(PaymentActivity.SHARED_PREFS,MODE_PRIVATE);
        hotelName = sharedPreferences.getString(PaymentActivity.HOTEL_NAME, "");
        rating = sharedPreferences.getFloat(PaymentActivity.RATING, 0f);
        checkIn = sharedPreferences.getString(PaymentActivity.CHECK_IN, "");
        checkOut = sharedPreferences.getString(PaymentActivity.CHECK_OUT, "");
        traveller = sharedPreferences.getInt(PaymentActivity.TRAVELLER, 0);
        tax = sharedPreferences.getFloat(PaymentActivity.TAX, 0f);
        serviceFee = sharedPreferences.getFloat(PaymentActivity.SERVICE_FEE, 0f);
        price = sharedPreferences.getFloat(PaymentActivity.PRICE, 0f);
        roomType = sharedPreferences.getString(PaymentActivity.ROOM_TYPE, "");
        hotelImgUrl = sharedPreferences.getString(PaymentActivity.HOTEL_IMG_URL, "");
        customerName = sharedPreferences.getString(PaymentActivity.CUSTOMER_NAME, "");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        try {
            String text = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(view.getContext(), text, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("toast err", "happened");
        }
    }
    public void removeHomePage(){
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        return;
    }
}