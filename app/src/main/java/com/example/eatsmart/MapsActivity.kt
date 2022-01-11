package com.example.eatsmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.eatsmart.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivityMapsBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val r1 = LatLng(31.973188,35.841687)
        val r2 = LatLng(31.973312,35.841313)
        val r3 = LatLng(31.974312,35.846563)
        val r4 = LatLng(31.973687,35.840312)
        val r5 = LatLng(31.974562,35.848813)
        val r6 = LatLng(31.975062,35.852812)
        mMap.addMarker(MarkerOptions().position(r1).title("Ray's Fried Chicken\n(Hypertension)"))
        mMap.addMarker(MarkerOptions().position(r2).title("Avotaco\n(Anemia)"))
        mMap.addMarker(MarkerOptions().position(r3).title("The Fries Shop\n(Celiac Disease)"))
        mMap.addMarker(MarkerOptions().position(r4).title("Ahwitna Cafe\n(Diabetes)"))
        mMap.addMarker(MarkerOptions().position(r5).title("DAJAJ\n(Rickets)"))
        mMap.addMarker(MarkerOptions().position(r6).title("Shawerma Al Hakam\n(Marasmus)"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(r1))
    }
}