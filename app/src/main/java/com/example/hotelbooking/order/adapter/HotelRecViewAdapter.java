package com.example.hotelbooking.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotelbooking.R;
import com.example.hotelbooking.order.model.HotelOrder;

import java.util.ArrayList;

public class HotelRecViewAdapter extends RecyclerView.Adapter<HotelRecViewAdapter.ViewHolder> {

    private ArrayList<HotelOrder> hotelOrders = new ArrayList<>();

    private Context context;

    public HotelRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_order_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.hoteNameTxtView.setText(hotelOrders.get(position).getHotelName());
        holder.ratingTxtView.setText(hotelOrders.get(position).getRating() + " rating");
        holder.roomTypeTxtView.setText(hotelOrders.get(position).getRoomType());
        holder.checkInTxtView.setText(hotelOrders.get(position).getCheckIn());
        holder.checkOutTxtView.setText(hotelOrders.get(position).getCheckOut());
//        Log.d("imgURL here", hotelOrders.get(position).getHotelImg());
        Glide.with(context).asBitmap().load(hotelOrders.get(position).getHotelImg()).into(holder.hotelImgView);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, hotelOrders.get(holder.getAdapterPosition()).getHotelName() + " selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotelOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView hoteNameTxtView;
        private TextView ratingTxtView;
        private TextView roomTypeTxtView;
        private TextView checkInTxtView;
        private TextView checkOutTxtView;
        private ImageView hotelImgView;
        private CardView parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hoteNameTxtView = itemView.findViewById(R.id.txtHotelName);
            parent = itemView.findViewById(R.id.CardViewParent);
            ratingTxtView = itemView.findViewById(R.id.txtRating);
            roomTypeTxtView = itemView.findViewById(R.id.txtRoomType);
            checkInTxtView = itemView.findViewById(R.id.txtCheckin);
            checkOutTxtView = itemView.findViewById(R.id.txtCheckout);
            hotelImgView = itemView.findViewById(R.id.imgHotel);
        }
    }

    public void setHotelOrders(ArrayList<HotelOrder> hotelOrders) {
        this.hotelOrders = hotelOrders;
        notifyDataSetChanged();
    }
}
