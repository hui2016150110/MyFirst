package com.example.cwh.mypermission;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivty extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private ViewPager mViewPager;
    private List<Fragment> mFragmentList = new ArrayList<>();

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ImageButton imageButton3;
    private ImageButton imageButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_viewpager);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mFragmentList.add(new Fragment1());
        mFragmentList.add(new Fragment2());
        mFragmentList.add(new Fragment3());
        mFragmentList.add(new Fragment4());
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(myFragmentPagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(this);


        imageButton1 = (ImageButton) findViewById(R.id.imagebutton1);
        imageButton2 = (ImageButton) findViewById(R.id.imagebutton2);
        imageButton3 = (ImageButton) findViewById(R.id.imagebutton3);
        imageButton4 = (ImageButton) findViewById(R.id.imagebutton4);

        imageButton1.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        imageButton4.setOnClickListener(this);
        //禁止viewpager的左右滑动,但是view一旦增多就会出现卡顿
//       mViewPager.setOnTouchListener(new View.OnTouchListener() {
//           @Override
//           public boolean onTouch(View v, MotionEvent event) {
//               return true;
//           }
//       });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imagebutton1:
                mViewPager.setCurrentItem(PAGE_ONE);
                imageButton1.setImageResource(R.drawable.info2);
                imageButton2.setImageResource(R.drawable.address_list);
                imageButton3.setImageResource(R.drawable.find);
                imageButton4.setImageResource(R.drawable.me);
                break;
            case R.id.imagebutton2:
                mViewPager.setCurrentItem(PAGE_TWO);
                imageButton1.setImageResource(R.drawable.info);
                imageButton2.setImageResource(R.drawable.address_list2);
                imageButton3.setImageResource(R.drawable.find);
                imageButton4.setImageResource(R.drawable.me);
                break;
            case R.id.imagebutton3:
                mViewPager.setCurrentItem(PAGE_THREE);
                imageButton1.setImageResource(R.drawable.info);
                imageButton2.setImageResource(R.drawable.address_list);
                imageButton3.setImageResource(R.drawable.find2);
                imageButton4.setImageResource(R.drawable.me);
                break;
            case R.id.imagebutton4:
                mViewPager.setCurrentItem(PAGE_FOUR);
                imageButton1.setImageResource(R.drawable.info);
                imageButton2.setImageResource(R.drawable.address_list);
                imageButton3.setImageResource(R.drawable.find);
                imageButton4.setImageResource(R.drawable.me2);
                break;
            default:
        }
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if(state==2){
            switch (mViewPager.getCurrentItem()){
                case PAGE_ONE:
                    imageButton1.setImageResource(R.drawable.info2);
                    imageButton2.setImageResource(R.drawable.address_list);
                    imageButton3.setImageResource(R.drawable.find);
                    imageButton4.setImageResource(R.drawable.me);
                    break;
                case PAGE_TWO:
                    imageButton1.setImageResource(R.drawable.info);
                    imageButton2.setImageResource(R.drawable.address_list2);
                    imageButton3.setImageResource(R.drawable.find);
                    imageButton4.setImageResource(R.drawable.me);
                    break;
                case PAGE_THREE:
                    imageButton1.setImageResource(R.drawable.info);
                    imageButton2.setImageResource(R.drawable.address_list);
                    imageButton3.setImageResource(R.drawable.find2);
                    imageButton4.setImageResource(R.drawable.me);
                    break;
                case PAGE_FOUR:
                    imageButton1.setImageResource(R.drawable.info);
                    imageButton2.setImageResource(R.drawable.address_list);
                    imageButton3.setImageResource(R.drawable.find);
                    imageButton4.setImageResource(R.drawable.me2);
                    break;
                default:
            }
        }
    }
}
