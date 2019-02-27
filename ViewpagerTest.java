package com.project.ttec.testproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import com.tmall.ultraviewpager.UltraViewPager;
import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerTest extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private UltraViewPager ultraViewPager;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        ultraViewPager = (UltraViewPager) findViewById(R.id.ultra_viewpager);

        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        adapter = new UltraPagerAdapter(true);
        ultraViewPager.setAdapter(adapter);
        ultraViewPager.setMultiScreen(0.8f);
        ultraViewPager.setItemRatio(1.0f);
        ultraViewPager.setRatio(2.0f);
        ultraViewPager.setMaxHeight(350);
        ultraViewPager.setAutoMeasureHeight(true);





    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}