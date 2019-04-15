package com.example.cwh.mypermission.broadcastpaBestPractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import mvp_view.UserLoginAcivity;

public class BaseActivity extends AppCompatActivity {

    private ForceOfflineReceiver mReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        //setContentView(R.layout.activity_base);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.cwh.mypermission.broadcastpaBestPractice.Force_Offline");
        mReceiver = new ForceOfflineReceiver();
        registerReceiver(mReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mReceiver!=null){
            unregisterReceiver(mReceiver);
        }
    }

    class ForceOfflineReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("警告");
            builder.setMessage("您的账号被另一台设备登录，您已被强制下线");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishedAll();
                    Intent intent = new Intent(context, UserLoginAcivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }
}
