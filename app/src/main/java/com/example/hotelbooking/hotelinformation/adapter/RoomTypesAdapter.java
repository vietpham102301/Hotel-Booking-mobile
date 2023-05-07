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
import com.example.hotelbooking.hotelinformation.model.Roomtypes;

import java.util.ArrayList;

public class RoomTypesAdapter extends RecyclerView.Adapter<RoomTypesAdapter.RoomTypesHolder> {
    private Context context;
    private ArrayList<Roomtypes> mListRoomTypes;
    //private String priceRoom;

    public RoomTypesAdapter(Context context, ArrayList<Roomtypes> mListRoomTypes) {
        this.context = context;
        this.mListRoomTypes = mListRoomTypes;
    }

    @NonNull
    @Override
    public RoomTypesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.hotel_inf_item_room,parent,false);
        return new RoomTypesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomTypesHolder holder, int position) {
        holder.blind(mListRoomTypes.get(position));
    }

    @Override
    public int getItemCount() {
        return mListRoomTypes.size();
    }

    public class RoomTypesHolder extends RecyclerView.ViewHolder {
        private TextView txtNameRoom;
        private TextView txtQuality;
        private TextView txtPriceRoom;
        private TextView getTxtPriceRoom;
        private Button btnadd;
        private Button btnremove;
        private Button btnSelectRoom;

        private EditText edtNumRoom;
        private int NumRoom = 1;
        public RoomTypesHolder(@NonNull View itemView) {
            super(itemView);
            txtNameRoom=itemView.findViewById(R.id.txtNameRoom);
            txtQuality=itemView.findViewById(R.id.txtQuatity);
            txtPriceRoom=itemView.findViewById(R.id.txtPriceRoom);
            btnadd=itemView.findViewById(R.id.addBtn);
            btnremove=itemView.findViewById(R.id.removeBtn);
            btnSelectRoom=itemView.findViewById(R.id.btnSelectRoom);
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
//            btnSelectRoom.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Collector.priceRoom=String.valueOf(txtPriceRoom.getText());
//                    //priceRoom=String.valueOf(txtPriceRoom.getText());
//                    //System.out.println(Collector.priceRoom);
//                }
//            });
        }
        public void blind(Roomtypes roomtypes){
                txtNameRoom.setText(roomtypes.getName());
                txtQuality.setText(roomtypes.getQuantity() + " rooms left");
                txtPriceRoom.setText(String.valueOf(roomtypes.getPrice()) + " VND");

        }
    }
}


