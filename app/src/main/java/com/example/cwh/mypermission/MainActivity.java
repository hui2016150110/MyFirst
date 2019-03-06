package com.example.cwh.mypermission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import view.UserLoginAcivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button create_listView = (Button) findViewById(R.id.create_listView);
        create_listView.setOnClickListener(this);
        Button create_picker = (Button) findViewById(R.id.create_Picker);
        create_picker.setOnClickListener(this);
        Button create_gridview = (Button) findViewById(R.id.create_GridView);
        create_gridview.setOnClickListener(this);
        Button create_spinner = (Button) findViewById(R.id.create_Spinner);
        create_spinner.setOnClickListener(this);
        Button create_progerssbar = (Button) findViewById(R.id.create_progressBar);
        create_progerssbar.setOnClickListener(this);
        Button create_webview = (Button) findViewById(R.id.create_webview);
        create_webview.setOnClickListener(this);
        Button create_recyclerview = (Button) findViewById(R.id.create_recyclerView);
        create_recyclerview.setOnClickListener(this);
        Button create_alertDialog = (Button) findViewById(R.id.create_alertDialog);
        create_alertDialog.setOnClickListener(this);
        Button create_broadcast = (Button) findViewById(R.id.create_broadcast);
        create_broadcast.setOnClickListener(this);
        Button create_gesture = (Button)findViewById(R.id.create_gesture);
        create_gesture.setOnClickListener(this);
        Button create_viewPager = (Button) findViewById(R.id.create_viewPager);
        create_viewPager.setOnClickListener(this);
        Button create_provider = (Button) findViewById(R.id.create_provider);
        create_provider.setOnClickListener(this);
        Button create_animation = (Button) findViewById(R.id.create_animation);
        create_animation.setOnClickListener(this);
        Button test_okhttp = (Button) findViewById(R.id.test_okhttp);
        test_okhttp.setOnClickListener(this);
        Button create_MVP = (Button) findViewById(R.id.create_MVP);
        create_MVP.setOnClickListener(this);
        Button create_Tab = (Button)findViewById(R.id.create_Tab);
        create_Tab.setOnClickListener(this);
        Button create_Algorithm3 = (Button) findViewById(R.id.create_Algorithm3);
        create_Algorithm3.setOnClickListener(this);
        Button create_Rxjava = (Button) findViewById(R.id.create_RXjava);
        create_Rxjava.setOnClickListener(this);
        Button test_retrofit = findViewById(R.id.retrofit);
        test_retrofit.setOnClickListener(this);
        Button test_news = findViewById(R.id.news);
        test_news.setOnClickListener(this);
        Button test_MyView = (Button) findViewById(R.id.myView);
        test_MyView.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,100,1,"setting");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.create_listView:
                intent = new Intent(MainActivity.this,ListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.create_Picker:
                intent = new Intent(MainActivity.this,PickerActivity.class);
                startActivity(intent);
                break;
            case R.id.create_GridView:
                intent = new Intent(MainActivity.this,GridViewActivity.class);
                startActivity(intent);
                break;
            case R.id.create_Spinner:
                intent = new Intent(MainActivity.this,SpinnerActivity.class);
                startActivity(intent);
                break;
            case R.id.create_progressBar:
                intent = new Intent(MainActivity.this,ProgressbarActivity.class);
                startActivity(intent);
                break;
            case R.id.create_webview:
                intent = new Intent(MainActivity.this,WebViewActivity.class);
                startActivity(intent);
                break;
            case R.id.create_recyclerView:
                intent = new Intent(MainActivity.this,RecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.create_alertDialog:
                intent = new Intent(MainActivity.this,AlertDialogActivity.class);
                startActivity(intent);
                break;
            case R.id.create_broadcast:
                intent = new Intent(MainActivity.this,BroadcastActivity.class);
                startActivity(intent);
                break;
            case R.id.create_gesture:
                intent = new Intent(MainActivity.this,GestureActivity.class);
                startActivity(intent);
                break;
            case R.id.create_viewPager:
                intent = new Intent(MainActivity.this,ViewPagerActivty.class);
                startActivity(intent);
                break;
            case R.id.create_provider:
                intent = new Intent(MainActivity.this,ProviderActivity.class);
                startActivity(intent);
                break;
            case R.id.create_animation:
                intent = new Intent(MainActivity.this,AnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.test_okhttp:
                intent = new Intent(MainActivity.this,OkhttpActivity.class);
                startActivity(intent);
                break;
            case R.id.create_MVP:
                intent = new Intent(MainActivity.this, UserLoginAcivity.class);
                startActivity(intent);
                break;
            case R.id.create_Tab:
                intent = new Intent(MainActivity.this,TestTab.class);
                startActivity(intent);
                break;
            case R.id.create_Algorithm3:
                intent = new Intent(MainActivity.this,Algorithm3.class);
                startActivity(intent);
                break;
            case R.id.create_RXjava:
                intent = new Intent(MainActivity.this,RXjava_activity.class);
                startActivity(intent);
                break;
            case R.id.retrofit:
                intent = new Intent(MainActivity.this,RetrofitActivity.class);
                startActivity(intent);
                break;
            case R.id.news:
                intent = new Intent(MainActivity.this,ShowNews.class);
                startActivity(intent);
                break;
            case R.id.myView:
                intent = new Intent(this,MyViewAcivity.class);
                startActivity(intent);
                break;
                default:
        }
    }
}


