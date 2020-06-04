package com.example.shopgroc.fragment.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.shopgroc.R;
import com.example.shopgroc.controller.ProductController;
import com.example.shopgroc.model.Product;


/**
 @author Abdul Rehaman
 */
public class AddItemToStore extends Fragment implements View.OnClickListener {

    NavController navigationController;
    ProductController productController = ProductController.getInstance();
    Button btnAddStoreItem;
    EditText storeItemTitle,storeItemTPrice,storeItemDescription;
    Spinner storeItemCategory;
    private ImageView storeItemImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_item_to_store, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InIt(view);
    }

    private void InIt(View view) {


        navigationController = Navigation.findNavController(view);
        btnAddStoreItem = view.findViewById(R.id.btnAddStoreItem);
        storeItemTitle = view.findViewById(R.id.storeItemTitle);
        storeItemTPrice = view.findViewById(R.id.storeItemTPrice);
        storeItemCategory = view.findViewById(R.id.storeItemCategory);
        storeItemDescription = view.findViewById(R.id.storeItemDescription);

        storeItemImage = (ImageView) view.findViewById(R.id.storeItemImage);
        storeItemImage.setOnClickListener(this);


        btnAddStoreItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnAddStoreItem){
            addProduct();
            navigationController.navigate(R.id.action_addItemToStore_to_storeDashboard);
        }
    }

    private void addProduct() {
        String title = "";
        String description = "";
        float price = 0F;
        String category = "";

        title = storeItemTitle.getText().toString();
        description = storeItemDescription.getText().toString();
        price = Float.parseFloat(storeItemTPrice.getText().toString());
        category = storeItemCategory.getSelectedItem().toString();

        Product product = new Product(title,price,description,category);
        productController.addProduct(product);
    }

}
