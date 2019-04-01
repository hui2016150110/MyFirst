package com.example.cwh.mypermission;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by cwh on 2018/8/6.
 */

public class Fragment2 extends Fragment implements View.OnClickListener{
    View mView;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment2_layout,container, false);
        Button button = (Button) mView.findViewById(R.id.button10);
        button.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button10:
                EventBus.getDefault().post(new MessageEvent("改变UI"));
        }
    }


}
