package com.example.appstoretechnology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_form_proveedor.*

class FormProveedorActivity : AppCompatActivity() {
    var v_distrito = arrayOf("Seleccione distrito","Ate","Ancon","San Juan de Miraflores","San Juan de Lurigancho","Lima")
    var distrito_seleccionado = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_proveedor)

        var adaptador = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            v_distrito
        )

        spnDistritoProveedorForm.adapter = adaptador
        spnDistritoProveedorForm.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View, p2: Int, p3: Long) {
                // p0 => spinner
                // p2 => indice
                distrito_seleccionado = v_distrito.get(p2)
                Snackbar.make(p1, "Seleccionaste: ${distrito_seleccionado}", Snackbar.LENGTH_LONG).show()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        btnAgregarProvForm.setOnClickListener {
            Toast.makeText(this,"Agregar nuevo proveedor",Toast.LENGTH_SHORT).show()
        }

        btnEditarProvForm.setOnClickListener {
            Toast.makeText(this,"Editar proveedor",Toast.LENGTH_SHORT).show()
        }
    }
}