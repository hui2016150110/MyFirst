package com.example.cwh.mypermission;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by cwh on 2018/8/1.
 */

public class My_single_select_Dialog extends Dialog {

    private Context mContext;
    private ImageView mImageView;
    private TextView title_textView;
    private RadioGroup mRadioGroup;
    private Button confirm_button;
    private Button cancel_button;
    private String select_item;

    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener;

    private DialogInterface.OnClickListener mOnClickListener;
    private DialogInterface.OnClickListener mCancelListen;

    public My_single_select_Dialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_select_dialog_layout);
        mImageView = (ImageView)findViewById(R.id.imageview);
        title_textView = (TextView)findViewById(R.id.title);
        mRadioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        confirm_button = (Button) findViewById(R.id.confirm_button);
        cancel_button = (Button) findViewById(R.id.cancel_button);
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onClick(My_single_select_Dialog.this,DialogInterface.BUTTON_NEGATIVE);
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCancelListen.onClick(My_single_select_Dialog.this,DialogInterface.BUTTON_NEGATIVE);
            }
        });

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(group.getCheckedRadioButtonId());
                select_item = String.valueOf(radioButton.getText());
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
        mOnClickListener = listener;
        confirm_button.setText(text);

    }

    public void setCancel_button(String text, OnClickListener listener){
        mCancelListen = listener;
        cancel_button.setText(text);
    }

    public void addRadiobutton(String text){
        RadioButton radioButton = new RadioButton(mContext);
        radioButton.setText(text);
        mRadioGroup.addView(radioButton, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public String getSelect_item(){
        return select_item;
    }
}
