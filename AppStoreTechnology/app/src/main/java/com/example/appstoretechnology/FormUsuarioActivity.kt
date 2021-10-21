package com.example.appstoretechnology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_form_usuario.*
import kotlinx.android.synthetic.main.activity_principal.*

class FormUsuarioActivity : AppCompatActivity() {
    var v_distrito = arrayOf("Seleccione distrito","Ate","Ancon","San Juan de Miraflores","San Juan de Lurigancho","Lima")
    var distrito_seleccionado = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_usuario)

        var adaptador = ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                v_distrito
        )

        spnDistrito.adapter = adaptador
        spnDistrito.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

        btnVolverLoginForm.setOnClickListener {
            limpiarCampos()
            finish()
        }
    }

    fun limpiarCampos(){
        edtNombreForm.setText("")
        edtTelefonoForm.setText("")
        spnDistrito.setSelection(0)
        edtDireccionForm.setText("")
        edtUsuarioForm.setText("")
        edtContrasenaForm.setText("")
        edtRepContrasenaForm.setText("")
    }
}