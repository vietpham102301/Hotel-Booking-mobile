package com.example.hotelbooking.homepage.adapter;

import android.content.Context;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotelbooking.R;
import com.example.hotelbooking.homepage.model.Homepage;

import java.util.ArrayList;

public class HomepageAdapter extends RecyclerView.Adapter<HomepageAdapter.ViewHolder>{

    Context context;
    ArrayList<Homepage> homepages;
    public HomepageAdapter(Context context,ArrayList<Homepage> arrayList) {
        this.context =context;
        this.homepages = arrayList;
    }

    @NonNull
    @Override
    public HomepageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.homepage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomepageAdapter.ViewHolder holder, int position) {
        holder.bind(homepages.get(position));
    }

    @Override
    public int getItemCount() {
        return homepages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView avatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.iv_name);
            avatar = itemView.findViewById(R.id.iv_avatar);

        }
        public void bind(Homepage homepage){
            name.setText(homepage.getName());
            Glide.with(context).load(homepage.getAvatar()).into(avatar);
        }
    }
}
