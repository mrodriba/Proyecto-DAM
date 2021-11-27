package com.example.appstoretechnology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.appstoretechnology.Controlador.VentasDAO
import kotlinx.android.synthetic.main.activity_mis_pedidos.*

class MisPedidosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_pedidos)
        MostrarPedidos()
    }

    fun MostrarPedidos(){
        var x = intent
        var idusuario = x.getIntExtra("IDUSU",0)
        var dao_venta = VentasDAO(this)
        var adap = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                dao_venta.ListarPedidos_String(idusuario)
        )
        LVMISPEDIDOS.adapter = adap
    }

    override fun onResume() {
        super.onResume()
        MostrarPedidos()
    }
}