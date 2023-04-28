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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.hotelbooking.hotelList.SliderItem;
import com.example.hotelbooking.hotelList.adapter.SliderAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    TextView txtNoiDungDescription;
    Button btnDescription;
    Button btnFeatures;
    Button btnRoomandprice;
    Button btnClick;


    private DatePickerDialog datePickerDialog;
    private DatePickerDialog datePickerDialog1;
    private Button checkInButton;
    private Button checkOutButton;

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
//        txtSeekBar =(TextView)findViewById(R.id.textViewSeekBar);
//        SeekBar=(SeekBar)findViewById(R.id.SeekBar);
//         perform seek bar change listener event used for getting the progress value
//        SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            int progressChangedValue = 0;
//
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                progressChangedValue = progress;
//                txtSeekBar.setText(String.valueOf(progressChangedValue));
//            }
//
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                // TODO Auto-generated method stub
//            }
//
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
        initDatePicker();
        checkInButton = findViewById(R.id.checkinButton);
        checkInButton.setText(getTodaysDate());
        checkOutButton = findViewById(R.id.checkoutButton);
        checkOutButton.setText(getTodaysDate());
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                R.layout.spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);






        txtNoiDungDescription = (TextView) findViewById(R.id.textDescription);
        btnDescription = (Button) findViewById(R.id.btnDescription);
        btnFeatures = (Button) findViewById(R.id.btnFeatures);
        btnRoomandprice = (Button) findViewById(R.id.btnRoomandprice);

        btnFeatures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnFeatures.setTextColor(Color.parseColor("#3F72AF"));
                btnRoomandprice.setTextColor(Color.parseColor("#99000000"));
                btnDescription.setTextColor(Color.parseColor("#99000000"));
                txtNoiDungDescription.setText(" btn Features");
            }
        });


        btnRoomandprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRoomandprice.setTextColor(Color.parseColor("#3F72AF"));
                btnDescription.setTextColor(Color.parseColor("#99000000"));
                btnFeatures.setTextColor(Color.parseColor("#99000000"));
                txtNoiDungDescription.setText("Phuong");
            }
        });


        btnDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDescription.setTextColor(Color.parseColor("#3F72AF"));
                btnRoomandprice.setTextColor(Color.parseColor("#99000000"));
                btnFeatures.setTextColor(Color.parseColor("#99000000"));
                txtNoiDungDescription.setText("Claiming a spectacular stretch of Vietnam’s coastline withinthe verdant embrace of Nui Chua National Park and UNESCO Biosphere Reserve,Amanoi is a natural paradise overlooking Vinh Hy Bay. From its remote location - a rich and diverse mosaic of ecosystems – the resort’s clifftop restaurants and pool, lakeside AmanSpa and private golden sand beach, offer limitless opportunities for outdoor exploration, cultural immersion and serene time out.");

            }
        });

        viewPager2 = findViewById(R.id.pager);

        //You can get it from API as well.
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
                sliderHandler.postDelayed(sliderRunnable, 3000);//Slide Duration 3 sec
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
                checkInButton.setText(date);
                checkOutButton.setText(date);

            }

        };
        DatePickerDialog.OnDateSetListener dateSetListener1 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                checkOutButton.setText(date);

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
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}