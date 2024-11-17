package com.example.recyclerview.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recyclerview.R
import com.example.recyclerview.databinding.ActivityEditarAutonomiaBinding
import com.example.recyclerview.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlin.math.log

class EditarAutonomiaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarAutonomiaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_RecyclerView)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_autonomia)

        binding = ActivityEditarAutonomiaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombreOriginal = intent.getStringExtra("nombre")
        val posicion = intent.getIntExtra("posicion", -1)
        val imagenEdit = intent.getStringExtra("imagen")

       binding.etNombreAutonomia.setText(nombreOriginal)
        if (imagenEdit == null) {
            Log.e("EditarAutonomiaActivity", "La URL de la imagen es null")
        } else {
            Log.d("EditarAutonomiaActivity", "La URL de la imagen recibida: $imagenEdit")
        }

        Picasso.get().load(imagenEdit).resize(80, 80) .into(binding.ivBandera2)

        val buttonCancelar = findViewById<Button>(R.id.btn_cancelar)
        buttonCancelar.setOnClickListener {
            finish()
        }

        val buttonCambiar = findViewById<Button>(R.id.btn_cambiar)
        buttonCambiar.setOnClickListener {
            val nuevoNombre = binding.etNombreAutonomia.text.toString()
            val resultIntent = Intent().apply {
                putExtra("nuevoNombre", nuevoNombre)
                putExtra("posicion", posicion)

            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}