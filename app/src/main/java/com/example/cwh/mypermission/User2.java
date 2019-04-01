package com.example.cwh.mypermission;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by hui on 2019/3/26.
 */

public class User2 implements Parcelable{
    public int userId;
    public String userName;
    public String password;
    public User2(int userId, String userName, String password){
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
    protected User2(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        password = in.readString();
    }

    public static final Creator<User2> CREATOR = new Creator<User2>() {
        @Override
        public User2 createFromParcel(Parcel in) {
            return new User2(in);
        }

        @Override
        public User2[] newArray(int size) {
            return new User2[size];
        }
    };

    @Override
    public int describeContents() {
        //几乎所有情况都返回0，仅在当前对象中存在文件描述符时返回1
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeString(password);
    }

    public void test() throws IOException, ClassNotFoundException {
        User2 user=new User2(0,"wcl_android@163.com","123456");
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("user.obj"));
        out.writeObject(user);
        out.close();

        //反序列化
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("user.obj"));
        user = (User2) in.readObject();
        in.close();
    }

}
