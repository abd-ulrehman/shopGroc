package com.example.shopgroc.controller;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.shopgroc.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.shopgroc.utility.Constant.DatabaseTableKey.USER_TABLE;

public class UserController {

    private final static String TAG="UserController";

    private static  UserController userController=null;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
//    private UserCallbackListener userCallbackListener=null;

    private UserController(){}

    public static UserController getInstance(){
        if(userController == null) userController = new UserController();
        return userController;
    }

    public void createUser(final Activity activity, final User user, final UserCallbackListener userCallbackListener){

        database.collection(USER_TABLE).document(user.getId()).set(user.getUserMap())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
//                        Log.i(TAG,"User is: "+task.getResult());
//                    String userId=documentReference.getId();
                    getUser(activity, user.getId(),userCallbackListener);
                }else{
                    userCallbackListener.onFailure(true,task.getException());
                }
            }
        });

    }
    public void getUser(Activity activity, String userId, final UserCallbackListener userCallbackListener){
        if(userId != null){
            final DocumentReference documentReference = database.collection(USER_TABLE).document(userId);
//            Log.i(TAG,"document Reference is --------- " + documentReference);

            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document!=null && document.exists() && document.getData()!=null) {
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            User user=new User();
                            user.setId(document.getId());
                            user.setUserMap(document.getData());
                            if (userCallbackListener!=null)userCallbackListener.onSuccess(true,user);
                        } else {
                            Log.i(TAG,"document is null ");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                        if (userCallbackListener!=null)userCallbackListener.onFailure(true,task.getException());
                    }
                }
            });
        }
    }

    public interface UserCallbackListener {
        void onSuccess(boolean isSuccess,User user);
        void onFailure(boolean isFailure,Exception e);
    }

}
