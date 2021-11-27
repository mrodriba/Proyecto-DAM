package com.example.appstoretechnology

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.appstoretechnology.Controlador.ProductoDAO
import com.example.appstoretechnology.Modelo.Producto
import kotlinx.android.synthetic.main.activity_man_productos.*

class ManProductosActivity : AppCompatActivity() {

    var lista_productos = ArrayList<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_man_productos)

        MostrarProductos()

        fabAgregarProducto.setOnClickListener {
            var vcrudprod = Intent(this,FormProductoActivity::class.java)
            vcrudprod.putExtra("FUNCION","registrar")
            startActivity(vcrudprod)
        }

        LVPRODUCTOSMAN.setOnItemClickListener { adapterView, view, i, l ->
            var x = Intent(this,FormProductoActivity::class.java)
            x.putExtra("INDICE",i)
            x.putExtra("FUNCION","editar")
            startActivity(x)
        }
    }

    fun MostrarProductos(){
        var dao_prod = ProductoDAO(this)
        lista_productos = dao_prod.ListarProductos()
        var adap = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            dao_prod.ListarProductos_String()
        )
        LVPRODUCTOSMAN.adapter = adap
    }

    override fun onResume() {
        super.onResume()
        MostrarProductos()
    }
}