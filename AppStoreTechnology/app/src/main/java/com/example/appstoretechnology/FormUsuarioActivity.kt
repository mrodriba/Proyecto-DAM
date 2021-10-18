package com.example.appstoretechnology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_form_usuario.*

class FormUsuarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_usuario)

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