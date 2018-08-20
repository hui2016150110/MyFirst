package com.example.cwh.mypermission;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.ContactsContract;
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

    public int TOPCONTACTS = 0;

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
        if(TOPCONTACTS>position)
            holder.itemView.setBackgroundColor(0xffcfcfcf);
        else
            holder.itemView.setBackgroundColor(0xffffffff);
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

    public void removeItem(int position,ContentResolver contentResolver,long rawCantactId){
        ContactsPerson person = mContactsPersonList.remove(position);
        contentResolver.delete(ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI,rawCantactId),null,null);
        if(person.getTop()){
            TOPCONTACTS--;
        }
        notifyDataSetChanged();
    }

    public void moveTotop(int position){
        TOPCONTACTS++;
        ContactsPerson person = mContactsPersonList.get(position);
        mContactsPersonList.remove(position);
        notifyDataSetChanged();
        mContactsPersonList.add(0,person);
        notifyDataSetChanged();
        person.setTop(true);
    }

    public void add(Uri rawContactUri, ContentValues values,long rawCantactId,ContentResolver contentResolver,String name,String phone){
        if(name==null||phone==null)
            return;
        ContactsPerson person = new ContactsPerson(name,phone);
        mContactsPersonList.add(TOPCONTACTS,person);
        notifyDataSetChanged();
        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID,rawCantactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,name);
        contentResolver.insert(ContactsContract.Data.CONTENT_URI,values);

        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID,rawCantactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER,phone);
        contentResolver.insert(ContactsContract.Data.CONTENT_URI,values);
    }

    public void cancelTop(int position){
        TOPCONTACTS--;
        ContactsPerson person = mContactsPersonList.get(position);
        person.setTop(false);
        mContactsPersonList.remove(position);
        notifyDataSetChanged();
        mContactsPersonList.add(TOPCONTACTS,person);
        notifyDataSetChanged();
    }



    //为每一个itemview添加一个OnCreateContextMenuListener（）事件
        class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        public TextView textView_name;
        public TextView textView_number;
        private View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            textView_name = (TextView) itemView.findViewById(R.id.textView_name);
            textView_number = (TextView) itemView.findViewById(R.id.textView_number);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            ContactsPerson person = mContactsPersonList.get(mPosition);

            menu.setHeaderTitle("联系人");
            menu.setHeaderIcon(R.drawable.me);
            if (person.getTop()){
                menu.add(1,1,1,"删除");
                menu.add(1,2,1,"修改");
                menu.add(1,3,1,"取消置顶");
                menu.add(1,4,1,"添加");
            }
            else{
                menu.add(2,1,1,"删除");
                menu.add(2,2,1,"修改");
                menu.add(2,3,1,"置顶");
                menu.add(2,4,1,"添加");
            }
        }
    }
}
