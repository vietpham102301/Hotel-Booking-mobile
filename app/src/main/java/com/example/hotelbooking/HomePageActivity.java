package com.example.hotelbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.hotelbooking.homepage.adapter.ListViewAdapter;
import com.example.hotelbooking.Collector.Collector;
import com.example.hotelbooking.filter.api.ApiService;
import com.example.hotelbooking.filter.model.ProvicesOutFit;
import com.example.hotelbooking.homepage.api.ApiClient;
import com.example.hotelbooking.homepage.model.HomepageListApiResponse;
import com.example.hotelbooking.homepage.adapter.HomepageListAdapter;
import com.example.hotelbooking.homepage.api.ApiHomepage;
import com.example.hotelbooking.homepage.model.HomepageList;
import com.example.hotelbooking.hotelinformation.SliderItem;
import com.example.hotelbooking.hotelinformation.SliderAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private HomepageListAdapter homepageListAdapter;
//    private ListViewAdapter mlistviewAdapter;
    private RecyclerView hp_listitem;
    private ArrayList<HomepageList> mListHomePage;
    //ArrayList<Homepage> homepages;
    public static final String SHARED_PREFS = "bookingApp";
    private ViewPager2 viewPager;
    private DatePickerDialog datePickerDialogHp;
    private DatePickerDialog datePickerDialogHp1;
    private Button checkInButton;
    private Button checkOutButton;
    private TextView customerNameTxtView;
    private String customerName;
    private Button btnSearch;
    TextView textView;
    Dialog dialog;
    EditText editText;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter3;
    private Handler sliderHandler = new Handler();

    private static final String[] paths = {
            "PASSENGER",
            "2 người lớn",
            "2 người lớn 1 trẻ em",
            "2 người lớn 2 trẻ em",
            "3 người lớn",
            "3 người lớn 1 trẻ em",
            "4 người lớn"};

    private Spinner spinner;

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

//        //call Api
//        mListHomePage = new ArrayList<>();
//        populateHomepage();
//        hp_listitem = findViewById(R.id.hp_listitem);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        hp_listitem.setHasFixedSize(true);
//        hp_listitem.setLayoutManager(linearLayoutManager);
//        hp_listitem.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
////        hp_listitem.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
////        mlistviewAdapter = new ListViewAdapter(HomePageActivity.this, );
//        homepageListAdapter = new HomepageListAdapter(HomePageActivity.this, mListHomePage);
//        hp_listitem.setAdapter(homepageListAdapter);

        //item payment
        Spinner spinner = findViewById(R.id.spinner);
        final ArrayAdapter<CharSequence>[] adapter = new ArrayAdapter[]{ArrayAdapter.createFromResource(this, R.array.lang, R.layout.payment_spinner_item)};
        adapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter[0]);
        spinner.setSelected(false);
        spinner.setSelection(0,true);
        spinner.setOnItemSelectedListener(this);

        //id customer
        customerNameTxtView = findViewById(R.id.customerNameTxtView);
        setData();

        customerNameTxtView.setText(customerName);
        customerNameTxtView.setOnClickListener( view -> startActivity(new Intent(HomePageActivity.this, ProfileActivity.class)));

        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(view -> startActivity( new Intent(HomePageActivity.this, HotelInformationActivity.class)));

        arrayList =new ArrayList<>();
//        callApiListProvinces(arrayList);


        //banner
        viewPager = findViewById(R.id.pager);
        List<SliderItem> sliderItemArrayList = new ArrayList<>();
        sliderItemArrayList.add(new SliderItem(R.drawable.home_amanoi2));
        sliderItemArrayList.add(new SliderItem(R.drawable.home_amina));
        sliderItemArrayList.add(new SliderItem(R.drawable.home_banyan));
        sliderItemArrayList.add(new SliderItem(R.drawable.home_majestic));
        sliderItemArrayList.add(new SliderItem(R.drawable.home_six_senses));
        sliderItemArrayList.add(new SliderItem(R.drawable.home_topas));

        viewPager.setAdapter(new SliderAdapter(sliderItemArrayList, viewPager));
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(5);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(250));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager.setPageTransformer(compositePageTransformer);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);//Slide Duration 3 sec
            }
        });


        //check in - out
        initDatePicker();
        checkInButton = findViewById(R.id.checkInButton);
        checkInButton.setText(getTodaysDate());
        checkOutButton = findViewById(R.id.checkOutButton);
        checkOutButton.setText(getTodaysDate());
        spinner = (Spinner) findViewById(R.id.btnpsg);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(HomePageActivity.this,
                R.layout.spinner_item,paths);
        adapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(this);

        //SearchBar
        arrayList =new ArrayList<>();
        callApiListProvinces(arrayList);

        textView = findViewById(R.id.search_barhomepage);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 dialog = new Dialog(HomePageActivity.this);
                 dialog.setContentView(R.layout.dialog_searchble_spinner);
                 dialog.getWindow().setLayout(650,800);
                 dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                 dialog.show();
                 editText = dialog.findViewById(R.id.edit_search_filter);
                 listView = dialog.findViewById(R.id.search_list_name_hotel);
                 adapter3 = new ArrayAdapter<>(HomePageActivity.this, android.R.layout.simple_list_item_1,arrayList);
                 listView.setAdapter(adapter3);
                 editText.addTextChangedListener(new TextWatcher() {
                     @Override
                     public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                     @Override
                     public void onTextChanged(CharSequence s, int start, int before, int count) {
                         adapter3.getFilter().filter(s);
                     }
                     @Override
                     public void afterTextChanged(Editable s) {}
                 });

                 listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                     @Override
                     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                         textView.setText(adapter3.getItem(position));
                         Collector.prv= adapter3.getItem(position);
                         System.out.println(Collector.prv);
                         dialog.dismiss();
                     }
                 });
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    //Call Api
    public void populateHomepage(){
        ApiClient.getClient().create(ApiHomepage.class).getHomepage().enqueue(new Callback<HomepageListApiResponse>() {
            @Override
            public void onResponse(Call<HomepageListApiResponse> call, Response<HomepageListApiResponse> response) {
                System.out.println("SUCCESS");
                if (response.code() == 200){
                        mListHomePage.addAll(response.body().getData());
                        homepageListAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<HomepageListApiResponse> call, Throwable t) {
                System.out.println("error");
            }
        });
    }


    public void setData(){
        SharedPreferences sharedPreferences = getSharedPreferences(PaymentActivity.SHARED_PREFS,MODE_PRIVATE);
        customerName = sharedPreferences.getString(PaymentActivity.CUSTOMER_NAME, "");
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            checkInButton.setText(date);
//            checkOutButton.setText(date);
        };
        DatePickerDialog.OnDateSetListener dateSetListener1 = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            checkOutButton.setText(date);
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        cal.set(year,month,day);

        datePickerDialogHp = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialogHp.getDatePicker().setMinDate(cal.getTimeInMillis());
        datePickerDialogHp1 = new DatePickerDialog(this, style, dateSetListener1, year, month, day);
        datePickerDialogHp1.getDatePicker().setMinDate(cal.getTimeInMillis());
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
        datePickerDialogHp.show();
    }
    public void openCheckOutPicker(View view)
    {
        datePickerDialogHp1.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {return;}
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {return;}


    private void callApiListProvinces(ArrayList arrayList){
        ApiService.apiService.provinces().enqueue(new Callback<ProvicesOutFit>() {
            @Override
            public void onResponse(Call<ProvicesOutFit> call, Response<ProvicesOutFit> response) {
                Toast.makeText(HomePageActivity.this, "Call Api Success", Toast.LENGTH_SHORT).show();
                ProvicesOutFit data = response.body();
                for (int i=0;i<data.getData().size();i++) {
                    System.out.println(data.getData().get(i).getId());
                    arrayList.add(data.getData().get(i).getId());
                }
            }
            @Override
            public void onFailure(Call<ProvicesOutFit> call, Throwable t) {
                Toast.makeText(HomePageActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}