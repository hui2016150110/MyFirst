package com.example.cwh.mypermission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkhttpActivity extends AppCompatActivity implements View.OnClickListener,UpdateCallback{

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        Button get_button = (Button) findViewById(R.id.get_button);
        get_button.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.textView);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get_button:
                //1.拿到okhttpclient对象
                OkHttpClient okHttpClient = new OkHttpClient();
                //2.够造request
                final Request request = new Request.Builder().get().url("http://www.imooc.com/").build();
                //3.将request封装为call
                Call call = okHttpClient.newCall(request);
                //同步执行
                //4.执行call
//                try {
//                    call.execute();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                //不是ui线程
                //异步执行
                call.enqueue(new MyCallback(this));
                break;
            default:
        }
    }


    @Override
    public void update(final String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(data);
            }
        });
    }
}
