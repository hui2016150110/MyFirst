package com.example.cwh.mypermission;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class MyService extends Service {

    private MyBinder mMyBinder = new MyBinder();
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mMyBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //前台服务
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, intent,0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("")
                .setContentText("")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.a1)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.a1))
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1,notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class MyBinder extends Binder{
        public void startDownload(){
            //具体下载任务
        }
    }
}
