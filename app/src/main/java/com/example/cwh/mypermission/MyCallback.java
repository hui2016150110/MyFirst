package com.example.cwh.mypermission;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by cwh on 2018/8/10.
 */

public class MyCallback implements Callback {

    private UpdateCallback callback;

    public MyCallback(UpdateCallback callback){
        this.callback=callback;
    }
    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        callback.update(response.body().string());
    }
}
