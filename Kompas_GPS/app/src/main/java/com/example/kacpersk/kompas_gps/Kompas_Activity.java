package com.example.kacpersk.kompas_gps;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.view.WindowManager;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


import android.Manifest;
import android.app.Activity.*  ;
import android.widget.Toast;

import java.lang.Object;

public class Kompas_Activity extends AppCompatActivity implements  SensorEventListener, LocationListener {

    public static final String NA = "N/A";
    public static final String FIXED = "FIXED";
    static final Integer LOCATION = 0x1;
    // ustalenie czasu minimalnego pobierania danych z GPS
    private static final int LOCATION_MIN_TIME = 30 * 10;
    // minimalna zmiana pozycji GPS
    private static final int LOCATION_MIN_DISTANCE = 1;


    //  manager zarządzania sensorami
    private SensorManager sensorManager;

    private LocationManager locationManager;
    private Location currentLocation;

    private Sensor mMagnetic;

    private Sensor mAccelerometer;

    private float[] magneticFieldValues;

    private TextView text_odchylenie, text_szerokosc_geograficzna, text_dlugosc_geograficzna;
    private float[] accelorometerValues;
    private float azimut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kompas_);
        text_szerokosc_geograficzna = (TextView) findViewById(R.id.szerokosc);
        text_dlugosc_geograficzna = (TextView) findViewById(R.id.dlugosc);
        text_odchylenie = (TextView) findViewById(R.id.polnoc);

        // utrzymywanie właczonego ekranu
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(Kompas_Activity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                // Jeżeli uprawnienia dla naszej aplikacji nie są nadane to pokazywane jest stosowne zapytanie
                // metoda shouldShowRequestPermissionRationale zwraca true jeżeli użytkownik nie podjął decyzji
                if (ActivityCompat.shouldShowRequestPermissionRationale(Kompas_Activity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {


                    // Jeżeli uprawnienie nie zostały nadane
                    ActivityCompat.requestPermissions(Kompas_Activity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION);

                } else {
                    //Metoda requestPermissions jest wywoływana aby uzytkownik mógł podjąć decyzję o nadaniu uprawnien
                    ActivityCompat.requestPermissions(Kompas_Activity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION);
                }

            } else {
                Toast.makeText(Kompas_Activity.this, " " + Manifest.permission.ACCESS_FINE_LOCATION + " uprawnienia zostały przyznane.", Toast.LENGTH_SHORT).show();

            }
            if (ActivityCompat.checkSelfPermission(Kompas_Activity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


                Toast.makeText(Kompas_Activity.this, " " + Manifest.permission.ACCESS_FINE_LOCATION + "Uprawnienia nie zostały nadane", Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(Kompas_Activity.this, "Wersja SDK systemu Android :  " + Build.VERSION.SDK_INT , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Kompas_Activity.this, "Wersja SDK systemu Android :  " + Build.VERSION.SDK_INT , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mMagnetic=sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(this,mMagnetic,SensorManager.SENSOR_DELAY_NORMAL);

        mAccelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);

        // Inicjacja menedżera lokalizacji
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                    LOCATION );
        }
        else
        {

        // żądanie pobierania danych GPS
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_MIN_TIME, LOCATION_MIN_DISTANCE, this);

        // pobranie ostatniej znanej pozycji
        Location gpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (gpsLocation != null) {
            currentLocation = gpsLocation;
        } else {
            // pobieranie danych na podstawie sieci
            Location networkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (networkLocation != null) {
                currentLocation = networkLocation;
            } else {
                // ustawienie pozycji
                currentLocation = new Location(FIXED);
                currentLocation.setAltitude(1);
                currentLocation.setLatitude(43.296482);
                currentLocation.setLongitude(5.36978);
            }

            // set current location
            onLocationChanged(currentLocation);
        }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // usuwanie nasłuchiwania

        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;
        // wywołanie metody odświeżającej informacje na ekranie
        updateLocation(location);

    }

    private void updateLocation(Location location) {
        if (FIXED.equals(location.getProvider())) {
            text_szerokosc_geograficzna.setText(NA);
            text_dlugosc_geograficzna.setText(NA);
        }

        // ustalenie formatu wyswietlania danych o pozycji
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        NumberFormat formatter = new DecimalFormat("#0.00", dfs);
        text_szerokosc_geograficzna.setText("Szerokość geo. : " + formatter.format(location.getLatitude()));
        text_dlugosc_geograficzna.setText("Długość geo. : " + formatter.format(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            accelorometerValues=Filtr_Dolnoprzepustowy.filtr(event.values,accelorometerValues);
            
        }
        if (event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            magneticFieldValues=Filtr_Dolnoprzepustowy.filtr(event.values,magneticFieldValues);
        }

        if (magneticFieldValues!=null && accelorometerValues!=null){
            float R[] = new float[9];
            float I[] = new float[9];

            if (SensorManager.getRotationMatrix(R, I, accelorometerValues, magneticFieldValues)) {

                // orientation contains azimut, pitch and roll
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation );

                azimut = orientation[0];
                float azimutInDegrees=(float)Math.toDegrees(azimut);
                if (azimutInDegrees < 0.0f) {
                    azimutInDegrees += 360.0f;
                }
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                dfs.setDecimalSeparator('.');
                text_odchylenie.setText("Odczylenie od północy: "+new DecimalFormat("#0.00",dfs).format(azimutInDegrees));
            }


        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    protected void  onResume(){
        super.onResume();
        sensorManager.registerListener(this,mMagnetic,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}
