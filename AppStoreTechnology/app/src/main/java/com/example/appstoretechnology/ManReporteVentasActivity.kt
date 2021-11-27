package com.example.appstoretechnology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.appstoretechnology.Controlador.VentasDAO
import kotlinx.android.synthetic.main.activity_man_reporte_ventas.*

class ManReporteVentasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_man_reporte_ventas)

        MostrarVentas()
    }

    fun MostrarVentas() {
        var dao_vent = VentasDAO(this)
        var adap = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            dao_vent.ListarVentas_String()
        )
        LVVENTASMAN.adapter = adap
    }

    override fun onResume() {
        super.onResume()
        MostrarVentas()
    }
}