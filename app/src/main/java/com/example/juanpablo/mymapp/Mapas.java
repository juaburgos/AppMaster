package com.example.juanpablo.mymapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapas extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    public Marker marcador;
    public double lat = 0.0;
    public double lon = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Bundle bundle = getIntent().getExtras();
        int lugar = bundle.getInt("lugar");

        switch (lugar) {
            case 1:
                LatLng sydney = new LatLng(54.6981568, 20.53385879999996);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Kaliningrad Stadium"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setZoomGesturesEnabled(true);
                mMap.setMapType(googleMap.MAP_TYPE_NORMAL);
                break;

            case 2:
                LatLng sydney2 = new LatLng(59.97304699999999, 30.220246599999996);
                mMap.addMarker(new MarkerOptions().position(sydney2).title("St. Petersburg Stadium"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney2, 15));
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setZoomGesturesEnabled(true);
                mMap.setMapType(googleMap.MAP_TYPE_NORMAL);
                break;

            case 3:
                LatLng sydney3 = new LatLng(5.7188138, 37.5547934);
                mMap.addMarker(new MarkerOptions().position(sydney3).title("Estadio de Loujniki"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney3, 15));
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setZoomGesturesEnabled(true);
                mMap.setMapType(googleMap.MAP_TYPE_NORMAL);
                break;

            case 4:
                LatLng sydney4 = new LatLng(47.20994160000001, 39.737528800000064);
                mMap.addMarker(new MarkerOptions().position(sydney4).title("Rostov Arena"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney4, 15));
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setZoomGesturesEnabled(true);
                mMap.setMapType(googleMap.MAP_TYPE_NORMAL);
                break;

            case 5:
                LatLng sydney5 = new LatLng(43.4020114, 39.9557088999999);
                mMap.addMarker(new MarkerOptions().position(sydney5).title("Estadio Olímpico de Sochi"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney5, 15));
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setZoomGesturesEnabled(true);
                mMap.setMapType(googleMap.MAP_TYPE_NORMAL);
                break;


            case 13:
                LatLng sydney13 = new LatLng(54.6981568, 20.53385879999996);
                mMap.addMarker(new MarkerOptions().position(sydney13).title("Kaliningrad Stadium"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney13));

                LatLng sydney14 = new LatLng(59.97304699999999, 30.220246599999996);
                mMap.addMarker(new MarkerOptions().position(sydney14).title("St. Petersburg Stadium"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney14));

                LatLng sydney15 = new LatLng(55.715765,37.551527);
                mMap.addMarker(new MarkerOptions().position(sydney15).title("Estadio de Loujniki"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney15));

                LatLng sydney16 = new LatLng(47.20994160000001, 39.737528800000064);
                mMap.addMarker(new MarkerOptions().position(sydney16).title("Rostov Arena"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney16));

                LatLng sydney17 = new LatLng(43.4020114, 39.9557088999999);
                mMap.addMarker(new MarkerOptions().position(sydney17).title("Estadio Olímpico de Sochi"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney17));
        }
    }

    public void agregarmark(double lat, double lon) {
        LatLng coor = new LatLng(lat, lon);
        CameraUpdate miubicacion = CameraUpdateFactory.newLatLngZoom(coor, 16);
        if (marcador != null) marcador.remove();
        {
            marcador = mMap.addMarker(new MarkerOptions().position(coor).title("tu estas aqui"));
            mMap.animateCamera(miubicacion);
        }
    }

    LocationListener locListe = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarubicacion(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    public void actualizarubicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lon = location.getLongitude();
            agregarmark(lat, lon);

        }
    }

    private void ub() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager locma = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loca = locma.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarubicacion(loca);
        locma.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,0,locListe);
    }

}
