package com.example.cwh.mypermission;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cwh on 2018/8/6.
 */

public class ContactsPerson_recycleciew_adapter extends RecyclerView.Adapter<ContactsPerson_recycleciew_adapter.ViewHolder>{

    private List<ContactsPerson>mContactsPersonList;
    private int mPosition = -1;

    public ContactsPerson_recycleciew_adapter(List<ContactsPerson> list){
        mContactsPersonList = list;
    }
    @Override
    public ContactsPerson_recycleciew_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_recycleview_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final ContactsPerson_recycleciew_adapter.ViewHolder holder, int position) {
        ContactsPerson person = mContactsPersonList.get(position);
        holder.textView_name.setText(person.getName());
        holder.textView_number.setText(person.getNumber());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPosition = holder.getAdapterPosition();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContactsPersonList.size();
    }
    public int getPosition(){
        return mPosition;
    }

    public void removeItem(int position){
        mContactsPersonList.remove(position);
        notifyDataSetChanged();
    }

    //为每一个itemview添加一个OnCreateContextMenuListener（）事件
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        public TextView textView_name;
        public TextView textView_number;
        public ViewHolder(View itemView) {
            super(itemView);
            textView_name = (TextView) itemView.findViewById(R.id.textView_name);
            textView_number = (TextView) itemView.findViewById(R.id.textView_number);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("联系人");
            menu.setHeaderIcon(R.drawable.me);
            menu.add(1,1,1,"删除");
            menu.add(1,2,1,"修改");
        }
    }
}
