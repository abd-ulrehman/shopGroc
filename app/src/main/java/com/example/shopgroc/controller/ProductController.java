package com.example.shopgroc.controller;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.shopgroc.model.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

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
    public void addProduct(Activity activity,Product product){
        createProduct(activity, product, new ProductCallbackListener() {
            @Override
            public void onSuccess(boolean isSuccess, Product product) {
                if (productCallbackListener!=null)productCallbackListener.onSuccess(isSuccess,product);
            }

            @Override
            public void onFailure(boolean isFailure, Exception e) {
                if(productCallbackListener!=null)productCallbackListener.onFailure(isFailure,e);
            }
        });

    }

    private void createProduct(Activity activity, Product product, ProductCallbackListener productCallbackListener) {
        database.collection(PRODUCT_TABLE).document(product.getId()).set(product.getProductMap())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.i(TAG,"Product is: "+task.getResult());
                        }
                    }
                });
    }

    public void getProduct(){

    }
    public void setProductCallbackListener(ProductCallbackListener productCallbackListener){
        this.productCallbackListener=productCallbackListener;
    }
    public interface ProductCallbackListener{
        void onSuccess(boolean isSuccess,Product product);
        void onFailure(boolean isFailure,Exception e);
    }
}
