package com.ilhamfidatama.androidwithkotlin.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ilhamfidatama.androidwithkotlin.Utils
import com.ilhamfidatama.androidwithkotlin.db.DatabaseContract.CryptoColumns

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "androidwithkotlin"

        private const val DATABASE_VERSION = 1

        private const val SQL_CREATE_TABLE_CRYPTO = "CREATE TABLE ${CryptoColumns.TABLE_NAME}" +
                " (${CryptoColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${CryptoColumns.NAME} TEXT NOT NULL," +
                " ${CryptoColumns.LAST_PRICE} DOUBLE NOT NULL," +
                " ${CryptoColumns.IMAGE_PATH} TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_CRYPTO)
        for(crypto in Utils.cryptocurrencies){
            val values = Utils.toContentValues(crypto)
            db.insert(CryptoColumns.TABLE_NAME, null, values)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${CryptoColumns.TABLE_NAME}")
        onCreate(db)
    }

}