package com.example.appstoretechnology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_form_categoria.*

class FormCategoriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_categoria)

        btnAgregarCategoriaForm.setOnClickListener {
            Toast.makeText(this,"Agregar nueva categoria", Toast.LENGTH_SHORT).show()
        }
        btnEditarCateForm.setOnClickListener {
            Toast.makeText(this,"Editar categoria", Toast.LENGTH_SHORT).show()
        }
    }
}