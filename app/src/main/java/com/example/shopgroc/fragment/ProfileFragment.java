package com.example.shopgroc.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.shopgroc.R;
import com.example.shopgroc.interfaces.ChildToParentCallback;
import com.example.shopgroc.utility.SharedUtility;


/**
 @author Abdul Rehman
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    ChildToParentCallback varChildToParentCallback;
    TextView buttonLogout;
    NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        varChildToParentCallback = (ChildToParentCallback)context;
        varChildToParentCallback.hideBottomNav(false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InIt(view);
    }

    private void InIt(View view) {
        buttonLogout = view.findViewById(R.id.logout);
        buttonLogout.setOnClickListener(this);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.logout){
            SharedUtility.getInstance(getContext()).logout();
            navController.navigate(R.id.action_navigation_profile_to_LoginFragment);
        }
    }
}
