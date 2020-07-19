package com.example.shopgroc.fragment.rider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.shopgroc.R;
import com.example.shopgroc.fragment.user.BaseFragment;
import com.example.shopgroc.interfaces.ChildToParentCallback;
import com.example.shopgroc.model.Order;
import com.example.shopgroc.model.OrderedProduct;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.example.shopgroc.utility.Constant.DataType.USER_ORDER;


/**
 * @author Abdul Rehman
 */
public class ConfirmOrderByRider extends BaseFragment implements View.OnClickListener, OnMapReadyCallback {
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    Order order;
    private ChildToParentCallback varChildToParentCallback;
    Location currentLocation,userLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    int PLACE_PICKER_REQUEST = 1;
    TextView customerAddress,customerTotalBill,selectLocationButton;
    Button orderCancelButton,orderConfirmButton;
    GeoPoint gPoint;
    Double latitude,longitude;
    GoogleMap map;
    SupportMapFragment mapFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        return inflater.inflate(R.layout.fragment_confirm_order_by_rider, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        fetchLastLocation();
        order = getOrder();
        InIt(view);
    }

    private void fetchLastLocation() {
        if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if (location != null) {
                    currentLocation = location;
                    setMap();
                }
            }
        });
    }

    private void setMap() {
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map_rider);
        mapFragment.getMapAsync(this);
    }

    private void InIt(View view) {
        customerAddress = view.findViewById(R.id.customerAddress);
        customerTotalBill = view.findViewById(R.id.customerTotalBill);
        orderCancelButton = view.findViewById(R.id.cancelCustomerRequest);
        orderConfirmButton = view.findViewById(R.id.confirmCustomerRequest);
        customerAddress.setText(getAddress(order.getGeoPoint().getLatitude(),order.getGeoPoint().getLongitude()));
        customerTotalBill.setText(""+getOrderAmount(order.getOrderedProductList()));
        orderCancelButton.setOnClickListener(this);
        orderConfirmButton.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng location = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        LatLng userLocation = new LatLng(order.getGeoPoint().getLatitude(),order.getGeoPoint().getLongitude());
        map.addMarker(new MarkerOptions().position(location).title("My location"));
        map.addMarker(new MarkerOptions().position(userLocation).title("Customer Location"));
        map.moveCamera(CameraUpdateFactory.newLatLng(location));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(location,18));
        map.setMinZoomPreference(6.0f);
        map.setMaxZoomPreference(15.0f);
    }

    private Order getOrder(){
        return (Order) getBundle().getSerializable(USER_ORDER);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.confirmCustomerRequest){

        }
        else if(id == R.id.cancelCustomerRequest){

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();
                }
                break;
        }

    }
    public double getOrderAmount(List<OrderedProduct> orderedProductList){
        double amount=0.0;

        for (OrderedProduct prod:orderedProductList){
            amount+=(prod.getProductQuantity()*prod.getProductPrice());
        }
        return amount;
    }

    public String getAddress(double lat, double lng) {
        String add1 = null;
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add1 = add;
            Log.v("IGA", "Address" + add);
            // Toast.makeText(this, "Address=>" + add,
            // Toast.LENGTH_SHORT).show();

            // TennisAppActivity.showDialog(add);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return add1;
    }
}
