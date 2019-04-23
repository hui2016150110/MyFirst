package com.example.cwh.mypermission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class LitepalOperate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litepal_operate);
    }

    public void createDatabase(){
        Connector.getDatabase();
    }

    public void addDate(){
        Book book = new Book();
        book.setAuthor("郭霖");
        book.setName("第一行代码");
        book.setPages(580);
        book.setPrice(58.4);
        book.save();
    }

    public void updateDate(){
        Book book = new Book();
        book.setPrice(19.99);
        book.updateAll("name = ? and author = ?","第一行代码","郭霖");
    }

    public void deleteDate(){
        LitePal.deleteAll(Book.class,"price > ?","50");
    }

    public void selectDate(){
       List<Book> books = LitePal.select("author","name")
               .where("pages > ?","400")
               .find(Book.class);
    }

}
