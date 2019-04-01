package com.example.cwh.mypermission;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by cwh on 2018/8/6.
 */

public class Fragment1 extends Fragment {

    View mView;
    String text;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(Fragment1.this);
        mView = inflater.inflate(R.layout.fragment1_layout,container, false);
        TextView textView = mView.findViewById(R.id.textview2);
        if(savedInstanceState!=null)
            textView.setText(savedInstanceState.getString("KEY"));
        return mView;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent){
        TextView textView = mView.findViewById(R.id.textView2);
        textView.setText("收到了");
        text = messageEvent.getMessage();
        Log.d("TAG",text);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("KEY",text);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
