package com.example.cwh.mypermission;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

/**
 * Created by cwh on 2018/7/23.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<Map<String,Object>> mMapList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        ImageView mImageView;
        TextView textView_name;
        TextView textView_price;
        TextView textView_desc;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.imageview);
            textView_name = (TextView) view.findViewById(R.id.textview1);
            textView_price = (TextView) view.findViewById(R.id.textview2);
            textView_desc = (TextView) view.findViewById(R.id.textview3);
        }
    }

    public MyRecyclerViewAdapter(List<Map<String,Object>>data){
        mMapList = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myitem,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Map<String,Object> map = mMapList.get(position);
                Toast.makeText(v.getContext(),"clicked view",Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Map<String,Object> map = mMapList.get(position);
                Toast.makeText(v.getContext(),"clicked image",Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.textView_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Map<String,Object> map = mMapList.get(position);
                Toast.makeText(v.getContext(),"clicked name",Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Map<String,Object> map = mMapList.get(position);
        viewHolder.mImageView.setImageResource((int)map.get("pic"));
        viewHolder.textView_name.setText((String)map.get("name_TextView"));
        viewHolder.textView_price.setText((String)map.get("price_TextView"));
        viewHolder.textView_desc.setText((String)map.get("desc_TextView"));
    }

    @Override
    public int getItemCount() {
        return mMapList.size();
    }
}
