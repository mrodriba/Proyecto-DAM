package com.example.appstoretechnology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_principal.*

class PrincipalActivity : AppCompatActivity() {
    var v_categoria = arrayOf("Seleccione categoria","Laptop","PC","Celular","TV")
    var categoria_seleccionada = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        //Nombre del appBar
        //supportActionBar?.title = "Opciones"

        var adaptador = ArrayAdapter<String>(
            this, // Contexto
            android.R.layout.simple_spinner_dropdown_item, // Diseño de la fila del control
            v_categoria // Data de los elementos del control
        )

        spnCategoria.adapter = adaptador

        spnCategoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View, p2: Int, p3: Long) {
                // p0 => spinner
                // p2 => indice
                categoria_seleccionada = v_categoria.get(p2)
                Snackbar.make(p1, "Seleccionaste: ${categoria_seleccionada} seleccionado", Snackbar.LENGTH_LONG).show()
                // Guardar este proyecto => File>Manage IDE Settings>Export to Zip File
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

    }

}