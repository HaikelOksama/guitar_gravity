package com.oksama.guitargravity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val ivAvatar = findViewById<ImageView>(R.id.imageView)
        val url = "https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_3,f_auto,g_face,h_130,q_auto:good,w_130/v1/gcs/platform-data-dsc/avatars/haikel_oksama_pd2N76Z.jpg"
        Glide.with(this).load(url).into(ivAvatar)
    }
}