package com.example.appfacebook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

        private Context mContext;
        private List<User> mListUser;

    public UserAdapter(Context mContext, List<User> mListUser) {
        this.mContext = mContext;
        this.mListUser = mListUser;
    }

    public void setData(List<User> list){
            this.mListUser = list;
            notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(mListUser!=null){
            return mListUser.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User user = mListUser.get(position);
        if(user==null){
            return;
        }

        holder.imgUser.setImageResource(user.getResourceImage());
        holder.tvName.setText(user.getName());
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(user);
            }
        });
    }

    private void onClickGoToDetail(User user) {
        Intent intent = new Intent(mContext,DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user",user);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private CardView layoutItem;
        private ImageView  imgUser;
        private TextView tvName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutItem = itemView.findViewById(R.id.layout_item);
            imgUser = itemView.findViewById(R.id.img_user);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
