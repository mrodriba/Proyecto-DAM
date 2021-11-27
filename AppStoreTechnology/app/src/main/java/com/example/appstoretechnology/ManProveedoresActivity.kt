package com.example.appstoretechnology

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.appstoretechnology.Controlador.ProveedorDAO
import com.example.appstoretechnology.Modelo.Proveedor
import kotlinx.android.synthetic.main.activity_man_proveedores.*

class ManProveedoresActivity : AppCompatActivity() {

    var lista_proveedores = ArrayList<Proveedor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_man_proveedores)

        MostrarProveedores()
        fabAgregarProveedor.setOnClickListener {
            val vcrudprov = Intent(this, FormProveedorActivity::class.java)
            vcrudprov.putExtra("FUNCION","registrar")
            startActivity(vcrudprov)
        }
        LVPROVEEDORESMAN.setOnItemClickListener { adapterView, view, i, l ->
            val x = Intent(this, FormProveedorActivity::class.java)
            x.putExtra("INDICE",i)
            x.putExtra("FUNCION","editar")
            startActivity(x)
        }
    }

    fun MostrarProveedores(){
        var dao_prov = ProveedorDAO(this)
        lista_proveedores = dao_prov.ListarProveedores()
        var adapa = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            dao_prov.ListarProveedores_String()
        )
        LVPROVEEDORESMAN.adapter = adapa
    }

    override fun onResume() {
        super.onResume()
        MostrarProveedores()
    }
}