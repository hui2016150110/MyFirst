package com.example.cwh.mypermission;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HandlerActivity extends AppCompatActivity implements View.OnClickListener{

    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        mButton = (Button) findViewById(R.id.bt1);
        mButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1:
                newThread();
                break;

        }
    }

    private void newThread(){
        new Thread(){
            @Override
            public void run() {
                Log.i("ims","rrr");
                Looper.prepare();
                new MyThread().start();

                Handler handler = new Handler(Looper.myLooper());
//                Handler handler2 = new Handler(){
//                    @Override
//                    public void handleMessage(Message msg) {
//                        Log.i("ims","shenm");
//                    }
//                };
//                Message message = new Message();
//                message.arg1 = 1;
               handler.sendEmptyMessage(1);

               Looper.loop();
            }
        }.start();
    }
}
