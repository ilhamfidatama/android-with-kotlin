package com.ilhamfidatama.androidwithkotlin

import android.content.ContentValues
import android.database.Cursor
import com.ilhamfidatama.androidwithkotlin.db.DatabaseContract
import com.ilhamfidatama.androidwithkotlin.db.crypto.Cryptocurrency

object Utils {

    val cryptocurrencies: MutableList<Cryptocurrency> = mutableListOf(
        Cryptocurrency(name = "Bitcoin", lastPrice = 825561000.0, imagePath = "https://indodax.com/v2/logo/png/color/btc.png"),
        Cryptocurrency(name = "Dogecoin", lastPrice = 6000.0, imagePath = "https://indodax.com/v2/logo/png/color/doge.png"),
        Cryptocurrency(name = "Ethereum", lastPrice = 82796.0, imagePath = "https://indodax.com/v2/logo/png/color/eth.png"),
        Cryptocurrency(name = "Litecoin", lastPrice = 118368.0, imagePath = "https://indodax.com/v2/logo/png/color/ltc.png"),
        Cryptocurrency(name = "Wrapped Bitcoin", lastPrice = 93844.0, imagePath = "https://indodax.com/v2/logo/png/color/wbtc.png"),
        Cryptocurrency(name = "BitShares", lastPrice = 14532.0, imagePath = "https://indodax.com/v2/logo/png/color/bts.png"),
        Cryptocurrency(name = "Coti", lastPrice = 93844.0, imagePath = "https://indodax.com/v2/logo/png/color/coti.png")
    )

    val images = mutableListOf<String>(
            "https://indodax.com/v2/logo/png/color/ten.png",
            "https://indodax.com/v2/logo/png/color/act.png",
            "https://indodax.com/v2/logo/png/color/ada.png",
            "https://indodax.com/v2/logo/png/color/bnb.png",
            "https://indodax.com/v2/logo/png/color/btt.png",
            "https://indodax.com/v2/logo/png/color/firo.png",
            "https://indodax.com/v2/logo/png/color/ignis.png",
            "https://indodax.com/v2/logo/png/color/nxt.png",
            "https://indodax.com/v2/logo/png/color/zil.png",
    )

    fun toCryptocurrency(cursor: Cursor): Cryptocurrency{
        val crypto = Cryptocurrency()
        crypto.id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.CryptoColumns._ID))
        crypto.name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.CryptoColumns.NAME))
        crypto.lastPrice = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.CryptoColumns.LAST_PRICE))
        crypto.imagePath = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.CryptoColumns.IMAGE_PATH))

        return crypto
    }

    fun toContentValues(cryptocurrency: Cryptocurrency): ContentValues{
        val values = ContentValues()
        values.put(DatabaseContract.CryptoColumns.NAME, cryptocurrency.name)
        values.put(DatabaseContract.CryptoColumns.LAST_PRICE, cryptocurrency.lastPrice)
        values.put(DatabaseContract.CryptoColumns.IMAGE_PATH, cryptocurrency.imagePath)

        return values
    }
}