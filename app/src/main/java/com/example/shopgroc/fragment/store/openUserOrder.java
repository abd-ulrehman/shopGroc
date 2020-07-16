package com.example.shopgroc.fragment.store;

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
import com.example.shopgroc.adapter.OrderedProductAdapterStore;
import com.example.shopgroc.fragment.user.BaseFragment;
import com.example.shopgroc.model.Order;

import static com.example.shopgroc.utility.Constant.DataType.ORDER;


/**
 * @author Abdul Rehman
 */
public class openUserOrder extends BaseFragment {
    Order order;
    TextView customerOrderNo,customerOrderDate,customerOrderTotalAmount;
    Button cancelUserOrderButton,confirmUserOrderButton;
    RecyclerView recyclerViewCustomerOrderDetail;
    OrderedProductAdapterStore orderedProductAdapterStore;
    LinearLayoutManager linearLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_open_user_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        order = getOrder();
        InIt(view);
    }

    private void InIt(View view) {
        customerOrderTotalAmount= view.findViewById(R.id.customerOrderTotalAmount);
        customerOrderNo= view.findViewById(R.id.customerOrderNo);
        customerOrderDate= view.findViewById(R.id.customerOrderDate);
        recyclerViewCustomerOrderDetail = view.findViewById(R.id.recyclerViewCustomerOrderDetail);
        cancelUserOrderButton= view.findViewById(R.id.cancelUserOrderButton);
        confirmUserOrderButton= view.findViewById(R.id.confirmUserOrderButton);

        recyclerViewCustomerOrderDetail.setNestedScrollingEnabled(false);
        orderedProductAdapterStore = new OrderedProductAdapterStore();
        orderedProductAdapterStore.setOrderList(order.getOrderedProductList());
        linearLayoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewCustomerOrderDetail.setLayoutManager(linearLayoutManager);
        recyclerViewCustomerOrderDetail.setAdapter(orderedProductAdapterStore);
    }

    private Order getOrder() {
        return (Order) getBundle().getSerializable(ORDER);
    }
}
