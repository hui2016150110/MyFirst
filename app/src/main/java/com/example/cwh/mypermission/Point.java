package com.example.cwh.mypermission;

/**
 * Created by cwh on 2018/8/20.
 */

public class Point {
    double radius;
    double angle;
    Point(){
        this(0,0);
    }
    Point(double radius,double angle){
        this.radius = radius;
        this.angle = angle;
    }

    public double getRadius() {
        return radius;
    }

    public double getAngle() {
        return angle;
    }
}
