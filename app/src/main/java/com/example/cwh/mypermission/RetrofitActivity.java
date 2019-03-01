package com.example.cwh.mypermission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mrequest;
    private Button fanyi;
    private EditText src;
    private EditText tgt;
    public static final int TIMEOUT = 60;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        mrequest = findViewById(R.id.request);
        mrequest.setOnClickListener(this);
        fanyi = findViewById(R.id.fanyi);
        fanyi.setOnClickListener(this);
        tgt = findViewById(R.id.tgt);
        src = findViewById(R.id.src);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.request:
                request();
                break;
            case R.id.fanyi:
                youdaofanyi();
                break;
        }
    }
    public void request(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetRequest_Interface getRequest_interface = retrofit.create(GetRequest_Interface.class);

        Call<Translation> call = getRequest_interface.getCall();

        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, final Response<Translation> response) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RetrofitActivity.this,response.body().toString(),Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                System.out.println("连接失败");
            }
        });

    }

    private void youdaofanyi(){
        final String en = src.getText().toString();
        Observer observer = new Observer<Translation1>(){

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Translation1 translation1) {
                //处理返回的json数据
                tgt.setText(translation1.getTranslateResult().get(0).get(0).getTgt());
                Log.i("ims", translation1.getTranslateResult().get(0).get(0).getTgt());
            }

            @Override
            public void onError(Throwable e) {
                Log.i("ims","eeee");
            }

            @Override
            public void onComplete() {

            }
        };
        HttpMethods.getInstance().fangyi(observer,en);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
//                .readTimeout(TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
//                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)//设置写入超时时间
//                .build();
//        String en = src.getText().toString();
//        System.out.println(en);
//        Retrofit retrofit = new Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl("http://fanyi.youdao.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        PostRequest_Interface postRequest_interface = retrofit.create(PostRequest_Interface.class);
/*
        Call<Translation1> call = postRequest_interface.getCall(en);

        call.enqueue(new Callback<Translation1>() {
            @Override
            public void onResponse(Call<Translation1> call, final Response<Translation1> response) {
                System.out.println(response.body().getTranslateResult());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tgt.setText(response.body().getTranslateResult().get(0).get(0).getTgt());
                    }
                });
            }

            @Override
            public void onFailure(Call<Translation1> call, Throwable t) {
                System.out.print("error");
            }
        });

*/




//        postRequest_interface.getCall(en)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Translation1>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Translation1 translation1) {
//                        tgt.setText(translation1.getTranslateResult().get(0).get(0).getTgt());
//                        Log.i("ims","sssss");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i("ims","eeeee");
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });



    }
}
