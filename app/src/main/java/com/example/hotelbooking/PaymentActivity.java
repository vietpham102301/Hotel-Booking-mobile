package com.example.hotelbooking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class PaymentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String SHARED_PREFS = "bookingApp";
    public static final String CHECK_IN = "checkin";
    public static final String CHECK_OUT = "checkout";
    public static final String TRAVELLER = "traveller";
    public static final String HOTEL_NAME = "hotel_name";
    public static final String RATING = "rating";
    public static final String ROOM_TYPE = "room_type";
    public static final String PRICE = "price";
    public static final String SERVICE_FEE = "fee";
    public static final String TAX ="tax";

    public static final String ROOM_TYPE_ID = "room_type_id";
    public static final String PHONE = "phone";
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String QUANTITY = "quantity";
    public static final String HOTEL_ID = "hotel_id";
    public static final String HOTEL_IMG_URL = "hotel_img_url";
    public static final String CUSTOMER_NAME = "customer_name";




    private String checkIn;
    private String checkOut;
    private Integer traveller;
    private String hotelName;
    private Float rating;
    private String roomType;
    private Float price;
    private Float serviceFee;
    private Float tax;
    private Integer roomTypeID;
    private String phone;
    private Integer userID;
    private Integer hotelID;
    private Integer quantity;
    private String hotelImgURL;
    private String customerName;



    private TextView checkInTxtView1;
    private TextView travellerTxtView;
    private TextView hotelNameTxtView;
    private TextView ratingTxtView;
    private TextView roomTypeTxtView;
    private TextView checkInTxtView2;
    private TextView checkOutTxtView;
    private TextView guestTxtView;
    private TextView priceTxtView;
    private TextView taxTxtView;
    private TextView feeTxtView;
    private TextView totalTxtView;
    private TextView priceCalTxtView;
    private Button confirmButton;
    private ImageView hotelImg;
    private CheckBox paymentCheckBox;
    private EditText editTxtCardNumber;
    private EditText editTextExpiry;
    private EditText editTxtCVC;
    private TextView customerNameTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lang, R.layout.payment_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelected(false);
        spinner.setSelection(0,true);
        spinner.setOnItemSelectedListener(this);


        saveData();
        checkInTxtView1 = findViewById(R.id.checkinTxtView1);
        checkOutTxtView = findViewById(R.id.checkoutTxtview);
        travellerTxtView = findViewById(R.id.travellerTxtView);
        hotelNameTxtView = findViewById(R.id.hotelNameTxtView);
        ratingTxtView = findViewById(R.id.ratingTxtView);
        roomTypeTxtView = findViewById(R.id.roomTypeTxtView);
        checkInTxtView2 = findViewById(R.id.checkInTxtView2);
        guestTxtView = findViewById(R.id.guestTxtView);
        priceTxtView = findViewById(R.id.priceTxtView);
        taxTxtView = findViewById(R.id.taxTxtView);
        feeTxtView = findViewById(R.id.serviceFeeTxtView);
        totalTxtView = findViewById(R.id.totalFeeTxtView);
        priceCalTxtView = findViewById(R.id.calPriceTxtView);
        hotelImg = findViewById(R.id.hotelImgView);
        customerNameTxtView = findViewById(R.id.customerNameTxtView);


        showDataToConsole();
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

        checkInTxtView1.setText( checkInFormatted + " - " +checkOutFormatted);

        //traveller
        travellerTxtView.setText(traveller.toString() +" Passenger");

        //hotelName
        hotelNameTxtView.setText(hotelName);

        //rating
        ratingTxtView.setText(rating.toString() + " rating");

        //roomType
        roomTypeTxtView.setText(roomType);

        //checkIn2 - checkOut
        checkInTxtView2.setText(checkInFormatted);
        checkOutTxtView.setText(checkOutFormatted);

        //cusname
        customerNameTxtView.setText(customerName);

        customerNameTxtView.setOnClickListener(view -> {
            openProfile();
        });

        guestTxtView.setText(traveller.toString() +" guests");

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
        priceCalTxtView.setText(calPrice);


        taxTxtView.setText("$"+tax);
        feeTxtView.setText("$"+serviceFee);

        Float total = tax+serviceFee+price*stayedDays;
        totalTxtView.setText("$"+total);
        confirmButton = findViewById(R.id.confirmButton);

        paymentCheckBox = findViewById(R.id.paymentCheckbox);
        editTextExpiry = findViewById((R.id.editTxtExpiry));
        editTxtCardNumber = findViewById(R.id.editTxtCardNumber);
        editTxtCVC = findViewById((R.id.editTxtCVC));

        confirmButton.setOnClickListener(view -> {

            if(paymentCheckBox.isChecked()){
                makeOrderRequest();

            }else{
                String cardNumber = editTxtCardNumber.getText().toString().trim();
                String expiry = editTextExpiry.getText().toString().trim();
                String CVC = editTxtCVC.getText().toString().trim();

                if(!cardNumber.equals("") && !expiry.equals("") && !CVC.equals("")){
                    makeOrderRequest();
                }else{
                    Toast.makeText(PaymentActivity.this, "Please fill card information for payment", Toast.LENGTH_SHORT).show();
                }
            }



//                Toast.makeText(PaymentActivity.this, "ORDER SUCCESSUL",Toast.LENGTH_SHORT).show();


        });

        //bit map
//        String imgURL = "https://media-cdn.tripadvisor.com/media/photo-s/23/ca/38/3a/au-lac-charner-hotel.jpg";
        Glide.with(this).asBitmap().load(hotelImgURL).into(hotelImg);

    }


    public void openOrderSuccessful(){
        Intent intent = new Intent(this, OrderSuccessful.class);
        startActivity(intent);
    }

    public void openProfile(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void makeOrderRequest(){
        Thread thread = new Thread(() -> {
            try  {
                URL url = new URL("http://14.225.255.238/booking/api/v1/order");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                String requestBody = String.format("{\n" +
                        "    \"checkin\": \"%s\",\n" +
                        "    \"checkout\": \"%s\",\n" +
                        "    \"hotelId\": %d,\n" +
                        "    \"name\": \"%s\",\n" +
                        "    \"orderDetails\": [\n" +
                        "        {\n" +
                        "            \"quantity\": %d,\n" +
                        "            \"roomId\": %d\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"phone\": \"%s\",\n" +
                        "    \"userId\": %d\n" +
                        "}", checkIn, checkOut, hotelID, customerName, quantity, roomTypeID, phone, userID);
                Log.d("Request Body", requestBody);

                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(requestBody.getBytes());
                outputStream.flush();
                outputStream.close();

                int responseCode = connection.getResponseCode();
                System.out.println("Response code: " + responseCode);

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder responseBody = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    responseBody.append(line);
                }
                in.close();

                openOrderSuccessful();

                Log.d("Response body ", responseBody.toString());
                new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(getApplicationContext(), "Order Successful", Toast.LENGTH_SHORT).show());



            } catch (Exception e) {
//                        Toast.makeText(PaymentActivity.this, "ORDER SUCCESSUL",Toast.LENGTH_SHORT).show();
                //fail but want a Toast message to show the fail
                new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(getApplicationContext(), "ERR WHEN MAKE ORDER PLEASE TRY AGAIN", Toast.LENGTH_SHORT).show());
                e.printStackTrace();
            }
        });

        thread.start();
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

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        return;
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

//        editor.putString(CHECK_IN, "2023-04-16");
//        editor.putString(CHECK_OUT, "2023-04-20");
//        editor.putInt(TRAVELLER, 1);
//        editor.putString(HOTEL_NAME, "LOTUS RESIDENCE - Landmark 81 Vinhomes Central Park");
//        editor.putFloat(RATING, new Float(4.8));
//        editor.putString(ROOM_TYPE, "Phòng đơn");
//        editor.putFloat(PRICE, new Float(125.0));
        editor.putFloat(TAX, new Float(10.0));
        editor.putFloat(SERVICE_FEE,new Float(10.0));
//        editor.putInt(ROOM_TYPE_ID, 1);
//        editor.putString(PHONE, "0325542310");
//        editor.putInt(USER_ID, 6);
//        editor.putInt(HOTEL_ID, 1);
//        editor.putInt(QUANTITY, 1);
//        editor.putString(HOTEL_IMG_URL, "https://media-cdn.tripadvisor.com/media/photo-s/23/ca/38/3a/au-lac-charner-hotel.jpg");
//        editor.putString(CUSTOMER_NAME, "Viet Pham");
        editor.apply();
    }
    public void showDataToConsole(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        checkIn = sharedPreferences.getString(CHECK_IN, "");
        checkOut = sharedPreferences.getString(CHECK_OUT, "");
        traveller = sharedPreferences.getInt(TRAVELLER, 0);
        hotelName = sharedPreferences.getString(HOTEL_NAME, "");
        rating = sharedPreferences.getFloat(RATING, 0f);
        roomType = sharedPreferences.getString(ROOM_TYPE, "");
        price = sharedPreferences.getFloat(PRICE, 0f);
        tax = sharedPreferences.getFloat(TAX, 0f);
        serviceFee = sharedPreferences.getFloat(SERVICE_FEE, 0f);
        roomTypeID = sharedPreferences.getInt(ROOM_TYPE_ID, 0);
        phone = sharedPreferences.getString(PHONE, "");
        userID = sharedPreferences.getInt(USER_ID, 0);
        quantity = sharedPreferences.getInt(QUANTITY, 0);
        hotelID = sharedPreferences.getInt(HOTEL_ID, 0);
        hotelImgURL = sharedPreferences.getString(HOTEL_IMG_URL, "");
        customerName = sharedPreferences.getString(CUSTOMER_NAME, "");

        Log.d("checkIn: ", checkIn);
        Log.d("checkout: ", checkOut);
        Log.d("traveller: ", String.valueOf(traveller));
        Log.d("hotelName: ", hotelName);
        Log.d("rating: ", String.valueOf(rating));
        Log.d("roomType: ", roomType);
        Log.d("price: ", String.valueOf(price));
        Log.d("tax: ", String.valueOf(tax));
        Log.d("service fee: ", String.valueOf(serviceFee));

    }
}