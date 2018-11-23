package com.example.cwh.mypermission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity implements View.OnClickListener,UpdateCallback{

    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");
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
        Button post_file = (Button) findViewById(R.id.post_file);
        Button download = (Button) findViewById(R.id.download);
        download.setOnClickListener(this);
        post_file.setOnClickListener(this);
        get_button.setOnClickListener(this);
        post_button.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.textView);
        if(ContextCompat.checkSelfPermission(OkhttpActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(OkhttpActivity.this,new String []{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
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
            case R.id.post_file:
                File file = new File("E:\\a.txt");
                Request request1 = new Request.Builder()
                        .url("https://github.com/hui2016150110/Test_Git/tree/master/app/src/main/java/com/example/hui/test_git")
                        .post(RequestBody.create(MEDIA_TYPE_MARKDOWN,file))
                        .build();
                okHttpClient.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("hui",e.getMessage());
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.i("hui",response.body().string());
                    }
                });
                break;
            case R.id.download:
                Request request2 = new Request.Builder()
                        .url("http://img02.tooopen.com/images/20160509/tooopen_sy_161967094653.jpg")
                        .build();
                okHttpClient.newCall(request2).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("hui", "文件下载失败");
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(ContextCompat.checkSelfPermission(OkhttpActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                            try {
                                InputStream inputStream = response.body().byteStream();
                                FileOutputStream fileOutputStream = null;
                                fileOutputStream = new FileOutputStream(new File("/storage/emulated/0/Android/data/2.jpg"));
                                byte[] buffer = new byte[2048];
                                int len = 0;
                                while ((len = inputStream.read(buffer)) != -1) {
                                    fileOutputStream.write(buffer, 0, len);
                                }
                                fileOutputStream.flush();
                            } catch (IOException e) {
                                Log.i("hui", "IOException");
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(OkhttpActivity.this,"图片下载成功",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
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
