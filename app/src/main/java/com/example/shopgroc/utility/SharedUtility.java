package com.example.shopgroc.utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.shopgroc.model.User;

import static com.example.shopgroc.utility.Constant.SharedPrefKey.PREF_NAME;
import static com.example.shopgroc.utility.Constant.SharedPrefKey.USER_ADDRESS;
import static com.example.shopgroc.utility.Constant.SharedPrefKey.USER_EMAIL;
import static com.example.shopgroc.utility.Constant.SharedPrefKey.USER_ID;
import static com.example.shopgroc.utility.Constant.SharedPrefKey.USER_NAME;
import static com.example.shopgroc.utility.Constant.SharedPrefKey.USER_PHONE;

public class SharedUtility {
    private static final String TAG="SharedUtility";

    private Context context;
    private static SharedUtility instance=null;
    SharedPreferences pref;

    SharedPreferences.Editor editor;

    int PRIVATE_MODE = 0;

    private SharedUtility(Context context){
        this.context=context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static SharedUtility getInstance(Context context){
        if (instance==null) instance=new SharedUtility(context);
        return instance;
    }

    public boolean isLoggedIn(){
        User user=getUser();
        return user!=null;
    }

    public void setUser(User user){

        editor.putString(USER_ID,user.getId());
        editor.putString(USER_NAME,user.getName());
        editor.putString(USER_EMAIL,user.getEmail());
        editor.putString(USER_PHONE,user.getPhone());
        editor.putString(USER_ADDRESS,user.getAddress());

        editor.commit();

    }

    public User getUser(){
        String id=pref.getString(USER_ID,null);
        String userName=pref.getString(USER_NAME,null);
        String userEmail=pref.getString(USER_EMAIL,null);
        String userPhone=pref.getString(USER_PHONE,null);
        String userAddress=pref.getString(USER_ADDRESS,null);

        if (id!=null && !id.isEmpty() && userEmail!=null && !userEmail.isEmpty()){
            return new User(id,userName,userEmail,userPhone,userAddress);
        }

        return  null;
    }

}
