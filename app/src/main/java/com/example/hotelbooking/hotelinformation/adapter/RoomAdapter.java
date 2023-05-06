package com.example.hotelbooking.hotelinformation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking.R;
import com.example.hotelbooking.hotelinformation.model.Room;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomHolder> {
    private final ArrayList<Room> roomList;

    private static Context context;

    public RoomAdapter(Context context, ArrayList<Room> roomList) {
        this.context=context;
        this.roomList = roomList;
    }

    @NonNull
    @Override
    public RoomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hotel_inf_item_room,parent,false);
        return new RoomHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public class RoomHolder extends RecyclerView.ViewHolder {
        private TextView txtNameRoom;
        private TextView txtQuality;
        private TextView txtPriceRoom;
        private Button btnadd;
        private Button btnremove;
        private EditText edtNumRoom;
        private int NumRoom = 1;
        public RoomHolder(@NonNull View itemView) {
            super(itemView);
            txtNameRoom=itemView.findViewById(R.id.txtNameRoom);
            txtQuality=itemView.findViewById(R.id.txtQuatity);
            txtPriceRoom=itemView.findViewById(R.id.txtPriceRoom);
            btnadd=itemView.findViewById(R.id.addBtn);
            btnremove=itemView.findViewById(R.id.removeBtn);
            edtNumRoom=itemView.findViewById(R.id.edtNumRoom);
            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(NumRoom>=1) {
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
                    }
                }
            });
        }
        public void blind(Room room){
            if(room.isActive()){
                txtNameRoom.setText(room.getName());
                txtQuality.setText(room.getQuantity() + " rooms left");
                txtPriceRoom.setText(String.valueOf(room.getPrice()) + " VND");
            }
        }
    }
}
