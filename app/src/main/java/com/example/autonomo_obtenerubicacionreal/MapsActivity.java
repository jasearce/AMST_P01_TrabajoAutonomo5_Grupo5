package com.example.autonomo_obtenerubicacionreal;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    DatabaseReference mDatabase;
    private ArrayList<Marker> tmpRealMarkers = new ArrayList<>();
    private ArrayList<Marker> realTimeMarkers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Grupo");
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
        /*Ubicacion ubi = new Ubicacion();
        Map<String, String> ubicaciones = new HashMap<String, String>();
        ubicaciones.put("latitud",String.valueOf(ubi.getLatitud()));
        ubicaciones.put("longitud", String.valueOf(ubi.getLongitud()));
        mDatabase.child("Grupo 5").child("ubicaciones");
        mDatabase.setValue("Id");
        mDatabase.child("Id").child("latitud").setValue(ubi.getLatitud());
        mDatabase.child("Id").child("longitud").setValue(ubi.getLongitud());
        */
        //Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        LatLng FCNM_espol = new LatLng(-2.1458275, -79.9683663);
        LatLng sweet_espol = new LatLng(-2.1459422, -79.9673716);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(FCNM_espol).title("Estamos en FCNM de ESPOL"));
        mMap.addMarker(new MarkerOptions().position(sweet_espol).title("Estamos en el sweet de ESPOL"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
