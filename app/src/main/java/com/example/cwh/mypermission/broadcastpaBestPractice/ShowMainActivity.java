package com.example.cwh.mypermission.broadcastpaBestPractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cwh.mypermission.R;

public class ShowMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_main);
        Button forceline = (Button) findViewById(R.id.force_line);
        forceline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.cwh.mypermission.broadcastpaBestPractice.Force_Offline");
                sendBroadcast(intent);
            }
        });
    }
}
