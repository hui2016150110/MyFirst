package com.example.cwh.myfetuscaredoctor;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hui on 2019/3/27.
 */

public class User implements Parcelable {
    int id;
    String name;
    String password;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public User(int id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }
    protected User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
