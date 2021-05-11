package com.ilhamfidatama.androidwithkotlin.db.crypto

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.ilhamfidatama.androidwithkotlin.Utils
import com.ilhamfidatama.androidwithkotlin.db.DatabaseContract.CryptoColumns
import com.ilhamfidatama.androidwithkotlin.db.DatabaseContract.CryptoColumns.Companion._ID
import com.ilhamfidatama.androidwithkotlin.db.DatabaseHelper

class CryptoHelper(context: Context) {

    private var dataBaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = CryptoColumns.TABLE_NAME

        private var INSTANCE: CryptoHelper? = null

        fun getInstance(context: Context): CryptoHelper = INSTANCE ?: synchronized(this){
            INSTANCE ?: CryptoHelper(context)
        }
    }

    @Throws(SQLException::class)
    fun open(){
        database = dataBaseHelper.writableDatabase
    }

    fun close(){
        dataBaseHelper.close()
        if (database.isOpen){
            database.close()
        }
    }

    fun getAll(): MutableList<Cryptocurrency>{
        val cryptocurrencies = mutableListOf<Cryptocurrency>()
        val cursor = database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC",
            null
        )
        cursor.moveToFirst()
        if (cursor.count > 0){
            do {
                val crypto = Utils.toCryptocurrency(cursor)
                cryptocurrencies.add(crypto)

                cursor.moveToNext()
            }while (!cursor.isAfterLast)
        }

        return cryptocurrencies
    }

    fun getById(id: String): Cryptocurrency{
        var crypto = Cryptocurrency()
        val cursor = database.query(
            DATABASE_TABLE,
            null,
            "$_ID = $id",
            null,
            null,
            null,
            null,
            null
        )
        cursor.moveToFirst()
        if (cursor.count == 1){
            crypto = Utils.toCryptocurrency(cursor)
        }
        return crypto
    }

    fun insertData(values: ContentValues): Long = database.insert(DATABASE_TABLE, null, values)

    fun updateData(id: String, values: ContentValues): Int = database.update(
        DATABASE_TABLE,
        values,
        "$_ID = $id",
        null
    )

    fun deleteById(id: String): Int = database.delete(DATABASE_TABLE, "$_ID = $id", null)

}