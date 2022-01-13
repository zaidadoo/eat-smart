package com.example.eatsmart

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import java.util.ArrayList




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

        val allergiesList = intent.getSerializableExtra("key") as ArrayList<String>

        Toast.makeText(applicationContext, "The allergies/diseases selected: " +
                allergiesList.toString(), Toast.LENGTH_SHORT).show()

        if(allergiesList.contains("celiac")){
            val r1 = LatLng(31.952562,35.855812)
            mMap.addMarker(MarkerOptions().position(r1).title("Cozmo"))

            val r2 = LatLng(31.949312,35.929063)
            mMap.addMarker(MarkerOptions().position(r2).title("Al-Sufara"))

            mMap.moveCamera(CameraUpdateFactory.newLatLng(r1))
        }

        if(allergiesList.contains("hypertension")){
            val r1 = LatLng(31.956688,35.859562)
            mMap.addMarker(MarkerOptions().position(r1).title("TheFitFactory"))

            mMap.moveCamera(CameraUpdateFactory.newLatLng(r1))
        }

        if(allergiesList.contains("marasmus")){
            val r1 = LatLng(31.952562,35.855812)
            mMap.addMarker(MarkerOptions().position(r1).title("Cozmo"))

            val r2 = LatLng(31.975563,35.884313)
            mMap.addMarker(MarkerOptions().position(r2).title("Healthy Fresh Amman"))

            mMap.moveCamera(CameraUpdateFactory.newLatLng(r1))
        }

        if(allergiesList.contains("diabetes")){
            val r1 = LatLng(31.957813,35.865938)
            mMap.addMarker(MarkerOptions().position(r1).title("OJ's - Super Fast Salads"))

            mMap.moveCamera(CameraUpdateFactory.newLatLng(r1))
        }

        if(allergiesList.contains("anemia")){
            val r1 = LatLng(31.939813,35.855937)
            mMap.addMarker(MarkerOptions().position(r1).title("Salad Mania"))

            val r2 = LatLng(31.953313,35.884062)
            mMap.addMarker(MarkerOptions().position(r2).title("Seed"))

            mMap.moveCamera(CameraUpdateFactory.newLatLng(r1))
        }

        if(allergiesList.contains("rickets")){
            val r1 = LatLng(31.982188,35.875812)
            mMap.addMarker(MarkerOptions().position(r1).title("Muscle Kitchen"))

            val r2 = LatLng(31.948313,35.865813)
            mMap.addMarker(MarkerOptions().position(r2).title("Primrose Cafe"))

            mMap.moveCamera(CameraUpdateFactory.newLatLng(r1))
        }

        if(allergiesList.contains("kwashiorkor")){
            val r1 = LatLng(31.979312,35.856188)
            mMap.addMarker(MarkerOptions().position(r1).title("Fitness Kitchen"))

            val r2 = LatLng(31.981187,35.878438)
            mMap.addMarker(MarkerOptions().position(r2).title("Juicitarian"))

            mMap.moveCamera(CameraUpdateFactory.newLatLng(r1))
        }

        //these were all test values
//        val r1 = LatLng(31.973188,35.841687)
//        val r2 = LatLng(31.973312,35.841313)
//        val r3 = LatLng(31.974312,35.846563)
//        val r4 = LatLng(31.973687,35.840312)
//        val r5 = LatLng(31.974562,35.848813)
//        val r6 = LatLng(31.975062,35.852812)
//        mMap.addMarker(MarkerOptions().position(r1).title("Ray's Fried Chicken"))
//        mMap.addMarker(MarkerOptions().position(r2).title("Avotaco"))
//        mMap.addMarker(MarkerOptions().position(r3).title("The Fries Shop"))
//        mMap.addMarker(MarkerOptions().position(r4).title("Ahwitna Cafe"))
//        mMap.addMarker(MarkerOptions().position(r5).title("DAJAJ"))
//        mMap.addMarker(MarkerOptions().position(r6).title("Shawerma Al Hakam"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(r1))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

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
                    //Toast.makeText(this, location.latitude.toString(), Toast.LENGTH_LONG).show()
                }
                // Got last known location. In some rare situations this can be null.
            }
//        binding = ActivityMapsBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.appServices -> {
                val intent = Intent(this, AppServices::class.java)
                startActivity(intent)
            }

            R.id.userSettings -> {
                val intent = Intent(this, UserSettings::class.java)
                startActivity(intent)
            }

            R.id.editAllergiesDiseases -> {
                val intent = Intent(this, AllergiesSelection::class.java)
                startActivity(intent)
            }

            R.id.signOut -> {
                val intent = Intent(this, SignIn::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
