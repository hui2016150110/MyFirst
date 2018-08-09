package com.example.cwh.mypermission;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    private List<ImageView> mImageViewList = new ArrayList<>();
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
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
}
