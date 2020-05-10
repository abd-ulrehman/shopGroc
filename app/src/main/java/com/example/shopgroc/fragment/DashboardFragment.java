package com.example.shopgroc.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopgroc.R;
import com.example.shopgroc.adapter.ProductAdapter;
import com.example.shopgroc.interfaces.ChildToParentCallback;
import com.example.shopgroc.model.Product;

import java.util.ArrayList;
import java.util.List;


/**
 * @author  Abdul Rehman
 */
public class DashboardFragment extends Fragment implements View.OnClickListener {


    LinearLayout itemCardView;
    NavController navigationController;
    ChildToParentCallback varChildToParentCallback;
    RecyclerView recyclerViewBeverages;
    ProductAdapter productAdapterBeverages,productAdapterHouseHolds,productAdapterGrocery;
    LinearLayoutManager linearLayoutManagerBeverages;
//    private List<Product> productList = new ArrayList<>();
    int[] imageList = {R.drawable.cup_cake,R.drawable.drink_3,R.drawable.drink_pepsi,R.drawable.food_burger};
    String[] title = {"Cup Cake", "Dink" , "Pepsi", "Burger"};
    String[] description = {"Cup Cake", "Dink" , "Pepsi", "Burger"};
    Float[] price = {200F,400F,120F,150F};




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        varChildToParentCallback = (ChildToParentCallback)context;
        varChildToParentCallback.hideBottomNav(false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemCardView = view.findViewById(R.id.itemId);
        InIt(view);
    }

    private void InIt(View view) {
        navigationController = Navigation.findNavController(view);
        itemCardView.setOnClickListener(this);
        recyclerViewBeverages = view.findViewById(R.id.recyclerViewBeverages);
        linearLayoutManagerBeverages = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);
        productAdapterBeverages = new ProductAdapter();
        productAdapterBeverages.setProductList(getProductList());
        recyclerViewBeverages.setLayoutManager(linearLayoutManagerBeverages);
        recyclerViewBeverages.setAdapter(productAdapterBeverages);
    }

    private List<Product> getProductList(){
        List<Product> list = new ArrayList<>();
        for(int i=0; i<title.length;i++){
            Product product = new Product(i,title[i],description[i],price[i],imageList[i]);
            list.add(product);
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        navigationController.navigate(R.id.action_navigation_dashboard_to_itemDisplayFragment);
    }

}
