package com.example.shopgroc.fragment.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.shopgroc.R;


/**
 @author Abdul Rehaman
 */
public class addItemToStore extends Fragment implements View.OnClickListener {

    NavController navigationController;

    Button btnAddStoreItem;
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
        storeItemImage = (ImageView) view.findViewById(R.id.storeItemImage);
        storeItemImage.setOnClickListener(this);


        btnAddStoreItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnAddStoreItem){
            navigationController.navigate(R.id.action_addItemToStore_to_storeDashboard);
        }
    }

}
