package com.example.shopgroc.manager;

import com.example.shopgroc.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance = null;
    private List<CartItem> cartItemList = new ArrayList<>();
    private CartListener cartListener=null;

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
    }
    public void removeItem(CartItem item){
        for(int i=0; i<cartItemList.size(); i++){
            if(item.getProduct().getId() == cartItemList.get(i).getProduct().getId()){
                cartItemList.remove(i);
                break;
            }
        }

        if (cartItemList.isEmpty()) cartListener.onCartEmpty();
        else cartListener.onCartHasData();
    }
    public void updateItem(CartItem item){
        for(int i=0;i<cartItemList.size(); i++){
            if(item.getProduct().getId() == cartItemList.get(i).getProduct().getId()){
                cartItemList.get(i).setQuantity(item.getQuantity());
                break;
            }
        }
    }
    public List<CartItem> getItemList(){
        return cartItemList;
    }
    public int getCartItemCount(){
        return cartItemList.size();
    }
    public boolean hasItem(CartItem item){
        for(int i=0;i<cartItemList.size(); i++){
            if(item.getProduct().getId() == cartItemList.get(i).getProduct().getId()){
                return true;
            }
        }
        return false;
    }

    public void setCartListener(CartListener cartListener){
        this.cartListener=cartListener;
    }

    public interface CartListener{
        public void onCartEmpty();
        public void onCartHasData();
    }
}
