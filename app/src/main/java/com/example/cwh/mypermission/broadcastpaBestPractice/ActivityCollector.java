package com.example.cwh.mypermission.broadcastpaBestPractice;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hui on 2019/4/15.
 */

public class ActivityCollector {
    public static List<Activity> activityies = new ArrayList<>();
    public static void addActivity(Activity activity){
        activityies.add(activity);
    }

    public static void removeActivity(Activity activity){
        activityies.remove(activity);
    }

    public static void finishedAll(){
        for(Activity activity:activityies){
            if(!activity.isFinishing())
                activity.finish();
        }
    }

}
