package com.ilhamfidatama.androidwithkotlin.crypto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilhamfidatama.androidwithkotlin.R
import com.ilhamfidatama.androidwithkotlin.Utils
import kotlinx.android.synthetic.main.fragment_cryptocurrency.*


class CryptocurrencyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cryptocurrency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setup recycler view with the adapter
        val cryptoAdapter = CryptoAdapter(Utils.cryptocurrencies)
        cryptoAdapter.notifyDataSetChanged()
        rv_crypto.adapter = cryptoAdapter
        rv_crypto.setHasFixedSize(true)
        rv_crypto.layoutManager = LinearLayoutManager(context)

        btn_save.setOnClickListener {
            cryptoAdapter.addData(getForm())
            clearForm()
        }

        btn_delete.setOnClickListener {
            val index = getIndex()
            if (isNotOutRange(index)) cryptoAdapter.deleteData(index)
            else noticeOutRange()
            clearForm()
        }

        btn_update.setOnClickListener {
            val index = getIndex()
            if (isNotOutRange(index)) cryptoAdapter.updateData(getForm(), index)
            else noticeOutRange()
            clearForm()
        }

        btn_insert.setOnClickListener {
            val index = getIndex()
            if (isNotOutRange(index)) cryptoAdapter.insertData(getForm(), index)
            else noticeOutRange()
            clearForm()
        }
    }

    private fun getForm(): Cryptocurrency {
        val name = edt_name.text.toString()
        val lastPrice = edt_last_price.text.toString()
        val imageIndex = (0 until Utils.images.size).random()

        return Cryptocurrency(name, lastPrice.toDouble(), Utils.images[imageIndex])
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