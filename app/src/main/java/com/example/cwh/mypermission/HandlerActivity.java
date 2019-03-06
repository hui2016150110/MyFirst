package com.example.cwh.mypermission;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class HandlerActivity extends AppCompatActivity implements View.OnClickListener{

    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        MyThread myThread = new MyThread();
        myThread.start();
        mButton = (Button) findViewById(R.id.bt1);
        mButton.setOnClickListener(this);

        Handler handler = new Handler(){
            @Override
          public void handleMessage(Message msg){
                Log.i("ims",msg.arg1+"");

          }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                    Log.d("debug",s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("error",volleyError.getMessage());
            }
        });
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
                Handler handler2 = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        Log.i("ims","shenm");
                    }
                };
                Message message = new Message();
                message.arg1 = 1;
               handler2.sendEmptyMessage(1);

            }
        }.start();
    }
}
