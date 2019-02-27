package com.project.ttec.testproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yooyongsuk on 2017. 12. 24..
 */

public class PickerTest1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickertest1);


        ScrollChoice scrollChoice = (ScrollChoice) findViewById(R.id.scroll_choice);


        List<String> data = new ArrayList<>();
        data.add("Brazil");
        data.add("USA");
        data.add("China");
        data.add("Pakistan");
        data.add("Australia");
        data.add("India");
        data.add("Nepal");
        data.add("Sri Lanka");
        data.add("Spain");
        data.add("Italy");
        data.add("France");


        scrollChoice.addItems(data,5);


        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                Log.d("webi",name);
            }
        });




    }
}
