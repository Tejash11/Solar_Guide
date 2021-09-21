package com.example.solarguide;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewOnReceiveContentListener;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Input_fragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Input_fragment3 extends Fragment {


    public int residential_check, commercial_check, industrial_check = 0;
    public int triangular_check, flat_check, shade_check, ground_check = 0;
    public int length, width = 0;
    public String latitude, longitude;
    public LocationRequest locationRequest;

    EditText length_et, width_et;
    LinearLayout location_fetched;
    TextView fetchlocation_btn;

    LocationManager locationManager;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Input_fragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Input_fragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static Input_fragment3 newInstance(String param1, String param2) {
        Input_fragment3 fragment = new Input_fragment3();
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
        View v = inflater.inflate(R.layout.fragment_input_fragment3, container, false);
        Bundle bundle = getArguments();
        residential_check = bundle.getInt("residential_check");
        commercial_check = bundle.getInt("commercial_check");
        industrial_check = bundle.getInt("industrial_check");
        triangular_check = bundle.getInt("triangular_check");
        flat_check = bundle.getInt("flat_check");
        ground_check = bundle.getInt("ground_check");
        shade_check = bundle.getInt("shade_check");
        length = bundle.getInt("length");
        width = bundle.getInt("width");


        // length_et = (EditText) v.findViewById(R.id.length);
        // width_et = (EditText) v.findViewById(R.id.width);
        location_fetched = (LinearLayout) v.findViewById(R.id.location_fetched);
        fetchlocation_btn = (TextView) v.findViewById(R.id.fetch_location);

      /*  String l = length_et.getText().toString();
        length = Integer.parseInt(l);

        String w = width_et.getText().toString();
        width = Integer.parseInt(w);*/


        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }


        fetchlocation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getcurrentlocation();



            }
        });









        return v;
    }


    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled = false;

        if (locationManager == null) {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;

    }



    public void getcurrentlocation()
    {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnabled()) {

                    LocationServices.getFusedLocationProviderClient(getContext())
                            .requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);

                                    LocationServices.getFusedLocationProviderClient(getActivity())
                                            .removeLocationUpdates(this);

                                    if (locationResult != null && locationResult.getLocations().size() >0){

                                        int index = locationResult.getLocations().size() - 1;
                                        double latitude = locationResult.getLocations().get(index).getLatitude();
                                        double longitude = locationResult.getLocations().get(index).getLongitude();

                                        Toast.makeText(getContext(), latitude+ ","+ longitude, Toast.LENGTH_SHORT).show();
                                        //AddressText.setText("Latitude: "+ latitude + "\n" + "Longitude: "+ longitude);
                                    }
                                    else
                                    {
                                        Toast.makeText(getContext(), latitude+ ","+ longitude, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, Looper.getMainLooper());


                } else {

                    Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();

                }

            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }









    }

    @Override
    public void onResume() {
        super.onResume();

        if(getView() == null){
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

                    Bundle bundle = new Bundle();
                    bundle.putInt("residential_check", residential_check);
                    bundle.putInt("commercial_check", commercial_check);
                    bundle.putInt("industrial_check", industrial_check);
                    bundle.putInt("triangular_check", triangular_check);
                    bundle.putInt("flat_check", flat_check);
                    bundle.putInt("ground_check", ground_check);
                    bundle.putInt("shade_check", shade_check);
                    bundle.putInt("length", length);
                    bundle.putInt("width", width);
                    bundle.putString("latitude", "0");
                    bundle.putString("longitude","0");


                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new Input_fragment2();
                    myFragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.input_fragment1, myFragment).addToBackStack(null).commit();


                    return true;
                }
                return false;
            }
        });
    }





}