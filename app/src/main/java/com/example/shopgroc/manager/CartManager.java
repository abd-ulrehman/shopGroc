package com.example.shopgroc.manager;

import android.util.Log;

import com.example.shopgroc.model.CartItem;
import com.example.shopgroc.model.Order;
import com.example.shopgroc.model.OrderedProduct;

import java.util.ArrayList;
import java.util.List;

import static com.example.shopgroc.utility.Constant.DeliveryStatus.ORDER_PENDING;
import static com.google.firebase.firestore.FieldValue.serverTimestamp;

public class CartManager {
    private static CartManager instance = null;
    private List<CartItem> cartItemList = new ArrayList<>();
    private CartListener cartListener=null;
    private CartItemCountListener cartItemCountListener;

    private CartManager(){}
    public static CartManager getInstance(){
        if(instance==null){
            instance = new CartManager();
        }
        return instance;
    }
    public void addToCart(CartItem item){
        cartItemList.add(item);
        if (cartListener!=null) cartListener.onCartHasData();
        if (cartItemCountListener!=null) cartItemCountListener.onCountUpdate(getCartItemCount());
    }
    public void removeItem(CartItem item){
        for(int i=0; i<cartItemList.size(); i++){
            if(item.getProduct().getId().equals(cartItemList.get(i).getProduct().getId())){
                cartItemList.remove(i);
                break;
            }
        }

        if (cartItemList.isEmpty()) cartListener.onCartEmpty();
        else cartListener.onCartHasData();
        if (cartItemCountListener!=null) cartItemCountListener.onCountUpdate(getCartItemCount());
    }
    public void updateItem(CartItem item){
        for(int i=0;i<cartItemList.size(); i++){
            if(item.getProduct().getId().equals(cartItemList.get(i).getProduct().getId())){
                cartItemList.get(i).setQuantity(item.getQuantity());
                break;
            }
        }
        if (cartItemCountListener!=null) cartItemCountListener.onCountUpdate(getCartItemCount());
    }
    public List<CartItem> getItemList(){
        return cartItemList;
    }
    public int getCartItemCount(){
        return cartItemList.size();
    }
    public boolean hasItem(CartItem item){
        for(int i=0;i<cartItemList.size(); i++){
            if(item.getProduct().getId().equals(cartItemList.get(i).getProduct().getId())){
                Log.i("Cart", "hasItem: " + cartItemList.get(i).getProduct().getTitle() + "as same as " + item.getProduct().getTitle());
                return true;
            }
        }
        return false;
    }

    public Order getOrderData(){

        List<OrderedProduct> orderedProductList=getOrderedProductList();
        return new Order(ORDER_PENDING, 50,serverTimestamp(),orderedProductList);

    }

    public List<OrderedProduct> getOrderedProductList(){
        List<OrderedProduct> list=new ArrayList<>();

        if (getItemList()!=null && !getItemList().isEmpty()){

            for (int i=0;i<getItemList().size();i++){
                CartItem item=getItemList().get(i);
                list.add(new OrderedProduct(item.getProduct().getId(),item.getQuantity(),item.getProduct().getPrice()));
            }

        }
        return list;
    }

    public void setCartListener(CartListener cartListener){
        this.cartListener=cartListener;
    }
    public void setCartItemCountListener(CartItemCountListener cartItemCountListener){this.cartItemCountListener = cartItemCountListener;}

    public interface CartListener{
        void onCartEmpty();
        void onCartHasData();
    }
    public interface CartItemCountListener{
        void onCountUpdate(int itemCount);
    }
}
