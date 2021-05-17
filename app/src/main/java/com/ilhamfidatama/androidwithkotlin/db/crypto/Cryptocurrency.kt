package com.ilhamfidatama.androidwithkotlin.db.crypto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cryptocurrency(
    var id: Int = 0,
    var name: String = "",
    var lastPrice: Double = 0.0,
    var imagePath: String = ""
): Parcelable