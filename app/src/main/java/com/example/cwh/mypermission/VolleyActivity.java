package com.example.cwh.mypermission;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class VolleyActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        Button get_button = (Button) findViewById(R.id.getMethod);
        get_button.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getMethod:
                getMethod();
                break;
        }
    }

    private void getMethod(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String s) {

                return null;
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {

            }
        });

//        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://www.baidu.com", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                Log.d("debug",s);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Log.e("error",volleyError.getMessage());
//            }
//        });

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://m.weather.com.cn/data/101010100.html", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d("TAG", jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("TAG",volleyError.getMessage(),volleyError);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


}
