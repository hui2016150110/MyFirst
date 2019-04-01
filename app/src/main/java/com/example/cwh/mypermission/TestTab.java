package com.example.cwh.mypermission;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class TestTab extends AppCompatActivity implements View.OnClickListener{

    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ImageButton imageButton3;
    private ImageButton imageButton4;

    Fragment mFragment1 ;
    Fragment mFragment2 ;
    Fragment mFragment3 ;
    Fragment mFragment4 ;

    ArrayList<Fragment> mFragmentArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tab);
        mFragment1 = new Fragment1();
        mFragmentArrayList.add(mFragment1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.framelayout,mFragment1);
        transaction.commit();

        imageButton1 = (ImageButton) findViewById(R.id.imagebutton1);
        imageButton2 = (ImageButton) findViewById(R.id.imagebutton2);
        imageButton3 = (ImageButton) findViewById(R.id.imagebutton3);
        imageButton4 = (ImageButton) findViewById(R.id.imagebutton4);
        imageButton1.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        imageButton4.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imagebutton1:
                if(mFragment1==null){
                    mFragment1 = new Fragment1();
                    mFragmentArrayList.add(mFragment1);
                }

                replaceFragment(mFragment1);
                imageButton1.setImageResource(R.drawable.info2);
                imageButton2.setImageResource(R.drawable.address_list);
                imageButton3.setImageResource(R.drawable.find);
                imageButton4.setImageResource(R.drawable.me);
                break;
            case R.id.imagebutton2:
                if(mFragment2==null){
                    mFragment2 = new Fragment2();
                    mFragmentArrayList.add(mFragment2);
                }

               replaceFragment(mFragment2);
                imageButton1.setImageResource(R.drawable.info);
                imageButton2.setImageResource(R.drawable.address_list2);
                imageButton3.setImageResource(R.drawable.find);
                imageButton4.setImageResource(R.drawable.me);
                break;
            case R.id.imagebutton3:
                if(mFragment3==null){
                    mFragment3 = new Fragment3();
                    mFragmentArrayList.add(mFragment3);
                }

                replaceFragment(mFragment3);
                imageButton1.setImageResource(R.drawable.info);
                imageButton2.setImageResource(R.drawable.address_list);
                imageButton3.setImageResource(R.drawable.find2);
                imageButton4.setImageResource(R.drawable.me);
                break;
            case R.id.imagebutton4:
                if(mFragment4==null){
                    mFragment4 = new Fragment4();
                    mFragmentArrayList.add(mFragment4);
                }

                replaceFragment(mFragment4);
                imageButton1.setImageResource(R.drawable.info);
                imageButton2.setImageResource(R.drawable.address_list);
                imageButton3.setImageResource(R.drawable.find);
                imageButton4.setImageResource(R.drawable.me2);
                break;
            default:
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(fragment.isHidden()&&fragment.isAdded())
            transaction.show(fragment);
        else if(fragment.isAdded()&&fragment.isVisible())
            return;
        else
            transaction.add(R.id.framelayout,fragment);

        for(int i = 0;i<mFragmentArrayList.size();i++){
            if(fragment.getClass()!=mFragmentArrayList.get(i).getClass()){
                transaction.hide(mFragmentArrayList.get(i));
            }
        }
        transaction.commit();
    }
}
