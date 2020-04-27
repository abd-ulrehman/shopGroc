package com.example.shopgroc.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.shopgroc.R;
import com.example.shopgroc.interfaces.ChildToParentCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements ChildToParentCallback {

    BottomNavigationView bottomNavigation;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation=findViewById(R.id.bottomNavigation);
        navController= Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigation, navController);
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
        bottomNavigation.setVisibility(hide? GONE:VISIBLE);
    }
}
