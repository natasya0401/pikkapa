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

        locations.add(LocationEntity("RSUD Dr. Saiful Anwar Malang", "Jl. Jaksa Agung Suprapto No.2, Klojen, Kec. Klojen, Kota Malang, Jawa Timur 65112", "081555606668", "-7.972251503711629", "112.63138576821063"))
        locations.add(LocationEntity("Lavalette General Hospital", "Jl. W.R. Supratman No.10, Rampal Celaket, Kec. Klojen, Kota Malang, Jawa Timur 65111", "0881023138505", "-7.965696252827271", "112.63787778019133"))
        locations.add(LocationEntity("Rumah Sakit Tk. II dr. Soepraoen", "Jl. S. Supriadi No.22, Sukun, Kec. Sukun, Kota Malang, Jawa Timur 65112", "081132299222", "-7.989879929066666", "112.62049022436923"))
        locations.add(LocationEntity("Persada Hospital", "Jl. Panji Suroso Kompleks Araya Business Center Kav.2-4, Kota Malang 65126", "0341-2996299", "-7.93483468572546", "112.6502128169738"))
        locations.add(LocationEntity("RSI Aisyiyah Malang", "Jl. Sulawesi No.16, Kasin, Kec. Klojen, Kota Malang, Jawa Timur 65117", "081234468808", "-7.9886124387788096", "112.62547543786255"))
        locations.add(LocationEntity("Rumah Sakit Panti Nirmala", "Jl. Kebalen Wetan No.2-8, Kotalama, Kec. Kedungkandang, Kota Malang, Jawa Timur 65134", "081133320666", "-7.994163441109994", "112.6340902339901"))
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
//            val myIntent = Intent(this, HomeActivity::class.java)
//            this.startActivity(myIntent)
            onBackPressed()
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