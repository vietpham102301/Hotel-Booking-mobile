package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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


        //callApiGetHotel("HOCHIMINH","2023-04-16","2023-04-20");



        if(Collector.prv!=null && Collector.ci!=null && Collector.co!=null){
            System.out.println("Nhận tham số thành công");
            System.out.println(Collector.prv);
            System.out.println(Collector.ci);
            System.out.println(Collector.co);
            System.out.println(Collector.rating);
            System.out.println(Collector.typeHotel);
            System.out.println(Collector.price);
            callApiGetHotel(Collector.prv,Collector.ci,Collector.co);
            Toast.makeText(HotelListActivity.this,"Success",Toast.LENGTH_SHORT).show();
        }
        else {
            System.out.println("Nhận tham số thất bại");
            Toast.makeText(HotelListActivity.this,"Error",Toast.LENGTH_SHORT).show();
        }

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
}
