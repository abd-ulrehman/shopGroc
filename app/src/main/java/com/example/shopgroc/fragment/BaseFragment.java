package com.example.shopgroc.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    public Bundle getBundle(){
        if(getArguments()!=null){
            return getArguments();
        }
        return null;
    }
}
