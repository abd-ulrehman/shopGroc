package com.example.shopgroc.manager;

public class CartManager {
    private static CartManager instance = null;
    private CartManager(){}
    public static CartManager getInstance(){
        if(instance==null){
            instance = new CartManager();
        }
        return instance;
    }
}
