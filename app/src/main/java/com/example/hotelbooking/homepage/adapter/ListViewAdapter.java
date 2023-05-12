package com.example.hotelbooking.homepage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotelbooking.R;
import com.example.hotelbooking.homepage.model.HomepageList;
import com.example.hotelbooking.homepage.model.HomepageListApiResponse;

import java.util.ArrayList;

//public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ItemViewHolder>{
//    private final ArrayList<HomepageListApiResponse> mListApiHomePages;
//    private Context context;
//
//    public ListViewAdapter(Context context, ArrayList<HomepageListApiResponse> mListApiHomePages) {
//        this.context = context;
//        this.mListApiHomePages = mListApiHomePages;
//    }
//
//    @NonNull
//    @Override
//    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.homepage, null);
//        return new ItemViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ItemViewHolder itemViewHolder, int position) {
//
////        HomepageListAdapter itemListAdapter = new HomepageListAdapter(context, false);
//        itemViewHolder.hp_listitem.setHasFixedSize(true);
//        itemViewHolder.hp_listitem.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
////        itemViewHolder.hp_listitem.setAdapter(itemListAdapter);
////        itemViewHolder.hp_listitem.setNestedScrollingEnabled(false);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mListApiHomePages.size();
//    }

//    public class ItemViewHolder extends RecyclerView.ViewHolder{
//        private RecyclerView hp_listitem;
//        private ItemViewHolder(View view) {
//            super(view);
//            hp_listitem = view.findViewById(R.id.hp_listitem);
//        }
//
//    }
//}

