package com.example.cwh.mypermission;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class BroadcastActivity extends AppCompatActivity {

    private MyBroadCastReceiver2 myBroadCastReceiver2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        Button send_button = (Button) findViewById(R.id.button_send);


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.cwh.mypermission.mybroadcast2");
        myBroadCastReceiver2 = new MyBroadCastReceiver2();
        registerReceiver(myBroadCastReceiver2,intentFilter);


        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("key","mybroadcast");
                intent.setAction("com.example.cwh.mypermission.mybroadcast");
                sendBroadcast(intent);
            }
        });

        Button send2_button = (Button) findViewById(R.id.button_send2);
        send2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("key","mybroadcast");
                intent.setAction("com.example.cwh.mypermission.mybroadcast2");
                sendBroadcast(intent);
            }
        });

        Button send3_button = (Button) findViewById(R.id.button_send3);
        send3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("key","mybroadcast");
                intent.setAction("com.example.cwh.mypermission.mybroadcast2");
                sendOrderedBroadcast(intent,null);
            }
        });

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadCastReceiver2);
    }
}
