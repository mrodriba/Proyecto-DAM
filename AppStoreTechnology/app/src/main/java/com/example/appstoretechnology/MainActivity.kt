package com.example.appstoretechnology

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRegistrarte.setOnClickListener {
            limpiarCampos()
            val i = Intent(this, FormUsuarioActivity::class.java)
            startActivity(i)
        }
    }

    fun BuscarUsuario(v:View){
        if (edtUsuarioLogin.text.toString().isNotEmpty() && edtContrasenaLogin.text.toString().isNotEmpty()) {
            var hilo = Thread(Runnable {
                var cuenta = "?xcuenta=" + edtUsuarioLogin.text.toString()
                var clave = "&xclave=" + edtContrasenaLogin.text.toString()
                var ruta_servicio = "http://192.168.56.1:8085/proyecto/ValidarUsuario.php" +cuenta + clave
                var resultado = Utilitario.traerDatos_String(ruta_servicio)
                runOnUiThread {
                    ValidarUsuario(resultado)
                }
            })
            hilo.start()
        }else{
            Toast.makeText(this, "Ingrese datos", Toast.LENGTH_SHORT).show()
        }
    }

    fun ValidarUsuario(cadena:String){
        try {
            val vcliente = Intent(this, PrincipalActivity::class.java)
            val vadmin = Intent(this, PanelControlActivity::class.java)
            var idcuenta:Int=0;
            var idtipo:Int=0;
            var nombre:String="";
            var json = JSONArray(cadena)
            var cant_elementos = json.length()-1
            if (cant_elementos >= 0){
                for (i in 0..cant_elementos){
                    idcuenta = json.getJSONObject(i).getInt("codusuario")
                    idtipo = json.getJSONObject(i).getInt("idtipo")
                    nombre = json.getJSONObject(i).getString("nomusu")
                }
                if (idtipo == 1){
                    vadmin.putExtra("ADMIN",nombre)
                    startActivity(vadmin)
                }else{
                    vcliente.putExtra("IDUSU",idcuenta)
                    startActivity(vcliente)
                }
            }else{
                Toast.makeText(this,"Cuenta no valida",Toast.LENGTH_LONG).show()
            }
        }catch (ex:Exception){
            Log.i("Error:", ex.message.toString())
        }
    }

    fun limpiarCampos(){
        edtUsuarioLogin.setText("")
        edtContrasenaLogin.setText("")
    }
}