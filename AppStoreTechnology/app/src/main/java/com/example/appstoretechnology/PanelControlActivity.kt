package com.example.appstoretechnology

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_panel_control.*

class PanelControlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panel_control)

        var x = intent
        tvNombreUsuario.setText(x.getStringExtra("ADMIN"))

        btnManProveedores.setOnClickListener {
            val vproveedores = Intent(this, ManProveedoresActivity::class.java)
            startActivity(vproveedores)
        }

        btnManCategorias.setOnClickListener {
            val vcategorias = Intent(this, ManCateagoriasActivity::class.java)
            startActivity(vcategorias)
        }

        btnManProductos.setOnClickListener {
            val vproductos = Intent(this, ManProductosActivity::class.java)
            startActivity(vproductos)
        }

        btnReporteVentas.setOnClickListener {
            val vrepventas = Intent(this, ManReporteVentasActivity::class.java)
            startActivity(vrepventas)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.optCerrarSesion -> {
                val vlogin = Intent(this, MainActivity::class.java)
                startActivity(vlogin)
                Toast.makeText(this,"Sesión cerrada",Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}