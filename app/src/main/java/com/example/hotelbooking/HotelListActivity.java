package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking.Collector.Collector;
import com.example.hotelbooking.hotelListp.adapter.HotelListAdapter;
import com.example.hotelbooking.hotelListp.api.Api;
import com.example.hotelbooking.hotelListp.api.Appclient;
import com.example.hotelbooking.hotelListp.model.Hotel;
import com.example.hotelbooking.hotelListp.model.HotelsOutfit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String SHARED_PREFS = "bookingApp";
    public static final String CHECK_IN = "checkin";
    public static final String CHECK_OUT = "checkout";
    public static final String HOTEL_ID = "hotel_id";
    public static final String CUSTOMER_NAME = "customer_name";
    private String customerName;
    private TextView customerNameTxtView;
    private TextView fltLocationAndHotel;
    private TextView removeHomePage;
    private RecyclerView rcvHotelList;
    private HotelListAdapter hotelListAdapter;
    private ArrayList<Hotel> mhotelsList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_list);


        //Declare
            removeHomePage=findViewById(R.id.txtTripGuide);
            customerNameTxtView = findViewById(R.id.customerNameTxtView);
            Spinner spinner = findViewById(R.id.spinner);
            fltLocationAndHotel=findViewById(R.id.filterLocationAndHotel);
            rcvHotelList=findViewById(R.id.rcvHotelList);


        //get Data
        saveData(Collector.ci,Collector.co);
        showDataToConsole();


        //Navigation-Bar

            //Btn-RemoveHomePage
            removeHomePage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeHomePage();
                }
            });

            //Customer-Name
            customerNameTxtView.setText(customerName);
            customerNameTxtView.setOnClickListener(view -> {
                openProfile();
            });

            //Language
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lang, R.layout.payment_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setSelected(false);
            spinner.setSelection(0,true);
            spinner.setOnItemSelectedListener(this);


        //Btn-NextHotelInf
        fltLocationAndHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextHotelInf();
            }
        });


        //HotelList
        mhotelsList= new ArrayList<>();
        rcvHotelList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        hotelListAdapter = new HotelListAdapter(HotelListActivity.this, mhotelsList);
        rcvHotelList.setAdapter(hotelListAdapter);


        //Call-API
//        callApiGetHotel("HOCHIMINH","2023-04-16","2023-04-20");
        if(Collector.prv!=null && Collector.ci!=null && Collector.co!=null){
            System.out.println("Nhận tham số thành công");
            System.out.println(Collector.prv);
            System.out.println(Collector.ci);
            System.out.println(Collector.co);
            System.out.println(Collector.rating);
            System.out.println(Collector.typeHotel);
            System.out.println(Collector.price);
            callApiGetHotel(Collector.prv,Collector.ci,Collector.co,Collector.typeHotel,Collector.rating,Collector.price);
            Toast.makeText(HotelListActivity.this,"Success",Toast.LENGTH_SHORT).show();
        }
        else {
            System.out.println("Nhận tham số thất bại");
            Toast.makeText(HotelListActivity.this,"Error",Toast.LENGTH_SHORT).show();
        }
    }


    private void callApiGetHotel(String prv, String ci, String co, String ht, String rt, String pri){
        Appclient.getClient().create(Api.class).getHotelList(prv,ci,co,ht,rt,pri).enqueue(new Callback<HotelsOutfit>() {
            @Override
            public void onResponse(Call<HotelsOutfit> call, Response<HotelsOutfit> response) {
                if(response.code()==200){
                    mhotelsList.addAll(response.body().getData().getData());
                    hotelListAdapter.notifyDataSetChanged();
                    Toast.makeText(HotelListActivity.this,"Success",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<HotelsOutfit> call, Throwable t) {
                Toast.makeText(HotelListActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void saveData(String checkIn,String checkOut){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(CHECK_IN, checkIn);
        editor.putString(CHECK_OUT, checkOut);
//        System.out.println(checkIn+"++++++"+checkOut);
        editor.putString(CUSTOMER_NAME, "Viet Pham");
        editor.apply();
    }


    public void showDataToConsole(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        customerName = sharedPreferences.getString(CUSTOMER_NAME, "");
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {}

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}


    public void openProfile(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }


    public void nextHotelInf(){
        Intent intent=new Intent(this,Filter_Activity.class);
        startActivity(intent);
    }


    public void removeHomePage(){
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }
}
