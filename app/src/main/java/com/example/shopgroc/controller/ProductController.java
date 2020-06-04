package com.example.shopgroc.controller;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.shopgroc.model.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.shopgroc.utility.Constant.DatabaseTableKey.PRODUCT_TABLE;

public class ProductController {

    private static String TAG = "productController";

    private ProductCallbackListener productCallbackListener=null;
    private static  ProductController productController=null;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    private ProductController(){}

    public static ProductController getInstance(){
        if(productController == null) productController = new ProductController();
        return productController;
    }
    public void addProduct(Product product){
        database.collection(PRODUCT_TABLE).document().set(product.getProductMap())
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.i(TAG,"Product is: "+task.getResult());
                }
            }
        });
    }
    public void getProduct(final ProductCallbackListener productCallbackListener){
        database.collection(PRODUCT_TABLE).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Product> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Product product = new Product();
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        HashMap<String,Object> obj = new HashMap<>(document.getData());
                        product.setProductMap(obj);
                        list.add(product);
                    }
                    if (productCallbackListener!= null)productCallbackListener.onSuccess(true,list);
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                    if (productCallbackListener!=null)productCallbackListener.onFailure(true,task.getException());
                }
            }
        });
    }
    public void setProductCallbackListener(ProductCallbackListener productCallbackListener){
        this.productCallbackListener=productCallbackListener;
    }
    public interface ProductCallbackListener {
        void onSuccess(boolean isSuccess,List<Product> productLista);
        void onFailure(boolean isFailure,Exception e);
    }
}
