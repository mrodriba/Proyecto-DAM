package com.example.appstoretechnology

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_mi_carrito.*

class MiCarritoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_carrito)

        fabPagarProductos.setOnClickListener {
            val dialogo = AlertDialog.Builder(this)
            val inflater = layoutInflater

            dialogo.setTitle("Realizar Compra")
            dialogo.setView(inflater.inflate(R.layout.alert_compra,null))
            dialogo.setPositiveButton("Comprar",DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
                Toast.makeText(this,"Compra realizada",Toast.LENGTH_LONG).show()
            })
            dialogo.setNegativeButton("Cancelar",DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            dialogo.show()
        }
    }
}