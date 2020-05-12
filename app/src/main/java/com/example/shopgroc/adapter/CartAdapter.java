package com.example.shopgroc.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopgroc.R;
import com.example.shopgroc.manager.CartManager;
import com.example.shopgroc.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CartItem> cartItemList = new ArrayList<>();



    public class CartViewHolder extends RecyclerView.ViewHolder{

        CardView itemCardView;
        ImageView itemDisplayImage;
        TextView textViewTitle , textViewQuantity , buttonRemoveItem , buttonEditItem , textViewPrice;

        public CartViewHolder(@NonNull View view) {
            super(view);
            itemCardView = view.findViewById(R.id.itemCardView);
            itemDisplayImage = view.findViewById(R.id.itemDisplayImage);
            textViewTitle = view.findViewById(R.id.textViewTitle);
            textViewQuantity = view.findViewById(R.id.textViewQuantity);
            buttonRemoveItem = view.findViewById(R.id.buttonRemoveItem);
            buttonEditItem = view.findViewById(R.id.buttonEditItem);
            textViewPrice = view.findViewById(R.id.textViewPrice);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        return new CartAdapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CartViewHolder){
            CartViewHolder cartViewHolder = (CartViewHolder) holder;
            final CartItem cartItem = cartItemList.get(position);
            Context context=cartViewHolder.itemCardView.getContext();
            Drawable drawable = context.getResources().getDrawable(cartItem.getProduct().getImage());
            cartViewHolder.itemDisplayImage.setImageDrawable(drawable);
            cartViewHolder.textViewTitle.setText(cartItem.getProduct().getTitle());
            cartViewHolder.textViewPrice.setText(cartItem.getProduct().getPrice() + "");
            cartViewHolder.textViewQuantity.setText(""+cartItem.getQuantity());

            cartViewHolder.buttonRemoveItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CartManager.getInstance().removeItem(cartItem);
                    setCartItemList(CartManager.getInstance().getItemList());
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return cartItemList.size();
    }
    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
        notifyDataSetChanged();
    }

}
