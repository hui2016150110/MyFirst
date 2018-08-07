package com.example.cwh.mypermission;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class GestureActivity extends AppCompatActivity {

    private ImageView mImageView;
    private int []imageId = {R.drawable.tv,R.drawable.sf,R.drawable.ps,R.drawable.lnk,R.drawable.li,R.drawable.jr};
    private int index = 0;
    private GestureDetector mGestureDetector;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            index++;
            index = index%6;
            mImageView.setImageResource(imageId[index]);
        }
    };
    private MyRunnable1 myRunnable = new MyRunnable1();
    private MyRunnable2 myRunnable2 = new MyRunnable2();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        mImageView = (ImageView) findViewById(R.id.imageview);
        mGestureDetector = new GestureDetector(new MyGestureListener());
        mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }


    /***一个最标准的异步消息处理线程的写法应该是这样：
     * class LooperThread extends Thread {
         public Handler mHandler;

         public void run() {
             Looper.prepare();

             mHandler = new Handler() {
                 public void handleMessage(Message msg) {
                 // process incoming messages here
                }
             };
            Looper.loop();
         }
     }

     */

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //从右向左滑动
            if(e1.getX()-e2.getX()>50){
                //利用handle机制在非ui线程中更新ui。
                //mHandler.post(myRunnable);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.sendEmptyMessage(1);
                    }
                }).start();




            }
            //从左向右滑动
            else if(e2.getX()-e1.getX()>50){
                //利用handle机制在非ui线程中更新ui。
                //mHandler.post(myRunnable2);
                final Handler handler = new Handler();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                index--;
                                index = (index+6)%6;
                                mImageView.setImageResource(imageId[index]);
                            }
                        });
                    }
                }).start();
            }
            else{
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    class MyRunnable1 implements Runnable{
        @Override
        public void run() {
            index++;
            index = index%6;
            mImageView.setImageResource(imageId[index]);
        }
    }
    class MyRunnable2 implements Runnable{
        @Override
        public void run() {
            index--;
            index = (index+6)%6;
            mImageView.setImageResource(imageId[index]);
        }
    }


    class LooperThread extends Thread {
        public Handler mHandler;
        public void run() {
            Looper.prepare();
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    // process incoming messages here
                     //super.handleMessage(msg);
                    imageUpdate();
                }
            };
            Looper.loop();
        }
    }


    public void imageUpdate(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                index++;
                index = index%6;
                mImageView.setImageResource(imageId[index]);
            }
        });
    }
}
