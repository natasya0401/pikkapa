package com.pikkapa.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pikkapa.R
import com.pikkapa.databinding.ActivityHomeBinding
import com.pikkapa.databinding.ActivityLocationBinding
import com.pikkapa.entity.InformationEntity
import com.pikkapa.entity.LocationEntity
import com.pikkapa.view.adapter.LocationItemAdapter

class LocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocationBinding
    private var locations : ArrayList<LocationEntity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_location)

        setToolbar()
        setAllView()
        setAllOnClick()

        locations.add(LocationEntity("Rumah Sakit 1", "Jl. Lokasi Rumah Sakit 1", "085123456789", "-7.95678280671865", "112.58977623771564"))
        locations.add(LocationEntity("Rumah Sakit 2", "Jl. Lokasi Rumah Sakit 2", "085123456789", "-7.994110125108967", "112.63401518004514"))
        locations.add(LocationEntity("Rumah Sakit 3", "Jl. Lokasi Rumah Sakit 3", "085123456789", "-7.986280123048572", "112.62486020703189"))
        locations.add(LocationEntity("Rumah Sakit 4", "Jl. Lokasi Rumah Sakit 4", "085123456789", "-7.986460744282259", "112.62498358864043"))
        locations.add(LocationEntity("Rumah Sakit 5", "Jl. Lokasi Rumah Sakit 5", "085123456789", "-7.986460744282259", "112.62498358864043"))

    }

    fun setToolbar() {
        binding.toolbar.tvTitle.text = "LOKASI RS"

        binding.toolbar.ivMenu.setOnClickListener{
            val myIntent = Intent(this, MenuActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.toolbar.ivInfo.setOnClickListener {
            val myIntent = Intent(this, AboutMeActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.footer.ivBack.setOnClickListener {
            val myIntent = Intent(this, HomeActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.footer.ivHome.setOnClickListener {
            val myIntent = Intent(this, HomeActivity::class.java)
            this.startActivity(myIntent)
        }
    }

    fun setAllView() {
        binding.rvItemLocation.layoutManager = LinearLayoutManager(this)
        binding.rvItemLocation.adapter = LocationItemAdapter(locations) {
            val myIntent = Intent(this, LocationDetailActivity::class.java)
            myIntent.putExtra("title", it.title)
            myIntent.putExtra("address", it.address)
            myIntent.putExtra("contactPerson", it.contactPerson)
            myIntent.putExtra("longitude", it.longitude)
            myIntent.putExtra("latitude", it.latitude)
            this.startActivity(myIntent)
        }
    }

    fun setAllOnClick() {

    }
}