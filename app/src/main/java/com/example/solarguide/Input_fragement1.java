package com.example.solarguide;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Input_fragement1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Input_fragement1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public int triangular_check, flat_check, shade_check, ground_check=0;
    public int length =0, width=0;
    public String latitude , longitude;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView nextbtn;
    LinearLayout residential, commercial, industrial;

    public int residential_check , commercial_check, industrial_check =0;


    public Input_fragement1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Input_fragement1.
     */
    // TODO: Rename and change types and number of parameters
    public static Input_fragement1 newInstance(String param1, String param2) {
        Input_fragement1 fragment = new Input_fragement1();
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
        View v = inflater.inflate(R.layout.fragment_input_fragement1, container, false);

        Bundle bundle = getArguments();
        residential_check = bundle.getInt("residential_check");
        commercial_check = bundle.getInt("commercial_check");
        industrial_check = bundle.getInt("industrial_check");
        triangular_check = bundle.getInt("triangular_check");
        flat_check = bundle.getInt("flat_check");
        ground_check = bundle.getInt("ground_check");
        shade_check = bundle.getInt("shade_check");
        width = bundle.getInt("width");
        length = bundle.getInt("length");
        latitude = bundle.getString("latitude");
        longitude = bundle.getString("longitude");

        nextbtn = (ImageView) v.findViewById(R.id.nextbtn);

        residential = (LinearLayout) v.findViewById(R.id.residential);
        commercial = (LinearLayout) v.findViewById(R.id.commercial);
        industrial = (LinearLayout) v.findViewById(R.id.industrial);


        if(residential_check==1)
        {
            residential_check = 1;
            commercial_check =0;
            industrial_check=0;


            residential.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
            commercial.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
            industrial.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
        }
        else if(commercial_check==1)
        {
            residential_check = 0;
            commercial_check =1;
            industrial_check=0;

            commercial.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
            residential.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
            industrial.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
        }
        else if(industrial_check==1)
        {
            residential_check = 0;
            commercial_check =0;
            industrial_check=1;


            industrial.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
            residential.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
            commercial.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));

        }
        else
        {

        }





        residential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                residential_check = 1;
                commercial_check =0;
                industrial_check=0;


                residential.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                commercial.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                industrial.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));

            }
        });

        commercial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                residential_check = 0;
                commercial_check =1;
                industrial_check=0;

                commercial.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                residential.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                industrial.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));

            }
        });

        industrial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                residential_check = 0;
                commercial_check =0;
                industrial_check=1;


                industrial.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.onboardingbar_background));
                residential.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));
                commercial.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellowoutline));

            }
        });




        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(commercial_check==0 && industrial_check==0 && residential_check==0)
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
                    bundle.putInt("length", length);
                    bundle.putInt("width", width);
                    bundle.putString("latitude", latitude);
                    bundle.putString("longitude",longitude);

                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new Input_fragment2();
                    myFragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.input_fragment1, myFragment).addToBackStack(null).commit();


                }


            }
        });





        return v;
    }
}