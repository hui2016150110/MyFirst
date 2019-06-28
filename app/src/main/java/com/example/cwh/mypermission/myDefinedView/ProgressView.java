package com.example.cwh.mypermission.myDefinedView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

public class ProgressView extends View {
    int width;
    int heigth;
    private Scroller mScroller;
    private int lastX;
    private int lastY;
    private int defalutSize;
    private Paint mPaint;
    private Paint newPaint;
    public ProgressView(Context context) {
        super(context);
    }
    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    private void initPaint(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.LTGRAY);
        mPaint.setStrokeWidth(10);
        newPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        newPaint.setStyle(Paint.Style.FILL);
        newPaint.setColor(Color.parseColor("#1296db"));
        newPaint.setStrokeWidth(10);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //使用padding属性必要
        width = getWidth();
        heigth = getHeight();
        canvas.drawRect(0,0, (float) (width*0.5),heigth,mPaint);
        canvas.drawRect((float) (width*0.5),0,width,heigth,newPaint);

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if(widthSpecMode==MeasureSpec.AT_MOST&&heightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(dip2px(100),dip2px(20));
        }
        else if(widthSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(dip2px(100),height);
        }
        else if(heightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(width,dip2px(20));
        }
    }

    private int dip2px(float dpValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
