package com.example.cwh.mypermission;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener{


    private ImageView mImageView;
    private int []imagesCount = {R.id.imageView1,R.id.imageView2,R.id.imageView3,
            R.id.imageView4,R.id.imageView5,R.id.imageView6,R.id.imageView7};

    private int []imagesCount2 = {R.id.imageView8,R.id.imageView9,R.id.imageView10,
            R.id.imageView11,R.id.imageView12,R.id.imageView13,R.id.imageView14};
    private List<ImageView> mImageViewList = new ArrayList<>();
    private List<ImageView> mImageViewList2 = new ArrayList<>();
    private boolean flag = true;
    private boolean flag2 = true;
    private ImageView roudImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        roudImage = findViewById(R.id.imageView15);
        roudImage.setOnClickListener(this);
        mImageView = (ImageView) findViewById(R.id.imageView);
        Button button_alpha =(Button) findViewById(R.id.button_alpha);
        button_alpha.setOnClickListener(this);
        Button button_rotate = (Button) findViewById(R.id.button_rotate);
        button_rotate.setOnClickListener(this);
        Button button_translate = (Button) findViewById(R.id.button_translate);
        button_translate.setOnClickListener(this);
        Button button_scale = (Button) findViewById(R.id.button_scale);
        button_scale.setOnClickListener(this);
        Button group_anim = (Button) findViewById(R.id.group_anim);
        group_anim.setOnClickListener(this);
        Button button_continue = (Button) findViewById(R.id.continue_button);
        button_continue.setOnClickListener(this);
        Button button_property = (Button) findViewById(R.id.button_property);
        button_property.setOnClickListener(this);
        Button button_xuanfu = (Button) findViewById(R.id.button_xuanfu);
        button_xuanfu.setOnClickListener(this);
        ImageView imageView = (ImageView) findViewById(R.id.imageView7);
        imageView.setOnClickListener(this);
        for(int i = 0;i<imagesCount.length;i++){
            ImageView imageView1 = (ImageView) findViewById(imagesCount[i]);
            mImageViewList.add(imageView1);
        }
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView14);
        imageView2.setOnClickListener(this);
        for(int i = 0;i<imagesCount2.length;i++){
            ImageView imageView1 = (ImageView) findViewById(imagesCount2[i]);
            mImageViewList2.add(imageView1);
        }

        Button margin_animator = (Button) findViewById(R.id.margin_animator);
        margin_animator.setOnClickListener(this);
        Button scale_animator = (Button)findViewById(R.id.scale_animator);
        scale_animator.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_alpha:
                Animation alphaAnim = AnimationUtils.loadAnimation(this,R.anim.alpha);
                mImageView.startAnimation(alphaAnim);
                break;
            case R.id.button_rotate:
                RotateAnimation rotateAnimation = new RotateAnimation(0,360,1,0.5f,1,0.5f);
                rotateAnimation.setDuration(4000);
                rotateAnimation.setRepeatCount(2);
                rotateAnimation.setRepeatMode(RotateAnimation.REVERSE);
                mImageView.startAnimation(rotateAnimation);
//                Animation rotateAnim = AnimationUtils.loadAnimation(this,R.anim.rotate);
//                mImageView.startAnimation(rotateAnim);
                break;
            case R.id.button_translate:
                TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,300);
                translateAnimation.setDuration(2000);
                mImageView.startAnimation(translateAnimation);
                break;
            case R.id.button_scale:
                ScaleAnimation scaleAnimation = new ScaleAnimation(0f,1.4f,0f,1.4f,1,0.5f,1,0.5f);
                scaleAnimation.setDuration(2000);
                mImageView.startAnimation(scaleAnimation);
                break;
            case R.id.group_anim:
                Animation group_anmiation = AnimationUtils.loadAnimation(this,R.anim.group);
                mImageView.startAnimation(group_anmiation);
                break;
            case R.id.continue_button:
                TranslateAnimation translateAnimation1 = new TranslateAnimation(0,0,0,300);
                translateAnimation1.setDuration(2000);
                final ScaleAnimation scaleAnimation1 = new ScaleAnimation(0f,1.4f,0f,1.4f,1,0.5f,1,0.5f);
                scaleAnimation1.setDuration(2000);
                translateAnimation1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mImageView.startAnimation(scaleAnimation1);
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mImageView.startAnimation(translateAnimation1);
                break;
            case R.id.button_property:
                /**
                 * View 类中新增的便于实现 property 动画的属性包括：
                 (1) translationX 和 translationY：这两个属性控制着 View 的屏幕位置坐标变化量，以 layout 容器的左上角为坐标原点;
                 (2) rotation、rotationX 和 rotationY：这三个属性控制着 2D 旋转角度（rotation属性）和围绕某枢轴点的 3D 旋转角度;
                 (3) scaleX、scaleY：这两个属性控制着 View 围绕某枢轴点的 2D 缩放比例;
                 (4) pivotX 和 pivotY: 这两个属性控制着枢轴点的位置，前述的旋转和缩放都是以此点为中心展开的,缺省的枢轴点是 View 对象的中心点;
                 (5) x 和 y：这是指 View 在容器内的最终位置，等于 View 左上角相对于容器的坐标加上 translationX 和 translationY 后的值;
                 (6)alpha：表示 View 的 alpha 透明度。缺省值为 1 （不透明），为 0 则表示完全透明（看不见）;
                 要动画显示 View 对象的某个属性，比如颜色或旋转值，我们所有要做的事情就是创建一个 Property animation，并设定对应的 View 属性。
                 那接下来我们就用ObjectAnimator类来分别实现View的透明度渐变、收缩、移动和旋转等动画效果，那在此之前我们也来总结下使用ObjectAnimator实现动画的几个步骤，如下：
                 1.通过调用ofFloat()、ofInt()等方法创建ObjectAnimator对象，并设置目标对象、需要改变的目标属性名、初始值和结束值；
                 2.设置动画的持续时间、是否重复及重复次数等属性；
                 3.启动动画。
                 */
                ObjectAnimator.ofFloat(mImageView,"translationX",0f,200f).setDuration(2000).start();
                ObjectAnimator.ofFloat(mImageView,"scaleX",0f,1.0f).setDuration(2000).start();
                ObjectAnimator.ofFloat(mImageView,"scaleY",0f,1.0f).setDuration(2000).start();
                break;
            case R.id.button_xuanfu:
                if(flag){
                    startAnim();
                }else {
                    closeAnim();
                }
                break;
            case R.id.imageView7:
                if(flag){
                    startAnim();
                }else {
                    closeAnim();
                }
                break;
            case R.id.imageView14:
                if(flag2)
                    myAnimator();
                else
                    closemyAnimator();
                break;
            case R.id.margin_animator:
                marginValueAnimator();
                break;
            case R.id.scale_animator:
                scaleValueAnimator();
                break;
            case R.id.imageView15:
                roundAnimator();

                break;
            default:
        }
    }
    private void startAnim(){
        for(int i = 0;i<imagesCount.length-1;i++){
            ObjectAnimator animator = ObjectAnimator.ofFloat(mImageViewList.get(i),"translationX",0,(i+1)*150);
            animator.setDuration(500);
            animator.setStartDelay(i*300);
            animator.start();
            flag = false;
        }
    }
    private void closeAnim(){
        for(int i = 0;i<imagesCount.length-1;i++){
            ObjectAnimator animator = ObjectAnimator.ofFloat(mImageViewList.get(i),"translationX",(i+1)*150,0);
            animator.setDuration(500);
            animator.setStartDelay(i*300);
            animator.start();
            flag = true;
        }
    }


    /**
     * 使用ValueAnimator实现动画的步骤及实践
     那一般使用ValueAnimator实现动画分为以下七个步骤：
     1. 调用ValueAnimation类中的ofInt(int...values)、ofFloat(String propertyName,float...values)等静态方法实例化ValueAnimator对象，并设置目标属性的属性名、初始值或结束值等值;
     2.调用addUpdateListener(AnimatorUpdateListener mListener)方法为ValueAnimator对象设置属性变化的监听器;
     3.创建自定义的Interpolator，调用setInterpolator(TimeInterpolator value)为ValueAniamtor设置自定义的Interpolator;(可选，不设置默认为缺省值)
     4.创建自定义的TypeEvaluator,调用setEvaluator(TypeEvaluator value)为ValueAnimator设置自定义的TypeEvaluator;(可选，不设置默认为缺省值)
     5.在AnimatorUpdateListener 中的实现方法为目标对象的属性设置计算好的属性值。
     6.设置动画的持续时间、是否重复及重复次数等属性;
     7.为ValueAnimator设置目标对象并开始执行动画。
     */
    private void marginValueAnimator(){
        //获得屏幕宽度
        WindowManager windowManager = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        //调用ofint方法创建ValueAnimator对象
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(0,screenWidth-mImageView.getWidth());
        //为目标对象的属性设置监听器
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 3.为目标对象的属性设置计算好的属性值
                int animatorValue = (int) valueAnimator.getAnimatedValue();
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) mImageView.getLayoutParams();
                marginLayoutParams.leftMargin = animatorValue;
                mImageView.setLayoutParams(marginLayoutParams);
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(3);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setTarget(mImageView);
        valueAnimator.start();
    }
    /**
     * 使用ValueAnimator实现图片缩放动画
     */
    public void scaleValueAnimator(){
        //1.设置目标属性名及属性变化的初始值和结束值
        PropertyValuesHolder mPropertyValuesHolderScaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f,0.0f);
        PropertyValuesHolder mPropertyValuesHolderScaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f,0.0f);
        ValueAnimator mAnimator = ValueAnimator.ofPropertyValuesHolder(mPropertyValuesHolderScaleX,mPropertyValuesHolderScaleY);
        //2.为目标对象的属性变化设置监听器
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 3.根据属性名获取属性变化的值分别为ImageView目标对象设置X和Y轴的缩放值
                float animatorValueScaleX =  (float) animation.getAnimatedValue("scaleX");
                float animatorValueScaleY = (float) animation.getAnimatedValue("scaleY");
                mImageView.setScaleX(animatorValueScaleX);
                mImageView.setScaleY(animatorValueScaleY);
            }
        });
        //4.为ValueAnimator设置自定义的Interpolator
        mAnimator.setInterpolator(new CustomInterpolator());
        //5.设置动画的持续时间、是否重复及重复次数等属性
        mAnimator.setDuration(2000);
        mAnimator.setRepeatCount(3);
        mAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //6.为ValueAnimator设置目标对象并开始执行动画
        mAnimator.setTarget(mImageView);
        mAnimator.start();
    }
    private class CustomInterpolator implements TimeInterpolator {
        @Override
        public float getInterpolation(float input) {
            return input*0.64f;
        }
    }

    public void myAnimator(){
        flag2 = false;
        int i;
        for(i = 0;i<6;i++){
            final ImageView myImageView = mImageViewList2.get(i);
            ValueAnimator animator=ValueAnimator.ofObject(new TypeEvaluator<Point>(){
                @Override
                public Point evaluate(float fraction, Point startValue, Point endValue) {
                    double radius = (endValue.radius-startValue.radius)*fraction+startValue.radius;
                    double angle = (endValue.angle-startValue.angle)*fraction+startValue.angle;
                    Point point = new Point(radius,angle);
                    return point;
                }
            }, new Point(0,60*i),new Point(240,60*i+360));
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Point point= (Point) animation.getAnimatedValue();
                    float animatorValueTranslationX = (float) ( Math.sin(Math.toRadians(point.getAngle()))*point.getRadius());
                    float animatorValueTranslationY = (float) ( Math.cos(Math.toRadians(point.getAngle()))*point.getRadius());
                    myImageView.setTranslationX(animatorValueTranslationX);
                    myImageView.setTranslationY(animatorValueTranslationY);
                }
            });
            animator.setDuration(500);
            animator.setTarget(myImageView);
            animator.start();
        }
    }

    public void closemyAnimator(){
        flag2 = true;
        int i;
        for(i = 0;i<6;i++){
            final ImageView myImageView = mImageViewList2.get(i);
            ValueAnimator animator=ValueAnimator.ofObject(new TypeEvaluator<Point>(){
                @Override
                public Point evaluate(float fraction, Point startValue, Point endValue) {
                    double radius = (endValue.radius-startValue.radius)*fraction+startValue.radius;
                    double angle = (endValue.angle-startValue.angle)*fraction+startValue.angle;
                    Point point = new Point(radius,angle);
                    return point;
                }
            }, new Point(240,60*i+360),new Point(0,60*i));
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Point point= (Point) animation.getAnimatedValue();
                    float animatorValueTranslationX = (float) ( Math.sin(Math.toRadians(point.getAngle()))*point.getRadius());
                    float animatorValueTranslationY = (float) ( Math.cos(Math.toRadians(point.getAngle()))*point.getRadius());
                    myImageView.setTranslationX(animatorValueTranslationX);
                    myImageView.setTranslationY(animatorValueTranslationY);
                }
            });
            animator.setDuration(500);
            animator.setTarget(myImageView);
            animator.start();
        }
    }

    private void roundAnimator(){
        final int startY = roudImage.getTop();
        Log.i("TAG",""+startY);
        ValueAnimator animator=ValueAnimator.ofObject(new TypeEvaluator<Point3>(){
            @Override
            public Point3 evaluate(float fraction, Point3 startValue, Point3 endValue) {

                float y = (endValue.getY()-startValue.getY())*fraction;
                float newY = y;
                if(y>=300)
                    newY %=300;
                float x = (float) Math.sqrt(newY*(300-newY));
                Log.i("TAG","x = "+x+" y = "+y);
                Point3 point = new Point3(x,y);
                return point;
            }
        }, new Point3(0,0),new Point3(0,900));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point3 point= (Point3) animation.getAnimatedValue();
                roudImage.setTranslationX(point.getX());
                roudImage.setTranslationY(point.getY());

            }
        });
        animator.setDuration(3000);
        animator.setTarget(roudImage);
        animator.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return 0;
            }
        });
        animator.start();
        Log.i("TAG","top = "+roudImage.getTop()+" left = "+roudImage.getLeft());
    }

}
