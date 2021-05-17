package com.ilhamfidatama.androidwithkotlin.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ilhamfidatama.androidwithkotlin.R
import com.ilhamfidatama.androidwithkotlin.crypto.CryptocurrencyFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout, CryptocurrencyFragment())
            .commit()
    }
}