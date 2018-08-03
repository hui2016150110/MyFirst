package com.example.cwh.mypermission;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class PickerActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date);
        createPicker();
    }
    private void createPicker(){
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        minute = calendar.get(Calendar.MINUTE);
        setTitle(year+"-"+month+"-"+day+"- "+hour+":"+minute);

        timePicker =(TimePicker) findViewById(R.id.timePicker);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker.setIs24HourView(true);

        datePicker.init(year, calendar.get(Calendar.MONTH), day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                setTitle(i+"-"+(i1+1)+"-"+i2);
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                setTitle(i+":"+i1);

            }
        });
        timePicker.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                Picker_Dialog picker_dialog = new Picker_Dialog(PickerActivity.this,timePicker);
                picker_dialog.show();
            }
        });
    }
}
