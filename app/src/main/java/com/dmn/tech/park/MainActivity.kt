package com.dmn.tech.park

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val status = googleApiAvailability.isGooglePlayServicesAvailable(this)

        if (status != ConnectionResult.SUCCESS) {
            googleApiAvailability.getErrorDialog(this, status, 1)?.show()
            return
        }

        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val location = LatLng(41.56, 2.01) // Ejemplo: Sydney
        map.addMarker(MarkerOptions().position(location).title("Marker in Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
        map.setOnMapClickListener {
            map.clear()
            map.addMarker(MarkerOptions().position(it).title("New Marker"))
        }
    }
}