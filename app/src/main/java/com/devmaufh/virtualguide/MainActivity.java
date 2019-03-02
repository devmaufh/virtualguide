package com.devmaufh.virtualguide;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {
    private Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check_permissions();
        open_camera();

    }
    private void check_permissions(){
        EasyPermissions.PermissionCallbacks p= new EasyPermissions.PermissionCallbacks() {
            @Override
            public void onPermissionsGranted(int requestCode, List<String> perms) {

            }

            @Override
            public void onPermissionsDenied(int requestCode, List<String> perms) {

            }

            @Override
            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

            }
        };

    }
    private void open_camera() {
        Intent intent_camera= new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent_camera);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
