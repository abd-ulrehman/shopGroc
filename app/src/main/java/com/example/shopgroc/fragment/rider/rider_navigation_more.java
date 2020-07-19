package com.example.shopgroc.fragment.rider;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.shopgroc.R;
import com.example.shopgroc.fragment.user.BaseFragment;
import com.example.shopgroc.interfaces.ChildToParentCallback;


/**
 * @author Abdul Rehman
 */
public class rider_navigation_more extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rider_navigation_more, container, false);
    }
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ChildToParentCallback varChildToParentCallback = (ChildToParentCallback) context;
        varChildToParentCallback.hideBottomNav(true);
        varChildToParentCallback.hideStoreBottomNav(true);
        varChildToParentCallback.hideRiderBottomNav(false);
    }
}
