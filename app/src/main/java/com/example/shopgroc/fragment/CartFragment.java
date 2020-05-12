package com.example.shopgroc.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopgroc.R;
import com.example.shopgroc.adapter.CartAdapter;
import com.example.shopgroc.interfaces.ChildToParentCallback;
import com.example.shopgroc.manager.CartManager;


/**
 * @author Abdul Rehman
 */
public class CartFragment extends BaseFragment implements CartManager.CartListener{

    RecyclerView recyclerViewCart;
    CartAdapter cartAdapter;
    CartManager cartManager = CartManager.getInstance();
    private ChildToParentCallback varChildToParentCallback;
    TextView textViewEmptyCart;
    Button buttonOrder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        varChildToParentCallback = (ChildToParentCallback)context;
        varChildToParentCallback.hideBottomNav(false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cartManager.setCartListener(this);
        InIt(view);
    }

    private void InIt(View view) {
        textViewEmptyCart = view.findViewById(R.id.textViewEmptyCart);
        recyclerViewCart = view.findViewById(R.id.recyclerViewCart);
        buttonOrder = view.findViewById(R.id.buttonOrder);

        cartAdapter = new CartAdapter();
        cartAdapter.setCartItemList(cartManager.getItemList());
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewCart.setAdapter(cartAdapter);
    }

    private void setEmptyView(boolean isEmpty){
        textViewEmptyCart.setVisibility(isEmpty?View.VISIBLE:View.GONE);

        buttonOrder.setAlpha(isEmpty?0.5f:1.0f);
        buttonOrder.setEnabled(!isEmpty);

    }

    @Override
    public void onCartEmpty() {
        if (!isAdded())return;
        setEmptyView(true);

    }

    @Override
    public void onCartHasData() {
        if (isAdded())return;
        setEmptyView(false);
    }
}
