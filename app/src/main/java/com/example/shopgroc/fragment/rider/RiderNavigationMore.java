package com.example.shopgroc.fragment.rider;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.example.shopgroc.R;
import com.example.shopgroc.fragment.user.BaseFragment;
import com.example.shopgroc.interfaces.ChildToParentCallback;
import com.example.shopgroc.model.User;


/**
 * @author Abdul Rehman
 */
public class RiderNavigationMore extends BaseFragment implements ChildToParentCallback, View.OnClickListener {
    ChildToParentCallback varChildToParentCallback;
    TextView riderName , logoutRider;
    User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rider_navigation_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InIt(view);
    }

    private void InIt(View view) {
        riderName = view.findViewById(R.id.riderName);
        logoutRider = view.findViewById(R.id.logoutRider);
        logoutRider.setOnClickListener(this);
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        varChildToParentCallback = (ChildToParentCallback) context;
        varChildToParentCallback.hideBottomNav(true);
        varChildToParentCallback.hideStoreBottomNav(true);
        varChildToParentCallback.hideRiderBottomNav(false);
    }

    @Override
    public void hideBottomNav(boolean hide) {
        hideBottomNav(true);
    }

    @Override
    public void hideStoreBottomNav(boolean hide) {
        hideStoreBottomNav(true);
    }

    @Override
    public void hideRiderBottomNav(boolean hide) {
        hideRiderBottomNav(false);
    }

    @Override
    public void onClick(View v) {
        Navigation.findNavController(v).navigate(R.id.landingFragment);
    }
}
