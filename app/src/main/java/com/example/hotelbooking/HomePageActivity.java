package com.example.hotelbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.hotelbooking.hotelinformation.SliderItem;
import com.example.hotelbooking.hotelinformation.SliderAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    private ViewPager2 viewPager;

    private Handler sliderHandler = new Handler();

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

        viewPager = findViewById(R.id.pager);

        //You can get it from API as well.
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
        compositePageTransformer.addTransformer(new MarginPageTransformer(150));
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
                sliderHandler.postDelayed(sliderRunnable, 5000);//Slide Duration 3 sec
            }
        });
    }

}