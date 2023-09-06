package com.oksama.guitargravity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Guitar(
    val name: String,
    val picture: String,
    val maker: String,
    val price: String,
    val description: String,
    var isFav: Int = 0,
): Parcelable

//var guitars = mutableMapOf<Int, Guitar>(
//    1 to Guitar("M-100", "ESP LTD", 700, isFav = true),
//    2 to Guitar("Les Paul Classic", "Gibson", 2500),
//    3 to Guitar("Stratocaster California Series", "Fender", 1800)
//)
//
//var listGuitars = ArrayList<Guitar>()
