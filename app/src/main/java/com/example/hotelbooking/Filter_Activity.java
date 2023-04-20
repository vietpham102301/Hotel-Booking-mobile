package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class Filter_Activity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    ListView listView;

    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    private DatePickerDialog datePickerDialog;
    private DatePickerDialog datePickerDialog1;
    private Button checkInButtonSearch;
    private Button checkOutButtonSearch;

    Dialog dialog;

    String[] nameHotelList=new String[]{"Amanoi","Furama","La Vela","Lotte","Muong Thanh","Preidot Grand","Bel Marina","Phuong","Nhat","Nhung"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter);
        textView=findViewById(R.id.search_bar);
        initDatePicker();
        checkInButtonSearch = findViewById(R.id.checkInButtonSearch);
        checkInButtonSearch.setText(getTodaysDate());
        checkOutButtonSearch = findViewById(R.id.checkOutButtonSearch);
        checkOutButtonSearch.setText(getTodaysDate());

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
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                checkInButtonSearch.setText(date);
                checkOutButtonSearch.setText(date);

            }

        };
        DatePickerDialog.OnDateSetListener dateSetListener1 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                checkOutButtonSearch.setText(date);

            }

        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        cal.set(year,month,day);


        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
        datePickerDialog1 = new DatePickerDialog(this, style, dateSetListener1, year, month, day);
        datePickerDialog1.getDatePicker().setMinDate(cal.getTimeInMillis());
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openCheckInPicker(View view)
    {
        datePickerDialog.show();
    }
    public void openCheckOutPicker(View view)
    {
        datePickerDialog1.show();
    }



}