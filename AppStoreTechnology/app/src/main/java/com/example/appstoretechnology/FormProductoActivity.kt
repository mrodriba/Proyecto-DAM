package com.example.appstoretechnology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_form_producto.*

class FormProductoActivity : AppCompatActivity() {
    var v_categoria = arrayOf("Seleccione categoria","Tv","Celular","PC","Mouse","Laptop")
    //var categoria_seleccionada = ""
    var v_proveedor = arrayOf("Seleccione proveedor","Acer","Lenovo","Huawei","Xiamo")
    //var proveedor_seleccionada = ""
    var v_estado = arrayOf("Seleccione estado","Activo","Vacio")
    //var estado_seleccionada = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_producto)

        var adaptador_cate = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            v_categoria
        )
        var adaptador_prov = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            v_proveedor
        )
        var adaptador_estado = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            v_estado
        )

        spnCategoriaProdForm.adapter = adaptador_cate
        spnProveedorProdForm.adapter = adaptador_prov
        spnEstadoProdForm.adapter = adaptador_estado

        btnAgregarProductoForm.setOnClickListener {
            Toast.makeText(this,"Agregar nuevo producto", Toast.LENGTH_SHORT).show()
        }

        btnEditarProductoForm.setOnClickListener {
            Toast.makeText(this,"Editar producto", Toast.LENGTH_SHORT).show()
        }
    }
}