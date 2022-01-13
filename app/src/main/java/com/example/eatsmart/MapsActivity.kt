package com.example.eatsmart

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.eatsmart.databinding.ActivityMapsBinding
import com.google.android.gms.location.LocationServices

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val r1 = LatLng(31.973188,35.841687)
        val r2 = LatLng(31.973312,35.841313)
        val r3 = LatLng(31.974312,35.846563)
        val r4 = LatLng(31.973687,35.840312)
        val r5 = LatLng(31.974562,35.848813)
        val r6 = LatLng(31.975062,35.852812)
        mMap.addMarker(MarkerOptions().position(r1).title("Ray's Fried Chicken"))
        mMap.addMarker(MarkerOptions().position(r2).title("Avotaco"))
        mMap.addMarker(MarkerOptions().position(r3).title("The Fries Shop"))
        mMap.addMarker(MarkerOptions().position(r4).title("Ahwitna Cafe"))
        mMap.addMarker(MarkerOptions().position(r5).title("DAJAJ"))
        mMap.addMarker(MarkerOptions().position(r6).title("Shawerma Al Hakam"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(r1))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val allergiesList = intent.getSerializableExtra( "key" )

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                Array(1){android.Manifest.permission.ACCESS_COARSE_LOCATION},111)
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                if (location != null) {
                    Toast.makeText(this, location.latitude.toString(), Toast.LENGTH_LONG).show()
                }
                // Got last known location. In some rare situations this can be null.
            }
//        binding = ActivityMapsBinding.inflate(layoutInflater)
//        setContentView(binding.root)


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode != 111 && grantResults[0]==PackageManager.PERMISSION_DENIED)
        {
            Toast.makeText(this, "Could not get location", Toast.LENGTH_SHORT).show()
        }
    }
}