package com.example.shopgroc.activity;

import android.app.Application;
import android.content.Context;

import com.example.shopgroc.utility.SharedUtility;

public class ShopGrocApplication extends Application {

    public static ShopGrocApplication instance;
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
        SharedUtility.getInstance(context);
    }
}
