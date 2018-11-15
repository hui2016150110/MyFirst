package com.example.cwh.mypermission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class Algorithm3 extends AppCompatActivity implements View.OnClickListener,Runnable{

    private Scanner input = new Scanner(System.in);
    private int row = 8;
    private int col = 4;
    private int max_score = 0;
    public Stack<Info_of_step> stack = new Stack<Info_of_step>();
    Info_of_step info_of_step2 = null;
    GridLayout gridLayout;
    public int init_arr[][] = new int [][]{{3,3,4,3},{3,2,3,3}, {2,4,3,4}, {1,3,4,3}, {3,3,1,1}, {3,4,3,3},{1,4,4,3},{1,2,3,2}};
    public int init_arr2[][] = new int [8][4];
    private HashSet<String> check = new HashSet<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suanfa);
        ArrayList<Info_of_step> info_of_stepArrayList = new ArrayList<Info_of_step>();
        gridLayout =findViewById(R.id.grid_layout);

        Algorithm3 algorithm3 = new Algorithm3();
        algorithm3.row = 8;
        algorithm3.col = 4;
        int m = algorithm3.row;
        int n = algorithm3.col;
        int arr[][] = new int[m][n];

        algorithm3.init(m,n,arr);


        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                ImageView image =(ImageView) gridLayout.getChildAt(i*n+j);
                if(arr[i][j]==1)
                    image.setImageResource(R.color.black);
                if(arr[i][j]==2)
                    image.setImageResource(R.color.blue);
                if(arr[i][j]==3)
                    image.setImageResource(R.color.yellow);
                if(arr[i][j]==4)
                    image.setImageResource(R.color.red);
            }
        }
        Button button = (Button) findViewById(R.id.next_button_al);
        button.setOnClickListener(this);
//        find_all_can_swap(init_arr,0,1,new ArrayList<Point2>());
//        while(!stack.empty()){
//            Info_of_step info_of_step = stack.pop();
//            //System.out.print(info_of_step);
//            find_all_can_swap(info_of_step.arr,info_of_step.score,info_of_step.step+1,info_of_step.route);
//            if(max_score<info_of_step.score){
//                max_score = info_of_step.score;
//                info_of_step2 = info_of_step;
//            }
//        }
//        System.out.println("max_score "+max_score);
//        System.out.println(info_of_step2);
        //这个图所有可以交换的点

        //long start = System.currentTimeMillis();

        new Thread(){
            @Override
            public void run(){
                find_all_can_swap(init_arr,0,1,new ArrayList<Point2>());
                while(!stack.empty()){
                    Info_of_step info_of_step = stack.pop();
//                    find_all_can_swap(info_of_step.arr,info_of_step.score,info_of_step.step+1,info_of_step.route);
//                    if(max_score<info_of_step.score){
//                        max_score = info_of_step.score;
//                        info_of_step2 = info_of_step;
//
//                    }
                    System.out.print(info_of_step);
//                    if(max_score==4)
//                        break;
                }
                System.out.println("max_score "+max_score);
                System.out.println(info_of_step2);
            }
        }.start();

        //long end = System.currentTimeMillis();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //System.out.println(end-start);

    }
    public void init(int m,int n,int arr[][]){
        int arr2[][] =new int [][]{{3,3,4,3},{3,2,3,3}, {2,4,3,4}, {1,3,4,3}, {3,3,1,1}, {3,4,3,3},{1,4,4,3},{1,2,3,2}};
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                arr[i][j] = arr2[i][j];
            }
        }
    }

    public void find_all_can_swap(int[][] arr,int pre_score,int step,ArrayList<Point2> pre_route){
        for(int i = 0;i<row;i++){
            for(int j = 0;j<col;j++){
                func(i,j,arr,pre_score,step,pre_route);
            }
        }
    }

    public void swap(int i, int j, int m, int n, int arr[][], int score, int step, ArrayList<Point2> pre_route){

        int[][] arr2 = new int[row][col];
        for(int p = 0;p<row;p++){
            for(int q = 0;q<col;q++){
                arr2[p][q] = arr[p][q];
            }
        }
        int temp = arr2[i][j];
        arr2[i][j] = arr2[m][n];
        arr2[m][n] = temp;
        Point2[] points = new Point2[2];
        points[0] = new Point2(i,j);
        points[1] = new Point2(m,n);
        Info_of_step info_of_step = new Info_of_step(arr2,row,col,points,score,step,pre_route);
        //System.out.println(info_of_step);
        //stack.push(info_of_step);
        store(i,j,info_of_step);
        store(m,n,info_of_step);
        eliminate_point(info_of_step);
        String string="";
        for(int p = 0;p<row;p++){
            for(int q = 0;q<col;q++){
                string = string+info_of_step.arr[p][q];
            }
        }
        if(check.contains(string)||info_of_step.step>=5){
            return;
        }else{
            check.add(string);
            stack.push(info_of_step);
        }

        //System.out.println(info_of_step);
    }

    public boolean is_swap(int i,int j,int[][] arr){
        int[][] arr2 = new int [row][col];
        for(int m = 0;m<row;m++){
            for(int n = 0;n<col;n++){
                arr2[m][n] = arr[m][n];
            }
        }

        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        int num4 = 0;
        //判断这个点的上下左右
        //上
        for(int k=i-1;k>=0;k--){
            if(arr2[i][j]==arr2[k][j])
                num1++;
            else
                break;
        }
        //下
        for(int k = i+1;k<(row>i+4?i+4:row);k++){
            if(arr2[i][j]==arr2[k][j])
                num2++;
            else
                break;
        }
        //左
        for(int k=j-1;k>=0;k--){
            if(arr2[i][j]==arr2[i][k])
                num3++;
            else
                break;
        }
        //右
        for(int k = j+1;k<(col>j+4?j+4:col);k++){
            if(arr2[i][j]==arr2[i][k])
                num4++;
            else
                break;
        }
        //纵向有多少个可以消去
        int col_num = num1+num2+1;
        //横向有多少个可以消去
        int row_num = num3+num4+1;

        if(col_num>=3||row_num>=3)
            return true;
        return false;
    }

    public Info_of_step store(int i,int j,Info_of_step info_of_step){
        int[][] arr = info_of_step.arr;
        HashSet<Point2> store_Point = info_of_step.store_point;
        int score = 0;
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        int num4 = 0;
        //判断这个点的上下左右
        //上
        for(int k=i-1;k>=0;k--){
            if(arr[i][j]==arr[k][j])
                num1++;
            else
                break;
        }
        //下
        for(int k = i+1;k<(row>i+4?i+4:row);k++){
            if(arr[i][j]==arr[k][j])
                num2++;
            else
                break;
        }
        //左
        for(int k=j-1;k>=0;k--){
            if(arr[i][j]==arr[i][k])
                num3++;
            else
                break;
        }
        //右
        for(int k = j+1;k<(col>j+4?j+4:col);k++){
            if(arr[i][j]==arr[i][k])
                num4++;
            else
                break;
        }
        //纵向有多少个可以消去
        int col_num = num1+num2+1;
        //横向有多少个可以消去
        int row_num = num3+num4+1;

        if(col_num==3)
            score=1;
        else if(col_num==4)
            score = 4;
        else if(col_num==5)
            score = 10;
        else if(col_num<3)
            score = 0;
        if(row_num==3)
            score=score+1;
        else if(row_num==4)
            score = score+4;
        else if(row_num==5)
            score = score+10;
        else if(row_num<3)
            score = score;

        info_of_step.score = info_of_step.score+score;

        if(col_num>=3||row_num>=3){
            if(row_num>=3){
                for(int k = 0;k<num3;k++){
                    store_Point.add(new Point2(i,j-k-1));
                }
                for(int k = 0;k<num4;k++){
                    store_Point.add(new Point2(i,j+k+1));
                }
            }
            if(col_num>=3){
                for(int k = 0;k<num1;k++){
                    store_Point.add(new Point2(i-k-1,j));
                }
                for(int k = 0;k<num2;k++){
                    store_Point.add(new Point2(i+k+1,j));
                }
            }
            store_Point.add(new Point2(i,j));
        }
        return info_of_step;
    }

    public void eliminate_point(final Info_of_step info_of_step)  {
        HashSet<Point2> points = info_of_step.store_point;
        int [][]arr = info_of_step.arr;
        if(points.isEmpty()){
            return;
        }
        Iterator iterator = points.iterator();
        //System.out.println("需要消掉的点坐标：");
        while (iterator.hasNext()){
            Point2 point =(Point2) iterator.next();
            arr[point.row][point.col] = 0;
            //System.out.println(point.row+" "+point.col);
        }
        int flag[][] = new int[row][col];
        for(int p = 0;p<row;p++){
            for(int q = 0;q<col;q++){
                flag[p][q] = 0;
            }
        }
        for(int p = 0;p<row;p++){
            for(int q = 0;q<col;q++){
                if(arr[p][q] == 0){
                    for(int k = p;k>0;k--){
                        arr[k][q] = arr[k-1][q];
                        arr[k-1][q] = 0;
                        if(arr[k][q]!=0)
                            flag[k][q] = 1;
                        else
                            flag[k][q] = 0;
                    }
                    arr[0][q] = 0;
                }

            }
        }
        for(int p = 0;p<row;p++){
            for(int q = 0;q<col;q++){
                init_arr2[p][q] = arr[p][q];
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i<row;i++){
                    for(int j = 0;j<col;j++){
                        ImageView image =(ImageView) gridLayout.getChildAt((i*col+j));
                        if(init_arr2[i][j]==1)
                            image.setImageResource(R.color.black);
                        else if(init_arr2[i][j]==2)
                            image.setImageResource(R.color.blue);
                        else if(init_arr2[i][j]==3)
                            image.setImageResource(R.color.yellow);
                        else if(init_arr2[i][j]==4)
                            image.setImageResource(R.color.red);
                        else
                            image.setImageResource(R.color.gray);
                    }
                }
                info_of_step.store_point.clear();
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        System.out.println("下落后的棋盘：");
//        for(int p = 0;p<row;p++){
//            for(int q = 0;q<col;q++){
//                System.out.print(info_of_step.arr[p][q]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        info_of_step.store_point.clear();
        for(int p = 0;p<row;p++){
            for(int q = 0;q<col;q++){
                if(flag[p][q]!=0){
                    store(p,q,info_of_step);
                }
            }
        }
        eliminate_point(info_of_step);
    }

    public void func(int i,int j,int[][] arr,int pre_score,int step,ArrayList<Point2> pre_route){
        if(j<col-1){
            if(arr[i][j]!=arr[i][j+1]&&arr[i][j]!=0&&arr[i][j+1]!=0){
                int arr2[][] = new int[row][col];
                for(int p = 0;p<row;p++){
                    for(int q = 0;q<col;q++){
                        arr2[p][q] = arr[p][q];
                    }
                }
                int temp = arr2[i][j];
                arr2[i][j] = arr2[i][j+1];
                arr2[i][j+1] = temp;
                if(is_swap(i,j,arr2)||is_swap(i,j+1,arr2)){
                    //System.out.println("点"+i+" "+j+"向右交换");
                    swap(i,j,i,j+1,arr,pre_score,step,pre_route);
                }
            }
        }
        if(i<row-1){
            if(arr[i][j]!=arr[i+1][j]&&arr[i][j]!=0&&arr[i+1][j]!=0){
                int arr2[][] = new int[row][col];
                for(int p = 0;p<row;p++){
                    for(int q = 0;q<col;q++){
                        arr2[p][q] = arr[p][q];
                    }
                }
                int temp = arr2[i][j];
                arr2[i][j] = arr2[i+1][j];
                arr2[i+1][j] = temp;
                if(is_swap(i,j,arr2)||is_swap(i+1,j,arr2)){
                    //System.out.println("点"+i+" "+j+"向下交换");
                    swap(i,j,i+1,j,arr,pre_score,step,pre_route);
                }
            }
        }
    }


    @Override
    public void onClick(View v) {

        ImageView imageView = (ImageView) gridLayout.getChildAt(0);
        imageView.setImageResource(R.color.gray);

//        Log.d("output","message");
    }
    public void change(int i,int j,int p,int q){

    }

    @Override
    public void run() {

    }
}
