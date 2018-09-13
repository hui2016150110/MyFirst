package com.example.cwh.mypermission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkhttpActivity extends AppCompatActivity implements View.OnClickListener,UpdateCallback{

    private TextView textView;
    //1.拿到okhttpclient对象
    //2.够造request
    // 3.将request封装为call
    //4.执行call
    OkHttpClient okHttpClient = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        Button get_button = (Button) findViewById(R.id.get_button);
        Button post_button = (Button) findViewById(R.id.post_button);
        get_button.setOnClickListener(this);
        post_button.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.textView);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get_button:
                Request request = new Request.Builder().url("http://www.imooc.com/").build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new MyCallback(this));
                break;
            case R.id.post_button:
                RequestBody requestBody = new FormBody.Builder().add("username","admin").build();
                Request mrequest = new Request.Builder().url("http://baidu.com").post(requestBody).build();
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
