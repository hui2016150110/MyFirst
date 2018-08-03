package com.example.cwh.mypermission;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by cwh on 2018/7/23.
 */

public class MySimpleAdapter extends SimpleAdapter {

    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     */
    private Context mContext;
    private List<Map<String,Object>> mMapList;
    private int resourseID;
    private String[] from;
    private int[] to;

    public MySimpleAdapter(Context context, List< Map<String, Object>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.mContext = context;
        this.mMapList = data;
        this.resourseID = resource;
        this.from = from;
        this.to = to;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Map<String,Object> map = (Map<String, Object>) getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(mContext).inflate(resourseID,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.mImageView = (ImageView) view.findViewById(R.id.imageview);
            viewHolder.textView_name = (TextView) view.findViewById(R.id.textview1);
            viewHolder.textView_price = (TextView) view.findViewById(R.id.textview2);
            viewHolder.textView_desc = (TextView) view.findViewById(R.id.textview3);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.mImageView.setImageResource((int)map.get("pic"));
        viewHolder.textView_name.setText((String)map.get("name_TextView"));
        viewHolder.textView_price.setText((String)map.get("price_TextView"));
        viewHolder.textView_desc.setText((String)map.get("desc_TextView"));
        return view;
    }
    class ViewHolder{
        ImageView mImageView;
        TextView textView_name;
        TextView textView_price;
        TextView textView_desc;
    }
}
