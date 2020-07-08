package com.example.shopgroc.fragment.user;

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
import com.example.shopgroc.controller.OrderController;
import com.example.shopgroc.controller.ProductController;
import com.example.shopgroc.interfaces.ChildToParentCallback;
import com.example.shopgroc.model.Order;
import com.example.shopgroc.model.Product;

import java.util.List;


/**
 * @author  Abdul Rehman
 */
public class DashboardFragment extends Fragment implements View.OnClickListener,ProductAdapter.ProductClickListener {


    LinearLayout itemCardView;
    NavController navigationController;
    ChildToParentCallback varChildToParentCallback;
    RecyclerView recyclerViewBeverages, recyclerViewDrinks;
    ProductAdapter productAdapterBeverages,productAdapterHouseHolds,productAdapterGrocery,productAdapterDrinks;
    LinearLayoutManager linearLayoutManagerBeverages, linearLayoutManagerDrinks;

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

        recyclerViewBeverages = view.findViewById(R.id.recyclerViewBeverages);
        recyclerViewDrinks = view.findViewById(R.id.recyclerViewDrinks);
        linearLayoutManagerBeverages = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);
        linearLayoutManagerDrinks = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);
        productAdapterBeverages = new ProductAdapter();
        productAdapterDrinks = new ProductAdapter();
        productAdapterBeverages.setClickListener(this);
        productAdapterDrinks.setClickListener(this);
        recyclerViewBeverages.setLayoutManager(linearLayoutManagerBeverages);
        recyclerViewDrinks.setLayoutManager(linearLayoutManagerDrinks);
        recyclerViewBeverages.setAdapter(productAdapterBeverages);
        recyclerViewDrinks.setAdapter(productAdapterDrinks);
        itemCardView.setOnClickListener(this);
        getProductList();
        getOrders();
    }

    private void getProductList(){
        ProductController.getInstance().getProduct(new ProductController.ProductCallbackListener() {
            @Override
            public void onSuccess(boolean isSuccess, List<Product> productLista) {
                productAdapterBeverages.setProductList(productLista);
                productAdapterDrinks.setProductList(productLista);
            }

            @Override
            public void onFailure(boolean isFailure, Exception e) {

            }
        });
    }

    private void getOrders(){
        OrderController.getInstance().getUserOrders(getActivity(), new OrderController.OrderCallback() {
            @Override
            public void onSuccess(boolean isSuccess, List<Order> orderList) {

            }

            @Override
            public void onFailure(boolean isFailure, Exception e) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        navigationController.navigate(R.id.action_navigation_dashboard_to_itemDisplayFragment);
    }

    @Override
    public void onProductClick(Bundle bundle) {
        navigationController.navigate(R.id.action_navigation_dashboard_to_itemDisplayFragment,bundle);
    }
}
