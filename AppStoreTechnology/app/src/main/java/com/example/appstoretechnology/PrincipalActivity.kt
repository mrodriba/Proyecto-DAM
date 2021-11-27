package com.example.appstoretechnology

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.appstoretechnology.Controlador.ProductoDAO
import kotlinx.android.synthetic.main.activity_principal.*

class PrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        //Nombre del appBar
        //supportActionBar?.title = "Opciones"

        MostrarProductos()

        LVPRODUCTOSPRINCIPAL.setOnItemClickListener { adapterView, view, i, l ->
            var x = intent
            var idusuario = x.getIntExtra("IDUSU",0)
            val v_detalle = Intent(this,DetalleProductoActivity::class.java)
            v_detalle.putExtra("INDICE",i)
            v_detalle.putExtra("IDUSU",idusuario)
            startActivity(v_detalle)
        }
    }
    fun MostrarProductos(){
        var dao_prod = ProductoDAO(this)
        var adap = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                dao_prod.ListarProductos_Cliente()
        )
        LVPRODUCTOSPRINCIPAL.adapter = adap
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cliente, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.optCarrito -> {
                var x = intent
                var idusuario = x.getIntExtra("IDUSU",0)
                val vcarrito = Intent(this, MiCarritoActivity::class.java)
                vcarrito.putExtra("IDUSU",idusuario)
                startActivity(vcarrito)
            }
            R.id.optMisPedidos -> {
                var x = intent
                var idusuario = x.getIntExtra("IDUSU",0)
                val vmispedidos = Intent(this, MisPedidosActivity::class.java)
                vmispedidos.putExtra("IDUSU",idusuario)
                startActivity(vmispedidos)
            }
            R.id.optCerrarSesion -> {
                val vlogin = Intent(this, MainActivity::class.java)
                startActivity(vlogin)
                Toast.makeText(this,"Sesión cerrada", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        MostrarProductos()
    }

}