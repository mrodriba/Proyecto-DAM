package com.example.appstoretechnology

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_man_proveedores.*

class ManProveedoresActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_man_proveedores)

        fabAgregarProveedor.setOnClickListener {
            val vcrudprov = Intent(this, FormProveedorActivity::class.java)
            startActivity(vcrudprov)
        }
    }
}