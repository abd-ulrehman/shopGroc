package com.example.shopgroc.model;

import com.google.firebase.firestore.FieldValue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.shopgroc.utility.Constant.DatabaseKey.ORDER_DELIVERY_CHARGE;
import static com.example.shopgroc.utility.Constant.DatabaseKey.ORDER_DELIVERY_STATUS;
import static com.example.shopgroc.utility.Constant.DatabaseKey.ORDER_PRODUCTS;
import static com.example.shopgroc.utility.Constant.DatabaseKey.ORDER_TIME;

public class Order implements Serializable {
    private String id;
    private int orderStatus;
    private double orderDeliveryCharges;
    private FieldValue orderTime;
    private List<OrderedProduct> orderedProductList;


    public Order(){}

    public Order(String id, int orderStatus, double deliveryCharges, FieldValue orderTime,List<OrderedProduct> orderedProductList) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.orderDeliveryCharges = deliveryCharges;
        this.orderTime = orderTime;
        this.orderedProductList = orderedProductList;
    }
    public Order(int orderStatus, double deliveryCharges, FieldValue orderTime,List<OrderedProduct> orderedProductList) {
        this.orderStatus = orderStatus;
        this.orderDeliveryCharges = deliveryCharges;
        this.orderTime = orderTime;
        this.orderedProductList = orderedProductList;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getDeliveryCharges() {
        return orderDeliveryCharges;
    }

    public void setDeliveryCharges(double deliveryCharges) {
        this.orderDeliveryCharges = deliveryCharges;
    }

    public FieldValue getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(FieldValue orderTime) {
        this.orderTime = orderTime;
    }

    public List<OrderedProduct> getOrderedProductList() {
        return orderedProductList;
    }

    public void setOrderedProductList(List<OrderedProduct> orderedProductList) {
        this.orderedProductList = orderedProductList;
    }

    public void setOrder(HashMap<String,Object> orderMap){
        if(orderMap.get(ORDER_PRODUCTS)!=null)setOrderedProductList((List<OrderedProduct>) orderMap.get(ORDER_PRODUCTS));
        if(orderMap.get(ORDER_DELIVERY_CHARGE)!=null)setDeliveryCharges((Double) orderMap.get(ORDER_DELIVERY_CHARGE));
        if(orderMap.get(ORDER_DELIVERY_STATUS)!=null)setOrderStatus(Integer.parseInt(orderMap.get(ORDER_DELIVERY_STATUS).toString()) );
//        if(orderMap.get(ORDER_TIME)!=null)setOrderTime((String) orderMap.get(ORDER_TIME));
    }
    public HashMap<String,Object> getOrder(){
        HashMap<String,Object> map=new HashMap<>();
        List<HashMap<String,Object>> prodList=new ArrayList<>();

        for (OrderedProduct product:orderedProductList){
            prodList.add(product.getOrderedProductMap());
        }

        map.put(ORDER_PRODUCTS,prodList);
        map.put(ORDER_DELIVERY_CHARGE,orderDeliveryCharges);
        map.put(ORDER_DELIVERY_STATUS,orderStatus);
        map.put(ORDER_TIME,orderTime);
        return map;
    }
}
