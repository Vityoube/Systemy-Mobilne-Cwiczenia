package com.example.andrii.poziomnica;

import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.example.andrii.poziomnica.R.layout.activity_main;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    public  static  float sensorX;
    public static float x;

    public static  float scale;

    final float  alpha = (float)0.8;
    //public  static   SensorManager mSensorManager;
    //public  static Sensor mAccelerometer;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CanvasView(this));
        SensorManager sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        sm.registerListener(this,sm.getDefaultSensor(Sensor.TYPE_ORIENTATION),0,null);
       // mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSensorChanged(SensorEvent event) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            sensorX = (alpha * sensorX + (1 - alpha) * event.values[1]);
            scale = (float) 8.0;
        } else {
            sensorX = (alpha * sensorX + (1 - alpha) * event.values[2]);
            scale = (float) 6.0;

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}