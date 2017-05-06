package com.qsr.graduationpro.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qsr.graduationpro.R;
import com.qsr.graduationpro.mvp.model.data.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.view.adapter
 * Author : qsr
 * Time : 2017/5/6 17:36
 * Description :
 **************************************/
public class FameAdapter extends RecyclerView.Adapter<FameAdapter.MyViewHolder> {
    private List<User> userList;
    private Context context;
    private LayoutInflater mInflater;
    public FameAdapter(Context context, List<User> list) {
        this.context = context;
        this.userList = list;
        mInflater = LayoutInflater.from(context);
    }

    //创建一个viewHolder
    @Override
    public FameAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycle_fameitem, parent, false);
        FameAdapter.MyViewHolder myViewHolder = new FameAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    //绑定一个viewholder
    @Override
    public void onBindViewHolder(FameAdapter.MyViewHolder holder, int position) {
        Picasso.with(context).load(userList.get(position).getAvatar()).into(holder.itemImageview);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageview;
        CardView itemCardview;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemImageview = (ImageView) itemView.findViewById(R.id.item_imageview);
            itemCardview = (CardView) itemView.findViewById(R.id.item_cardview);
        }
    }
}


