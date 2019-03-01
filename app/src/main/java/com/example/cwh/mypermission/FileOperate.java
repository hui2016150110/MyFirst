package com.example.cwh.mypermission;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileOperate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_operate);

    }

    public void save(String text){
        FileOutputStream out = null;
        BufferedWriter writer = null;

        try {
            out = openFileOutput("test",MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

                try {
                    if(writer!=null){
                        writer.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public String load(){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder context = new StringBuilder();

        try {
            in = openFileInput("test");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine())!=null){
                context.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return context.toString();
    }

    public void testSharedPreferences(){
        SharedPreferences.Editor editor = getSharedPreferences("date",MODE_PRIVATE).edit();
        editor.putBoolean("married",false);
        editor.putString("name","Tom");
        editor.putInt("age",20);
        editor.apply();
    }
}
