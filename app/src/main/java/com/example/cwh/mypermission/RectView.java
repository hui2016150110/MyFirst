package com.example.cwh.mypermission;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hui on 2019/3/4.
 */

public class RectView extends View implements Runnable{
    private int defalutSize;
    private Paint mPaint;
    private int radiu;
    public RectView(Context context) {
        super(context);
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaint(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.LTGRAY);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        radiu = getMeasuredHeight()/2;
        int centerX = getLeft()+radiu;
        int centerY = getTop()+radiu;
        canvas.drawCircle(centerX,centerY,radiu,mPaint);

    }

    public synchronized void setRadiu(int radiu){
        this.radiu = radiu;
        invalidate();
    }

    @Override
    public void run() {
        while (true) {
            try {
				/*
				 * 如果半径小于200则自加否则大于200后重置半径值以实现往复
				 */
                if (radiu <= 200) {
                    radiu += 10;
                    // 刷新View
                    postInvalidate();
                } else {
                    radiu = 0;
                }
                // 每执行一次暂停40毫秒
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getSize(100,widthMeasureSpec);
        int height = getSize(100,heightMeasureSpec);

        if(width<height){
            height = width;
        }else{
            width = height;
        }
        setMeasuredDimension(width,height);
    }

    private int getSize(int defaultSize,int measureSpec){
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode){
            //没有指定大小，设置为默认大小
            case MeasureSpec.UNSPECIFIED:
                mySize = defaultSize;
                break;
            //取最大值，设置为默认大小，也可以取其他值
            case MeasureSpec.AT_MOST:
                mySize = size;
                break;
            //如果是固定大小，不要改变它
            case MeasureSpec.EXACTLY:
                mySize = size;
                break;
        }
        return mySize;
    }
}
