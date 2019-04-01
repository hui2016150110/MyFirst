package com.example.cwh.mypermission;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HandlerActivity extends AppCompatActivity implements View.OnClickListener{

    Handler handler = null;
    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        mButton = (Button) findViewById(R.id.bt1);
        mButton.setOnClickListener(this);
        Button test = (Button) findViewById(R.id.test);
        test.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1:
//                newThread();
                Log.i("ims","rrr");

                MyThread myThread = new MyThread();
                myThread.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Handler handler = myThread.getHandler();

                if (handler != null) {
                    handler.sendMessage(Message.obtain());
                }else{
                    Log.d("null","handler is null");
                }
                break;
            case R.id.test:
                newThread();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                send();
                break;

        }
    }

    private void newThread(){
        
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                 handler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        Log.d("TAG",msg.arg1+"");
                    }
                };

                Looper.loop();
            }
        }.start();
    }

    private void send(){
        new Thread(){
            @Override
            public void run() {
                Message message = new Message();
                message.arg1 = 1;
                handler.sendMessage(message);

            }
        }.start();
    }
}
