package com.example.appstoretechnology

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.example.appstoretechnology.Controlador.VentasDAO
import kotlinx.android.synthetic.main.activity_mi_carrito.*
import kotlinx.android.synthetic.main.alert_compra.*

class MiCarritoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_carrito)

        MostrarCarrito()

        fabPagarProductos.setOnClickListener {
            val dialogo = AlertDialog.Builder(this)
            val inflater = layoutInflater

            dialogo.setTitle("Realizar Compra")
            dialogo.setView(inflater.inflate(R.layout.alert_compra,null))
            dialogo.setPositiveButton("Comprar",DialogInterface.OnClickListener { dialogInterface, i ->
                ComprarProducto()
                dialogInterface.dismiss()
                MostrarCarrito()
            })
            dialogo.setNegativeButton("Cancelar",DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            dialogo.show()
        }
    }

    fun MostrarCarrito(){
        var x = intent
        var idusuario = x.getIntExtra("IDUSU",0)
        var dao_venta = VentasDAO(this)
        var adap = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            dao_venta.ListarCarrito_String(idusuario)
        )
        LVPRODCARRITO.adapter = adap
    }

    fun ComprarProducto(){
        var x = intent
        var idusuario = x.getIntExtra("IDUSU",0)
        var dao_venta = VentasDAO(this)
        dao_venta.ComprarVentaDeUsuario(idusuario)
        Toast.makeText(this,"Compra realizada",Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        MostrarCarrito()
    }
}