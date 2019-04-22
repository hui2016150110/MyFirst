package com.example.cwh.mypermission.myDefinedView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by hui on 2019/3/4.
 */

public class RectView extends View implements Runnable{

    int width;
    int heigth;
    private Scroller mScroller;
    private int lastX;
    private int lastY;
    private int defalutSize;
    private Paint mPaint;

    public RectView(Context context) {
        super(context);
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
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
        //使用padding属性必要
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        width = getWidth()-paddingLeft-paddingRight;
        heigth = getHeight()-paddingTop-paddingBottom;
        canvas.drawRect(paddingLeft,paddingTop,width,heigth,mPaint);
    }

    @Override
    public void run() {

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x-lastX;
                int offsetY = y-lastY;
                //layout(getLeft()+offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);
                ((View)getParent()).scrollBy(-offsetX,-offsetY);


                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    public void smoothScrollTo(int destX,int destY){
        int scrollX = getScrollX();
        int delta = destX-scrollX;
        mScroller.startScroll(scrollX,0,delta,0,2000);
        invalidate();
    }
}
