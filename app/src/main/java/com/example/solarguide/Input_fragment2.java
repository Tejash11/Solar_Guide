package com.example.solarguide;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Input_fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Input_fragment2 extends Fragment {

    public ImageView nextbtn;

    public int residential_check , commercial_check, industrial_check =0;

    LinearLayout triangular, flat, shade, ground;
    public int triangular_check, flat_check, shade_check, ground_check=0;
    public float length, width=0;
    public String latitude , longitude;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Input_fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Input_fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Input_fragment2 newInstance(String param1, String param2) {
        Input_fragment2 fragment = new Input_fragment2();
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
        View v = inflater.inflate(R.layout.fragment_input_fragment2, container, false);

        Bundle bundle = getArguments();
        residential_check = bundle.getInt("residential_check");
        commercial_check = bundle.getInt("commercial_check");
        industrial_check = bundle.getInt("industrial_check");
        triangular_check = bundle.getInt("triangular_check");
        flat_check = bundle.getInt("flat_check");
        ground_check = bundle.getInt("ground_check");
        shade_check = bundle.getInt("shade_check");
        width = bundle.getFloat("width");
        length = bundle.getFloat("length");
        latitude = bundle.getString("latitude");
        longitude = bundle.getString("longitude");


        triangular = (LinearLayout) v.findViewById(R.id.triangular);
        shade = (LinearLayout) v.findViewById(R.id.shade);
        flat = (LinearLayout) v.findViewById(R.id.flat);
        ground = (LinearLayout) v.findViewById(R.id.ground);
        nextbtn = (ImageView) v.findViewById(R.id.nextbtn);



        if(triangular_check == 1)
        {
            triangular_check = 1;
            flat_check = 0;
            ground_check =0;
            shade_check =0;


            triangular.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
            ground.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
            flat.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
            shade.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));

        }
        else if(flat_check == 1)
        {
            triangular_check = 0;
            flat_check = 1;
            ground_check =0;
            shade_check =0;


            flat.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
            ground.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
            triangular.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
            shade.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
        }
        else if(ground_check == 1)
        {
            triangular_check = 0;
            flat_check = 0;
            ground_check =1;
            shade_check =0;


            ground.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
            triangular.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
            flat.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
            shade.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
        }
        else if(shade_check == 1)
        {
            triangular_check = 0;
            flat_check = 0;
            ground_check =0;
            shade_check =1;


            shade.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
            ground.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
            flat.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
            triangular.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
        }
        else
        {

        }


        triangular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triangular_check = 1;
                flat_check = 0;
                ground_check =0;
                shade_check =0;


                triangular.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                ground.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                flat.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                shade.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));

            }
        });

        flat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triangular_check = 0;
                flat_check = 1;
                ground_check =0;
                shade_check =0;


                flat.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                ground.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                triangular.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                shade.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));

            }
        });

        ground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triangular_check = 0;
                flat_check = 0;
                ground_check =1;
                shade_check =0;


                ground.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                triangular.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                flat.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                shade.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));

            }
        });

        shade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triangular_check = 0;
                flat_check = 0;
                ground_check =0;
                shade_check =1;


                shade.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                ground.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                flat.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                triangular.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));

            }
        });




        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(triangular_check==0 && flat_check==0 && ground_check==0)
                {
                    Toast.makeText(getContext(), "Select one type to proceed", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    Bundle bundle = new Bundle();
                    bundle.putInt("residential_check", residential_check);
                    bundle.putInt("commercial_check", commercial_check);
                    bundle.putInt("industrial_check", industrial_check);
                    bundle.putInt("triangular_check", triangular_check);
                    bundle.putInt("flat_check", flat_check);
                    bundle.putInt("ground_check", ground_check);
                    bundle.putInt("shade_check", shade_check);
                    bundle.putFloat("length", length);
                    bundle.putFloat("width", width);
                    bundle.putString("latitude", latitude);
                    bundle.putString("longitude",longitude);


                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new Input_fragment3();
                    myFragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.input_fragment1, myFragment).addToBackStack(null).commit();

                    /*Intent i = new Intent(getContext(), states_list.class);
                    startActivity(i);*/


                }



            }
        });









        return v;


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
                    bundle.putFloat("length", length);
                    bundle.putFloat("width", width);
                    bundle.putString("latitude", "0");
                    bundle.putString("longitude","0");


                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new Input_fragement1();
                    myFragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.input_fragment1, myFragment).addToBackStack(null).commit();


                    return true;
                }
                return false;
            }
        });
    }






}