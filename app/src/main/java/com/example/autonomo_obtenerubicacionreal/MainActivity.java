package com.example.autonomo_obtenerubicacionreal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS;
    private FusedLocationProviderClient fusedLocationClient;
    DatabaseReference mDatabase;
    private Button btnMaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Grupo");
        denunciar();
        btnMaps = (Button)findViewById(R.id.btn_denuncia);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btn_denuncia:
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
                System.out.println("Cambio a segunda pantalla");
                break;
        }

    }
    /**
     * Autor: Javier Arce
     * Funcion denunciar: Tiene como objetivo tomar la ubicacion en tiempo real del celular
     * mediante un fusedLocationClient para subirlo a la base de datos no relacional
     * en Firebase
    **/
    private void denunciar() {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);

            return;
        }
        //Conocer la ubicacion mas reciente
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            Log.e("Latitud: ", + location.getLatitude()+ "Longitud: "+ location.getLongitude());
                            Map<String, Object> latLang = new HashMap<>();
                            latLang.put("latitud",location.getLatitude());
                            latLang.put("longitud",location.getLongitude());
                            mDatabase.child("Grupo 5").child("ubicaciones").setValue(latLang);
                        }
                    }
                });
    }
}
