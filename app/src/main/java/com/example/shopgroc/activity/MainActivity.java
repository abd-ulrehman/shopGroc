package com.example.shopgroc.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.shopgroc.R;
import com.example.shopgroc.interfaces.ChildToParentCallback;
import com.example.shopgroc.manager.CartManager;
import com.example.shopgroc.utility.SharedUtility;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements ChildToParentCallback, CartManager.CartItemCountListener {

    private static final String TAG = "MainActivity";
    BottomNavigationView bottomNavigation;
    NavController navController;
    SharedUtility sharedUtility;
    TextView textViewCount;
    int mCartItemCount = 10;
    View badge;
    CartManager cartManager= CartManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedUtility=SharedUtility.getInstance(this);
        cartManager.setCartItemCountListener(this);
        bottomNavigation=findViewById(R.id.bottomNavigation);

        //Setting badge for item count
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) bottomNavigation.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(0);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;
        badge = LayoutInflater.from(this).inflate(R.layout.custom_action_item_layout, itemView, true);
        textViewCount=badge.findViewById(R.id.textViewCount);

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

    @Override
    public void onCountUpdate(int itemCount) {
        if (textViewCount!=null){
            if (itemCount>0){
                textViewCount.setVisibility(VISIBLE);
                textViewCount.setText(""+itemCount);
            }else{
                textViewCount.setVisibility(GONE);
            }
        }else {
            Log.i(TAG,"textViewCount is null");
        }
    }
}
