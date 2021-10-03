package com.example.solarguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public int triangular_check, flat_check, shade_check, ground_check=0;

    //ImageView nextbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* nextbtn = (ImageView) findViewById(R.id.nextbtn);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });*/






        Bundle bundle = new Bundle();
        bundle.putInt("residential_check", 0);
        bundle.putInt("commercial_check", 0);
        bundle.putInt("industrial_check", 0);
        bundle.putInt("triangular_check", 0);
        bundle.putInt("flat_check", 0);
        bundle.putInt("ground_check", 0);
        bundle.putInt("shade_check", 0);
        bundle.putFloat("length", 0);
        bundle.putFloat("width", 0);
        bundle.putString("latitude", "0");
        bundle.putString("longitude","0");


        FragmentManager m = getSupportFragmentManager();
        FragmentTransaction t = m.beginTransaction();
        Fragment myfragment = new Input_fragement1();
        myfragment.setArguments(bundle);
        t.replace(R.id.input_fragment1, myfragment);
        t.commit();





    }
}