package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.hotelbooking.Collector.Collector;
import com.example.hotelbooking.hotelinformation.SliderItem;
import com.example.hotelbooking.hotelinformation.adapter.RoomTypesAdapter;
import com.example.hotelbooking.hotelinformation.adapter.SliderAdapter;
import com.example.hotelbooking.hotelinformation.api.Api;
import com.example.hotelbooking.hotelinformation.api.Appclient;
import com.example.hotelbooking.hotelinformation.model.HotelOutfit;
import com.example.hotelbooking.hotelinformation.model.Roomtypes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelInformationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    TextView txtNoiDung;
    Button btnDescription;
    Button btnFeatures;
    Button btnRoomandprice;
    Button btnClick;


    private TextView txtNameHotelInf;
    private TextView txtRatingHotelInf;
    private TextView txtNumRatingHotelInf;
    private TextView txtProvinceHotelInf;
    private TextView txtNameHotelInfSub;
    private TextView txtAddressHotelInf;
    private TextView txtTimeBooking;
    private TextView txtPriceRoomHotel;


    private DatePickerDialog datePickerDialog;
    private DatePickerDialog datePickerDialog1;
    private Button checkInButtonPay;
    private Button checkOutButtonPay;
    private RecyclerView rcvRoomList;
    private RoomTypesAdapter roomTypesAdapter;
    private ArrayList<Roomtypes> mRoomTypesList;

    private String Description;



    private Spinner spinner;
    private static final String[] paths = {"2 người lớn",
            "2 người lớn 1 trẻ em",
            "2 người lớn 2 trẻ em",
            "3 người lớn",
            "3 người lớn 1 trẻ em",
            "4 người lớn"};

    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_information);



        //Declare
            //Header
                txtNameHotelInf=findViewById(R.id.txtNameHotelInf);
                txtRatingHotelInf=findViewById(R.id.txtRatingHotelInf);
                txtNumRatingHotelInf=findViewById(R.id.txtNumRatingHotelInf);
                txtProvinceHotelInf=findViewById(R.id.txtProviceHotelInf);
            //Body
                txtNameHotelInfSub=findViewById(R.id.txtNameHotelInfSub);
                txtAddressHotelInf=findViewById(R.id.txtAddressHotelInf);
                //View ImageHotel
                    viewPager2 = findViewById(R.id.pager);
                //Description-Features-Roomandprice
                    btnDescription = (Button) findViewById(R.id.btnDescription);
                    btnFeatures = (Button) findViewById(R.id.btnFeatures);
                    btnRoomandprice = (Button) findViewById(R.id.btnRoomandprice);
                    txtNoiDung = (TextView) findViewById(R.id.textDescription);
                //Payment

                    txtPriceRoomHotel=findViewById(R.id.txtPriceRoomHotel);
                    txtTimeBooking=findViewById(R.id.txtTimeBooking);
                    checkInButtonPay = findViewById(R.id.checkinButton);
                    checkOutButtonPay = findViewById(R.id.checkoutButton);
                    spinner = (Spinner)findViewById(R.id.spinner);

                //Room
                    mRoomTypesList=new ArrayList<>();
                    rcvRoomList=findViewById(R.id.rcvRoomList);
                    rcvRoomList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    roomTypesAdapter = new RoomTypesAdapter(HotelInformationActivity.this,mRoomTypesList);
                    rcvRoomList.setAdapter(roomTypesAdapter);
                    //txtPriceRoomHotel.setText(new );




        //Description-Features-Roomandprice
        btnFeatures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnFeatures.setTextColor(Color.parseColor("#3F72AF"));
                btnRoomandprice.setTextColor(Color.parseColor("#99000000"));
                btnDescription.setTextColor(Color.parseColor("#99000000"));
                txtNoiDung.setText(" btn Features");
            }
        });
        btnRoomandprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRoomandprice.setTextColor(Color.parseColor("#3F72AF"));
                btnDescription.setTextColor(Color.parseColor("#99000000"));
                btnFeatures.setTextColor(Color.parseColor("#99000000"));
                txtNoiDung.setText("Phuong");
            }
        });
        btnDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDescription.setTextColor(Color.parseColor("#3F72AF"));
                btnRoomandprice.setTextColor(Color.parseColor("#99000000"));
                btnFeatures.setTextColor(Color.parseColor("#99000000"));
                txtNoiDung.setText("Claiming a spectacular stretch of Vietnam’s coastline withinthe verdant embrace of Nui Chua National Park and UNESCO Biosphere Reserve,Amanoi is a natural paradise overlooking Vinh Hy Bay. From its remote location - a rich and diverse mosaic of ecosystems – the resort’s clifftop restaurants and pool, lakeside AmanSpa and private golden sand beach, offer limitless opportunities for outdoor exploration, cultural immersion and serene time out.");
            }
        });


        //Date
        checkInButtonPay.setText(getTodaysDate());
        checkOutButtonPay.setText(getTodaysDate());
        initDatePicker();



        //Spinner Traveller
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HotelInformationActivity.this,
                R.layout.spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        //ViewPage2 ImageHotel
        List<SliderItem> sliderItemArrayList = new ArrayList<>();
        sliderItemArrayList.add(new SliderItem(R.drawable.hotel_amanoi));
        sliderItemArrayList.add(new SliderItem(R.drawable.hotel_amanoi));
        sliderItemArrayList.add(new SliderItem(R.drawable.hotel_amanoi));
        sliderItemArrayList.add(new SliderItem(R.drawable.hotel_amanoi));
        sliderItemArrayList.add(new SliderItem(R.drawable.hotel_amanoi));

        viewPager2.setAdapter(new SliderAdapter(sliderItemArrayList, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(5);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(200));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 1000);//Slide Duration 3 sec
            }
        });


        //callApiRoomInHotel(1,"2023-04-16","2023-04-20");
        callApiHotelInformation(1);

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
                checkInButtonPay.setText(date);
                //System.out.println(datePickerDialog.getDatePicker().getDayOfMonth());
//                String datetocall= makeDayToCallApi(day,month,year);
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
                String checkOut=makeDayToCallApi(day,month,year);
                String countDate;
                if(compareDate(Collector.ci,checkOut)){
                    Toast.makeText(HotelInformationActivity.this,"Check-out Error",Toast.LENGTH_SHORT).show();
                }else {
                    checkOutButtonPay.setText(date1);
                    Collector.co = makeDayToCallApi(day, month, year);
                    System.out.println(Collector.co);
                    countDate=countDate(Collector.ci,Collector.co);
                    txtTimeBooking.setText(countDate + " nights");
                }
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
    public static String countDate(String ci, String co){
        Date date1 = Date.valueOf(ci);
        Date date2 = Date.valueOf(co);

        long start=date1.getTime();
        long end=date2.getTime();
        long tmp=Math.abs(start-end);
        long result=tmp/(24*60*60*1000);

        return String.valueOf(result);
    }



    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                System.out.println("2");
                break;
            case 1:
                System.out.println("3");
                break;
            case 2:
                System.out.println("4");
                break;
            case 3:
                System.out.println("3");
                break;
            case 4:
                System.out.println("4");
                break;
            case 5:
                System.out.println("4");
                break;

        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void callApiHotelInformation(int id){
        Appclient.getClient().create(Api.class).getHotelInformation(id).enqueue(new Callback<HotelOutfit>() {
            @Override
            public void onResponse(Call<HotelOutfit> call, Response<HotelOutfit> response) {
                HotelOutfit hotel=response.body();
                if (hotel!=null) {
                    txtNameHotelInf.setText(hotel.getData().getName());
                    txtNameHotelInfSub.setText(hotel.getData().getName());
                    txtRatingHotelInf.setText(String.valueOf(hotel.getData().getRating()));
                    txtNumRatingHotelInf.setText("( " + String.valueOf(hotel.getData().getNumRating()) + " reviews)");
                    txtProvinceHotelInf.setText(hotel.getData().getProvinceId());
                    txtAddressHotelInf.setText(hotel.getData().getAddress());
                    if(hotel.getData().getRoomTypes()!=null) {
                        mRoomTypesList.addAll(hotel.getData().getRoomTypes());
                        roomTypesAdapter.notifyDataSetChanged();
                        Toast.makeText(HotelInformationActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
                        System.out.println("Success1");
                    } else System.out.println("error1");
                    Toast.makeText(HotelInformationActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
                    System.out.println("Success");
                }
//                else System.out.println("error");
            }

            @Override
            public void onFailure(Call<HotelOutfit> call, Throwable t) {
                System.out.println("Error");
            }
        });
    }
//    private void callApiRoomInHotel(int id,String ci,String co){
//        Appclient.getClient().create(Api.class).getRoomInHotel(id,ci,co).enqueue(new Callback<RoomOutFit>() {
//            @Override
//            public void onResponse(Call<RoomOutFit> call, Response<RoomOutFit> response) {
//                System.out.println("Success3");
//                RoomOutFit room= response.body();
//                mRoomTypesList.addAll(room.getData());
//            }
//
//            @Override
//            public void onFailure(Call<RoomOutFit> call, Throwable t) {
//                System.out.println("ERROR");
//
//            }
//        });
//    }

}