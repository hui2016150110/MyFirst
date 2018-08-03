package com.example.cwh.mypermission;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener{

    private MyAlertDialog alert;
    //private MyAlertDialog.Builder builder;
    private Button bt1,bt2,bt3,bt4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        bt1 = (Button) findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);
        bt4 = (Button) findViewById(R.id.button4);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                alert = new MyAlertDialog(this);
                alert.show();
                alert.setTitle_textView("普通对话框".toString());
                alert.setContext_textView("这是一个普通的对话框".toString());
                alert.setTitle_image(R.drawable.hamburger);
                alert.setConfirmbutton("想吃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDialogActivity.this,"我想吃这个汉堡",Toast.LENGTH_SHORT).show();
                        alert.dismiss();
                    }
                });
                alert.setCancelbutton("不想吃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDialogActivity.this,"热量太高不想吃",Toast.LENGTH_SHORT).show();
                        alert.dismiss();
                    }
                });
                break;
            case R.id.button2:
                MyListDialog listDialog = new MyListDialog(this);
                listDialog.show();
                listDialog.setImageView(R.drawable.chicken);
                listDialog.setTitle("学习需要能量，鸡腿就是能量");
                listDialog.add_List(new String[]{"数学","语文","英语","物理","化学","生物","地理","政治","历史"});
                listDialog.setOnClickItemListener(listDialog);
                break;
            case R.id.button3:
                final My_single_select_Dialog single_select_dialog = new My_single_select_Dialog(this);
                single_select_dialog.show();
                single_select_dialog.setTitle("软件");
                single_select_dialog.setImageView(R.drawable.fruit);
                single_select_dialog.addRadiobutton("ps");
                single_select_dialog.addRadiobutton("ai");
                single_select_dialog.addRadiobutton("au");
                single_select_dialog.setCancel_button("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDialogActivity.this,"都不好玩",Toast.LENGTH_SHORT).show();
                        single_select_dialog.dismiss();
                    }
                });
                single_select_dialog.setConfirm_button("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDialogActivity.this,"选中:"+single_select_dialog.getSelect_item(),Toast.LENGTH_SHORT).show();
                        single_select_dialog.dismiss();
                    }
                });
                break;
            case R.id.button4:
                final My_more_select_Dialog more_select_dialog = new My_more_select_Dialog(this);
                more_select_dialog.show();
                more_select_dialog.setTitle("多选");
                more_select_dialog.setImageView(R.drawable.chicken);
                more_select_dialog.addCheckbox("100块钱");
                more_select_dialog.addCheckbox("200块钱");
                more_select_dialog.addCheckbox("300块钱");
                more_select_dialog.addCheckbox("400块钱");
                more_select_dialog.setConfirm_button("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        more_select_dialog.dismiss();
                    }
                });
                more_select_dialog.setCancel_button("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        more_select_dialog.dismiss();
                    }
                });
                break;
            default:
        }
    }
}
