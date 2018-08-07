package com.example.cwh.mypermission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProviderActivity extends AppCompatActivity {

    private Uri mUri;
    private List<ContactsPerson> mContactsPersonList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ContactsPerson_recycleciew_adapter mContactsPerson_recycleciew_adapter;

    /**
     *
     * @param savedInstanceState
     * recycleview的contextmenu
     * 1.viewholder（实现OnCreateContextMenuListener接口）中在为每一个itemview添加一个OnCreateContextMenuListener（）事件
     * 2.在适配器中的onBindViewHolder函数中为每一个holder.itemview设置setOnLongClickListener（）监听
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        //判断用户有没有给我们授权
        //没有授权就要申请授权，已经授权就直接读取联系人
        if(ContextCompat.checkSelfPermission(ProviderActivity.this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            //用户没有授权，需要申请授权，无论是同意还是拒绝，最后都会调用onRequestPermissionsResult()方法
            ActivityCompat.requestPermissions(ProviderActivity.this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }else {
            readContacts();
        }
        //每一个recycleview都有一个布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(ProviderActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mContactsPerson_recycleciew_adapter = new ContactsPerson_recycleciew_adapter(mContactsPersonList);
        mRecyclerView.setAdapter(mContactsPerson_recycleciew_adapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, GridLayoutManager.VERTICAL));

        //注册上下文菜单
        //重写onCreateContextMenu方法
        registerForContextMenu(mRecyclerView);

    }

    //读取联系人信息
    private void readContacts(){
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            if(cursor!=null){
                cursor.moveToFirst();
                while (cursor.moveToNext()){
                    ContactsPerson person = new ContactsPerson();
                    person.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
                    person.setNumber(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                    mContactsPersonList.add(person);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null)
                cursor.close();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                //如果同意读取权限，则读取联系人的电话和号码
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    readContacts();
                }else{
                    Toast.makeText(this,"You denied the prefermission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                mContactsPerson_recycleciew_adapter.removeItem(mContactsPerson_recycleciew_adapter.getPosition());
                break;
            case 2:
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;
            case 3:
                mContactsPerson_recycleciew_adapter.moveTotop(mContactsPerson_recycleciew_adapter.getPosition());
                break;
            case 4:
                mContactsPerson_recycleciew_adapter.add();
                break;
            default:
        }
        return super.onContextItemSelected(item);
    }


}
