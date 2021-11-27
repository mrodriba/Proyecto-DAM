package com.example.appstoretechnology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import com.example.appstoretechnology.Controlador.ProductoDAO
import kotlinx.android.synthetic.main.activity_detalle_producto.*
import java.util.regex.Pattern

class DetalleProductoActivity : AppCompatActivity() {

    var patron_cantidad = Pattern.compile("[0-3]{1}")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_producto)

        var x = intent
        var indice:Int = x.getIntExtra("INDICE",0)
        var dao = ProductoDAO(this)
        var obj_prod = dao.ListarProductos().get(indice)
        EDTCODPRODCLI.isVisible = false
        EDTCODPRODCLI.setText(""+obj_prod.idproducto)
        TVNOMPRODCLI.setText(""+obj_prod.nombre)
        EDTPRECIOPRODCLI.setText(""+obj_prod.precio+"0")
        EDTCARACTERISTICAS.setText(""+obj_prod.descripcion)
    }

    fun AgregarCarrito(v:View){
        var idproducto = EDTCODPRODCLI.text.toString().toInt()
        var cantidad = EDTCANTIDADPRODCLI.text.toString()
        Log.i("Codigo: ", idproducto.toString())
        if (patron_cantidad.matcher(cantidad).matches() == false){
            EDTCANTIDADPRODCLI.setError("Solo puede escojer un maximo de 3 productos")
            return
        }else{
            EDTCANTIDADPRODCLI.setError(null)
        }
    }
}