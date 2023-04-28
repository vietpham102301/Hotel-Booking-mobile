package com.example.hotelbooking.hotelList.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking.R;
import com.example.hotelbooking.hotelList.model.Hotels;
import com.example.hotelbooking.order.Hotel;

import java.util.List;

public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.HotelListHoler>{
    private final List<Hotels> listHotel;

    public HotelListAdapter(List<Hotels> listHotel) {
        this.listHotel = listHotel;
    }
    @NonNull
    @Override
    public HotelListHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_list_item,parent,false);
        return new HotelListHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelListHoler holder, int position) {
        Hotels hotels = listHotel.get(position);
        holder.txtNameHotel.setText(hotels.getData().getName());
        holder.txtNameHotel.setText(hotels.getData().getName());
        holder.txtRating.setText(String.valueOf(hotels.getData().getRating()));
        holder.txtNumrating.setText("( "+String.valueOf(hotels.getData().getNumRating())+" reviews)");
        holder.txtProvice.setText(hotels.getData().getProvinceId());
        holder.txtAddress.setText(hotels.getData().getAddress());
        holder.txtPhone.setText(hotels.getData().getPhone());
    }

    @Override
    public int getItemCount() {
        if(listHotel != null){
            return listHotel.size();
        }
        return 0;
    }

    public static class HotelListHoler extends RecyclerView.ViewHolder{
        private final TextView txtNameHotel;
        private final TextView txtRating;
        private final TextView txtNumrating;
        private final TextView txtProvice;
        private final TextView txtAddress;
        private final TextView txtPhone;
        private final TextView txtPrice;
        public HotelListHoler(@NonNull View itemView) {
            super(itemView);
            txtNameHotel=itemView.findViewById(R.id.txtNameHotel);
            txtRating = itemView.findViewById(R.id.txtRatingHotel);
            txtNumrating = itemView.findViewById(R.id.txtNumRatingHotel);
            txtProvice = itemView.findViewById(R.id.txtProviceHotel);
            txtAddress=itemView.findViewById(R.id.txtAddressHotel);
            txtPhone=itemView.findViewById(R.id.txtPhoneHotel);
            txtPrice= itemView.findViewById(R.id.txtPriceHotel);
        }
    }
}
