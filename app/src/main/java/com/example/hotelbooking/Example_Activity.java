package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Example_Activity extends AppCompatActivity {

    private TextView txtNameHotel;
    private TextView txtRating;
    private TextView txtNumrating;
    private TextView txtProvice;
    private TextView txtAddress;
    private TextView txtPhone;
    private TextView txtPrice;

    private ImageView imgViewHotel;

    private Button btnclick;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_list_item);


//        imgViewHotel=findViewById(R.id.imgViewHotel);
//        txtNameHotel = findViewById(R.id.txtNameHotel);
//        txtRating = findViewById(R.id.txtRatingHotel);
//        txtNumrating = findViewById(R.id.txtNumRatingHotel);
//        txtProvice = findViewById(R.id.txtProviceHotel);
//        txtAddress=findViewById(R.id.txtAddressHotel);
//        txtPhone=findViewById(R.id.txtPhoneHotel);
//        txtPrice=findViewById(R.id.txtPriceHotel);
//
//
//        callApi(1);
//    }
//
//    private void callApi(int id) {
//        ApiService.apiService.hotelCardView(id).enqueue(new Callback<Hotels>() {
//            @Override
//            public void onResponse(Call<Hotels> call, Response<Hotels> response) {
//                Toast.makeText(Example_Activity.this, "Call Api Success", Toast.LENGTH_SHORT).show();
//
//                Hotels hotels = response.body();
//                if (hotels != null) {
//                    //String url = hotels.getData().getAvatar();
//                    txtNameHotel.setText(hotels.getData().getName());
//                    txtRating.setText(String.valueOf(hotels.getData().getRating()));
//                    txtNumrating.setText("( "+String.valueOf(hotels.getData().getNumRating())+" reviews)");
//                    txtProvice.setText(hotels.getData().getProvinceId());
//                    txtAddress.setText(hotels.getData().getAddress());
//                    txtPhone.setText(hotels.getData().getPhone());
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Hotels> call, Throwable t) {
//                Toast.makeText(Example_Activity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
    }


}