package com.example.appstoretechnology

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnIniciarSesion.setOnClickListener {
            limpiarCampos()
            val i = Intent(this, PrincipalActivity::class.java)
            startActivity(i)
        }

        btnRegistrarte.setOnClickListener {
            limpiarCampos()
            val i = Intent(this, FormUsuarioActivity::class.java)
            startActivity(i)
        }
    }

    fun limpiarCampos(){
        edtUsuarioLogin.setText("")
        edtContrasenaLogin.setText("")
    }
}