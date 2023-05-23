package com.example.hotelbooking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking.order.adapter.HotelRecViewAdapter;
import com.example.hotelbooking.order.model.Hotel;
import com.example.hotelbooking.order.model.HotelOrder;
import com.example.hotelbooking.order.model.HotelResposne;
import com.example.hotelbooking.order.model.Order;
import com.example.hotelbooking.order.model.OrderResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class OrderHistory extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private RecyclerView bookingHistoryRecyclerView;
    private Integer userID;

    private String customerName;
    private String token;

    private TextView customerNameTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
//        saveData();
        setData();
        Log.d("token at Orderhis", token);
        customerNameTxtView = findViewById(R.id.customerNameTxtView);
        //cusname
        customerNameTxtView.setText(customerName);

        customerNameTxtView.setOnClickListener(view -> {
            openProfile();
        });

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lang, R.layout.payment_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelected(false);
        spinner.setSelection(0,true);
        spinner.setOnItemSelectedListener(this);
        new ApiTask().execute();
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

    private class ApiTask extends AsyncTask<Void, Void, ArrayList<HotelOrder>> {
        @Override
        protected ArrayList<HotelOrder> doInBackground(Void... voids) {
            try {
                SharedPreferences sharedPreferences = getSharedPreferences(PaymentActivity.SHARED_PREFS,MODE_PRIVATE);
                userID = sharedPreferences.getInt(PaymentActivity.USER_ID, 0);
                    String orderRes = makeRequest("GET","http://14.225.255.238/booking/api/v1/order/user/" + userID);

                    Gson gson = new Gson();

                    OrderResponse orderResponse = gson.fromJson(orderRes, OrderResponse.class);

                    ArrayList<HotelOrder> res = new ArrayList<HotelOrder>();
                    if(orderResponse != null){
                        for(Order order: orderResponse.getData()){
                            int hotelId = order.getHotelId();
                            String hotelRes = makeRequest("GET", "http://14.225.255.238/booking/api/v1/hotels/"+hotelId);
                            HotelResposne hotelResposne = gson.fromJson(hotelRes, HotelResposne.class);
                            Hotel hotel = hotelResposne.getData();
                            String roomType = "";
                            for(int i=0; i<hotel.getRoomTypes().size(); i++){
                                if(hotel.getRoomTypes().get(i).getId() == order.getOrderDetails().get(0).getRoomTypeId()){
                                    roomType = hotel.getRoomTypes().get(i).getName();
                                    break;
                                }
                            }
                            Double pricePerNight = order.getOrderDetails().get(0).getPrice();
                            Integer quantity = order.getOrderDetails().get(0).getQuantity();
                            String[] receiveDate1 = order.getCheckin().split("-");
                            String[] receiveDate2 = order.getCheckout().split("-");
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
                            Double totalPrice = pricePerNight*quantity*stayedDays;
                            String cmt = order.getComment();
                            Double cmtRating = order.getRating();
                            HotelOrder hotelOrder = new HotelOrder(hotel.getName(), hotel.getRating(), roomType, order.getCheckin(), order.getCheckout(),"http://14.225.255.238/booking"+ hotel.getAvatar(), order.getStatusId(), totalPrice, order.getId(), order.getComment(), order.getRating());
                            res.add(hotelOrder);
                        }


                        return res;
                    }
                    return null;

            } catch (Exception e) {
                Log.e("ERR", "API call failed", e);
                Toast.makeText(OrderHistory.this, "Failed to connect to server please try again later", Toast.LENGTH_LONG).show();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<HotelOrder> hotelOrders) {
            if (hotelOrders != null) {

//                ArrayList<HotelOrder> hotelOrders = new ArrayList<>();
//                hotelOrders.add(new HotelOrder("Hotel 01", 4, "Phòng đơn", "June, 7, 2022", "June, 10, 2022", "https://pix10.agoda.net/hotelImages/124/1246280/1246280_16061017110043391702.jpg"));
//                hotelOrders.add(new HotelOrder("Hotel 02", 4, "Phòng đơn", "June, 7, 2022", "June, 10, 2022", "https://cdn.britannica.com/96/115096-050-5AFDAF5D/Bellagio-Hotel-Casino-Las-Vegas.jpg"));
//                hotelOrders.add(new HotelOrder("Hotel 03", 4, "Phòng đơn", "June, 7, 2022", "June, 10, 2022", "https://www.filmexpos.com/wp-content/uploads/2023/02/eb7551cd93224863612f7472c55d933f.jpeg"));
//                hotelOrders.add(new HotelOrder("Hotel 04", 4, "Phòng đơn", "June, 7, 2022", "June, 10, 2022", "https://img.etimg.com/thumb/msid-96836017,width-1015,height-761,imgsize-120772,resizemode-8/prime/corporate-governance/from-marriotts-to-hyatts-to-le-mridiens-premium-hotel-assets-are-stuck-at-bankruptcy-courts.jpg"));
                bookingHistoryRecyclerView = findViewById(R.id.bookingHistory);
                HotelRecViewAdapter adapter = new HotelRecViewAdapter(OrderHistory.this);
                adapter.setHotelOrders(hotelOrders);
//                adapter.setToken(token);
                bookingHistoryRecyclerView.setAdapter(adapter);
                bookingHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(OrderHistory.this));
            }
        }

        protected String makeRequest(String requestType, String u){
            try {
                URL url = new URL(u);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod(requestType);
                con.setRequestProperty("Authorization", token);
                int responseCode = con.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    in.close();
                    String apiResponse = response.toString();
                    return apiResponse;
                } else {
                    Log.e("ERR", "API call failed with response code: " + responseCode);
                }
            } catch (Exception e) {
                Log.e("ERR", "API call failed", e);
            }

            return null;
        }
    }

    public void setData(){
        SharedPreferences sharedPreferences = getSharedPreferences(PaymentActivity.SHARED_PREFS,MODE_PRIVATE);
        customerName = sharedPreferences.getString(PaymentActivity.CUSTOMER_NAME, "");
        token = sharedPreferences.getString("token", "");

    }

//    public void saveData(){
//        SharedPreferences sharedPreferences = getSharedPreferences(PaymentActivity.SHARED_PREFS,MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString("token", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2IiwiaWF0IjoxNjg0NDIwODA5LCJleHAiOjE2ODQ1OTYxOTh9.Vbzhbau12939WTo6UnsNNmPk7iaK5xbKlTm8ci3NiG9vtBL4Rk8is0aDeD0P2TtMxrgiW4R7t_A7kWV9ljqHxg");
//        editor.apply();
//    }

    public void openProfile(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}