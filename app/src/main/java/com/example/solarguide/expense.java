package com.example.solarguide;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PanZoom;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;

public class expense extends AppCompatActivity {

    ProgressBar progressBar;
    TextView progress_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);



        XYPlot plot = findViewById(R.id.plot);
        TextView fetch = findViewById(R.id.fetchbtn);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progress_txt = (TextView) findViewById(R.id.text_view_progress);

        final Number[] domainLabels = {2021,2022,2023,2024,2025,2026,2027,2028,2029,2030};
        //Number[] series1Numbers = {0,0,0,0,0,0.144,0.23,0.11,0.1233,0.223};
        Number[] series1Numbers = {3.5,2.6,1.8,0.98,0.14,0.022,0.0023,0.00011,0,0};



        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setProgress(50);
                progress_txt.setText("5 Years");









            }
        });

        XYSeries series1 = new SimpleXYSeries(Arrays.asList(series1Numbers),
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Remaining Cover Amount");
        /*XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers),
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Series 2");*/

        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.RED, Color.BLACK,null,null);
        // LineAndPointFormatter series2Format = new LineAndPointFormatter(Color.YELLOW,Color.BLUE,null,null);

        series1Format.setInterpolationParams(new CatmullRomInterpolator.Params(10,
                CatmullRomInterpolator.Type.Centripetal));
        /*series2Format.setInterpolationParams(new CatmullRomInterpolator.Params(10,
                CatmullRomInterpolator.Type.Centripetal));*/

        plot.addSeries(series1,series1Format);
        //plot.addSeries(series2,series2Format);

        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                int i = Math.round( ((Number)obj).floatValue() );
                return toAppendTo.append(domainLabels[i]);
            }

            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });

        PanZoom.attach(plot);









    }
}