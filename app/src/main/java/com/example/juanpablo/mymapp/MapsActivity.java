package com.example.juanpablo.mymapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.juanpablo.mymapp.API.DatosAPI;
import com.example.juanpablo.mymapp.models.CentrosAyuda;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import android.content.Intent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        retrofit=new Retrofit.Builder().baseUrl("https://www.datos.gov.co/resource/").addConverterFactory(GsonConverterFactory.create()).build();

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

        // Add a marker in Sydney and move the camera
        DatosAPI service = retrofit.create(DatosAPI.class);

        final Call<List<CentrosAyuda>> institucionCall = service.obtenerLista();

        institucionCall.enqueue(new Callback<List<CentrosAyuda>>()
        {
            @Override
            public void onResponse(Call<List<CentrosAyuda>> call, Response<List<CentrosAyuda>> response) {

                if (response.isSuccessful()) {
                    List institucion = response.body();
                    for (int i = 0; i < institucion.size(); i++) {
                        CentrosAyuda u = (CentrosAyuda) institucion.get(i);
                        LatLng sydney = new LatLng(u.motrarLatitud(), u.motrarLongitud());
                        mMap.addMarker(new MarkerOptions().position(sydney).title(u.getMunicipio() + " " + u.getDepartamento() + " "+ u.getTipo()));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                        mMap.getUiSettings().setZoomControlsEnabled(true);
                    }
                } else {

                    Toast notificacion = Toast.makeText(MapsActivity.this, "Error", Toast.LENGTH_LONG);
                    notificacion.show();
                }

            }

            @Override
            public void onFailure(Call<List<CentrosAyuda>> call, Throwable t) {

                Toast notificacion = Toast.makeText(MapsActivity.this, "Error", Toast.LENGTH_LONG);
                notificacion.show();

            }
        });
    }
}
