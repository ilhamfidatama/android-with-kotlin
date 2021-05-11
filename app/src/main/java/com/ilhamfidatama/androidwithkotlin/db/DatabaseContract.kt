package com.ilhamfidatama.androidwithkotlin.db

import android.provider.BaseColumns

class DatabaseContract {

    class CryptoColumns: BaseColumns{
        companion object {
            const val TABLE_NAME = "crypto"
            const val _ID = "id"
            const val NAME = "name"
            const val LAST_PRICE = "lastPrice"
            const val IMAGE_PATH = "imagePath"
        }
    }

}