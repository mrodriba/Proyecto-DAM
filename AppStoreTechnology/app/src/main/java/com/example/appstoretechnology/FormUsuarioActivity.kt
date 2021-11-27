package com.example.appstoretechnology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.appstoretechnology.Controlador.DistritoDAO
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_form_usuario.*
import java.util.regex.Pattern

class FormUsuarioActivity : AppCompatActivity() {

    // VALIDACIONES
    var patron_nombre = Pattern.compile("[a-zA-Z ]{5,50}")
    var patron_telefono = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{3}")
    var patron_direccion = Pattern.compile("[a-zA-Z-0-9 ]{5,50}")
    var patron_cuenta = Pattern.compile("[a-zA-Z-0-9 ]{5,14}@tec.com")
    var patron_clave = Pattern.compile("[a-zA-Z-0-9 ]{6,40}")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_usuario)

        PoblarComboDistrito()

        btnVolverLoginForm.setOnClickListener {
            limpiarCampos()
            finish()
        }
    }

    fun PoblarComboDistrito(){
        var dao_dist = DistritoDAO(this)
        var adaptador = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            dao_dist.ListarDistritos_String()
        )
        spnDistrito.adapter = adaptador
    }

    fun BotonGrabarUsuario(v:View){
        try{
            var nombre = edtNombreForm.text.toString()
            var telefono = edtTelefonoForm.text.toString()
            var distrito = spnDistrito.selectedItemPosition
            var direccion = edtDireccionForm.text.toString()
            var cuenta = edtUsuarioForm.text.toString()
            var clave = edtContrasenaForm.text.toString()
            var repclave = edtRepContrasenaForm.text.toString()
            var tipo = 2

            // VALIDAR
            if (patron_nombre.matcher(nombre).matches() == false){
                edtNombreForm.setError("Solo debe ingresar letras")
                return
            }else{
                edtNombreForm.setError(null)
                edtTelefonoForm.requestFocus()
            }
            if (patron_telefono.matcher(telefono).matches() == false){
                edtTelefonoForm.setError("Debe colocar de después de cada 3 digitos un '-'")
                return
            }else{
                edtTelefonoForm.setError(null)
                edtDireccionForm.requestFocus()
            }
            if (patron_direccion.matcher(direccion).matches() == false){
                edtDireccionForm.setError("El rango de la dirección debe ser entre 5 a 50 caracteres.")
                return
            }else{
                edtDireccionForm.setError(null)
                edtUsuarioForm.requestFocus()
            }
            if (patron_cuenta.matcher(cuenta).matches() == false){
                edtUsuarioForm.setError("El formato debe ser: NombreDeCuenta@tec.com")
                return
            }else{
                edtUsuarioForm.setError(null)
                edtContrasenaForm.requestFocus()
            }
            if (patron_clave.matcher(clave).matches() == false){
                edtContrasenaForm.setError("Debe ingresar más de 6 caracteres")
                return
            }else{
                edtContrasenaForm.setError(null)
                edtRepContrasenaForm.requestFocus()
            }
            if (distrito == 0){
                Toast.makeText(this,"Debe seleccionar un distrito",Toast.LENGTH_LONG).show()
            }else if(clave != repclave){
                edtRepContrasenaForm.setError("La contraseña debe coincidir con lo anterior")
            }else{
                var parametros = "?xnom=" + nombre +
                        "&xcel=" + telefono +
                        "&xdist=" + distrito +
                        "&xdir=" + direccion +
                        "&xcuenta=" + cuenta +
                        "&xclave=" + clave +
                        "&xtipo=" + tipo

                var ruta_servicio = "http://192.168.56.1:8085/proyecto/NuevoUsuario.php" + parametros

                var hilo = Thread(Runnable {
                    var rpta = Utilitario.enviarDatos_String(ruta_servicio)
                    runOnUiThread {
                        Toast.makeText(this,"Su cuenta $cuenta se registro correctamente",Toast.LENGTH_LONG).show()
                        finish()
                        //limpiarCampos()
                    }
                })
                hilo.start()
            }
        }catch (ex:Exception){
            Snackbar.make(v,"Hubo problemas al registrar su cuenta",Snackbar.LENGTH_LONG).show()
        }

    }

    fun limpiarCampos(){
        edtNombreForm.setText("")
        edtTelefonoForm.setText("")
        spnDistrito.setSelection(0)
        edtDireccionForm.setText("")
        edtUsuarioForm.setText("")
        edtContrasenaForm.setText("")
        edtRepContrasenaForm.setText("")
    }
}