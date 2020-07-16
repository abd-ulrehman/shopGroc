package com.example.shopgroc.fragment.store;

import android.content.Context;
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
import com.example.shopgroc.controller.ProductController;
import com.example.shopgroc.interfaces.ChildToParentCallback;
import com.example.shopgroc.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * @author Abdul Rehman
 */
public class StoreDashboard extends Fragment implements View.OnClickListener, ProductAdapter.ProductClickListener {


    private static final String TAG = "storeDashbpard";
    ProductAdapter productAdapterBeverages;
    RecyclerView recyclerViewBeverages;
    LinearLayoutManager linearLayoutManagerBeverages;
    NavController navigationController;
    ChildToParentCallback varChildToParentCallback;

    FloatingActionButton addItem;

    int[] imageList = {R.drawable.cup_cake,R.drawable.drink_3,R.drawable.drink_pepsi,R.drawable.food_burger};
    String[] title = {"Cup Cake", "Dink" , "Pepsi", "Burger"};
    String[] description = {"Cup Cake", "Dink" , "Pepsi", "Burger"};
    Float[] price = {200F,400F,120F,150F};
    ProductController productController = ProductController.getInstance();
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
        productAdapterBeverages.setClickListener(this);
        recyclerViewBeverages.setLayoutManager(linearLayoutManagerBeverages);
        recyclerViewBeverages.setAdapter(productAdapterBeverages);
        addItem.setOnClickListener(this);
        getProductList();
    }

    private void getProductList(){
        ProductController.getInstance().getProduct(new ProductController.ProductCallbackListener() {
            @Override
            public void onSuccess(boolean isSuccess, List<Product> productLista) {
                productAdapterBeverages.setProductList(productLista);
            }

            @Override
            public void onFailure(boolean isFailure, Exception e) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.addItem){
            navigationController.navigate(R.id.action_storeDashboard_to_addItemToStore);
        }
    }

    @Override
    public void onProductClick(Bundle bundle) {
        navigationController.navigate(R.id.action_storeDashboard_to_itemDisplayFragment2,bundle);
    }
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        varChildToParentCallback = (ChildToParentCallback)context;
        varChildToParentCallback.hideBottomNav(true);
        varChildToParentCallback.hideStoreBottomNav(false);
    }
}
