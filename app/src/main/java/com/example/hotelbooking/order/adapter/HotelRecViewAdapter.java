package com.example.hotelbooking.order.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotelbooking.PaymentActivity;
import com.example.hotelbooking.R;
import com.example.hotelbooking.order.model.HotelOrder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class HotelRecViewAdapter extends RecyclerView.Adapter<HotelRecViewAdapter.ViewHolder> {

    private ArrayList<HotelOrder> hotelOrders = new ArrayList<>();

    private Context context;
    private String token;

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
        holder.setIsRecyclable(false);
        holder.hoteNameTxtView.setText(hotelOrders.get(position).getHotelName());
        holder.ratingTxtView.setText((int)hotelOrders.get(position).getRating() + " stars");
        holder.roomTypeTxtView.setText(hotelOrders.get(position).getRoomType());
        holder.checkInTxtView.setText(hotelOrders.get(position).getCheckIn());
        holder.checkOutTxtView.setText(hotelOrders.get(position).getCheckOut());
        String status = hotelOrders.get(position).getStatus();
        String displayStatus = "";
        if(status.equals("CHOXACNHAN")){
            displayStatus = "Waiting for confirm";
        }else if(status.equals("DATTHANHCONG")){
            displayStatus = "Confirmed";
        }else if(status.equals("DAHOANTHANH")){
            displayStatus = "Done";
        }else if(status.equals("DAHUY")){
            displayStatus = "Cancel";
        }else if(status.equals("DANGSUDUNG")){
            displayStatus = "Using";
        }
        holder.statusTxtView.setText(displayStatus);
        holder.priceTxtView.setText("$"+hotelOrders.get(position).getPrice());



        //old rating
        String oldCmt = hotelOrders.get(position).getComment();
        Double cmtRating = hotelOrders.get(position).getRatingComment();
        if(oldCmt != null && cmtRating != null ){
            holder.submitButton.setEnabled(false);
            holder.commentEditTxt.setText(oldCmt);
            holder.commentEditTxt.setFocusable(false);
            if(cmtRating == 1){
                holder.star1.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star2.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star3.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star4.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star5.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
            }else if(cmtRating == 2){
                holder.star1.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star2.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star3.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star4.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star5.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
            }else if(cmtRating == 3){
                holder.star1.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star2.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star3.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star4.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star5.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
            }else if(cmtRating == 4){
                holder.star1.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star2.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star3.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star4.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star5.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
            }else {
                holder.star1.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star2.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star3.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star4.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star5.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
            }
        }

        //rating
        final double[] rating = {-1};
        if(oldCmt == null && cmtRating == null ){

            holder.star1.setOnClickListener(view -> {
                holder.star1.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                rating[0] = 1;
                holder.star2.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star3.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star4.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star5.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
            });

            holder.star2.setOnClickListener(view ->{
                holder.star1.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star2.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                rating[0] = 2;
                holder.star3.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star4.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star5.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
            });
            holder.star3.setOnClickListener(view ->{
                holder.star1.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star2.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star3.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                rating[0] = 3;
                holder.star4.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star5.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
            });
            holder.star4.setOnClickListener(view ->{
                holder.star1.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star2.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star3.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star4.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                rating[0] = 4;
                holder.star5.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
            });
            holder.star5.setOnClickListener(view ->{
                holder.star1.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star2.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star3.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star4.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.star5.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
                rating[0] = 5;
            });
        }

                setData();

                holder.submitButton.setOnClickListener(view -> {
                    String cmt = String.valueOf(holder.commentEditTxt.getText());

                    if (!cmt.equals("") && status.equals("DAHOANTHANH") && !(rating[0] == -1)) {
                        String url = "http://14.225.255.238/booking/api/v1/order/" + hotelOrders.get(position).getOrderID() + "/rating?comment=" + cmt + "&rating=" + (int)rating[0];
                        try{
                            makeRequest("PATCH", url);
                            holder.submitButton.setEnabled(false);
                            holder.commentEditTxt.setFocusable(false);
                        }catch (Exception e){
                            Log.e("ERR", "API call failed", e);
                            return;
                        }
                        Toast.makeText(context, "Your comment is record successful", Toast.LENGTH_SHORT).show();
                    } else {
                        if (cmt.equals("")) {
                            Toast.makeText(context, "Comment must not be empty!", Toast.LENGTH_SHORT).show();
                        } else if(!status.equals("DAHOANTHANH")){
                            Toast.makeText(context, "You need to wait until status is Done", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "You need to give a rating star", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

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

    public void makeRequest(String requestType, String u){
        Thread thread = new Thread(()->{
            try {
                URL url = new URL(u);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod(requestType);
                con.setRequestProperty("Authorization", token);
                Log.d("token at adapter", token);
                Log.d("url", u);
                Log.d("requestType", requestType);
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
                } else {
                    Log.e("ERR", "API call failed with response code: " + responseCode);
                }
            } catch (Exception e) {
                Log.e("ERR", "API call failed", e);
            }
        });
        thread.start();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView hoteNameTxtView;
        private TextView ratingTxtView;
        private TextView roomTypeTxtView;
        private TextView checkInTxtView;
        private TextView checkOutTxtView;
        private ImageView hotelImgView;
        private TextView statusTxtView;
        private TextView priceTxtView;
        private Button submitButton;
        private EditText commentEditTxt;
        private CardView parent;
        private ImageView star1;
        private ImageView star2;
        private ImageView star3;
        private ImageView star4;
        private ImageView star5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hoteNameTxtView = itemView.findViewById(R.id.txtHotelName);
            parent = itemView.findViewById(R.id.CardViewParent);
            ratingTxtView = itemView.findViewById(R.id.txtRating);
            roomTypeTxtView = itemView.findViewById(R.id.txtRoomType);
            checkInTxtView = itemView.findViewById(R.id.txtCheckin);
            checkOutTxtView = itemView.findViewById(R.id.txtCheckout);
            hotelImgView = itemView.findViewById(R.id.imgHotel);
            statusTxtView = itemView.findViewById(R.id.statusTxtView);
            priceTxtView = itemView.findViewById(R.id.priceTxtView);
            submitButton = itemView.findViewById(R.id.submitButton);
            commentEditTxt = itemView.findViewById(R.id.commentEditTxt);
            star1 = itemView.findViewById(R.id.star2);
            star2 = itemView.findViewById(R.id.star3);
            star3 = itemView.findViewById(R.id.star5);
            star4 = itemView.findViewById(R.id.star6);
            star5 = itemView.findViewById(R.id.star7);
        }
    }

    public void setHotelOrders(ArrayList<HotelOrder> hotelOrders) {
        this.hotelOrders = hotelOrders;
        notifyDataSetChanged();
    }

    public void setData(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PaymentActivity.SHARED_PREFS, PaymentActivity.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

    }

//    public void setToken(String token) {
//        this.token = token;
//    }
}
