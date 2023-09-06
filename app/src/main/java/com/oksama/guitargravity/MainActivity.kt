package com.oksama.guitargravity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    private lateinit var rvGuitar: RecyclerView
    private var listGuitar = ArrayList<Guitar>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.main_toolbar))

        rvGuitar = findViewById(R.id.rv_guitar)
        rvGuitar.setHasFixedSize(true)
        listGuitar.addAll(getListGuitar())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.act_profile -> {
                val intentProfile = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(intentProfile)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getListGuitar(): ArrayList<Guitar>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataMaker = resources.getStringArray(R.array.data_maker)
        val dataPicture = resources.getStringArray(R.array.data_picture)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataIsFav = resources.getIntArray(R.array.data_is_fav)

        val listGuitar = ArrayList<Guitar>()
        for(i in dataName.indices) {
            val guitar = Guitar(dataName[i], dataPicture[i], dataMaker[i], dataPrice[i], dataDescription[i], dataIsFav[i])
            listGuitar.add(guitar)
        }
        return listGuitar
    }

    private fun showRecyclerList() {
        rvGuitar.layoutManager = LinearLayoutManager(this)
        val listGuitarAdapter = ListGuitarAdapter(listGuitar)
        rvGuitar.adapter = listGuitarAdapter

        listGuitarAdapter.setOnClickCallback(object: ListGuitarAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Guitar) {
                val intentDetail = Intent(this@MainActivity, GuitarDetailActivity::class.java)
                intentDetail.putExtra(GuitarDetailActivity.EXTRA_GUITAR, data)
                startActivity(intentDetail)
            }
        })
    }
}
