package com.example.shopgroc.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.shopgroc.R;
import com.example.shopgroc.interfaces.ChildToParentCallback;
import com.example.shopgroc.utility.SharedUtility;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements ChildToParentCallback {

    private static final String TAG = "MainActivity";
    BottomNavigationView bottomNavigation;
    NavController navController;
    SharedUtility sharedUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedUtility=SharedUtility.getInstance(this);
        bottomNavigation=findViewById(R.id.bottomNavigation);
        navController= Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigation, navController);

        if (sharedUtility.isLoggedIn()){

            Log.i(TAG,"User is loggedIn");
            //TODO: Go to DashboardFragment
//            navController.navigate(R.id.action_loginFragment_to_homeScreenNavigation);
            Log.i(TAG,"going to dashboard");
            navController.navigate(R.id.homeScreenNavigation);
//            bottomNavigation.setVisibility(View.VISIBLE);
        }else{
            Log.i(TAG,"User is not loggedIn");
            Log.i(TAG,"going to landing page");
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hideBottomNavigation();
    }

    private void hideBottomNavigation() {
        if(navController.getCurrentDestination().getId() == R.id.registrationFragment ||
                navController.getCurrentDestination().getId() == R.id.loginFragment ||
                navController.getCurrentDestination().getId() == R.id.landingFragment){
            bottomNavigation.setVisibility(GONE);
        }
        else{
            bottomNavigation.setVisibility(VISIBLE);
        }
    }

    @Override
    public void hideBottomNav(boolean hide) {
        Log.i(TAG,"Going to hide: " + hide);
        if(sharedUtility.isLoggedIn())hide=false;
        bottomNavigation.setVisibility(hide? GONE:VISIBLE);
    }
}
