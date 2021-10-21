package com.example.appstoretechnology

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_man_productos.*

class ManProductosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_man_productos)

        fabAgregarProducto.setOnClickListener {
            var vcrudprod = Intent(this,FormProductoActivity::class.java)
            startActivity(vcrudprod)
        }
    }
}