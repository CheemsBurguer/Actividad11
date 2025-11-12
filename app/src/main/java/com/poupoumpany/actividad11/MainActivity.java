package com.poupoumpany.actividad11;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import android.location.Address;
import android.location.Geocoder;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText etDireccion;
    private Button btnBuscar;
    private Spinner spinnerMapType;

    private Marker currentLocationMarker;
    private LatLng lastSearchedLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDireccion = findViewById(R.id.etDireccion);
        btnBuscar = findViewById(R.id.btnBuscar);
        spinnerMapType = findViewById(R.id.spinnerMapType);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setupSpinner();

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarDireccion_Simulado();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng mexicoCity = new LatLng(19.4326, -99.1332);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mexicoCity, 10));
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.map_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMapType.setAdapter(adapter);

        spinnerMapType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mMap != null) {
                    cambiarTipoMapa(position);
                    if (lastSearchedLocation != null) {
                        agregarMarcador(lastSearchedLocation);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void cambiarTipoMapa(int position) {
        if (mMap == null) return;
        switch (position) {
            case 0: mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); break;
            case 1: mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); break;
            case 2: mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); break;
        }
    }

    private void buscarDireccion_Simulado() {
        String direccionTexto = etDireccion.getText().toString().toLowerCase().trim();
        if (direccionTexto.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa una dirección", Toast.LENGTH_SHORT).show();
            return;
        }

        if (direccionTexto.contains("tecmi casita")) {
            lastSearchedLocation = new LatLng(19.685121, -99.213086);
        } else if (direccionTexto.contains("bellas artes")) {
            lastSearchedLocation = new LatLng(19.4352, -99.1412);
        } else if (direccionTexto.contains("ultima ubicacion conocida de maram")) {
            lastSearchedLocation = new LatLng(19.685377, -99.209676);
        } else {
            Toast.makeText(this, "Dirección simulada no encontrada", Toast.LENGTH_SHORT).show();
            lastSearchedLocation = null;
            return;
        }

        Toast.makeText(this, "Mostrando ubicación para: " + direccionTexto, Toast.LENGTH_SHORT).show();
        agregarMarcador(lastSearchedLocation);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastSearchedLocation, 15));
    }

    private void agregarMarcador(LatLng latLng) {
        if (mMap == null) return;
        if (currentLocationMarker != null) {
            currentLocationMarker.remove();
        }

        float markerColor;
        int mapTypePosition = spinnerMapType.getSelectedItemPosition();
        switch (mapTypePosition) {
            case 1: markerColor = BitmapDescriptorFactory.HUE_RED; break;
            case 2: markerColor = BitmapDescriptorFactory.HUE_GREEN; break;
            default: markerColor = BitmapDescriptorFactory.HUE_AZURE; break;
        }

        String snippet = String.format(Locale.getDefault(), "Lat: %.4f, Lng: %.4f", latLng.latitude, latLng.longitude);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title("Ubicación Encontrada")
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.defaultMarker(markerColor));
        currentLocationMarker = mMap.addMarker(markerOptions);
    }
}