package com.example.cwh.mypermission;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DatabaseOperate extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_operate);
    }

    public void createDatebase(){
        dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,1);
        dbHelper.getWritableDatabase();
    }

    public void addDate(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //第一条数据
        values.put("name","第一行代码");
        values.put("author","郭霖");
        values.put("pages",580);
        values.put("price",64.8);
        db.insert("Book",null,values);//插入第一条数据
        values.clear();//每插入一次，都要将ContentValues的值清除掉

        //
        values.put("name","android群英传");
        values.put("author","徐宜生");
        values.put("pages",358);
        values.put("price",69);
        db.insert("Book",null,values);//插入第二条数据
        values.clear();//每插入一次，都要将ContentValues的值清除掉
    }

    public void updateData(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price",19.99);
        db.update("Book",values,"name = ?", new String[]{"郭霖"});
    }

    public  void deleteData(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Book","pages>?",new String[]{"500"});
    }

    public void selectDate(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //查询所有价格为69元的书
        Cursor cursor = db.query("Book",null,"price = ?",new String[]{"69"},null,null,null);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String auther = cursor.getString(cursor.getColumnIndex("author"));
            int pages = cursor.getInt(cursor.getColumnIndex("pages"));

        }
    }
}
