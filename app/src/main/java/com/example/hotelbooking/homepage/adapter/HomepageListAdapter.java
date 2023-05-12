package com.example.hotelbooking.homepage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotelbooking.R;
import com.example.hotelbooking.homepage.model.HomepageList;

import java.util.ArrayList;

public class HomepageListAdapter extends RecyclerView.Adapter<HomepageListAdapter.ViewHolder>{
    private Context context;
    private ArrayList<HomepageList> mListHomePages;
    public HomepageListAdapter(Context context, ArrayList<HomepageList> mListHomePages) {
        this.context =  context;
        this.mListHomePages = mListHomePages;
    }

    @NonNull
    @Override
    public HomepageListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.homepage_itemview, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull HomepageListAdapter.ViewHolder holder, int position) {
        holder.bind(mListHomePages.get(position));
    }

    @Override
    public int getItemCount() {
        return mListHomePages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView avatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.iv_name);
            avatar = itemView.findViewById(R.id.iv_avatar);
        }
        public void bind(HomepageList homepageList) {
            name.setText(homepageList.getName());
            Glide.with(context).load(homepageList.getAvatar()).into(avatar);
        }
    }
}
