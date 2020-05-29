package com.example.shopgroc.fragment.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopgroc.R;
import com.example.shopgroc.adapter.ProductAdapter;
import com.example.shopgroc.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Abdul Rehman
 */
public class storeDashboard extends Fragment implements View.OnClickListener {

    ProductAdapter productAdapterBeverages;
    RecyclerView recyclerViewBeverages;
    LinearLayoutManager linearLayoutManagerBeverages;
    NavController navigationController;

    FloatingActionButton addItem;

    int[] imageList = {R.drawable.cup_cake,R.drawable.drink_3,R.drawable.drink_pepsi,R.drawable.food_burger};
    String[] title = {"Cup Cake", "Dink" , "Pepsi", "Burger"};
    String[] description = {"Cup Cake", "Dink" , "Pepsi", "Burger"};
    Float[] price = {200F,400F,120F,150F};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InIt(view);
    }

    private void InIt(View view) {
        navigationController = Navigation.findNavController(view);
        recyclerViewBeverages = view.findViewById(R.id.recyclerViewBeverages);
        addItem = view.findViewById(R.id.addItem);

        linearLayoutManagerBeverages = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);
        productAdapterBeverages = new ProductAdapter();
        productAdapterBeverages.setProductList(getProductList());
        recyclerViewBeverages.setLayoutManager(linearLayoutManagerBeverages);
        recyclerViewBeverages.setAdapter(productAdapterBeverages);

        addItem.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.addItem){
            navigationController.navigate(R.id.action_storeDashboard_to_addItemToStore);
        }
    }

    private List<Product> getProductList(){
        List<Product> list = new ArrayList<>();
        for(int i=0; i<title.length;i++){
            Product product = new Product(i,title[i],description[i],price[i],imageList[i]);
            list.add(product);
        }
        return list;
    }


}
