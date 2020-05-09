package com.example.shopgroc.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shopgroc.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemDisplayFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnPlus, btnNimus;
    TextView counter;
    int itemCount, maxLength = 10 , minLength = 0;

    public ItemDisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemDisplayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemDisplayFragment newInstance(String param1, String param2) {
        ItemDisplayFragment fragment = new ItemDisplayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InIt(view);
    }

    private void InIt(View view) {
        btnNimus = view.findViewById(R.id.buttonMinus);
        btnPlus = view.findViewById(R.id.buttonPlus);
        counter = view.findViewById(R.id.itemCount);
        btnPlus.setOnClickListener(this);
        btnNimus.setOnClickListener(this);

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
}
