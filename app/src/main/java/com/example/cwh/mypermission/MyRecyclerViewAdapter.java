package com.example.cwh.mypermission;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cwh on 2018/7/23.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<Map<String,Object>> mMapList;//数据源
    private Context context;

    //这个ViewHolder含有recycleView每一个子项中所含有的控件，
    static class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        ImageView mImageView;
        TextView textView_name;
        TextView textView_price;
        TextView textView_desc;

        public ViewHolder(View view) {
            //参数View是recyclerView中子项的最外层布局
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.imageview);
            textView_name = (TextView) view.findViewById(R.id.textview1);
            textView_price = (TextView) view.findViewById(R.id.textview2);
            textView_desc = (TextView) view.findViewById(R.id.textview3);
        }
    }

    public MyRecyclerViewAdapter(List<Map<String,Object>>data, Context context){
        mMapList = data;
        this.context = context;

    }

    //这个方法是创建ViewHolder实例，我们一般将Recyclerview子项中的布局加载进来创建ViewHolder实例，
    // 并把加载出来的布局传到构造函数中，最后将ViewHolder实例返回
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myitem,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Map<String,Object> map = mMapList.get(position);
                addData(position);
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

        viewHolder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = viewHolder.getAdapterPosition();
                removeData(position);
                return true;
            }
        });
        return viewHolder;
    }

    //对于RecyclerView的子项的数据进行赋值
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Map<String,Object> map = mMapList.get(position);
        Glide.with(context)
                .load((int)map.get("pic"))
                .placeholder(R.drawable.loading)
                .override(60,60)
                .into(viewHolder.mImageView);
        //viewHolder.mImageView.setImageResource((int)map.get("pic"));
        viewHolder.textView_name.setText((String)map.get("name_TextView"));
        viewHolder.textView_price.setText((String)map.get("price_TextView"));
        viewHolder.textView_desc.setText((String)map.get("desc_TextView"));
    }

    @Override
    public int getItemCount() {
        return mMapList.size();
    }

    public void addData(int position){
        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("pic",R.drawable.chicken);
        map1.put("name_TextView","名称：小鸡鸡");
        map1.put("price_TextView","价格：50元");
        map1.put("desc_TextView","描述：这是世界上最好吃的小鸡鸡");
        mMapList.add(position,map1);
        notifyItemInserted(position);
    }

    public void removeData(int position){
        mMapList.remove(position);
        notifyItemRemoved(position);
    }
}
