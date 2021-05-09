package com.ilhamfidatama.androidwithkotlin.crypto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ilhamfidatama.androidwithkotlin.R
import kotlinx.android.synthetic.main.item_crypto.view.*

class CryptoAdapter(private var cryptoList: MutableList<Cryptocurrency>): RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    fun deleteData(index: Int){
        cryptoList.removeAt(index)
        notifyItemRemoved(index)
    }

    fun updateData(crypto: Cryptocurrency, index: Int){
        cryptoList[index] = crypto
        notifyItemChanged(index)
    }

    fun addData(crypto: Cryptocurrency){
        cryptoList.add(crypto)
        notifyDataSetChanged()
    }

    fun insertData(crypto: Cryptocurrency, index: Int){
        cryptoList.add(index, crypto)
        notifyItemInserted(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder = CryptoViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_crypto, parent, false)
    )

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.binding(cryptoList[position])
    }

    override fun getItemCount(): Int = cryptoList.size

    inner class CryptoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun binding(crypto: Cryptocurrency){
            itemView.tv_name.text = crypto.name
            itemView.tv_last_price.text = crypto.lastPrice.toString()

            //setup image
            Glide.with(itemView)
                .load(crypto.imagePath)
                .apply(RequestOptions.circleCropTransform().override(75,75)) //circle crop image
                .into(itemView.iv_logo)

            //setup click listener of list
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "cryptocurrency ${crypto.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}