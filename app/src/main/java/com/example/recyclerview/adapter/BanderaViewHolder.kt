package com.example.recyclerview.adapter

import android.view.ContextMenu
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recyclerview.databinding.ItemBanderaBinding
import com.example.recyclerview.model.Autonomia
import com.squareup.picasso.Picasso

class BanderaViewHolder(view: View):ViewHolder(view),View.OnCreateContextMenuListener{
    val binding = ItemBanderaBinding.bind(view)
    lateinit var bandera: Autonomia

    fun render(item: Autonomia, onClickListener:(Autonomia) -> Unit){
        bandera = item
        binding.tvNombre.text = item.nombre
        Picasso.get().load(item.imagen).resize(80,80).into(binding.ivBandera)
        itemView.setOnClickListener{
        onClickListener(item)
        }
        itemView.setOnCreateContextMenuListener(this)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu!!.setHeaderTitle(bandera.nombre)
        menu.add(this.adapterPosition,0,0,"Borrar")
        menu.add(this.adapterPosition,1,0,"Editar")

    }


}