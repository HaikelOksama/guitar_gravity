package com.oksama.guitargravity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide

class GuitarDetailActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_GUITAR = "extra_guitar"
    }
    private lateinit var guitarDetail: Guitar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guitar_detail)

        val actionBar :Toolbar = findViewById(R.id.detail_toolbar)
        setSupportActionBar(actionBar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val tvName = findViewById<TextView>(R.id.tv_detail_name)
        val tvMaker = findViewById<TextView>(R.id.tv_detail_maker)
        val tvPrice = findViewById<TextView>(R.id.tv_detail_price)
        val tvDescription = findViewById<TextView>(R.id.tv_detail_description)
        val ivPicture = findViewById<ImageView>(R.id.iv_detail)
        val btnShare = findViewById<ImageButton>(R.id.action_share)
        btnShare.setOnClickListener(this)

        val guitar = if(Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_GUITAR, Guitar::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_GUITAR)
        }

        if(guitar != null) {
            guitarDetail = guitar
            supportActionBar?.title = guitar.name
            tvName.text = guitar.name
            tvMaker.text = guitar.maker
            tvPrice.text = guitar.price
            tvDescription.text = guitar.description
            Glide.with(this).load(guitar.picture).into(ivPicture)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun shareItem(text: String) {
        val share :Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(share, null)
        startActivity(shareIntent)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.action_share -> {
                val name = guitarDetail.name
                val maker = guitarDetail.maker
                val price = guitarDetail.price
                shareItem("Check Out this Beautiful Guitar!\n$name by $maker , Currently with price of $price !!")
            }
        }
    }
}