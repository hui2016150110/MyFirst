package com.example.cwh.mypermission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridViewActivity extends AppCompatActivity {

    private GridView gridView;
    private List<Map<String,Object>> dataList_Grid;
    private SimpleAdapter simp_adapter_Grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);
        createGirdView();
    }
    private void createGirdView(){

        gridView =(GridView) findViewById(R.id.gridView);
        dataList_Grid = new ArrayList<Map<String, Object>>();

        String[] from = {"pic","text"};
        int []to = {R.id.grid_imageView,R.id.grid_textView};
        simp_adapter_Grid = new SimpleAdapter(this,getDataList_Grid(),R.layout.gridview_item,from,to);

        gridView.setAdapter(simp_adapter_Grid);

    }

    private List<Map<String,Object>> getDataList_Grid(){
        int[] icon = {R.drawable.ac,R.drawable.ae,R.drawable.ai,R.drawable.fb,R.drawable.foo,
                R.drawable.fz,R.drawable.jr,R.drawable.li,R.drawable.lnk,R.drawable.ps,R.drawable.sf,R.drawable.tv};
        String[] icon_name = {"ac","ae","ai","fb","foo","fz","jr","li","lnk","ps","sf","tv"};
        for(int i = 0;i<(int) icon.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("pic",icon[i]);
            map.put("text",icon_name[i]);
            dataList_Grid.add(map);
        }
        return dataList_Grid;
    }
}
