package com.example.shopgroc.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shopgroc.R;
import com.example.shopgroc.model.Product;

import static com.example.shopgroc.utility.Constant.DataType.PRODUCT;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemDisplayFragment extends BaseFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button buttonPlus, buttonMinus;
    TextView counter;
    int itemCount, maxLength = 10 , minLength = 0;
    Product product;
    ImageView displayImage;
    TextView textViewTitle,textViewPrice,textViewDescription;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        product = getProduct();
        InIt(view);

    }

    private void InIt(View view) {
        displayImage = view.findViewById(R.id.displayImage);
        textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewPrice = view.findViewById(R.id.textViewPrice);
        textViewDescription = view.findViewById(R.id.textViewDescription);

        buttonMinus = view.findViewById(R.id.buttonMinus);
        buttonPlus = view.findViewById(R.id.buttonPlus);
        counter = view.findViewById(R.id.itemCount);

        if(product == null)return;
        Drawable drawable = view.getContext().getResources().getDrawable(product.getImage());

        displayImage.setImageDrawable(drawable);
        textViewTitle.setText(product.getTitle());
        textViewPrice.setText(product.getPrice()+" Pkr");
        textViewDescription.setText(product.getDescription());

        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.buttonPlus){
            maxLength = Integer.parseInt(counter.getText().toString());
            if(maxLength < 10) {
                itemCount = itemCount + 1;
                counter.setText(Integer.toString(itemCount));
            }
        }
        else if(id == R.id.buttonMinus){
            minLength = Integer.parseInt(counter.getText().toString());
            if(minLength >= 1) {
                itemCount = itemCount - 1;
                counter.setText(Integer.toString(itemCount));
            }
        }
    }
    public Product getProduct(){
        return (Product) getBundle().getSerializable(PRODUCT);
    }
}
