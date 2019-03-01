package com.example.cwh.mypermission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewActivity extends AppCompatActivity {

    private List<Map<String,Object>> datalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_layout);
        createRecycleView();
    }
        private void createRecycleView(){

        datalist = new ArrayList<Map<String, Object>>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //线性布局
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(layoutManager);

        //网格布局
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, GridLayoutManager.VERTICAL));

        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(getData());

        recyclerView.setAdapter(myRecyclerViewAdapter);
    }

    private  List<Map<String,Object>> getData(){

        Map<String ,Object> map1 = new HashMap<String,Object>();
        map1.put("pic",R.drawable.chicken);
        map1.put("name_TextView","名称：小鸡鸡");
        map1.put("price_TextView","价格：50元");
        map1.put("desc_TextView","描述：这是世界上最好吃的小鸡鸡");
        datalist.add(map1);

        Map<String ,Object> map2 = new HashMap<String,Object>();
        map2.put("pic",R.drawable.fruit);
        map2.put("name_TextView","名称：恶魔果实");
        map2.put("price_TextView","价格：10000元");
        map2.put("desc_TextView","描述：吃了这个就可以当上海贼王了");
        datalist.add(map2);

        Map<String ,Object> map3 = new HashMap<String,Object>();
        map3.put("pic",R.drawable.hamburger);
        map3.put("name_TextView","名称：神奇汉堡");
        map3.put("price_TextView","价格：10元");
        map3.put("desc_TextView","描述：听说没人能拒绝这个味道");
        datalist.add(map3);

        for(int i = 0;i<5;i++){
            datalist.add(map1);
            datalist.add(map2);
            datalist.add(map3);
        }
        return datalist;
    }



}
