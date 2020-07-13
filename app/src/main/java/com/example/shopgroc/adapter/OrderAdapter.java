package com.example.shopgroc.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopgroc.R;
import com.example.shopgroc.model.Order;
import com.example.shopgroc.model.OrderedProduct;

import java.util.ArrayList;
import java.util.List;

import static com.example.shopgroc.utility.Constant.DeliveryStatus.ORDER_PENDING;
import static com.example.shopgroc.utility.Constant.DeliveryStatus.ORDER_PENDING_STATUS;
import static com.example.shopgroc.utility.Utility.getDateAtTime;

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  static  final String TAG="OrderAdapter";

    private List<Order> orderList = new ArrayList<>();
    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView orderCardView;
        TextView orderDetail,orderStatus,orderDate,orderPrice,serialNo;
        public MyViewHolder(@NonNull View view) {
            super(view);
            orderCardView = view.findViewById(R.id.orderCardView);
            orderStatus = view.findViewById(R.id.orderStatus);
            orderDate = view.findViewById(R.id.orderDate);
            orderPrice = view.findViewById(R.id.orderPrice);
            serialNo = view.findViewById(R.id.serialNo);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent,false);
        return new OrderAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OrderAdapter.MyViewHolder myViewHolder = (OrderAdapter.MyViewHolder) holder;
        final Order order = orderList.get(position);
        Log.i(TAG,"Order id: "+order.getId());
        Log.i(TAG,"Order time: "+order.getOrderTime().toString());
//        Log.i(TAG,"Order time: "+order.getOrderTime());
        myViewHolder.orderPrice.setText(""+getOrderAmount(order.getOrderedProductList()));

        long milliSecond=order.getOrderTime().getSeconds()*1000;

        String formattedDate=getDateAtTime(milliSecond);

        Log.i(TAG,"formatted date is : "+formattedDate);

        myViewHolder.orderDate.setText(formattedDate);
        myViewHolder.orderStatus.setText("Status: " + getOrderStatus(order.getOrderStatus()));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
    public void setOrderList(List<Order> orderList){
        this.orderList = orderList;
        notifyDataSetChanged();
    }

    public double getOrderAmount(List<OrderedProduct> orderedProductList){
        double amount=0.0;

        for (OrderedProduct prod:orderedProductList){
            amount+=(prod.getProductQuantity()*prod.getProductPrice());
        }

        return amount;
    }

    public String getOrderStatus(int status){
        if (status==ORDER_PENDING)return ORDER_PENDING_STATUS;
        else return ORDER_PENDING_STATUS;
    }

}
