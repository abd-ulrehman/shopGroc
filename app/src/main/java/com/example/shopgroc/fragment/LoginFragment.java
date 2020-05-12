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
import com.example.shopgroc.model.User;
import com.example.shopgroc.utility.SharedUtility;
import com.google.android.material.textfield.TextInputEditText;

import static com.example.shopgroc.utility.Constant.Messege.EMPTY_EMAIL_ERROR;
import static com.example.shopgroc.utility.Constant.Messege.EMPTY_PASSWORD_ERROR;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    Button loginButton;
    NavController navigationController;
    ChildToParentCallback varChildToParentCallback;

    TextInputEditText textViewEmail,textViewPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    private void initUI(View view) {
        textViewEmail = view.findViewById(R.id.textViewEmail);
        textViewPassword = view.findViewById(R.id.textViewPassword);
        loginButton = view.findViewById(R.id.buttonLogin);
        navigationController = Navigation.findNavController(view);
        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.buttonLogin){

            String email=null;
            String password=null;

            if (textViewEmail.getText()==null){
                textViewEmail.setError(EMPTY_EMAIL_ERROR);
                return;
            }

            if (textViewPassword.getText()==null){
                textViewPassword.setError(EMPTY_PASSWORD_ERROR);
                return;
            }

            if (textViewEmail.getText().toString().isEmpty()){
                textViewEmail.setError(EMPTY_EMAIL_ERROR);
                return;
            }

            if (textViewPassword.getText().toString().isEmpty()){
                textViewPassword.setError(EMPTY_PASSWORD_ERROR);
                return;
            }

            email=textViewEmail.getText().toString();
            password=textViewPassword.getText().toString();

            SharedUtility.getInstance(v.getContext()).setUser(new User("ABdrf","Abdul Rehman",email,"+92123456789","Fortabbas"));

            navigationController.navigate(R.id.action_loginFragment_to_homeScreenNavigation);
        }
    }
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        varChildToParentCallback = (ChildToParentCallback)context;
        varChildToParentCallback.hideBottomNav(true);
    }
}
