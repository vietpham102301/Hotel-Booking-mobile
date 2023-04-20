package com.example.hotelbooking;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Filter_Activity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    ListView listView;

    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    Dialog dialog;

    String[] nameHotelList=new String[]{"Amanoi","Furama","La Vela","Lotte","Muong Thanh","Preidot Grand","Bel Marina","Phuong","Nhat","Nhung"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter);
        textView=findViewById(R.id.search_bar);

        arrayList =new ArrayList<>();
        //arrayList.add(String.valueOf(nameHotelList));

        arrayList.add("Amanoi");
        arrayList.add("Furama");
        arrayList.add("La Vela");
        arrayList.add("Lotte");
        arrayList.add("Muong Thanh");
        arrayList.add("Preidot Grand");
        arrayList.add("Bel Marina");


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(Filter_Activity.this);
                dialog.setContentView(R.layout.dialog_searchble_spinner);
                dialog.getWindow().setLayout(650,800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                editText=dialog.findViewById(R.id.edit_search_filter);
                listView=dialog.findViewById(R.id.search_list_name_hotel);

                adapter=new ArrayAdapter<>(Filter_Activity.this, android.R.layout.simple_list_item_1,arrayList);

                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        textView.setText(adapter.getItem(position));
                        dialog.dismiss();
                    }
                });


            }
        });



    }


}