package com.example.solarguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Onboarding extends AppCompatActivity {

    ImageView onboardimage,nextbtn,bar2, backbtn,onboardimage1;
    TextView head1,head2,head3,para, startbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        onboardimage = (ImageView) findViewById(R.id.onboard_image);
        onboardimage1 = (ImageView) findViewById(R.id.onboard_image1);

        nextbtn = (ImageView) findViewById(R.id.nextbtn);
        backbtn = (ImageView) findViewById(R.id.backbtn);
        bar2 = (ImageView) findViewById(R.id.bar2);
        head1 = (TextView) findViewById(R.id.onboard_head1);
        head2 = (TextView) findViewById(R.id.onboard_head2);
        head3 = (TextView) findViewById(R.id.onboard_head3);
        para = (TextView) findViewById(R.id.onboard_para);

        startbtn = (TextView) findViewById(R.id.startbtn);


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onboardimage.setVisibility(View.GONE);
                onboardimage1.setVisibility(View.VISIBLE);
               // onboardimage.setBackground(R.drawable.solarhouse);
                //onboardimage.setBackground(ContextCompat.getDrawable(Onboarding.this, R.drawable.solarhouse));
                bar2.setVisibility(View.VISIBLE);
                String head11 = "Bring";
                String head22 = "Solar";
                String head33 = "Home";
                head1.setText(head11);
                head2.setText(head22);
                head3.setText(head33);

                String paragraph = "View 3D visualisation of Solar panels for maximum efficiency and know your consumptions cutoff";
                para.setText(paragraph);

                startbtn.setVisibility(View.VISIBLE);
                nextbtn.setVisibility(View.INVISIBLE);
                backbtn.setVisibility(View.VISIBLE);


            }
        });


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = Onboarding.this;
                //onboardimage.setBackground(ContextCompat.getDrawable(context, R.drawable.solarenergy));
                onboardimage1.setVisibility(View.GONE);
                onboardimage.setVisibility(View.VISIBLE);
                bar2.setVisibility(View.INVISIBLE);
                String head11 = "Analyse";
                String head22 = "Solar";
                String head33 = "Power";
                head1.setText(head11);
                head2.setText(head22);
                head3.setText(head33);

                String paragraph = "Check, If Solar is right for you or not by calculating the power generation on your area";
                para.setText(paragraph);

                startbtn.setVisibility(View.INVISIBLE);
                nextbtn.setVisibility(View.VISIBLE);
                backbtn.setVisibility(View.INVISIBLE);
            }
        });



        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Onboarding.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}