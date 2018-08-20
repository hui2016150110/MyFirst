package com.example.cwh.mypermission;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwh on 2018/8/1.
 */

public class My_more_select_Dialog extends Dialog {


    private Context mContext;
    private ImageView mImageView;
    private TextView title_textView;
    private Button confirm_button;
    private Button cancel_button;
    private LinearLayout mLinearLayout;
    private List<CheckBox> mCheckBoxList = new ArrayList<>();


    private DialogInterface.OnClickListener mConfirmListener;
    private DialogInterface.OnClickListener mCancelListen;

    public My_more_select_Dialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_select_dialog_layout);
        mImageView = (ImageView)findViewById(R.id.imageview);
        title_textView = (TextView)findViewById(R.id.title);
        mLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        confirm_button = (Button) findViewById(R.id.confirm_button);
        cancel_button = (Button) findViewById(R.id.cancel_button);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String select_items = "";
                for(int i = 0;i<mCheckBoxList.size();i++){
                    CheckBox checkBox = mCheckBoxList.get(i);
                    if(checkBox.isChecked()){
                        select_items+=(i+1)+" ";
                    }
                }
                Toast.makeText(mContext,"选中了:"+select_items,Toast.LENGTH_SHORT).show();
                mConfirmListener.onClick(My_more_select_Dialog.this,DialogInterface.BUTTON_NEGATIVE);
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCancelListen.onClick(My_more_select_Dialog.this,DialogInterface.BUTTON_NEGATIVE);
            }
        });
    }

    public void setTitle(String title){
        title_textView.setText(title);
    }

    public void setImageView(int id){
        mImageView.setImageResource(id);
    }

    public void setConfirm_button(String text, DialogInterface.OnClickListener listener){
        mConfirmListener = listener;
        confirm_button.setText(text);
    }

    public void setCancel_button(String text, OnClickListener listener){
        mCancelListen = listener;
        cancel_button.setText(text);
    }

    public void addCheckbox(String text){
        CheckBox checkBox = new CheckBox(mContext);
        mCheckBoxList.add(checkBox);
        checkBox.setText(text);
        mLinearLayout.addView(checkBox,LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

}
