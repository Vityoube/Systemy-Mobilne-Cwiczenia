package pl.lodz.p.imsi.maciekka.uprawnienia;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.Manifest;


public class UprawnieniaActivity extends AppCompatActivity {

    static final Integer LOCATION = 0x1;
    static final Integer CALL = 0x2;
    static final Integer WRITE_EXST = 0x3;
    static final Integer READ_EXST = 0x4;
    static final Integer CAMERA = 0x5;
    static final Integer ACCOUNTS = 0x6;
    static final Integer GPS_SETTINGS = 0x7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uprawnienia);

        Button gps = (Button) findViewById(R.id.lokalizacja);
        gps.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(UprawnieniaActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    // Jeżeli uprawnienia dla naszej aplikacji nie są nadane to pokazywane jest stosowne zapytanie
                    // metoda shouldShowRequestPermissionRationale zwraca true jeżeli użytkownik nie podjął decyzji
                    if (ActivityCompat.shouldShowRequestPermissionRationale(UprawnieniaActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {


                        // Jeżeli uprawnienie nie zostały nadane
                        ActivityCompat.requestPermissions(UprawnieniaActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION);

                    } else {
                        //Metoda requestPermissions jest wywoływana aby uzytkownik mógł podjąć decyzję o nadaniu uprawnien
                        ActivityCompat.requestPermissions(UprawnieniaActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION);
                                            }

                }
                else {
                    Toast.makeText(UprawnieniaActivity.this, " " + Manifest.permission.ACCESS_FINE_LOCATION+ " uprawnienia zostały przyznane.", Toast.LENGTH_SHORT).show();

                }
                if(ActivityCompat.checkSelfPermission(UprawnieniaActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){




                    Toast.makeText(UprawnieniaActivity.this, " " + Manifest.permission.ACCESS_FINE_LOCATION + " Uprawnienia nie zostały nadane", Toast.LENGTH_SHORT).show();
                }



                 }

        });
        Button writeToSd = (Button) findViewById(R.id.write);
        writeToSd.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(UprawnieniaActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    // Jeżeli uprawnienia dla naszej aplikacji nie są nadane to pokazywane jest stosowne zapytanie
                    // metoda shouldShowRequestPermissionRationale zwraca true jeżeli użytkownik nie podjął decyzji
                    if (ActivityCompat.shouldShowRequestPermissionRationale(UprawnieniaActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {


                        // Jeżeli uprawnienie nie zostały nadane
                        ActivityCompat.requestPermissions(UprawnieniaActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXST);

                    } else {
                        //Metoda requestPermissions jest wywoływana aby uzytkownik mógł podjąć decyzję o nadaniu uprawnien
                        ActivityCompat.requestPermissions(UprawnieniaActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXST);
                    }

                }
                else {
                    Toast.makeText(UprawnieniaActivity.this, " " + Manifest.permission.WRITE_EXTERNAL_STORAGE + " uprawnienia zostały przyznane.", Toast.LENGTH_SHORT).show();

                }
                if(ActivityCompat.checkSelfPermission(UprawnieniaActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){




                    Toast.makeText(UprawnieniaActivity.this, " " + Manifest.permission.WRITE_EXTERNAL_STORAGE + " Uprawnienia nie zostały nadane", Toast.LENGTH_SHORT).show();
                }

            }

        });
        Button readFromSd = (Button) findViewById(R.id.read);
        readFromSd.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(UprawnieniaActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    // Jeżeli uprawnienia dla naszej aplikacji nie są nadane to pokazywane jest stosowne zapytanie
                    // metoda shouldShowRequestPermissionRationale zwraca true jeżeli użytkownik nie podjął decyzji
                    if (ActivityCompat.shouldShowRequestPermissionRationale(UprawnieniaActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {


                        // Jeżeli uprawnienie nie zostały nadane
                        ActivityCompat.requestPermissions(UprawnieniaActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXST);

                    } else {
                        //Metoda requestPermissions jest wywoływana aby uzytkownik mógł podjąć decyzję o nadaniu uprawnien
                        ActivityCompat.requestPermissions(UprawnieniaActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXST);
                    }

                }
                else {
                    Toast.makeText(UprawnieniaActivity.this, " " + Manifest.permission.READ_EXTERNAL_STORAGE + " uprawnienia zostały przyznane.", Toast.LENGTH_SHORT).show();

                }
                if(ActivityCompat.checkSelfPermission(UprawnieniaActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){




                    Toast.makeText(UprawnieniaActivity.this, " " + Manifest.permission.READ_EXTERNAL_STORAGE + " Uprawnienia nie zostały nadane", Toast.LENGTH_SHORT).show();
                }

            }

        });
        Button call = (Button) findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(UprawnieniaActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    // Jeżeli uprawnienia dla naszej aplikacji nie są nadane to pokazywane jest stosowne zapytanie
                    // metoda shouldShowRequestPermissionRationale zwraca true jeżeli użytkownik nie podjął decyzji
                    if (ActivityCompat.shouldShowRequestPermissionRationale(UprawnieniaActivity.this, Manifest.permission.CALL_PHONE)) {


                        // Jeżeli uprawnienie nie zostały nadane
                        ActivityCompat.requestPermissions(UprawnieniaActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL);

                    } else {
                        //Metoda requestPermissions jest wywoływana aby uzytkownik mógł podjąć decyzję o nadaniu uprawnien
                        ActivityCompat.requestPermissions(UprawnieniaActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL);
                    }

                }
                else {
                    Toast.makeText(UprawnieniaActivity.this, " " + Manifest.permission.CALL_PHONE + " uprawnienia zostały przyznane.", Toast.LENGTH_SHORT).show();

                }
                if(ActivityCompat.checkSelfPermission(UprawnieniaActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){




                    Toast.makeText(UprawnieniaActivity.this, " " + Manifest.permission.CALL_PHONE + " Uprawnienia nie zostały nadane", Toast.LENGTH_SHORT).show();
                }

            }

        });

        Button camera = (Button) findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(UprawnieniaActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    // Jeżeli uprawnienia dla naszej aplikacji nie są nadane to pokazywane jest stosowne zapytanie
                    // metoda shouldShowRequestPermissionRationale zwraca true jeżeli użytkownik nie podjął decyzji
                    if (ActivityCompat.shouldShowRequestPermissionRationale(UprawnieniaActivity.this, Manifest.permission.CAMERA)) {


                        // Jeżeli upr   awnienie nie zostały nadane
                        ActivityCompat.requestPermissions(UprawnieniaActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA);

                    } else {
                        //Metoda requestPermissions jest wywoływana aby uzytkownik mógł podjąć decyzję o nadaniu uprawnien
                        ActivityCompat.requestPermissions(UprawnieniaActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA);
                    }

                }
                else {
                    Toast.makeText(UprawnieniaActivity.this, " " + Manifest.permission.CAMERA + " uprawnienia zostały przyznane.", Toast.LENGTH_SHORT).show();

                }
                if(ActivityCompat.checkSelfPermission(UprawnieniaActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){




                    Toast.makeText(UprawnieniaActivity.this, " " + Manifest.permission.CAMERA + " Uprawnienia nie zostały nadane", Toast.LENGTH_SHORT).show();
                }

            }

        });
        Button accounts = (Button) findViewById(R.id.accounts);
        accounts.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(UprawnieniaActivity.this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {

                    // Jeżeli uprawnienia dla naszej aplikacji nie są nadane to pokazywane jest stosowne zapytanie
                    // metoda shouldShowRequestPermissionRationale zwraca true jeżeli użytkownik nie podjął decyzji
                    if (ActivityCompat.shouldShowRequestPermissionRationale(UprawnieniaActivity.this, Manifest.permission.GET_ACCOUNTS)) {


                        // Jeżeli uprawnienie nie zostały nadane
                        ActivityCompat.requestPermissions(UprawnieniaActivity.this, new String[]{Manifest.permission.GET_ACCOUNTS}, ACCOUNTS);

                    } else {
                        //Metoda requestPermissions jest wywoływana aby uzytkownik mógł podjąć decyzję o nadaniu uprawnien
                        ActivityCompat.requestPermissions(UprawnieniaActivity.this, new String[]{Manifest.permission.GET_ACCOUNTS}, ACCOUNTS);
                    }

                }
                else {
                    Toast.makeText(UprawnieniaActivity.this, " " + Manifest.permission.GET_ACCOUNTS + " uprawnienia zostały przyznane.", Toast.LENGTH_SHORT).show();

                }
                if(ActivityCompat.checkSelfPermission(UprawnieniaActivity.this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED){




                    Toast.makeText(UprawnieniaActivity.this, " " + Manifest.permission.GET_ACCOUNTS + " Uprawnienia nie zostały nadane", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}
