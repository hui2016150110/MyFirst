package com.example.cwh.mypermission;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwh on 2018/8/1.
 */

public class MyListDialog extends Dialog {

    private Context mContext;
    private List<String> list_data = new ArrayList<>();
    private ImageView mImageView;
    private TextView title_textView;
    private ListView mListView;
    private ArrayAdapter<String> arr_adapter;

    public MyListDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdialog_layout);

        mImageView = (ImageView) findViewById(R.id.imageview);
        title_textView = (TextView) findViewById(R.id.textView);
        mListView = (ListView) findViewById(R.id.listView);
        arr_adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1,list_data);
        mListView.setAdapter(arr_adapter);



    }

    public void add_list(String item){
        list_data.add(item);
        arr_adapter.notifyDataSetChanged();
    }

    public void add_List(String[] collection){
        for(String item:collection){
            arr_adapter.add(item);
            arr_adapter.notifyDataSetChanged();
        }
    }

    public void setTitle(String title){
        title_textView.setText(title);
    }

    public void setImageView(int id){
        mImageView.setImageResource(id);
    }
    public void setOnClickItemListener(final MyListDialog listDialog){
       mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String select_item = list_data.get(position);
               Toast.makeText(mContext,"选中："+select_item,Toast.LENGTH_SHORT).show();
               listDialog.dismiss();
           }
       });
    }

}

