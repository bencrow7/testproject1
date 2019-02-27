package com.project.ttec.testproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yooyongsuk on 2017. 12. 24..
 */

public class PickerTest2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickertest2);


        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.number_picker);
        NumberPicker numberPicker1 = (NumberPicker) findViewById(R.id.number_picker1);

        String tempWeightValue;
//        String nums[] = {"Select Fraction","1/64","1/32","3/64","1/16","5/64","3/32","7/64","1/8","9/64","5/32","11/64","3/16","13/64","7/32","15/64","1/4","17/64","9/32","19/64","5/16","21/64","11/32","23/64","3/8","25/64","13/32","27/64","7/16","29/64"};
//        for(int i=35; i<90; i++) {  //totla 550 items
//            for(int y=0; y<10; y++) {
//                tempWeightValue = String.valueOf(i) + "." + String.valueOf(y);
//                nums[i] = tempWeightValue;
//            }
//        }

        String[] nums = new String[550];
        int x=0;
        for(int i=35; i<90; i++) {  //totla 550 items
            for(int y=0; y<10; y++) {
                tempWeightValue = String.valueOf(i) + "." + String.valueOf(y);
                nums[x] = tempWeightValue;
                x++;
            }
        }

        numberPicker.setMaxValue(nums.length-1);
        numberPicker.setMinValue(0);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setDisplayedValues(nums);

        numberPicker.setValue(120);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // do your other stuff depends on the new value
                String aa = String.valueOf(picker.getValue());
                System.out.println("aa === " + aa);
                System.out.println("oldval === " + oldVal);
                System.out.println("newval === " + newVal);
            }
        });


        numberPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // do your other stuff depends on the new value
                String aa = String.valueOf(picker.getValue());
                System.out.println("aa === " + aa);
                System.out.println("oldval === " + oldVal);
                System.out.println("newval === " + newVal);
            }
        });

//        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

    }
}
