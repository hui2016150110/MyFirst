package com.example.cwh.mypermission;

import android.animation.TypeEvaluator;

/**
 * Created by hui on 2019/3/4.
 */

public class PointEvaluator implements TypeEvaluator{

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point3 startPoint = (Point3) startValue;
        Point3 endPoint = (Point3) endValue;
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        Point3 point = new Point3(x, y);
        return point;
    }

}
