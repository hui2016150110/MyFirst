package com.example.cwh.mypermission;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class TestTab extends AppCompatActivity implements View.OnClickListener{

    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ImageButton imageButton3;
    private ImageButton imageButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fragment fragment1 = new Fragment1();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tab);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout,fragment1);
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
                replaceFragment(new Fragment1());
                imageButton1.setImageResource(R.drawable.info2);
                imageButton2.setImageResource(R.drawable.address_list);
                imageButton3.setImageResource(R.drawable.find);
                imageButton4.setImageResource(R.drawable.me);
                break;
            case R.id.imagebutton2:
               replaceFragment(new Fragment2());
                imageButton1.setImageResource(R.drawable.info);
                imageButton2.setImageResource(R.drawable.address_list2);
                imageButton3.setImageResource(R.drawable.find);
                imageButton4.setImageResource(R.drawable.me);
                break;
            case R.id.imagebutton3:
                replaceFragment(new Fragment3());
                imageButton1.setImageResource(R.drawable.info);
                imageButton2.setImageResource(R.drawable.address_list);
                imageButton3.setImageResource(R.drawable.find2);
                imageButton4.setImageResource(R.drawable.me);
                break;
            case R.id.imagebutton4:
                replaceFragment(new Fragment4());
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
        transaction.replace(R.id.framelayout,fragment);
        transaction.commit();
    }
}
