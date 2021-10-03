package com.example.solarguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class farm extends AppCompatActivity {

    TextView interbtn, belowbtn, avhead, avsum, crops;
    ImageView avpic, croppic, croppic_below, avpic_below;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);

        interbtn = (TextView) findViewById(R.id.interbtn);
        belowbtn = (TextView) findViewById(R.id.belowbtn);
        avhead = (TextView) findViewById(R.id.avhead);
        avsum = (TextView) findViewById(R.id.avsum);
        crops = (TextView) findViewById(R.id.crops);

        avpic = (ImageView) findViewById(R.id.avpic);
        croppic = (ImageView) findViewById(R.id.croppic);

        avpic_below = (ImageView) findViewById(R.id.avpic_below);
        croppic_below = (ImageView) findViewById(R.id.croppic_below);




        avhead.setText("Interspace Farming");
        avsum.setText("Cultivation of crops between space of two crops(most commonly observed pattern)");
        crops.setText("vegetables, cucumber, pumpkin etc");

        //avpic.setBackground(ContextCompat.getDrawable(farm.this, R.drawable.inter));
        //croppic.setBackground(ContextCompat.getDrawable(farm.this, R.drawable.harvest));

        avpic_below.setVisibility(View.GONE);
        avpic.setVisibility(View.VISIBLE);
        croppic_below.setVisibility(View.GONE);
        croppic.setVisibility(View.VISIBLE);

        interbtn.setBackground(ContextCompat.getDrawable(farm.this, R.drawable.onboardingbar_background));
        belowbtn.setBackground(ContextCompat.getDrawable(farm.this, R.drawable.yellowoutline));









        interbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                avhead.setText("Interspace Farming");
                avsum.setText("Cultivation of crops between space of two crops(most commonly observed pattern)");
                crops.setText("vegetables, cucumber, pumpkin etc");

                avpic_below.setVisibility(View.GONE);
                avpic.setVisibility(View.VISIBLE);
                croppic_below.setVisibility(View.GONE);
                croppic.setVisibility(View.VISIBLE);

                interbtn.setBackground(ContextCompat.getDrawable(farm.this, R.drawable.onboardingbar_background));
                belowbtn.setBackground(ContextCompat.getDrawable(farm.this, R.drawable.yellowoutline));


            }
        });


        belowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                avhead.setText("Below the panel farming");
                avsum.setText("Farming below the panels which are installed at latitude tilt angles so that they provide shade with moisture");
                crops.setText("green leaf vegetables, spinnach etc");

                avpic.setVisibility(View.GONE);
                avpic_below.setVisibility(View.VISIBLE);
                croppic.setVisibility(View.GONE);
                croppic_below.setVisibility(View.VISIBLE);

                belowbtn.setBackground(ContextCompat.getDrawable(farm.this, R.drawable.onboardingbar_background));
                interbtn.setBackground(ContextCompat.getDrawable(farm.this, R.drawable.yellowoutline));


            }
        });









    }
}