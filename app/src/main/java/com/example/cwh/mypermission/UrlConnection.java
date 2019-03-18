package com.example.cwh.mypermission;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hui on 2019/3/12.
 */

public class UrlConnection {
    public static HttpURLConnection getHttpURLConnection(String url){
        HttpURLConnection httpURLConnection = null;
        //
        try {
            //通过url的openConnection来获取httpConnection实例
            URL mUrl = new URL(url);
            httpURLConnection =(HttpURLConnection) mUrl.openConnection();
            //设置连接超时时间
            httpURLConnection.setConnectTimeout(15000);
            //设置读取超时时间
            httpURLConnection.setReadTimeout(15000);
            //设置请求方法
            httpURLConnection.setRequestMethod("POST");
            //添加请求头
            httpURLConnection.setRequestProperty("Connection","Keep-Alive");
            //接收输入流
            httpURLConnection.setDoInput(true);
            //传递参数的时候需要开启
            httpURLConnection.setDoOutput(true);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(),"UTF-8"));
            write.write("");

            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            int code = httpURLConnection.getResponseCode();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return httpURLConnection;
    }



}
