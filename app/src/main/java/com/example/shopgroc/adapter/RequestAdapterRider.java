package com.example.shopgroc.adapter;

import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopgroc.R;
import com.example.shopgroc.model.Order;
import com.example.shopgroc.model.OrderedProduct;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.example.shopgroc.utility.Constant.DataType.USER_ORDER;
import static com.example.shopgroc.utility.Constant.DatabaseTableKey.STORE_ORDER_TABLE;
import static com.example.shopgroc.utility.Constant.DeliveryStatus.ORDER_CANCEL_STATUS;
import static com.example.shopgroc.utility.Constant.DeliveryStatus.ORDER_COMPLETE;
import static com.example.shopgroc.utility.Constant.DeliveryStatus.ORDER_COMPLETE_STATUS;
import static com.example.shopgroc.utility.Constant.DeliveryStatus.ORDER_CONFIRMED;
import static com.example.shopgroc.utility.Constant.DeliveryStatus.ORDER_CONFIRMED_STATUS;
import static com.example.shopgroc.utility.Constant.DeliveryStatus.ORDER_PENDING;
import static com.example.shopgroc.utility.Constant.DeliveryStatus.ORDER_PENDING_STATUS;
import static com.example.shopgroc.utility.Utility.getDate;

public class RequestAdapterRider extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
private List<Order> orderList = new ArrayList<>();
        NavController navController;
    private Context context;
    Order order = null;


    public class MyViewHolder extends RecyclerView.ViewHolder {
    Button pickOrder, cancelOrder;
    TextView customerOrderId,customerOrderDate,customerOrderPrice,buttonCustomerViewOrder, customerOrderStoreName,customerLocation;
    public MyViewHolder(@NonNull View view) {
        super(view);
        customerOrderId = view.findViewById(R.id.customerOrderIdForRider);
        customerOrderDate = view.findViewById(R.id.customerOrderDateForRider);
        customerOrderPrice = view.findViewById(R.id.customerOrderPriceForRider);
        customerOrderStoreName = view.findViewById(R.id.customerOrderStoreName);
        customerLocation = view.findViewById(R.id.customerLocation);
        pickOrder = view.findViewById(R.id.pickOrder);
        cancelOrder = view.findViewById(R.id.cancelOrder);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_customer_order_for_rider,parent,false);
        context = parent.getContext();
        return new RequestAdapterRider.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof RequestAdapterRider.MyViewHolder){
            RequestAdapterRider.MyViewHolder myViewHolder = (RequestAdapterRider.MyViewHolder) holder;
            order = orderList.get(position);
            long milliSecond=order.getOrderTime().getSeconds()*1000;
            String date = getDate(milliSecond);
            myViewHolder.customerOrderDate.setText(date);
            myViewHolder.customerOrderId.setText(order.getOrderNumber()+"");
            myViewHolder.customerOrderPrice.setText(""+getOrderAmount(order.getOrderedProductList()));
            myViewHolder.customerOrderStoreName.setText("Rainbow");
            myViewHolder.customerLocation.setText(getAddress(order.getGeoPoint().getLatitude(),order.getGeoPoint().getLongitude()));
//            Order order1 = UserController.getInstance().getCustomerData(order.getUserId());


            if(order.getOrderStatus() == 0){
                myViewHolder.customerOrderStoreName.setTextColor(Color.rgb(227, 212, 48));
            }if(order.getOrderStatus() == 1){
                myViewHolder.customerOrderStoreName.setTextColor(Color.rgb(32, 199, 54));
            }if(order.getOrderStatus() == 2){
                myViewHolder.customerOrderStoreName.setTextColor(Color.rgb(133, 155, 255));
            }if(order.getOrderStatus() == 3){
                myViewHolder.customerOrderStoreName.setTextColor(Color.rgb(186, 44, 39));
            }
            myViewHolder.cancelOrder.setOnClickListener(this);
            myViewHolder.pickOrder.setOnClickListener(this);
//            myViewHolder.buttonCustomerViewOrder.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable(USER_ORDER,order);
////                    Navigation.findNavController(v).navigate(R.id.action_store_navigation_orders_to_openUserOrder,bundle);
//                }
//            });
        }
    }
    public String getAddress(double lat, double lng) {
    String add1 = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add1 = add;
            Log.v("IGA", "Address" + add);
            // Toast.makeText(this, "Address=>" + add,
            // Toast.LENGTH_SHORT).show();

            // TennisAppActivity.showDialog(add);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return add1;
    }

    @Override
    public void onClick(View v) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        int id = v.getId();
        if(id == R.id.cancelOrder){
            Map<String, Object> docData = new HashMap<>();
            docData.put("deliveryStatus", 3);
            database.collection(STORE_ORDER_TABLE).document(order.getId()).update(docData);
            Navigation.findNavController(v).navigate(R.id.rider_navigation_request);
        }
        else if(id == R.id.pickOrder){
            Bundle bundle = new Bundle();
            bundle.putSerializable(USER_ORDER,order);
            Navigation.findNavController(v).navigate(R.id.action_rider_navigation_request_to_confirmOrderByRider,bundle);
        }
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
        else if(status==ORDER_COMPLETE) return ORDER_COMPLETE_STATUS;
        else if(status==ORDER_CONFIRMED) return ORDER_CONFIRMED_STATUS;
        else return ORDER_CANCEL_STATUS;
    }
}

