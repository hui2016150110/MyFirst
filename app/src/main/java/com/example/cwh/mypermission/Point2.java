package com.example.cwh.mypermission;

/**
 * Created by hui on 2018/11/13.
 */

public class Point2 {
    public int row;
    public int col;
    public Point2(){
        this(0,0);
    }
    public Point2(int row,int col){
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
