package com.example.shopgroc.utility;

import android.content.Context;

import com.example.shopgroc.model.User;

public class SharedUtility {
    public static SharedUtility instance=null;
    private Context context;

    public static SharedUtility getInstance(Context context){
        if (instance==null)instance=new SharedUtility(context);
        return instance;
    }

    private  SharedUtility(Context context){
        this.context=context;
    }

    public User getUser(){}
    public void setUser(User user){
        user.setId("12");
        user.setName("Abdul Rehman");
        user.setEmail("");

    }
}
