package com.project.ttec.testproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by yooyongsuk on 2017. 12. 24..
 */

public class PickerTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickertest);

        PickerView picker1 = (PickerView)findViewById(R.id.pickerView1);

        ArrayList<String> items = new ArrayList<>();
        for (int i = 1; i <= 100; i++){
            items.add("" + i);
        }

        picker1.setList(items);

//        PickerView picker2 = (PickerView)findViewById(R.id.pickerView2);
//
//        ArrayList<String> items2 = new ArrayList<>();
//        for (int i = 100; i <= 200; i++){
//            items2.add("" + i);
//        }
//
//        picker2.setList(items2);

        PickerView picker3 = (PickerView)findViewById(R.id.pickerView3);

        ArrayList<String> items3 = new ArrayList<>();
        for (int i = 200; i <= 300; i++){
            items3.add("" + i);
        }

        picker3.setList(items3);

    }
}
