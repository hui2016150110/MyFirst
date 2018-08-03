package com.example.cwh.mypermission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
                default:
        }
    }
}


