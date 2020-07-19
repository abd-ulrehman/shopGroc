package com.example.shopgroc.fragment.store;

import android.os.Bundle;
import android.util.Log;
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
import com.example.shopgroc.model.OrderedProduct;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.shopgroc.utility.Constant.DataType.USER_ORDER;
import static com.example.shopgroc.utility.Constant.DatabaseTableKey.STORE_ORDER_TABLE;
import static com.example.shopgroc.utility.Utility.getDate;


/**
 * @author Abdul Rehman
 */
public class openUserOrder extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "openUserOrder";
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

        long milliSecond=order.getOrderTime().getSeconds()*1000;
        String date = getDate(milliSecond);

        customerOrderNo.setText(order.getOrderNumber());
        customerOrderDate.setText(date);
        customerOrderTotalAmount.setText(""+getOrderAmount(order.getOrderedProductList()));
        customerOrderNo.setText(order.getOrderNumber());

        cancelUserOrderButton.setOnClickListener(this);
        confirmUserOrderButton.setOnClickListener(this);

    }
    public double getOrderAmount(List<OrderedProduct> orderedProductList){
        double amount=0.0;

        for (OrderedProduct prod:orderedProductList){
            amount+=(prod.getProductQuantity()*prod.getProductPrice());
        }
        return amount;
    }

    private Order getOrder() {
        return (Order) getBundle().getSerializable(USER_ORDER);
    }

    @Override
    public void onClick(View v) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        int id = v.getId();
        if(id == R.id.cancelUserOrderButton){
            Log.i(TAG, "onClick: Order Canceled");
            Map<String, Object> docData = new HashMap<>();
            docData.put("deliveryStatus", 3);
            Log.i(TAG, "onClick: "+ order.getUserId());
            database.collection(STORE_ORDER_TABLE).document(order.getId()).update(docData);
        }
        if(id == R.id.confirmUserOrderButton){
            Log.i(TAG, "onClick: Order Canceled");
            Map<String, Object> docData = new HashMap<>();
            docData.put("deliveryStatus", 2);
            Log.i(TAG, "onClick: "+ order.getUserId());
            database.collection(STORE_ORDER_TABLE).document(order.getId()).update(docData);
        }
    }
}
