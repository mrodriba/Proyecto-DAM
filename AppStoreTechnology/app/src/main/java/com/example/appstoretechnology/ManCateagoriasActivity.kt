package com.example.appstoretechnology

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_man_cateagorias.*

class ManCateagoriasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_man_cateagorias)

        fabAgregarCategoria.setOnClickListener {
            val vcrudprov = Intent(this, FormCategoriaActivity::class.java)
            startActivity(vcrudprov)
        }
    }
}