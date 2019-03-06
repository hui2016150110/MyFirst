package com.example.cwh.mypermission;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MyViewAcivity extends AppCompatActivity implements View.OnClickListener{

    RectView mRectView;
    ImageView mImageView;
    ImageView mImageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view);
        mRectView = (RectView)findViewById(R.id.myView);
        mImageView = (ImageView)findViewById(R.id.image);
        mImageView2 = (ImageView)findViewById(R.id.image2);

        mImageView.setOnClickListener(this);
        mImageView2.setOnClickListener(this);


        //new Thread(mRectView).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image:
                startAnimator();
                break;
            case R.id.image2:
                changeSize();
                break;
        }
    }

    private void startAnimator(){
        int myView_height = mRectView.getMeasuredHeight();

        Log.i("ims",myView_height+"");


        int height = mImageView.getMeasuredHeight();
        int width = mImageView.getMeasuredWidth();
        //获得屏幕宽度
        WindowManager windowManager = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenheight = dm.heightPixels;

        ValueAnimator animator = ValueAnimator.ofObject(new TypeEvaluator<Point3>() {
            @Override
            public Point3 evaluate(float fraction, Point3 startValue, Point3 endValue) {
                float x = startValue.getX() + (endValue.getX()-startValue.getX())*fraction;
                float y = startValue.getY() + (endValue.getY()-startValue.getY())*fraction;;
                return new Point3(x,y);
            }
        },new Point3(0,0),new Point3(screenWidth,screenheight));

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point3 current = (Point3) animation.getAnimatedValue();
                mImageView.setTranslationY(current.getY());
                mImageView.setTranslationX(current.getX());
            }
        });
        animator.setDuration(3000);
        animator.setTarget(mImageView);
        animator.start();
    }

    private void changeSize(){
            ValueAnimator valueAnimator = ValueAnimator.ofInt();
    }
}
