package com.example.cwh.mypermission;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwh on 2018/8/3.
 */

public class Picker_Dialog extends Dialog {

    private Context mContext;
    private Spinner spinner_hour;
    private Spinner spinner_minute;
    private List<Integer> list_hour = new ArrayList<>();
    private List<Integer> list_minute = new ArrayList<>();
    private ArrayAdapter<Integer> adapter_hour;
    private ArrayAdapter<Integer> adapter_minute;
    private Integer select_hour = 0;
    private Integer select_minute = 0;
    private TimePicker mTimePicker;


    public Picker_Dialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }
    public  Picker_Dialog(Context context, TimePicker timePicker){
        super(context);
        mContext = context;
        mTimePicker = timePicker;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picker_dialog_layout);
        spinner_hour = (Spinner) findViewById(R.id.spinner_hour);
        spinner_minute = (Spinner) findViewById(R.id.spinner_minute);

        for(Integer i = 0;i<24;i++){
            list_hour.add(i);
        }
        for (Integer i = 0;i<60;i++){
            list_minute.add(i);
        }
        adapter_hour = new ArrayAdapter<Integer>(mContext,android.R.layout.simple_list_item_1,list_hour);
        adapter_minute = new ArrayAdapter<Integer>(mContext,android.R.layout.simple_list_item_1,list_minute);
        adapter_hour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_minute.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_hour.setAdapter(adapter_hour);
        spinner_minute.setAdapter(adapter_minute);

        spinner_hour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select_hour = list_hour.get(position);
                mTimePicker.setHour(list_hour.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_minute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select_minute = list_minute.get(position);
                mTimePicker.setMinute(list_minute.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public  Integer getSelect_hour(){
        return select_hour;
    }
    public Integer getSelect_minute(){
        return select_minute;
    }
}
