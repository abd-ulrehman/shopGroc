package com.example.shopgroc.fragment.user;

import com.example.shopgroc.model.User;

public class MessageEventBus {
    User user;
    public MessageEventBus(User user){
        this.user = user;
    }
    public User getBusData(){
        return user;
    }
}
