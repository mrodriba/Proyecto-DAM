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

    private var email by Delegates.notNull<String>()
    private var password by Delegates.notNull<String>()
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnIniciarSesion.setOnClickListener {
            etEmail = findViewById(R.id.edtUsuarioLogin)
            etPassword = findViewById(R.id.edtContrasenaLogin)
            //Obtenemos usuario y contraseña
            email = etEmail.text.toString()
            password = etPassword.text.toString()
            //
            val vcliente = Intent(this, PrincipalActivity::class.java)
            val vadmin = Intent(this, PanelControlActivity::class.java)

            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                if (email == "admin@admin.com" && password == "123"){
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