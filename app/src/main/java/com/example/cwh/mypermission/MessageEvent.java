package com.example.cwh.mypermission;

/**
 * Created by hui on 2019/3/18.
 */

public class MessageEvent {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageEvent(String message){
        this.message = message;

    }
}
