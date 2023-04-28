package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking.hotelList.adapter.HotelListAdapter;
import com.example.hotelbooking.hotelList.api.ApiService;
import com.example.hotelbooking.hotelList.model.Hotels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelList extends AppCompatActivity {
    private RecyclerView rcvHotelList;
    private List<Hotels> hotelsList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_list);

        rcvHotelList=findViewById(R.id.rcvHotelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvHotelList.setLayoutManager(linearLayoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcvHotelList.addItemDecoration(itemDecoration);

        hotelsList = new ArrayList<>();
        callApiGetHotel();
    }
    private void callApiGetHotel(){
        ApiService.apiService.hotelList().enqueue(new Callback<List<Hotels>>() {
            @Override
            public void onResponse(Call<List<Hotels>> call, Response<List<Hotels>> response) {
                hotelsList = response.body();
                HotelListAdapter hotelListAdapter= new HotelListAdapter(hotelsList);
                rcvHotelList.setAdapter(hotelListAdapter);

            }

            @Override
            public void onFailure(Call<List<Hotels>> call, Throwable t) {

            }
        });

    }
}
