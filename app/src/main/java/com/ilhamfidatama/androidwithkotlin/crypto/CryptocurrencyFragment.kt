package com.ilhamfidatama.androidwithkotlin.crypto

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilhamfidatama.androidwithkotlin.R
import com.ilhamfidatama.androidwithkotlin.Utils
import com.ilhamfidatama.androidwithkotlin.db.DatabaseContract
import com.ilhamfidatama.androidwithkotlin.db.crypto.Cryptocurrency
import com.ilhamfidatama.androidwithkotlin.db.crypto.CryptoHelper
import kotlinx.android.synthetic.main.fragment_cryptocurrency.*


class CryptocurrencyFragment : Fragment() {
    private lateinit var cryptoAdapter: CryptoAdapter
    private lateinit var cryptoHelper: CryptoHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cryptocurrency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setup database
        cryptoHelper = CryptoHelper.getInstance(requireContext())
        cryptoHelper.open()

        //setup recycler view with the adapter
        cryptoAdapter = CryptoAdapter()
        rv_crypto.adapter = cryptoAdapter
        rv_crypto.setHasFixedSize(true)
        rv_crypto.layoutManager = LinearLayoutManager(context)

        //get crypto from database SQLite
        loadCrypto()

        btn_save.setOnClickListener {
            saveData()
        }

        btn_delete.setOnClickListener {
            deleteData()
        }

        btn_update.setOnClickListener {
            updateData()
        }

        btn_insert.setOnClickListener {
            insertData()
        }
    }

    private fun loadCrypto(){
        val cryptocurrencies = cryptoHelper.getAll()
        cryptoAdapter.cryptoList = cryptocurrencies
    }

    private fun saveData(){
        val crypto = getForm()
        //save to database
        val values = Utils.toContentValues(crypto)
        val result = cryptoHelper.insertData(values)
        if (result > 0) {
            crypto.id = result.toInt()
            cryptoAdapter.addData(crypto)
        }
        clearForm()
    }

    private fun updateData(){
        val index = getIndex()
        if (isNotOutRange(index)) {
            val crypto = getForm()
            val id = cryptoAdapter.cryptoList[index].id

            //update to database
//            val values = Utils.toContentValues(crypto)
            val values = ContentValues()
            values.put(DatabaseContract.CryptoColumns.NAME, crypto.name)
            val result = cryptoHelper.updateData(id.toString(), values)
            if (result > 0){
//                cryptoAdapter.updateData(crypto, index)
                cryptoAdapter.updateName(crypto.name, index)
            }
        }
        else noticeOutRange()
        clearForm()
    }

    private fun deleteData(){
        val index = getIndex()
        if (isNotOutRange(index)) {
            val id = cryptoAdapter.cryptoList[index].id

            //delete to database
            val result = cryptoHelper.deleteById(id.toString())
            if (result > 0){
                cryptoAdapter.deleteData(index)
            }
        }
        else noticeOutRange()
        clearForm()
    }

    private fun insertData(){
        val index = getIndex()
        if (isNotOutRange(index)) {
            val crypto = getForm()
            cryptoAdapter.insertData(crypto, index)

            //save to database
            val values = Utils.toContentValues(crypto)
            cryptoHelper.insertData(values)
        }
        else noticeOutRange()
        clearForm()
    }

    private fun getForm(): Cryptocurrency {
        val name = edt_name.text.toString()
        val lastPrice = edt_last_price.text.toString()
        val imageIndex = (0 until Utils.images.size).random()

        return Cryptocurrency(
            name = name,
            lastPrice = if (lastPrice.isNotEmpty()) lastPrice.toDouble() else 0.0,
            imagePath = Utils.images[imageIndex]
        )
    }

    private fun getIndex(): Int{
        val index = edt_row.text.toString()
        return if (index.isNotEmpty()) index.toInt() else 0
    }

    private fun clearForm(){
        edt_name.setText("")
        edt_last_price.setText("")
        edt_row.setText("")
    }

    private fun isNotOutRange(index: Int): Boolean = index < Utils.cryptocurrencies.size

    private fun noticeOutRange(){
        Toast.makeText(context, "Out of range index of list", Toast.LENGTH_LONG).show()
    }

}