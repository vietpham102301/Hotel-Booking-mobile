package com.example.hotelbooking.hotelinformation.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking.HotelInformationActivity;
import com.example.hotelbooking.PaymentActivity;
import com.example.hotelbooking.R;
import com.example.hotelbooking.hotelinformation.model.Room;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomHolder> {
    private Context context;
    private ArrayList<Room> roomList;


    public RoomAdapter(Context context, ArrayList<Room> roomList ) {
        this.context = context;
        this.roomList = roomList;

    }

    @NonNull
    @Override
    public RoomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.hotel_inf_item_room,parent,false);
        return new RoomHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomHolder holder, int position) {
        holder.blind(roomList.get(position));
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public class RoomHolder extends RecyclerView.ViewHolder {
        private TextView txtNameRoom;
        private TextView txtQuatity;
        private TextView txtPriceRoom;

        private Button btnadd;
        private Button btnremove;
        private Button btnSelectRoom;
        private EditText edtNumRoom;
        private int NumRoom =1 ;
        private int MaxRoom;
        private float priceRoom;
        private int idRoom;
        private String nameRoom;
        private int quantityRoom;
        public RoomHolder(@NonNull View itemView) {
            super(itemView);
            txtNameRoom=itemView.findViewById(R.id.txtNameRoom);
            txtQuatity=itemView.findViewById(R.id.txtQuatity);
            txtPriceRoom=itemView.findViewById(R.id.txtPriceRoom);
            btnadd=itemView.findViewById(R.id.addBtn);
            btnremove=itemView.findViewById(R.id.removeBtn);
            btnSelectRoom=itemView.findViewById(R.id.btnSelectRoom);
            edtNumRoom=itemView.findViewById(R.id.edtNumRoom);

            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(NumRoom>=1 && NumRoom <=MaxRoom-1) {
                        NumRoom += 1;
                        edtNumRoom.setText(String.valueOf(NumRoom));
                    }
                }
            });
            btnremove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(NumRoom>1) {
                        NumRoom -= 1;
                        edtNumRoom.setText(String.valueOf(NumRoom));
                        //txtQuatity.setText(String.valueOf(MaxRoom-1));

                    }
                }
            });
            btnSelectRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, PaymentActivity.class);
                    context.startActivity(intent);
                    saveData(idRoom,nameRoom,quantityRoom,priceRoom);
                }
            });
        }


        public void saveData(int idRooms, String nameRooms,int quantityRooms, Float priceRooms){
            SharedPreferences sharedPreferences = context.getSharedPreferences(HotelInformationActivity.SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putFloat(HotelInformationActivity.PRICE, priceRooms);
            editor.putString(HotelInformationActivity.ROOM_TYPE, nameRooms);
            editor.putInt(HotelInformationActivity.ROOM_TYPE_ID, idRooms);
            editor.putInt(HotelInformationActivity.QUANTITY, quantityRooms);
            editor.apply();
        }
        public void blind(Room room){
                txtNameRoom.setText(room.getName());
                txtQuatity.setText(String.valueOf(room.getQuantity())+ " rooms left");
                txtPriceRoom.setText(String.valueOf(room.getPrice()) + " VND");
                MaxRoom=room.getQuantity();
                idRoom=room.getId();
                nameRoom=room.getName();
                quantityRoom=room.getQuantity();
                priceRoom=Float.parseFloat(String.valueOf(room.getPrice()*NumRoom));
        }

    }

}

