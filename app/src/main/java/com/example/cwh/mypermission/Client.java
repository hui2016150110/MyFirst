package com.example.cwh.mypermission;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.cwh.myfetuscaredoctor.IMyAidlInterface;
import com.example.cwh.myfetuscaredoctor.User;

import java.util.List;


public class Client extends AppCompatActivity implements View.OnClickListener{
    private IMyAidlInterface mIMyAidlInterface;
    private List<User> mList;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIMyAidlInterface = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        Button connect = (Button) findViewById(R.id.connect);
        connect.setOnClickListener(this);

        Button send = (Button) findViewById(R.id.send);
        send.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.connect:
                connect();
                break;
            case R.id.send:
                try {
                    send();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private void connect(){
        Intent intent = new Intent();
        intent.setAction("com.example.cwh.myfetuscaredoctor.MyService");
        intent.setPackage("com.example.cwh.myfetuscaredoctor");
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        if(mIMyAidlInterface==null){
            Log.d("TAG","aidl is null");
        }
    }
    private void send() throws RemoteException {
        User user = new User(1,"hui","123456");
        if(mIMyAidlInterface==null){
            connect();
            Log.d("TAG","aidl is null");
        }
        mIMyAidlInterface.addUser(user);
    }

}
