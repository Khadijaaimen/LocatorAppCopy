package com.example.latlong.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.latlong.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

public class LatLongActivity extends AppCompatActivity {

    private GpsTracker gpsTracker;
    private TextView tvLatitude,tvLongitude,tvLatitude2,tvLongitude2;
    private Button logout, profile;
    GoogleApiClient GoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_lat_long);

        tvLatitude = (TextView)findViewById(R.id.latitude);
        tvLongitude = (TextView)findViewById(R.id.longitude);
        tvLatitude2 = (TextView)findViewById(R.id.latitude2);
        tvLongitude2 = (TextView)findViewById(R.id.longitude2);
        logout = findViewById(R.id.logoutButton);
        profile = findViewById(R.id.profileButton);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(LatLongActivity.this, MainActivity.class));
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(LatLongActivity.this, ProfileActivity.class));
                    finish();
            }
        });


        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getLocation(View view){
        gpsTracker = new GpsTracker(LatLongActivity.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitudeFromNetwork();
            double longitude = gpsTracker.getLongitudeFromNetwork();
            tvLatitude.setText(String.valueOf(latitude));
            tvLongitude.setText(String.valueOf(longitude));
        }else{
            gpsTracker.showSettingsAlert();
        }
    }

    public void getLocationGPS(View view){
        gpsTracker = new GpsTracker(LatLongActivity.this);
        if(gpsTracker.canGetLocation()){
            double latitude2 = gpsTracker.getLatitudeFromGPS();
            double longitude2 = gpsTracker.getLongitudeFromGPS();
            tvLatitude2.setText(String.valueOf(latitude2));
            tvLongitude2.setText(String.valueOf(longitude2));
        }else{
            gpsTracker.showSettingsAlert();
        }
    }
}