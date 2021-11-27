package com.example.appstoretechnology

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.appstoretechnology.Controlador.CategoriaDAO
import com.example.appstoretechnology.Modelo.Categoria
import kotlinx.android.synthetic.main.activity_man_cateagorias.*

class ManCateagoriasActivity : AppCompatActivity() {

    var lista_categorias = ArrayList<Categoria>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_man_cateagorias)

        MostrarCategorias()
        fabAgregarCategoria.setOnClickListener {
            val vcrudcat = Intent(this, FormCategoriaActivity::class.java)
            vcrudcat.putExtra("FUNCION","registrar")
            startActivity(vcrudcat)
        }
        LVCATEGORIASMAN.setOnItemClickListener { adapterView, view, i, l ->
            var x = Intent(this, FormCategoriaActivity::class.java)
            x.putExtra("INDICE",i)
            x.putExtra("FUNCION","editar")
            startActivity(x)
        }
    }

    fun MostrarCategorias(){
        var dao_cate = CategoriaDAO(this)
        lista_categorias = dao_cate.ListarCategorias()
        var adap = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            dao_cate.ListarCategorias_String()
        )
        LVCATEGORIASMAN.adapter = adap
    }

    override fun onResume() {
        super.onResume()
        MostrarCategorias()
    }
}