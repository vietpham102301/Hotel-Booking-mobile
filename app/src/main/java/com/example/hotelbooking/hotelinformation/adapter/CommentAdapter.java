package com.example.hotelbooking.hotelinformation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking.R;
import com.example.hotelbooking.hotelinformation.model.Comments;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {
    private ArrayList<Comments> mCommentList;
    private Context context;

    public CommentAdapter(Context context, ArrayList<Comments> mCommentList) {
        this.mCommentList = mCommentList;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.hotel_inf_item_review,parent,false);
        return new CommentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        holder.blind(mCommentList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvatarUser;
        private TextView txtNameUser;
        private TextView txtGenderUser;
        private TextView txtRatingUser;
        private TextView txtModifyTime;
        private TextView txtCommentUser;
        public CommentHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatarUser=itemView.findViewById(R.id.imgAvatarUser);
            txtNameUser=itemView.findViewById(R.id.txtNameUser);
            txtGenderUser=itemView.findViewById(R.id.txtGenderUser);
            txtRatingUser=itemView.findViewById(R.id.txtRatingUser);
            txtModifyTime=itemView.findViewById(R.id.txtModifyTime);
            txtCommentUser=itemView.findViewById(R.id.txtCommnetUser);
        }
        public void blind(Comments comments){
            //Glide.with(context).load(comments.getAvatar()).into(imgAvatarUser);
            txtNameUser.setText(comments.getName());
            txtGenderUser.setText(comments.getGender());
            txtRatingUser.setText(String.valueOf(comments.getRating()));
            txtModifyTime.setText(comments.getModifyTime());
            txtCommentUser.setText(comments.getComment());
        }
    }
}
