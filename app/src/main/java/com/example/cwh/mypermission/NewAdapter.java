package com.example.cwh.mypermission;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

/**
 * Created by hui on 2019/2/28.
 */

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder>{

    List<Map<String,Object>> listdate;

    public NewAdapter( List<Map<String,Object>>  listdate){
       this.listdate = listdate;
    }

    //recyclerView中每一个子项的中含有的控件
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
