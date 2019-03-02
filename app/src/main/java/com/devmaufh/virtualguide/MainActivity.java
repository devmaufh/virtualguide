package com.devmaufh.virtualguide;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    Camera camera;
    FrameLayout fr_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUI();
    }
    private void bindUI(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 50);
        else{
            fr_camera=(FrameLayout)findViewById(R.id.frame_camera);
            camera=Camera.open();
            ShowCamera showCamera= new ShowCamera(this,camera);
            fr_camera.addView(showCamera);
        }
    }
    private void releaseCameraAndPreview(){

    }
}
