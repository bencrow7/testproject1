package com.project.ttec.testproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by yooyongsuk on 2017. 12. 24..
 */

public class fileDownSave extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filedownsave);

        Button btn_test_pdf_down = (Button)findViewById(R.id.btn_test_pdf_down);
        btn_test_pdf_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL ="http://bencrow7.cafe24.com:8080/test/test.pdf";

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL)));
            }
        });


        Button btn_test_hwp_down = (Button)findViewById(R.id.btn_test_hwp_down);
        btn_test_hwp_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL ="http://bencrow7.cafe24.com:8080/test/test1.hwp";

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL)));
            }
        });

    }
}
