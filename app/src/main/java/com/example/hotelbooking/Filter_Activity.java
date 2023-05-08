package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking.Collector.Collector;
import com.example.hotelbooking.filter.api.ApiService;
import com.example.hotelbooking.filter.model.ProvicesOutFit;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private Button btnNextPage;

    private CheckBox checkBoxRating1;
    private CheckBox checkBoxRating2;
    private CheckBox checkBoxRating3;
    private CheckBox checkBoxRating4;
    private CheckBox checkBoxRating5;
    private CheckBox checkBoxPrice1;
    private CheckBox checkBoxPrice2;
    private CheckBox checkBoxPrice3;
    private CheckBox checkBoxPrice4;
    private CheckBox checkBoxHotelType1;
    private CheckBox checkBoxHotelType2;
    private CheckBox checkBoxHotelType3;


    Dialog dialog;

    //String[] nameHotelList=new String[]{"Amanoi","Furama","La Vela","Lotte","Muong Thanh","Preidot Grand","Bel Marina","Phuong","Nhat","Nhung"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter);



        //Declare
        textView=findViewById(R.id.search_bar);
        checkInButtonSearch = findViewById(R.id.checkInButtonSearch);
        checkInButtonSearch.setText(getTodaysDate());
        checkOutButtonSearch = findViewById(R.id.checkOutButtonSearch);
        checkOutButtonSearch.setText(getTodaysDate());
        checkBoxRating1=findViewById(R.id.checkBox1Sub1);
        checkBoxRating2=findViewById(R.id.checkBox2Sub1);
        checkBoxRating3=findViewById(R.id.checkBox3Sub1);
        checkBoxRating4=findViewById(R.id.checkBox4Sub1);
        checkBoxRating5=findViewById(R.id.checkBox5Sub1);
        checkBoxPrice1=findViewById(R.id.checkBox1Sub2);
        checkBoxPrice2=findViewById(R.id.checkBox2Sub2);
        checkBoxPrice3=findViewById(R.id.checkBox3Sub2);
        checkBoxPrice4=findViewById(R.id.checkBox4Sub2);
        checkBoxHotelType1=findViewById(R.id.checkBox1Sub3);
        checkBoxHotelType2=findViewById(R.id.checkBox2Sub3);
        checkBoxHotelType3=findViewById(R.id.checkBox3Sub3);
        btnNextPage=findViewById(R.id.btnNextHotelList);


        //CallApi
        arrayList =new ArrayList<>();
        callApiListProvinces(arrayList);


        //SearchBar
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
                        Collector.prv=adapter.getItem(position);
                        System.out.println(Collector.prv);
                        dialog.dismiss();
                    }
                });
            }
        });
        //Check-in and Check-out
        initDatePicker();



        //BTNNextPage
        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HotelType
                if(checkBoxHotelType1.isChecked()){
                    Collector.typeHotel ="1";
                    if(checkBoxHotelType2.isChecked()){Collector.typeHotel +="2";}
                    if(checkBoxHotelType3.isChecked()){Collector.typeHotel +="3";}
                }
                else if(checkBoxHotelType2.isChecked()){
                    Collector.typeHotel ="2";
                    if(checkBoxHotelType1.isChecked()){Collector.typeHotel +="1";}
                    if(checkBoxHotelType3.isChecked()){Collector.typeHotel +="3";}
                }
                else if(checkBoxHotelType3.isChecked()){
                    Collector.typeHotel ="3";
                    if(checkBoxHotelType2.isChecked()){Collector.typeHotel +="2";}
                    if(checkBoxHotelType1.isChecked()){Collector.typeHotel +="1";}
                }
                //Rating
                if(checkBoxRating1.isChecked()){
                    Collector.rating = "11";
                    if(checkBoxRating2.isChecked()){Collector.rating += ",12"; }
                    if(checkBoxRating3.isChecked()){Collector.rating += ",13"; }
                    if(checkBoxRating4.isChecked()){Collector.rating += ",14"; }
                    if(checkBoxRating5.isChecked()){Collector.rating += ",15"; }
                }
                else if(checkBoxRating2.isChecked()){
                    Collector.rating = "12";
                    if(checkBoxRating1.isChecked()){Collector.rating += ",11"; }
                    if(checkBoxRating3.isChecked()){Collector.rating += ",13"; }
                    if(checkBoxRating4.isChecked()){Collector.rating += ",14"; }
                    if(checkBoxRating5.isChecked()){Collector.rating += ",15"; }
                }
                else if(checkBoxRating3.isChecked()){
                    Collector.rating = "13";
                    if(checkBoxRating2.isChecked()){Collector.rating += ",12"; }
                    if(checkBoxRating1.isChecked()){Collector.rating += ",11"; }
                    if(checkBoxRating4.isChecked()){Collector.rating += ",14"; }
                    if(checkBoxRating5.isChecked()){Collector.rating += ",15"; }
                }
                else if(checkBoxRating4.isChecked()){
                    Collector.rating = "14";
                    if(checkBoxRating2.isChecked()){Collector.rating += ",12"; }
                    if(checkBoxRating3.isChecked()){Collector.rating += ",13"; }
                    if(checkBoxRating1.isChecked()){Collector.rating += ",11"; }
                    if(checkBoxRating5.isChecked()){Collector.rating += ",15"; }
                }
                else if(checkBoxRating5.isChecked()){
                    Collector.rating = "15";
                    if(checkBoxRating2.isChecked()){Collector.rating += ",12"; }
                    if(checkBoxRating3.isChecked()){Collector.rating += ",13"; }
                    if(checkBoxRating4.isChecked()){Collector.rating += ",14"; }
                    if(checkBoxRating1.isChecked()){Collector.rating += ",11"; }
                }
                //Price
                if(checkBoxPrice1.isChecked()){
                    Collector.price ="21";
                    if(checkBoxPrice2.isChecked()){Collector.price +="22";}
                    if(checkBoxPrice3.isChecked()){Collector.price +="23";}
                    if(checkBoxPrice4.isChecked()){Collector.price +="24";}
                }
                if(checkBoxPrice2.isChecked()){
                    Collector.price ="22";
                    if(checkBoxPrice1.isChecked()){Collector.price +="21";}
                    if(checkBoxPrice3.isChecked()){Collector.price +="23";}
                    if(checkBoxPrice4.isChecked()){Collector.price +="24";}
                }
                if(checkBoxPrice3.isChecked()){
                    Collector.price ="23";
                    if(checkBoxPrice2.isChecked()){Collector.price +="22";}
                    if(checkBoxPrice1.isChecked()){Collector.price +="21";}
                    if(checkBoxPrice4.isChecked()){Collector.price +="24";}
                }
                if(checkBoxPrice4.isChecked()){
                    Collector.price ="24";
                    if(checkBoxPrice2.isChecked()){Collector.price +="22";}
                    if(checkBoxPrice3.isChecked()){Collector.price +="23";}
                    if(checkBoxPrice1.isChecked()){Collector.price +="21";}
                }

                if(Collector.prv==null){Toast.makeText(Filter_Activity.this,"Province Blank",Toast.LENGTH_SHORT).show();}
                else if(Collector.ci==null){Toast.makeText(Filter_Activity.this,"Check-in Blank",Toast.LENGTH_SHORT).show();}
                else if(Collector.co==null){Toast.makeText(Filter_Activity.this,"Check-out Blank",Toast.LENGTH_SHORT).show();}
                else if(compareDate(Collector.ci,Collector.co)){Toast.makeText(Filter_Activity.this,"Check-out Error",Toast.LENGTH_SHORT).show();}
                else {
                    Intent intent=new Intent(Filter_Activity.this,HotelListActivity.class);
                    startActivity(intent);
                }

            }

        });
    }


    //Date in Checkin-Checkout
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
                //System.out.println(datePickerDialog.getDatePicker().getDayOfMonth());
                String datetocall= makeDayToCallApi(day,month,year);
                Collector.ci=makeDayToCallApi(day,month,year);
                System.out.println(Collector.ci);

            }

        };
        DatePickerDialog.OnDateSetListener dateSetListener1 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date1 = makeDateString(day, month, year);
                checkOutButtonSearch.setText(date1);
                Collector.co=makeDayToCallApi(day,month,year);
                System.out.println(Collector.co);
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
    private String makeDayToCallApi(int day, int month, int year){
        return year+"-"+month+"-"+day;
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
    public static boolean compareDate(String ci,String co){
        Date date1 = Date.valueOf(ci);
        Date date2 = Date.valueOf(co);

        if (date2.equals(date1)) return false;
        else if (date2.after(date1)) return false;
        else return true;

    }

    //CallApiProvince
    private void callApiListProvinces(ArrayList arrayList){
        ApiService.apiService.provinces().enqueue(new Callback<ProvicesOutFit>() {
            @Override
            public void onResponse(Call<ProvicesOutFit> call, Response<ProvicesOutFit> response) {
                Toast.makeText(Filter_Activity.this, "Call Api Success", Toast.LENGTH_SHORT).show();
                ProvicesOutFit data = response.body();
                for (int i=0;i<data.getData().size();i++) {
                    System.out.println(data.getData().get(i).getId());
                    arrayList.add(data.getData().get(i).getId());
                }
            }
            @Override
            public void onFailure(Call<ProvicesOutFit> call, Throwable t) {
                Toast.makeText(Filter_Activity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}