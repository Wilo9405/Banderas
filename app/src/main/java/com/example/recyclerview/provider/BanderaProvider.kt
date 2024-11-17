package com.example.recyclerview.provider

import com.example.recyclerview.model.Autonomia
import com.example.recyclerview.R

class BanderaProvider {

    fun cargarBandera(): MutableList<Autonomia> {
        return mutableListOf<Autonomia>(

            Autonomia(nombre = "andalucia", imagen = R.drawable.andalucia),
            Autonomia(nombre = "aragon", imagen = R.drawable.aragon),
            Autonomia(nombre = "asturias", imagen = R.drawable.asturias),
            Autonomia(nombre = "baleares", imagen = R.drawable.baleares),
            Autonomia(nombre = "canarias", imagen = R.drawable.canarias),
            Autonomia(nombre = "cantabria", imagen = R.drawable.cantabria),
            Autonomia(nombre = "castillaleon", imagen = R.drawable.castillaleon),
            Autonomia(nombre = "castillalamancha", imagen = R.drawable.castillamancha),
            Autonomia(nombre = "cataluña", imagen = R.drawable.catalunya),
            Autonomia(nombre = "ceuta", imagen = R.drawable.ceuta),
            Autonomia(nombre = "extremadura", imagen = R.drawable.extremadura),
            Autonomia(nombre = "galicia", imagen = R.drawable.galicia),
            Autonomia(nombre = "larioja", imagen = R.drawable.larioja),
            Autonomia(nombre = "madrid", imagen = R.drawable.madrid),
            Autonomia(nombre = "murcia", imagen = R.drawable.murcia),
            Autonomia(nombre = "navarra", imagen = R.drawable.navarra),
            Autonomia(nombre = "paisvasco", imagen = R.drawable.paisvasco),
            Autonomia(nombre = "rioja", imagen = R.drawable.larioja),
            Autonomia(nombre = "valencia", imagen = R.drawable.valencia),

            )
    }
}
