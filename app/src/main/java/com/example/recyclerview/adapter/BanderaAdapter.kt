package com.example.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.model.Autonomia

class BanderaAdapter(
    private val lista: List<Autonomia>,
    private val onClickListener:(Autonomia) -> Unit): RecyclerView.Adapter<BanderaViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BanderaViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        return BanderaViewHolder(layoutInflater.inflate(R.layout.item_bandera, parent, false))
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: BanderaViewHolder, position: Int) {
        val item = lista[position]
        holder.render(item, onClickListener)


    }

}