package com.example.appstoretechnology

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnIniciarSesion.setOnClickListener {
            //
            val vcliente = Intent(this, PrincipalActivity::class.java)
            val vadmin = Intent(this, PanelControlActivity::class.java)

            if (edtUsuarioLogin.text.toString().isNotEmpty() && edtContrasenaLogin.text.toString().isNotEmpty()) {
                if (edtUsuarioLogin.text.toString() == "admin@admin.com" && edtContrasenaLogin.text.toString() == "123"){
                    startActivity(vadmin)
                }else{
                    startActivity(vcliente)
                }
            } else {
                Toast.makeText(this, "Ingrese datos", Toast.LENGTH_SHORT).show()
            }
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