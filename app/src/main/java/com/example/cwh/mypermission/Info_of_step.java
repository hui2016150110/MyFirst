package com.example.cwh.mypermission;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by hui on 2018/11/13.
 */

public class Info_of_step {
    Point2[] points;
    int row;
    int col;
    public int[][] arr=null;
    public int score;
    public int step;
    public HashSet<Point2> store_point;
    public ArrayList<Point2> route;
    public Info_of_step(int[][] arr,int row,int col,Point2[] points,int score,int step,ArrayList<Point2> pre_route){
        this.row = row;
        this.col = col;
        this.arr = new int[row][col];
        for(int i = 0;i<row;i++){
            for(int j = 0;j<col;j++)
                this.arr[i][j] = arr[i][j];
        }
        this.points = new Point2[2];
        this.points = points;
        this.score = score;
        this.step = step;
        store_point = new HashSet<Point2>();
        route = new ArrayList<Point2>();
        route.add(points[0]);
        route.add(points[1]);
        route.addAll(pre_route);

    }
    public int[][] getArr() {
        return arr;
    }

    public int getScore() {
        return score;
    }

    public int getStep() {
        return step;
    }

    public HashSet<Point2> getStore_point() {
        return store_point;
    }
    public Point2[] getPoints(){
        return points;
    }

    @Override
    public String toString() {
        for(int i = 0; i<row;i++){
            for(int j = 0;j<col;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(score);
        return "";
    }
}
