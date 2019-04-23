package com.example.cwh.mypermission.myDefinedView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hui on 2019/4/22.
 */

public class CircleView extends View implements Runnable{

    private onViewClick mOnViewClick;

    private float startRawX;
    private float startRawY;
    private Paint mPaint;
    private float radius;
    private float lastX = 0;
    private float lastY = 0;
    private  float centerX = 0;
    private float centerY = 0;
    private int flaction = 0;
    volatile boolean flag = false;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(3);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if(widthSpecMode==MeasureSpec.AT_MOST&&heightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(100,100);
        }
        else if(widthSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(100,height);
        }
        else if(heightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(width,100);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //使用padding属性必要
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        radius = getWidth()/2;
        centerX = paddingLeft+getWidth()/2;
        centerY = paddingTop+getHeight()/2;
        canvas.drawCircle(paddingLeft+getWidth()/2,paddingTop+getHeight()/2,radius,mPaint);
        canvas.drawCircle(paddingLeft+getWidth()/2,paddingTop+getHeight()/2,radius-20,mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getLastX(),getLastY(),10,mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
    }


    @Override
    public void run() {
        while (true){
            try {
                if(!flag)
                    Thread.sleep(100);
                else
                    Thread.sleep(10);
                flaction++;
                if(flaction>=360){
                    flaction = 0;
                    postInvalidate();
                    break;
                }
                postInvalidate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private float getLastX(){
        lastX = (float) ((radius-10)*Math.sin(flaction/180.0*Math.PI))+centerX;
        return lastX;
    }
    private float getLastY(){
        lastY = (float) -((radius-10)*Math.cos(flaction/180.0*Math.PI))+centerY;
        return lastY;
    }
    public void setFlag(){
        flag = true;
    }

    public void setOnClickListener(onViewClick onViewClick) {
        this.mOnViewClick = onViewClick;
    }


    public interface onViewClick{
        void onClick(float scrollX,float scrollY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        float rawX = event.getRawX();
        float rawY = event.getRawY();
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_DOWN:
                startRawX = rawX;
                startRawY = rawY;
                break;
            case MotionEvent.ACTION_UP:
                if(x+getLeft()<getRight()&&y+getTop()<getBottom()){
                    mOnViewClick.onClick(rawX-startRawX,rawY-startRawY);
                }
        }
        return true;
    }
}
