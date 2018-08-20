package com.example.cwh.mypermission;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by cwh on 2018/8/20.
 */

public class AddContactsDialog extends Dialog implements View.OnClickListener{
    private String name;
    private String phone;
    private DialogInterface.OnClickListener mConfirmListener;
    private DialogInterface.OnClickListener mCancelListen;
    EditText name_editText;
    EditText phone_editText;
    public AddContactsDialog(@NonNull Context context) {
        super(context);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contacts_dialog);
        name_editText = (EditText) findViewById(R.id.name_editText);
        phone_editText = (EditText) findViewById(R.id.phone_editText);

        Button confirm_button = (Button) findViewById(R.id.confirm_button);
        Button cancel_button = (Button) findViewById(R.id.cancel_button);
        confirm_button.setOnClickListener(this);
        cancel_button.setOnClickListener(this);
    }
    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.cancel_button){
            name = null;
            phone = null;
        }
        if(v.getId()==R.id.confirm_button){
            name = name_editText.getText().toString();
            phone = phone_editText.getText().toString();
        }
        mConfirmListener.onClick(this,DialogInterface.BUTTON_NEGATIVE);
        mCancelListen.onClick(this,DialogInterface.BUTTON_NEGATIVE);
    }
    public void setConfirm_button( DialogInterface.OnClickListener listener){
        mConfirmListener = listener;
    }

    public void setCancel_button(OnClickListener listener){
        mCancelListen = listener;
    }
}
