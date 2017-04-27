package com.example.kulickip.systemymobilny;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickMe(View view){
        Intent in = new Intent(this, WIFIActivity.class);
        startActivity(in);
    }

    public void showBluetoothActivity(View view) {
        Intent in = new Intent(this, BluetoothActivity.class);
        startActivity(in);
    }

    public void showCameraActivity(View view) {
        Intent in = new Intent(this, CameraActivity.class);
        startActivity(in);
    }
    public void showAccelerometrActivity(View view) {
        Intent in = new Intent(this, AccelerometrActivity.class);
        startActivity(in);
    }

    public void showGPSActivity(View view) {
        Intent in = new Intent(this, GPSActivity.class);
        startActivity(in);
    }

    public void showCompassActivity(View view) {
        Intent in = new Intent(this, CompassActivity.class);
        startActivity(in);
    }

    public void showLightActivity(View view) {
        Intent in = new Intent(this, LightActivity.class);
        startActivity(in);
    }
}
