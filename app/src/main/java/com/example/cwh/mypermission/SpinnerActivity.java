package com.example.cwh.mypermission;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity {

    //实现Spinner的变量声明
    private android.widget.Spinner spinner;
    private Spinner mySpinner;
    private List<String> list_spinner;
    private ArrayAdapter<String> arr_adapter_spinner;
    private TextView textView_spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner_item);
        createSpinner();
        mySpinner = (Spinner) findViewById(R.id.myspinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.grade,R.layout.myspinner_layout);
        mySpinner.setBackgroundColor(0x0);
        adapter.setDropDownViewResource(R.layout.myspinner_item_layout);
        mySpinner.setDropDownVerticalOffset(dip2px(30));
        mySpinner.setAdapter(adapter);


    }
    private void createSpinner(){

        spinner =(android.widget.Spinner) findViewById(R.id.spinner);
        textView_spinner = findViewById(R.id.textView_spinner);
        list_spinner = new ArrayList<String>();
        list_spinner.add("北京");
        list_spinner.add("上海");
        list_spinner.add("广州");
        list_spinner.add("深圳");
        arr_adapter_spinner = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_spinner);
        arr_adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arr_adapter_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String city_name = arr_adapter_spinner.getItem(i);
                textView_spinner.setText("你选择的城市是："+city_name);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private int dip2px(float dpValue) {
        float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
