// IMyAidlInterface.aidl
package com.example.cwh.myfetuscaredoctor;

// Declare any non-default types here with import statements
import com.example.cwh.myfetuscaredoctor.User;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

     void addUser(in User user);
     List<User> getUserList();
}
