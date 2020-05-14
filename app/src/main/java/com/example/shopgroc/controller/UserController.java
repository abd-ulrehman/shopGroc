package com.example.shopgroc.controller;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.example.shopgroc.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.shopgroc.utility.Constant.DatabaseTableKey.USER_TABLE;

public class UserController {

    private final static String TAG="UserController";

    private static  UserController userController=null;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private UserCallbackListener userCallbackListener=null;

    private UserController(){}

    public static UserController getInstance(){
        if(userController == null) userController = new UserController();
        return userController;
    }

    public void createUser(Activity activity, User user, final UserCallbackListener userCallbackListener){

        database.collection(USER_TABLE).add(user.getUserMap())
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                }).addOnFailureListener(activity, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                userCallbackListener.onFailure(true,e);
            }
        });

    }

    private void setUserCallbackListener(UserCallbackListener userCallbackListener){
        this.userCallbackListener=userCallbackListener;
    }

    public interface UserCallbackListener {
        void onSuccess(boolean isSuccess,User user);
        void onFailure(boolean isFailure,Exception e);
    }

}
