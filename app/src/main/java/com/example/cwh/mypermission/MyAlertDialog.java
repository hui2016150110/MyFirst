package com.example.cwh.mypermission;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by cwh on 2018/7/27.
 */

public class MyAlertDialog extends Dialog {


        private DialogInterface.OnClickListener cancelbuttonListener,confirmbuttonListener;
        private Button cancelbutton;
        private Button confirmbutton;
        private ImageView title_image;
        private TextView title_textView;
        private TextView context_textView;

    public MyAlertDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterdialog_layout);

        confirmbutton = (Button) findViewById(R.id.positiveButton);
        cancelbutton = (Button) findViewById(R.id.negetiveButton);
        title_image = (ImageView) findViewById(R.id.imageview);
        title_textView = (TextView) findViewById(R.id.title);
        context_textView = (TextView) findViewById(R.id.message);

        this.cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelbuttonListener.onClick(MyAlertDialog.this,DialogInterface.BUTTON_NEGATIVE);
            }
        });
        this.confirmbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmbuttonListener.onClick(MyAlertDialog.this,DialogInterface.BUTTON_NEGATIVE);
            }
        });
}
    public void setCancelbutton(String cancel,DialogInterface.OnClickListener listener) {
        this.cancelbutton.setText(cancel);
        this.cancelbuttonListener = listener;
    }

    public void setConfirmbutton(String confirm,DialogInterface.OnClickListener listener) {
        this.confirmbutton.setText(confirm);
        this.confirmbuttonListener = listener;
    }

    public void setTitle_image(int imageID) {
        this.title_image.setImageResource(imageID);
    }

    public void setTitle_textView(String title) {
        this.title_textView.setText(title);
    }

    public void setContext_textView(String context) {
        this.context_textView.setText(context);
    }
    //    public static class Builder{
//        private Context context;
//        private int iconID;
//        private String title;   //标题
//        private String message; //内容
//        private String cancel_text; //取消键内容
//        private String confirm_text;    //确定键内容
//        private View contentView;
//
//        //对话框监听事件
//        private DialogInterface.OnClickListener cancelbuttonListener,confirmbuttonListener;
//
//        public Builder(Context context){
//            this.context = context;
//        }
//
//        public Builder setTitle(String title) {
//            this.title = title;
//            return this;
//        }
//
//        public Builder setMessage(String message) {
//            this.message = message;
//            return this;
//        }
//
//        public Builder setPositiveButton(String cancel_text,DialogInterface.OnClickListener listener) {
//            this.cancel_text = cancel_text;
//            this.cancelbuttonListener = listener;
//            return this;
//        }
//
//        public Builder setNegativeButton(String confirm_text,DialogInterface.OnClickListener listener) {
//            this.confirm_text = confirm_text;
//            this.confirmbuttonListener = listener;
//            return this;
//        }
//        public Builder setConentView(View v){
//            this.contentView = v;
//            return this;
//        }
//
//        public Builder setIcon(int id){
//            this.iconID = id;
//            return this;
//        }
//
//        public MyAlertDialog create(){
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            final MyAlertDialog dialog = new MyAlertDialog(context);
//            View layoutView = inflater.inflate(R.layout.alterdialog_layout,null);
//
//
//            ((ImageView) layoutView.findViewById(R.id.imageview)).setImageResource(iconID);
//            ((TextView) layoutView.findViewById(R.id.title)).setText(title);
//            ((TextView) layoutView.findViewById(R.id.message)).setText(message);
//
//            final Button cancelbutton = (Button) layoutView.findViewById(R.id.negetiveButton);
//            cancelbutton.setText(cancel_text);
//            if(cancelbuttonListener!=null){
//                cancelbutton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        cancelbuttonListener.onClick(dialog,DialogInterface.BUTTON_NEGATIVE);
//                    }
//                });
//            }
//
//            final Button confirmbutton = (Button) layoutView.findViewById(R.id.positiveButton);
//            if(confirmbuttonListener!=null){
//                confirmbutton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        confirmbuttonListener.onClick(dialog,DialogInterface.BUTTON_NEGATIVE);
//                    }
//                });
//            }
//
//            dialog.addContentView(layoutView,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
//            dialog.setContentView(layoutView);
//            return dialog;
//        }
//    }
}
