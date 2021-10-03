package com.example.solarguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.solarguide.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class result extends AppCompatActivity {

    ActivityMainBinding binding;

    ProgressBar progressBar;
    TextView progress_txt;
    LinearLayout expense, farms;

    TextView fetchbtn;


    public String jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec = null;

    public String avg_allmonth, units, max_panels, area_panel, total_area;


    public int residential_check, commercial_check, industrial_check = 0;
    public int triangular_check, flat_check, shade_check, ground_check = 0;
    public float length, width = 0;
    public String latitude=null, longitude = null;
    public String state_name = null;
    public int position = -1;

    public int jp,jh;
    ViewGroup.LayoutParams params;

    LinearLayout janparent, febparent, marparent, aprparent, mayparent, junparent, julparent, augparent, sepparent, octparent, novparent, decparent;
    View janv, febv, marv, aprv, mayv, junv, julv, augv, sepv, octv, novv, decv;

    TextView annualtab, monthtab;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());


        farms = (LinearLayout) findViewById(R.id.farm);
        farms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(result.this, farm.class);
                startActivity(i);
            }
        });


        expense = (LinearLayout) findViewById(R.id.analysebtn);
        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(result.this, expense.class);
                startActivity(i);
            }
        });
        janparent = (LinearLayout) findViewById(R.id.janparent);
        febparent = (LinearLayout) findViewById(R.id.febparent);
        marparent = (LinearLayout) findViewById(R.id.marparent);
        mayparent = (LinearLayout) findViewById(R.id.mayparent);
        junparent = (LinearLayout) findViewById(R.id.junparent);
        julparent = (LinearLayout) findViewById(R.id.julparent);
        augparent = (LinearLayout) findViewById(R.id.augparent);
        sepparent = (LinearLayout) findViewById(R.id.sepparent);
        octparent = (LinearLayout) findViewById(R.id.octparent);
        novparent = (LinearLayout) findViewById(R.id.novparent);
        decparent = (LinearLayout) findViewById(R.id.decparent);
        aprparent = (LinearLayout) findViewById(R.id.aprparent);


        janv = (View) findViewById(R.id.jan);
        febv = (View) findViewById(R.id.feb);
        marv = (View) findViewById(R.id.mar);
        mayv = (View) findViewById(R.id.may);
        junv = (View) findViewById(R.id.jun);
        julv = (View) findViewById(R.id.jul);
        augv = (View) findViewById(R.id.aug);
        sepv = (View) findViewById(R.id.sep);
        octv = (View) findViewById(R.id.oct);
        novv = (View) findViewById(R.id.nov);
        decv = (View) findViewById(R.id.dec);
        aprv = (View) findViewById(R.id.apr);


        annualtab = (TextView) findViewById(R.id.annualtab);
        monthtab = (TextView) findViewById(R.id.monthlytab);


        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progress_txt = (TextView) findViewById(R.id.text_view_progress);
        //fetchbtn = (TextView) findViewById(R.id.fetchbtn);


        /*progressBar.setProgress(60);
        progress_txt.setText("100\nunits/year");*/


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




        annualtab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 new fetchdata().start();

                annualtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.onboardingbar_background));

                monthtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                janparent.setBackgroundColor(0);
                febparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);




                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                   progress_txt.setText("null");
                }
                else
                {
                    float area = length*width;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));


                    Float jani, febi, mari, apri, mayi, juni, juli, augi, sepi, octi, novi, deci;
                    jani = Float.parseFloat(jan) * e;
                    febi = Float.parseFloat(feb) * e;
                    mari = Float.parseFloat(mar)* e;
                    apri = Float.parseFloat(apr)* e;
                    mayi = Float.parseFloat(may)* e;
                    juni = Float.parseFloat(jun)* e;
                    juli = Float.parseFloat(jul)* e;
                    augi = Float.parseFloat(aug)* e;
                    sepi = Float.parseFloat(sep)* e;
                    octi= Float.parseFloat(oct)* e;
                    novi = Float.parseFloat(nov)* e;
                    deci = Float.parseFloat(dec)* e;

                    Float avg = (jani + febi + mari + apri + mayi + juni + juli + augi + sepi + octi + novi + deci);






                    //progress_txt.setText(s);

                    int i = Math.round(avg);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/year");

                    i = i*100;
                    i= (int) (i/(area*17.5*12));

                    progressBar.setProgress(i);



                    jp = Math.round(jani);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                     params = janv.getLayoutParams();
                    params.height = jh;
                    janv.setLayoutParams(params);

                    if(jp>75)
                    {
                        janv.setBackgroundColor(Color.parseColor("#FF6D00"));
                    }
                    else if(jp>50)
                    {
                        janv.setBackgroundColor(Color.parseColor("#FFAB00"));
                    }
                    else if(jp>25)
                    {
                        janv.setBackgroundColor(Color.parseColor("#FFD600"));
                    }
                    else
                    {
                        janv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }



                    jp = Math.round(febi);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = febv.getLayoutParams();
                    params.height = jh;
                    febv.setLayoutParams(params);
                    if(jp>75)
                    {
                        febv.setBackgroundColor(Color.parseColor("#FF6D00"));
                    }
                    else if(jp>50)
                    {
                        febv.setBackgroundColor(Color.parseColor("#FFAB00"));
                    }
                    else if(jp>25)
                    {
                        febv.setBackgroundColor(Color.parseColor("#FFD600"));
                    }
                    else
                    {
                        febv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }



                    jp = Math.round(mari);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = marv.getLayoutParams();
                    params.height = jh;
                    marv.setLayoutParams(params);
                    if(jp>75)
                    {
                        marv.setBackgroundColor(Color.parseColor("#FF6D00"));
                    }
                    else if(jp>50)
                    {
                        marv.setBackgroundColor(Color.parseColor("#FFAB00"));
                    }
                    else if(jp>25)
                    {
                        marv.setBackgroundColor(Color.parseColor("#FFD600"));
                    }
                    else
                    {
                        marv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }



                    jp = Math.round(apri);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = aprv.getLayoutParams();
                    params.height = jh;
                    aprv.setLayoutParams(params);

                    if(jp>75)
                    {
                        aprv.setBackgroundColor(Color.parseColor("#FF6D00"));
                    }
                    else if(jp>50)
                    {
                        aprv.setBackgroundColor(Color.parseColor("#FFAB00"));
                    }
                    else if(jp>25)
                    {
                        aprv.setBackgroundColor(Color.parseColor("#FFD600"));
                    }
                    else
                    {
                        aprv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }



                    jp = Math.round(mayi);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = mayv.getLayoutParams();
                    params.height = jh;
                    mayv.setLayoutParams(params);
                    if(jp>75)
                    {
                        mayv.setBackgroundColor(Color.parseColor("#FF6D00"));
                    }
                    else if(jp>50){
                        mayv.setBackgroundColor(Color.parseColor("#FFAB00"));
                    }
                    else if(jp>25)
                    {
                        mayv.setBackgroundColor(Color.parseColor("#FFD600"));
                    }
                    else
                    {
                        mayv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }



                    jp = Math.round(juni);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = junv.getLayoutParams();
                    params.height = jh;
                    junv.setLayoutParams(params);

                    if(jp>75)
                    {
                        junv.setBackgroundColor(Color.parseColor("#FF6D00"));
                    }
                    else if(jp>50)
                    {
                        junv.setBackgroundColor(Color.parseColor("#FFAB00"));
                    }
                    else if(jp>25)
                    {
                        junv.setBackgroundColor(Color.parseColor("#FFD600"));
                    }
                    else
                    {
                        junv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }


                    jp = Math.round(juli);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = julv.getLayoutParams();
                    params.height = jh;
                    julv.setLayoutParams(params);

                    if(jp>75)
                    {
                        julv.setBackgroundColor(Color.parseColor("#FF6D00"));
                    }
                    else if(jp>50)
                    {
                        julv.setBackgroundColor(Color.parseColor("#FFAB00"));
                    }
                    else if(jp>25)
                    {
                        julv.setBackgroundColor(Color.parseColor("#FFD600"));
                    }
                    else
                    {
                        julv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }


                    jp = Math.round(augi);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = augv.getLayoutParams();
                    params.height = jh;
                    augv.setLayoutParams(params);

                    if(jp>75)
                    {
                        augv.setBackgroundColor(Color.parseColor("#FF6D00"));
                    }
                    else if(jp>50)
                    {
                        augv.setBackgroundColor(Color.parseColor("#FFAB00"));
                    }
                    else if(jp>25)
                    {
                        augv.setBackgroundColor(Color.parseColor("#FFD600"));
                    }
                    else
                    {
                        augv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }



                    jp = Math.round(sepi);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = sepv.getLayoutParams();
                    params.height = jh;
                    sepv.setLayoutParams(params);

                    if(jp>75)
                    {
                        sepv.setBackgroundColor(Color.parseColor("#FF6D00"));
                    }
                    else if(jp>50)
                    {
                        sepv.setBackgroundColor(Color.parseColor("#FFAB00"));
                    }
                    else if(jp>25)
                    {
                        sepv.setBackgroundColor(Color.parseColor("#FFD600"));
                    }
                    else
                    {
                        sepv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }



                    jp = Math.round(octi);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = octv.getLayoutParams();
                    params.height = jh;
                    octv.setLayoutParams(params);

                    if(jp>75)
                    {
                        octv.setBackgroundColor(Color.parseColor("#FF6D00"));
                    }
                    else if(jp>50)
                    {
                        octv.setBackgroundColor(Color.parseColor("#FFAB00"));
                    }
                    else if(jp>25)
                    {
                        octv.setBackgroundColor(Color.parseColor("#FFD600"));
                    }
                    else
                    {
                        octv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }


                    jp = Math.round(novi);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = novv.getLayoutParams();
                    params.height = jh;
                    novv.setLayoutParams(params);

                    if(jp>75)
                    {
                        novv.setBackgroundColor(Color.parseColor("#FF6D00"));
                    }
                    else if(jp>50)
                    {
                        novv.setBackgroundColor(Color.parseColor("#FFAB00"));
                    }
                    else if(jp>25)
                    {
                        novv.setBackgroundColor(Color.parseColor("#FFD600"));
                    }
                    else
                    {
                        novv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }



                    jp = Math.round(deci);
                    jp = (int) ((jp*100)/(area*17.5));
                    jh = (400*jp)/100;
                    params = decv.getLayoutParams();
                    params.height = jh;
                    decv.setLayoutParams(params);

                    if(jp>75)
                    {
                        decv.setBackgroundColor(Color.parseColor("#FF6D00"));
                    }
                    else if(jp>50)
                    {
                        decv.setBackgroundColor(Color.parseColor("#FFAB00"));
                    }
                    else if(jp>25)
                    {
                        decv.setBackgroundColor(Color.parseColor("#FFD600"));
                    }
                    else
                    {
                        decv.setBackgroundColor(Color.parseColor("#AEEA00"));
                    }

                }

            }
        });



        monthtab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.onboardingbar_background));
                annualtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                janparent.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                febparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                }

                else
                {

                    float area = length*width;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(jan) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/January");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);


                }




            }
        });


        janparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.onboardingbar_background));
                annualtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                janparent.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                febparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                }

                else
                {

                    float area = length*width;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(jan) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/January");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);


                }




            }
        });


        febparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.onboardingbar_background));
                annualtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                febparent.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                janparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));



                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                }

                else
                {
                    float area = length*width;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float febi = Float.parseFloat(feb) * e;

                    int i = Math.round(febi);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/February");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);
                }




            }
        });


        marparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.onboardingbar_background));
                annualtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                marparent.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                febparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                }

                else
                {
                    float area = length*width;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(mar) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/March");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);



                }



            }
        });




        aprparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.onboardingbar_background));
                annualtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                aprparent.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                febparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                }

                else
                {
                    float area = length*width;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(apr) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/April");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);



                }



            }
        });




        mayparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.onboardingbar_background));
                annualtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                mayparent.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                febparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                }

                else
                {
                    float area = length*width;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(may) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/May");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);



                }



            }
        });




        junparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.onboardingbar_background));
                annualtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                junparent.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                febparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                }

                else
                {
                    float area = length*width;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(jun) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/June");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);



                }



            }
        });



        julparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.onboardingbar_background));
                annualtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                julparent.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                febparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                }

                else
                {
                    float area = length*width;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(jul) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/July");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);



                }



            }
        });


        augparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.onboardingbar_background));
                annualtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                augparent.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                febparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                }

                else
                {
                    float area = length*width;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(aug) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/August");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);



                }



            }
        });



        sepparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.onboardingbar_background));
                annualtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                sepparent.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                febparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                }

                else
                {
                    float area = length*width;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(sep) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/September");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);



                }



            }
        });

        octparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.onboardingbar_background));
                annualtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                octparent.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                febparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                }

                else
                {
                    float area = length*width;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(oct) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/October");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);



                }



            }
        });



        novparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.onboardingbar_background));
                annualtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                novparent.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                febparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                decparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                }

                else
                {
                    float area = length*width;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(nov) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/November");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);



                }



            }
        });


        decparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.onboardingbar_background));
                annualtab.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                decparent.setBackgroundDrawable(ContextCompat.getDrawable(result.this, R.drawable.yellowoutline));
                febparent.setBackgroundColor(0);
                janparent.setBackgroundColor(0);
                mayparent.setBackgroundColor(0);
                junparent.setBackgroundColor(0);
                julparent.setBackgroundColor(0);
                augparent.setBackgroundColor(0);
                sepparent.setBackgroundColor(0);
                octparent.setBackgroundColor(0);
                novparent.setBackgroundColor(0);
                marparent.setBackgroundColor(0);
                aprparent.setBackgroundColor(0);
                //janparent.setBackgroundColor(Color.parseColor("#FFD600"));


                if(jan==null || feb==null || mar==null || apr==null || may==null || jun==null || jul==null || aug==null || sep==null || oct==null || nov==null || dec==null)
                {
                    progress_txt.setText("null");
                }

                else
                {
                    float area = length*width;
                    float capacity = (float) (area*0.165);
                    float r = capacity/2;
                    float e = (float) (area*r*(0.75));
                    Float jani = Float.parseFloat(dec) * e;

                    int i = Math.round(jani);
                    String s = Integer.toString(i);
                    progress_txt.setText(s + "\nunits/December");

                    i = i*100;
                    i=i/700;

                    progressBar.setProgress(i);



                }



            }
        });








    }









      class fetchdata extends Thread{

        @Override
        public void run() {
            super.run();


            String data = " ";
            try {

                URL url = new URL("https://power.larc.nasa.gov/api/temporal/climatology/point?parameters=ALLSKY_SFC_SW_DWN&community=RE&longitude=77.1025&latitude=28.8400&format=JSON");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while( (line = bufferedReader.readLine()) != null)
                {
                    data = data + line;
                }

                if(!data.isEmpty())
                {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONObject irradiance = jsonObject.getJSONObject("properties").getJSONObject("parameter").getJSONObject("ALLSKY_SFC_SW_DWN");

                    jan = irradiance.getString("JAN");
                    feb = irradiance.getString("FEB");
                    mar = irradiance.getString("MAR");
                    apr = irradiance.getString("APR");
                    may = irradiance.getString("MAY");
                    jun = irradiance.getString("JUN");
                    jul = irradiance.getString("JUL");
                    aug = irradiance.getString("AUG");
                    sep = irradiance.getString("SEP");
                    oct = irradiance.getString("OCT");
                    nov = irradiance.getString("NOV");
                    dec = irradiance.getString("DEC");


                }







            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }



}