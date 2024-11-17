package com.example.recyclerview.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.os.IResultReceiver._Parcel
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.R
import com.example.recyclerview.adapter.BanderaAdapter
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.model.Autonomia
import com.example.recyclerview.provider.BanderaProvider
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listAtonomias: MutableList<Autonomia>
    private lateinit var layautManager: LinearLayoutManager
    private lateinit var adapter: BanderaAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        listAtonomias = BanderaProvider().cargarBandera()
        layautManager = LinearLayoutManager(this)
        binding.rvBanderas.layoutManager = layautManager
        adapter = BanderaAdapter(listAtonomias) { bandera ->
            onItemSelected(bandera)
        }
        binding.rvBanderas.adapter = adapter


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.limpiar -> {
                listAtonomias.clear()
                adapter.notifyDataSetChanged()

                Toast.makeText(this, "Lista limpiada", Toast.LENGTH_SHORT).show()
                return true

            }

            R.id.recargar -> {
                Log.d("MainActivity", "recargar lista seleccionada")
                recargarLista()

                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun recargarLista() {
        Log.d("MainActivity", "Antes de recargar: $listAtonomias")

        listAtonomias = BanderaProvider().cargarBandera()
        Log.d("MainActivity", "Lista recargada: $listAtonomias")

        adapter = BanderaAdapter(listAtonomias) { bandera -> onItemSelected(bandera) }
        binding.rvBanderas.adapter = adapter

        adapter.notifyDataSetChanged()
        Toast.makeText(this, "Lista recargada", Toast.LENGTH_SHORT).show()
    }

    private fun onItemSelected(bandera: Autonomia) {
        Toast.makeText(this, "Yo soy de ${bandera.nombre}", Toast.LENGTH_SHORT).show()

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val banderaSeleccionada = listAtonomias[item.groupId]
        when (item.itemId) {
            0 -> {
                val alert =
                    AlertDialog.Builder(this).setTitle("Borrar ${banderaSeleccionada.nombre}")
                        .setNeutralButton("Cancelar", null)
                        .setPositiveButton("Aceptar") { _, _ ->
                            Snackbar.make(
                                binding.root,
                                "Borrando ${banderaSeleccionada.nombre}",
                                Snackbar.LENGTH_SHORT
                            ).show()
                            listAtonomias.removeAt(item.groupId)
                            adapter.notifyItemRemoved(item.groupId)
                        }.create()
                alert.show()
            }

            1 -> {
                val intent = Intent(this, EditarAutonomiaActivity::class.java).apply {
                    putExtra("nombre", banderaSeleccionada.nombre)
                    putExtra("posicion", item.groupId)
                    putExtra("imagen", banderaSeleccionada.imagen)
                }
                startActivityForResult(intent, 1)
            }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 1) {
            val nuevoNombre = data?.getStringExtra("nuevoNombre")
            val posicion = data?.getIntExtra("posicion", -1)

            if (posicion != null && posicion != -1 && nuevoNombre != null) {
                listAtonomias[posicion].nombre = nuevoNombre
                adapter.notifyItemChanged(posicion)
            }
        }
    }
}