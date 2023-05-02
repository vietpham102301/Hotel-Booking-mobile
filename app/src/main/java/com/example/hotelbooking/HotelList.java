package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking.Collector.Collector;
import com.example.hotelbooking.hotelList.adapter.HotelListAdapter;
import com.example.hotelbooking.hotelList.api.ApiService;
import com.example.hotelbooking.hotelList.model.HotelsOutfit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelList extends AppCompatActivity {
    private RecyclerView rcvHotelList;
    private List<HotelsOutfit> hotelsList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_list);

        rcvHotelList=findViewById(R.id.rcvHotelList);


        if(Collector.prv!=null && Collector.ci!=null && Collector.co!=null){
            System.out.println("Nhận tham số thành công");
            System.out.println(Collector.prv);
            System.out.println(Collector.ci);
            System.out.println(Collector.co);
            callApiGetHotel(Collector.prv,Collector.ci,Collector.co);
        }
        else {
            System.out.println("Nhận tham số thất bại");
            Toast.makeText(HotelList.this,"Error",Toast.LENGTH_SHORT).show();
        }

    }
    private void callApiGetHotel(String prv, String ci, String co){
        ApiService.apiService.hotelList(prv,ci,co).enqueue(new Callback<HotelsOutfit>() {
            @Override
            public void onResponse(Call<HotelsOutfit> call, Response<HotelsOutfit> response) {
                hotelsList = new ArrayList<>();
                hotelsList = (List<HotelsOutfit>) response.body();
                HotelListAdapter hotelListAdapter= new HotelListAdapter(hotelsList);
                rcvHotelList.setAdapter(hotelListAdapter);
            }

            @Override
            public void onFailure(Call<HotelsOutfit> call, Throwable t) {

            }
        });

    }
}
