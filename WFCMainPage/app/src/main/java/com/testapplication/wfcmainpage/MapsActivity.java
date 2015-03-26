package com.testapplication.wfcmainpage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends ActionBarActivity implements View.OnClickListener {
    private final LatLng LOCATION_WFC = new LatLng(52.354484, 4.841304);
    private final String LOCATION_WFC_STRING = "Koningin Wilhelminaplein 13 1062 HH Amsterdam";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setTitle(getString(R.string.maps_title_text));

        ImageButton buttonCar = (ImageButton) findViewById(R.id.buttonCar);
        ImageButton buttonWalk = (ImageButton) findViewById(R.id.buttonWalk);
        ImageButton buttonBicycle = (ImageButton) findViewById(R.id.buttonBicycle);
        ImageButton buttonOV = (ImageButton) findViewById(R.id.buttonOV);

        buttonCar.setOnClickListener(this);
        buttonWalk.setOnClickListener(this);
        buttonBicycle.setOnClickListener(this);
        buttonOV.setOnClickListener(this);

        GoogleMap mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        CameraUpdate start = CameraUpdateFactory.newLatLngZoom(LOCATION_WFC, 18);

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker pMarker) {
                // Getting view from the layout file info_window_layout
                View v = getLayoutInflater().inflate(R.layout.info_window_layout, null);

                // Getting the position from the marker
                LatLng latLng = pMarker.getPosition();

                // Getting reference to the TextView to set info text
                TextView infoText = (TextView) v.findViewById(R.id.tv_info_window_text);

                // Setting the info text
                //infoText.setText());

                return v;
            }
        });

        MarkerOptions tempMarkerOptions = new MarkerOptions();
        tempMarkerOptions.position(LOCATION_WFC);
        Marker tempMarker = mMap.addMarker(tempMarkerOptions);
        tempMarker.showInfoWindow();
        tempMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.kleinlogo));

        mMap.animateCamera(start);
        mMap.moveCamera(start);
        mMap.setMyLocationEnabled(true);
    }

    public void startCar(View v) {
        //Start Google Maps Drive, Navigate immediately
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + LOCATION_WFC_STRING);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void startWalk(View v) {
        //Start Google Maps Walk, Navigate immediately
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + LOCATION_WFC_STRING + "&mode=w");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void startBicycle(View v) {
        //Start Google Maps Bike, Navigate immediately
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + LOCATION_WFC_STRING + "&mode=b");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void startOV(View v) {
        //Start Google Maps Public Transport, Navigate immediately
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + LOCATION_WFC_STRING + "&mode=r");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.buttonCar:
                startCar(v);
                break;
            case R.id.buttonWalk:
                startWalk(v);
                break;
            case R.id.buttonBicycle:
                startBicycle(v);
                break;
            case R.id.buttonOV:
                startOV(v);
                break;
        }
    }
}