package com.example.shopgroc.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.shopgroc.R;
import com.example.shopgroc.interfaces.ChildToParentCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class LandingFragment extends Fragment implements View.OnClickListener {

    Button buttonSignup,buttonLogin;
    NavController navigationController;
    ChildToParentCallback varChildToParentCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_landing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);

    }

    private void initUI(View view) {
        navigationController=Navigation.findNavController(view);
        buttonSignup=view.findViewById(R.id.buttonSignup);
        buttonLogin=view.findViewById(R.id.buttonLogin);

        buttonSignup.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();

        if (id==R.id.buttonSignup){
            navigationController.navigate(R.id.action_landingFragment_to_registrationFragment);
        }else if(id==R.id.buttonLogin) {
            navigationController.navigate(R.id.action_landingFragment_to_loginFragment);
        }
    }
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        varChildToParentCallback = (ChildToParentCallback)context;
        varChildToParentCallback.hideBottomNav(true);
    }
}
