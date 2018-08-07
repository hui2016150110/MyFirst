package com.example.cwh.mypermission;

/**
 * Created by cwh on 2018/8/6.
 */

public class ContactsPerson {
    private boolean isTop = false;
    private String name;
    private String number;


    public ContactsPerson(){};
    public ContactsPerson(String name,String number){
        this.name = name;
        this.number = number;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setTop(boolean isTop){
        this.isTop = isTop;
    }

    public boolean getTop(){
        return isTop;
    }
}
