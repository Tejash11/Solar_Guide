package com.example.solarguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class states_list extends AppCompatActivity {

    RecyclerView state_list;
    state_custom_adapter custom_firebase_adapter;


    public int residential_check, commercial_check, industrial_check = 0;
    public int triangular_check, flat_check, shade_check, ground_check = 0;
    public float length, width = 0;
    public String latitude=null, longitude = null;
    public String state_name = null;
    public int position = -1;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_states_list);


        Bundle bundle = getIntent().getExtras();
        //Intent i =
        residential_check = bundle.getInt("residential_check");
        commercial_check = bundle.getInt("commercial_check");
        industrial_check = bundle.getInt("industrial_check");
        triangular_check = bundle.getInt("triangular_check");
        flat_check = bundle.getInt("flat_check");
        ground_check = bundle.getInt("ground_check");
        shade_check = bundle.getInt("shade_check");
        length = bundle.getFloat("length");
        width = bundle.getFloat("width");
        position = bundle.getInt("position");
        state_name = bundle.getString("state_name");


        //state_name = "Haryana";
















        state_list = (RecyclerView) findViewById(R.id.state_list);
        state_list.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<state_data> options=
                new FirebaseRecyclerOptions.Builder<state_data>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("states"), state_data.class)
                        .build();


        custom_firebase_adapter = new state_custom_adapter(options, this, residential_check, commercial_check, industrial_check, shade_check, flat_check, triangular_check, ground_check, length, width, state_name, position);
        state_list.setAdapter(custom_firebase_adapter);



        ImageView nextbtn = (ImageView) findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
                bundle.putString("latitude","0");
                bundle.putString("longitude","0");
                bundle.putInt("position", -1);
                bundle.putString("state_name", state_name);



                Intent i = new Intent(states_list.this, result.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });











    }



    @Override
    public void onStart() {
        super.onStart();
        custom_firebase_adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        custom_firebase_adapter.stopListening();
    }



    @Override
    public void onResume() {
        super.onResume();

        if(getWindow().getDecorView().getRootView() == null){
            return;
        }

        getWindow().getDecorView().getRootView().setFocusableInTouchMode(true);
        getWindow().getDecorView().getRootView().requestFocus();
        getWindow().getDecorView().getRootView().setOnKeyListener(new View.OnKeyListener() {
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