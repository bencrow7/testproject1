package com.project.ttec.testproject;

import android.Manifest;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import github.nisrulz.qreader.QRDataListener;
import github.nisrulz.qreader.QREader;

/**
 * Created by yooyongsuk on 2017. 12. 24..
 */

public class QrReadTest extends AppCompatActivity  {

    private static final String cameraPerm = Manifest.permission.CAMERA;

    private TextView text;

    // QREader
    private SurfaceView mySurfaceView;
    private QREader qrEader;

    boolean hasCameraPermission = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrreadtest);


        hasCameraPermission = RuntimePermissionUtil.checkPermissonGranted(this, cameraPerm);


        text = findViewById(R.id.code_info);

        final Button stateBtn = findViewById(R.id.btn_start_stop);
        // change of reader state in dynamic
        stateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qrEader.isCameraRunning()) {
                    stateBtn.setText("Start QREader");
//                    mySurfaceView.setVisibility(View.GONE);
                    text.setText("");
                    qrEader.stop();
                } else {
                    stateBtn.setText("Stop QREader");
//                    mySurfaceView.setVisibility(View.VISIBLE);
                    qrEader.start();
                }
            }
        });

        stateBtn.setVisibility(View.VISIBLE);



        // Setup SurfaceView
        // -----------------
        mySurfaceView = findViewById(R.id.camera_view);
        System.out.println("value ===" + hasCameraPermission);
        if (hasCameraPermission) {

            setupQREader();
        } else {
            RuntimePermissionUtil.requestPermission(QrReadTest.this, cameraPerm, 100);
        }
    }



    void setupQREader() {
        // Init QREader
        // ------------
        qrEader = new QREader.Builder(this, mySurfaceView, new QRDataListener() {
            @Override
            public void onDetected(final String data) {
                Log.d("QREader", "Value : " + data);
                System.out.println("value ===" + data);
                text.post(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(data);
//                        mySurfaceView.setVisibility(View.GONE);
                        qrEader.stop();
                    }
                });
            }
        }).facing(QREader.BACK_CAM)
                .enableAutofocus(true)
                .height(mySurfaceView.getHeight())
                .width(mySurfaceView.getWidth())
                .build();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (hasCameraPermission) {

            // Cleanup in onPause()
            // --------------------
            qrEader.releaseAndCleanup();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (hasCameraPermission) {

            // Init and Start with SurfaceView
            // -------------------------------
            qrEader.initAndStart(mySurfaceView);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        if (requestCode == 100) {
            RuntimePermissionUtil.onRequestPermissionsResult(grantResults, new RPResultListener() {
                @Override
                public void onPermissionGranted() {
                    if ( RuntimePermissionUtil.checkPermissonGranted(QrReadTest.this, cameraPerm)) {
                        System.out.println("restart 함수가 있었음.;;");
                    }
                }

                @Override
                public void onPermissionDenied() {
                    // do nothing
                }
            });
        }
    }
}
