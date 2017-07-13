package com.example.a15017206.ps_abc_pte_ltd;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btnNorth, btnEast, btnCentral;
    private GoogleMap map;
    TextView tv;
    String text;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNorth = (Button) findViewById(R.id.btnNorth);
        btnEast = (Button) findViewById(R.id.btnEast);
        btnCentral = (Button) findViewById(R.id.btnCentral);
        tv = (TextView) findViewById(R.id.tv);
        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner.getSelectedItemPosition()==0){
                    btnNorth.performClick();
                }
                else if (spinner.getSelectedItemPosition()==1){
                    btnEast.performClick();
                }
                else if (spinner.getSelectedItemPosition()==2){
                    btnCentral.performClick();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        text = "ABC Pte Ltd \n \n We now have 3 branches. Look below for the address & info.";

        tv.setText(text);

        FragmentManager fm = getSupportFragmentManager();
        final SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                map = googleMap;

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);

                UiSettings ui = map.getUiSettings();
                ui.setZoomControlsEnabled(true);
                ui.setCompassEnabled(true);

                final LatLng hqNorth = new LatLng(1.442704, 103.776116);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(hqNorth, 15));
                final Marker cp = map.addMarker(new
                        MarkerOptions()
                        .position(hqNorth)
                        .title("North - HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm" +
                                "Tel:65433456")
                        .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.btn_star_big_on)));

                final LatLng hqCentral = new LatLng(1.298146, 103.847431);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(hqCentral, 15));
                final Marker cp2 = map.addMarker(new
                        MarkerOptions()
                        .position(hqCentral)
                        .title("Causeway Point")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.arrow_up_float)));

                final LatLng hqEast = new LatLng(1.367428, 103.928043);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(hqEast, 15));
                final Marker cp3 = map.addMarker(new
                        MarkerOptions()
                        .position(hqEast)
                        .title("Causeway Point")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\"\n")
                        .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.alert_dark_frame)));


//                LatLng poi_RP = new LatLng(1.44224, 103.785733);
//                Marker rp = map.addMarker(new
//                        MarkerOptions()
//                        .position(poi_RP)
//                        .title("Republic Polytechnic")
//                        .snippet("C347 Android Programming II")
//                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (marker.equals(cp)) {
                            Toast.makeText(getApplicationContext(), "Title: " + cp.getTitle(), Toast.LENGTH_LONG).show();
                        } else if (marker.equals(cp2)) {
                            Toast.makeText(getApplicationContext(), "Title: " + cp.getTitle(), Toast.LENGTH_LONG).show();
                        } else if (marker.equals(cp3)) {
                            Toast.makeText(getApplicationContext(), "Title: " + cp.getTitle(), Toast.LENGTH_LONG).show();
                        }

                        return false;
                    }
                });


                btnCentral.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(">>", "onClick: ");
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(hqCentral, 14));

                    }
                });

                btnNorth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(hqNorth, 14));
                    }
                });

                btnEast.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(hqEast, 14));
                    }
                });










            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION);
                    if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                        map.setMyLocationEnabled(true);
                    } else {
                        Log.e("GMap - Permission", "GPS access has not been granted");
                    }
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
