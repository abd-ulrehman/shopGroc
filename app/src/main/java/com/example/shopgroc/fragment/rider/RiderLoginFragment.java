package com.example.shopgroc.fragment.rider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.shopgroc.R;


/**
    @author Abdul Rehman
 */
public class RiderLoginFragment extends Fragment implements View.OnClickListener {
    Button buttonRiderLogin;
    TextView signupRider;
    NavController navigationController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rider_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InInt(view);
    }

    private void InInt(View view) {
        buttonRiderLogin = view.findViewById(R.id.buttonRiderLogin);
        signupRider = view.findViewById(R.id.signupRider);
        navigationController = Navigation.findNavController(view);

        buttonRiderLogin.setOnClickListener(this);
        signupRider.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnRiderLogin){
            navigationController.navigate(R.id.action_riderLoginFragment_to_riderHomeScreenNavigation);
        }
        else if(id == R.id.signupRider){
            navigationController.navigate(R.id.action_riderLoginFragment_to_riderSignupFragment);
        }
    }
}
