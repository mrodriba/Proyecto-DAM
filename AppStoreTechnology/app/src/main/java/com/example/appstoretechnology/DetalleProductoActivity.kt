package com.example.appstoretechnology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.appstoretechnology.Controlador.ProductoDAO
import com.example.appstoretechnology.Controlador.VentasDAO
import com.example.appstoretechnology.Modelo.Venta
import kotlinx.android.synthetic.main.activity_detalle_producto.*
import java.util.regex.Pattern

class DetalleProductoActivity : AppCompatActivity() {

    var patron_cantidad = Pattern.compile("[0-3]{1}")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_producto)

        var x = intent
        var indice:Int = x.getIntExtra("INDICE",0)
        var idusuario:Int = x.getIntExtra("IDUSU",0)

        var dao = ProductoDAO(this)
        var obj_prod = dao.ListarProductos().get(indice)

        EDTIDUSU.isVisible = false
        EDTCODPRODCLI.isVisible = false

        EDTCODPRODCLI.setText(""+obj_prod.idproducto)
        EDTIDUSU.setText(""+idusuario)
        TVNOMPRODCLI.setText(""+obj_prod.nombre)
        EDTPRECIOPRODCLI.setText(""+obj_prod.precio+"0")
        EDTCARACTERISTICAS.setText(""+obj_prod.descripcion)
    }

    fun AgregarCarrito(v:View){
        var dao_venta = VentasDAO(applicationContext)

        var idusuario = EDTIDUSU.text.toString().toInt()
        var idproducto = EDTCODPRODCLI.text.toString().toInt()
        var cantidad = EDTCANTIDADPRODCLI.text.toString().toInt()
        var precio = EDTPRECIOPRODCLI.text.toString().toDouble()
        var monto = cantidad * precio
        var estado = 1
        //Log.i("Codigo: ", idproducto.toString())
        if (patron_cantidad.matcher(cantidad.toString()).matches() == false){
            EDTCANTIDADPRODCLI.setError("Solo puede escojer un maximo de 3 productos")
            return
        }else{
            EDTCANTIDADPRODCLI.setError(null)
        }
        var venta = Venta(0,idusuario,idproducto,cantidad,monto,estado)
        if (venta != null){
            dao_venta.GrabarVenta(venta)
            Toast.makeText(this,"Agrego un producto a su carrito", Toast.LENGTH_SHORT).show()
            EDTCANTIDADPRODCLI.setText("")
        }else{
            Toast.makeText(this,"Producto no agregado", Toast.LENGTH_SHORT).show()
        }
    }
}