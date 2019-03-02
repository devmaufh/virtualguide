package com.devmaufh.virtualguide;

import android.Manifest;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Camera camera;
    Dialog notification;
    FrameLayout fr_camera;
    TextToSpeech ts;

    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUI();
        Toast.makeText(this, "La camara funciona chidorring", Toast.LENGTH_SHORT).show();
        getLocation();
    }
    private void bindUI(){
        ts= new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i==TextToSpeech.SUCCESS){
                    Toast.makeText(MainActivity.this, "SUCEEEESS", Toast.LENGTH_SHORT).show();
                    ts.setLanguage(new Locale("spa", "MEX"));
                    ts.speak("A bueno gracias",TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });
        ts.speak("Apparently, I'm not bound to the TTS engine.  Maybe too soon to talk.", TextToSpeech.QUEUE_FLUSH, null);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 50);
        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 50);
        else{
            fr_camera=(FrameLayout)findViewById(R.id.frame_camera);
            camera=Camera.open();
            ShowCamera showCamera= new ShowCamera(this,camera);
            fr_camera.addView(showCamera);
            client= LocationServices.getFusedLocationProviderClient(this);
        }
    }
    private void getLocation(){
        notification= new Dialog(this);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 50);

        }
        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    Toast.makeText(MainActivity.this, "Lat: "+location.getLatitude()+"\nLon"+location.getLongitude(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void showCard(String text, String title){
        notification.setContentView(0);//Set layout
        //   Bind all widgets


    }
    private void speakInfo(String text){
        AudioManager audioManager= (AudioManager)getSystemService(AUDIO_SERVICE);
        audioManager.setSpeakerphoneOn(true);
        ts.speak(text,TextToSpeech.QUEUE_FLUSH,null);
        ts.setSpeechRate(0.0f);
        Toast.makeText(this, "SpeakInfo", Toast.LENGTH_SHORT).show();

    }
}
