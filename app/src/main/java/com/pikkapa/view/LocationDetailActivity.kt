package com.pikkapa.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pikkapa.R
import com.pikkapa.databinding.ActivityInformationDetailBinding
import com.pikkapa.databinding.ActivityLocationDetailBinding
import kotlin.math.log

class LocationDetailActivity : AppCompatActivity(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null
    private lateinit var binding : ActivityLocationDetailBinding

    private var lttd = 51.5074 // Default latitude (e.g., London)
    private var lgtd = -0.1278 // Default longitude (e.g., London)

    private var title = "LOKASI RS"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_location_detail)


        binding.footer.ivBack.setOnClickListener {
            val myIntent = Intent(this, LocationActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.toolbar.tvTitle.text = "LOKASI RS"

        binding.footer.ivHome.setOnClickListener {
            val myIntent = Intent(this, HomeActivity::class.java)
            this.startActivity(myIntent)
        }


        if (intent.hasExtra("title")) {
            binding.tvLocationTitle.text = intent.getStringExtra("title")
            title = intent.getStringExtra("title").toString()
            Log.d("title", "ada title")
        }

        if (intent.hasExtra("address")) {
            binding.tvLocationDetail.text = intent.getStringExtra("address")
            Log.d("address", "ada address")
        }

        if (intent.hasExtra("contactPerson")) {
            binding.tvLocationCp.text = intent.getStringExtra("contactPerson")
            Log.d("contactPerson", "ada CP")
        }

        if (intent.hasExtra("longitude")) {
            lgtd = intent.getStringExtra("longitude").toString().toDouble()
        }

        if (intent.hasExtra("latitude")) {
            lttd = intent.getStringExtra("latitude").toString().toDouble()
        }


        val mapFragment = binding.mapView
        mapFragment.onCreate(savedInstanceState)
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val coordinate = LatLng(lgtd, lttd)
        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 15f))
        mMap?.uiSettings?.isScrollGesturesEnabled = true
        mMap?.uiSettings?.isZoomControlsEnabled = true
        mMap?.uiSettings?.isZoomGesturesEnabled = true
        mMap?.addMarker(MarkerOptions().position(coordinate).title(title))
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(coordinate))

    }
}