package com.example.cwh.mypermission.downloadTask;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cwh.mypermission.R;

public class DownloadAcivity extends AppCompatActivity implements View.OnClickListener{

    private DownService.DownloadBinder mDownloadBinder;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadBinder = (DownService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Button download = (Button) findViewById(R.id.download);
        Button cancel = (Button) findViewById(R.id.cancel);
        Button pause = (Button) findViewById(R.id.pause);
        download.setOnClickListener(this);
        cancel.setOnClickListener(this);
        pause.setOnClickListener(this);
        Intent intent = new Intent(this,DownService.class);
        startService(intent);
        bindService(intent,mConnection,BIND_AUTO_CREATE);

        if(ContextCompat.checkSelfPermission(DownloadAcivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(DownloadAcivity.this, Manifest.permission.WRITE_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(DownloadAcivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                }else{
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }
    @Override
    public void onClick(View v) {
        if(mDownloadBinder==null){
            return;
        }
        switch (v.getId()){
            case R.id.download:
                String url = "http://sqdd.myapp.com/myapp/qqteam/tim/down/tim.apk";
                mDownloadBinder.startDownload(url);
                Toast.makeText(this,"click D",Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancel:
                mDownloadBinder.cancelDownload();
                Toast.makeText(this,"click C",Toast.LENGTH_SHORT).show();
                break;
            case R.id.pause:
                mDownloadBinder.pauseDownload();
                Toast.makeText(this,"click P",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
