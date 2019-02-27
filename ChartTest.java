package com.project.ttec.testproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

/**
 * Created by yooyongsuk on 2017. 12. 24..
 */

public class ChartTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charttest);


        LineChart lchart = (LineChart)findViewById(R.id.chart_test);
//        lchart.setBackgroundColor(Color.rgb(255, 255, 255));
//        lchart.setDrawGridBackground(false);
        lchart.getDescription().setEnabled(false);
        lchart.getAxisLeft().setDrawGridLines(false);
        lchart.getXAxis().setDrawGridLines(false);
        lchart.getXAxis().setDrawAxisLine(false);
        lchart.getLegend().setEnabled(false);

//        lchart.getAxisLeft().setAxisMaxValue(60f);
//        lchart.getAxisLeft().setAxisMinValue(0f);



        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 51.1f));
        entries.add(new Entry(1, 50.7f));
        entries.add(new Entry(2, 51.5f));
        entries.add(new Entry(3, 52.6f));


        LineDataSet dataset = new LineDataSet(entries, "");
        dataset.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataset.setCubicIntensity(0.2f);

        dataset.setCircleRadius(5f);
        dataset.setCircleColor(Color.rgb(101, 123, 229));

        dataset.setCircleColorHole(Color.rgb(219, 123, 81));
        dataset.setCircleHoleRadius(3f);

        dataset.setValueTextSize(7f);
//        dataset.setValueTextColor(Color.rgb());
//        dataset.setColors(ColorTemplate.COLORFUL_COLORS);



        dataset.setLineWidth(2.5f);
//        dataset.setColor(Color.rgb());

        XAxis xAxis = lchart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        final String[] quarters = new String[] { "22일", "23일", "24일", "25일" };

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }

            // we don't draw numbers, so no decimal digits needed

        };


        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);



        YAxis yAxis = lchart.getAxisLeft(); // upper part
        yAxis.setEnabled(false);
        yAxis.setTextSize(9f); // set the text size
        yAxis.setAxisMinimum(30f); // start at zero
        yAxis.setAxisMaximum(70f);

        yAxis = lchart.getAxisRight(); // lower part
        yAxis.setEnabled(false);


        LineData data = new LineData(dataset);


        lchart.setData(data);
//        lchart.animateY(5000);
    }
}
