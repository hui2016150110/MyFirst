package com.example.cwh.mypermission;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressbarActivity extends AppCompatActivity {

    //实现progressBar的变量声明
    private ProgressBar progressBar;
    private Button add_button;
    private Button sub_button;
    private Button re_button;
    private TextView textView_progress;
    private Button show_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar_item);
        createProgressBar();
    }
    private void createProgressBar(){
        setProgressBarVisibility(true);
        progressBar =(ProgressBar) findViewById(R.id.progress_horizontal);
        add_button =(Button) findViewById(R.id.button_add);
        sub_button =(Button) findViewById(R.id.button_sub);
        re_button =(Button) findViewById(R.id.button_re);
        textView_progress = findViewById(R.id.textView_progressBar);
        add_button.setOnClickListener(new MyClickListener(progressBar,textView_progress));
        sub_button.setOnClickListener(new MyClickListener(progressBar,textView_progress));
        re_button.setOnClickListener(new MyClickListener(progressBar,textView_progress));
        int firstProgress = progressBar.getProgress();
        int secondProgress = progressBar.getSecondaryProgress();
        int maxProgress = progressBar.getMax();
        textView_progress.setText("第一进度条百分比："+(int)(firstProgress/(float)maxProgress*100)+
                "% 第二进度条的百分比："+(int)(secondProgress/(float)maxProgress*100)+"%");
        show_button = findViewById(R.id.button_show);
        show_button.setOnClickListener(new View.OnClickListener() {


            /**
             *1.新建progressDialog对象
             * 2.设置显示风格
             * 3.设置标题
             * 4.设置对话框的文字信息
             * 设置图标
             */

            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog = new ProgressDialog(ProgressbarActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setTitle("进度条");
                progressDialog.setMessage("显示具体的百分比");
                progressDialog.setIcon(R.drawable.hamburger);

                progressDialog.setMax(100);
                progressDialog.incrementProgressBy(50);
                progressDialog.setIndeterminate(false);

                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                progressDialog.setCancelable(true);
                progressDialog.show();
            }
        });

    }
}
class MyClickListener implements View.OnClickListener{

    private ProgressBar progressBar;
    private TextView textView;
    public MyClickListener(ProgressBar progressBar,TextView textView){
        this.progressBar = progressBar;
        this.textView = textView;
    }
    @Override

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.button_add:
                progressBar.incrementProgressBy(10);
                progressBar.incrementSecondaryProgressBy(10);
                break;
            case R.id.button_sub:
                progressBar.incrementProgressBy(-10);
                progressBar.incrementSecondaryProgressBy(-10);
                break;
            case R.id.button_re:
                progressBar.setProgress(50);
                progressBar.setSecondaryProgress(80);
                break;
        }
        int firstProgress = progressBar.getProgress();
        int secondProgress = progressBar.getSecondaryProgress();
        int maxProgress = progressBar.getMax();
        textView.setText("第一进度条百分比："+(int)(firstProgress/(float)maxProgress*100)+
                "% 第二进度条的百分比："+(int)(secondProgress/(float)maxProgress*100)+"%");
    }
}
