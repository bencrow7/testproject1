package com.project.ttec.testproject;

import android.Manifest;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE},
                1);

        Button pickertest = (Button)findViewById(R.id.btn_pickertest);
        pickertest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PickerTest.class);
                startActivity(intent);

            }
        });

        Button pickertest1 = (Button)findViewById(R.id.btn_pickertest1);
        pickertest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PickerTest1.class);
                startActivity(intent);

            }
        });

        Button pickertest2 = (Button)findViewById(R.id.btn_pickertest2);
        pickertest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PickerTest2.class);
                startActivity(intent);

            }
        });

        Button charttest = (Button)findViewById(R.id.btn_charttest);
        charttest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChartTest.class);
                startActivity(intent);

            }
        });

        Button testview = (Button)findViewById(R.id.btn_testview);
        testview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Testview.class);
                startActivity(intent);

            }
        });

        Button btn_timepicker = (Button)findViewById(R.id.btn_timepicker);
        btn_timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        Button btn_qrcodereader = (Button)findViewById(R.id.btn_qrcodereader);
        btn_qrcodereader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QrReadTest.class);
                startActivity(intent);
            }
        });

        Button btn_qrcodereader_1 = (Button)findViewById(R.id.btn_qrcodereader_1);
        btn_qrcodereader_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QrReadTest_1.class);
                startActivity(intent);
            }
        });

        Button btn_qrcodereader_2 = (Button)findViewById(R.id.btn_qrcodereader_2);
        btn_qrcodereader_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QrReadTest_2.class);
                startActivity(intent);
            }
        });

        Button btn_calendartest = (Button)findViewById(R.id.btn_calendartest);
        btn_calendartest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calendartest.class);
                startActivity(intent);
            }
        });


        Button btn_camera_test = (Button)findViewById(R.id.btn_camera_test);
        btn_camera_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CameraTest.class);
                startActivity(intent);
            }
        });


        Button btn_button_weight_dynamic_test = (Button)findViewById(R.id.btn_button_weight_dynamic_test);
        btn_button_weight_dynamic_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CameraTest.class);
                startActivity(intent);
            }
        });


        Button btn_button_viewpager = (Button)findViewById(R.id.btn_button_viewpager);
        btn_button_viewpager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewpagerTest.class);
                startActivity(intent);


                System.out.println("HomeActivity onResume");
            }
        });

        Button btn_multipart = (Button)findViewById(R.id.btn_multipart);
        btn_multipart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, multipartTest.class);
                startActivity(intent);
            }
        });

        Button btn_file_download_test = (Button)findViewById(R.id.btn_file_download_test);
        btn_file_download_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, fileDownSave.class);
                startActivity(intent);
            }
        });

        Button btn_youtube_test = (Button)findViewById(R.id.btn_youtube_test);
        btn_youtube_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, YoutubeTest.class);
                startActivity(intent);
            }
        });

    }

    public void showTimePicker() {
        final Calendar myCalender = Calendar.getInstance();
        int hour = myCalender.get(Calendar.HOUR_OF_DAY);
        int minute = myCalender.get(Calendar.MINUTE);


        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(android.widget.TimePicker timePicker, int hour, int min) {

                String time = hour + ":" + min;
                DateFormat sdf = new SimpleDateFormat("hh:mm");
                try {
                    Format formatter;
                    formatter = new SimpleDateFormat("a h:mm");

                    Date date = sdf.parse(time);

                    String timestr = "(" + formatter.format(date) + ")";

                    System.out.println(timestr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

        };
        IntervalTimePickerDialog timePickerDialog = new IntervalTimePickerDialog(MainActivity.this, AlertDialog.THEME_HOLO_LIGHT, myTimeListener, hour, minute, false);
        timePickerDialog.setTitle("");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }
}
