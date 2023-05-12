package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class HotelListActivity extends AppCompatActivity {
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
    private RecyclerView rcvHotelList;
    private HotelListAdapter hotelListAdapter;
    private ArrayList<Hotel> mhotelsList;


    private TextView fltLocationAndHotel;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_list);

        mhotelsList= new ArrayList<>();
        rcvHotelList=findViewById(R.id.rcvHotelList);
        rcvHotelList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        hotelListAdapter = new HotelListAdapter(HotelListActivity.this, mhotelsList);
        rcvHotelList.setAdapter(hotelListAdapter);
        fltLocationAndHotel=findViewById(R.id.filterLocationAndHotel);


        fltLocationAndHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HotelListActivity.this,Filter_Activity.class);
                startActivity(intent);
            }
        });



        callApiGetHotel("HOCHIMINH","2023-04-16","2023-04-20");



//        if(Collector.prv!=null && Collector.ci!=null && Collector.co!=null){
//            System.out.println("Nhận tham số thành công");
//            System.out.println(Collector.prv);
//            System.out.println(Collector.ci);
//            System.out.println(Collector.co);
//            System.out.println(Collector.rating);
//            System.out.println(Collector.typeHotel);
//            System.out.println(Collector.price);
//            callApiGetHotel(Collector.prv,Collector.ci,Collector.co);
//            Toast.makeText(HotelListActivity.this,"Success",Toast.LENGTH_SHORT).show();
//        }
//        else {
//            System.out.println("Nhận tham số thất bại");
//            Toast.makeText(HotelListActivity.this,"Error",Toast.LENGTH_SHORT).show();
//        }



    }
    private void callApiGetHotel(String prv, String ci, String co){
        Appclient.getClient().create(Api.class).getHotelList(prv,ci,co).enqueue(new Callback<HotelsOutfit>() {
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
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString(CHECK_IN, "2023-04-16");
//        editor.putString(CHECK_OUT, "2023-04-20");
//        editor.putInt(TRAVELLER, 1);
//        editor.putString(HOTEL_NAME, "LOTUS RESIDENCE - Landmark 81 Vinhomes Central Park");
//        editor.putFloat(RATING, new Float(4.8));
//        editor.putString(ROOM_TYPE, "Phòng đơn");
//        editor.putFloat(PRICE, new Float(125.0));
//        editor.putFloat(TAX, new Float(10.0));
//        editor.putFloat(SERVICE_FEE,new Float(10.0));
//        editor.putInt(ROOM_TYPE_ID, 1);
//        editor.putString(PHONE, "0325542310");
//        editor.putInt(USER_ID, 6);
//        editor.putString(USERNAME,"viet pham");
//        editor.putInt(HOTEL_ID, 1);
//        editor.putInt(QUANTITY, 1);
//        editor.putString(HOTEL_IMG_URL, "https://media-cdn.tripadvisor.com/media/photo-s/23/ca/38/3a/au-lac-charner-hotel.jpg");
//        editor.putString(CUSTOMER_NAME, "Viet Pham");
        editor.apply();
    }
}
