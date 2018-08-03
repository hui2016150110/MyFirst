package com.example.cwh.mypermission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> arr_adapter;
    private SimpleAdapter simp_adapter;
    private List<Map<String,Object>> datalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        createListView();
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
    //实现ListView的一个实例
    private void createListView(){
        listView = findViewById(R.id.listView1);
        /*
        1.新建一个适配器
        适配器的构造参数（上下文，ListView加载每一项列表的布局文件，数据源）
        2.适配器加载数据源
        3.视图加载适配器
         */
        String []arr_data = {"慕课网1","慕课网2","慕课网3","慕课网4","慕课网5","慕课网6","慕课网7","慕课网8","慕课网9","慕课网10",
                "慕课网1","慕课网2","慕课网3","慕课网4","慕课网5","慕课网6","慕课网7","慕课网8","慕课网9","慕课网10"};
        final ArrayList<String> arrayList=new ArrayList<String>();
        for(String str:arr_data){
            arrayList.add(str);
        }
        arr_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        //listView.setAdapter(arr_adapter);


        /**
         * context:上下文
         * data：数据源List<Map<K,E>>,就是一个map所组成的List集合
         *      每一个map都会对应ListView中列表中的一行
         *      每一个map（键值对）中的键必须包含在from中所指定的键
         * resource：列表项的布局文件的ID
         * from:map中的键名
         * to：绑定数据视图中的ID，与from成对应关系
         * */

        datalist = new ArrayList<Map<String, Object>>();
        simp_adapter = new MySimpleAdapter(this,getData(),R.layout.myitem,
                new String[]{"pic","name_TextView","price_TextView","desc_TextView"},
                new int[]{R.id.imageview,R.id.textview1,R.id.textview2,R.id.textview3});
        listView.setAdapter(simp_adapter);

        //实现点击监听的方法：
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map select_item = datalist.get(i);
                String name_textView =(String) select_item.get("name_TextView");
                String price_textView = (String) select_item.get("price_TextView");
                Toast.makeText(ListViewActivity.this,"选中："+name_textView+"\r\n"+"要付款:"+price_textView
                        ,Toast.LENGTH_SHORT).show();
            }
        });

        //实现滚动状态的监听方法
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                switch (i){
                    case SCROLL_STATE_FLING:
                        Toast.makeText(ListViewActivity.this,"用力划了一下",Toast.LENGTH_SHORT).show();
                        Log.i("tag","用户手指离开屏幕前，用力划了一下，视图依靠惯性继续滑动");
                        arrayList.add("增加项");
                        arr_adapter.notifyDataSetChanged();
                        break;
                    case SCROLL_STATE_IDLE:
                        Toast.makeText(ListViewActivity.this,"视图停止滑动",Toast.LENGTH_SHORT).show();
                        Log.i("tag","视图停止滑动");
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        Toast.makeText(ListViewActivity.this,"视图正在滑动",Toast.LENGTH_SHORT).show();
                        Log.i("tag","手指没有离开屏幕，视图正在滑动");
                        break;
                }
            }
            @Override
            public void onScroll(AbsListView absListView, int firstVisibleitem, int visibleItemCount, int totalItemCount) {
                    Log.i("tag","firstVisibleitem:"+firstVisibleitem+" visibleItemCount:"+visibleItemCount+" totalItemCount:"+totalItemCount);
            }
        });
    }
}
