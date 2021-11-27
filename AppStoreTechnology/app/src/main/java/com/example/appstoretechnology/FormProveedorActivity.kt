package com.example.appstoretechnology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.appstoretechnology.Controlador.DistritoDAO
import com.example.appstoretechnology.Controlador.ProveedorDAO
import com.example.appstoretechnology.Modelo.Proveedor
import kotlinx.android.synthetic.main.activity_form_proveedor.*
import java.util.regex.Pattern

class FormProveedorActivity : AppCompatActivity() {

    // VALIDACIONES
    var patron_ruc = Pattern.compile("[a-zA-Z-0-9 ]{3,20}")
    var patron_nombre = Pattern.compile("[a-zA-Z]{3,25}")
    var patron_telefono = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{3}")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_proveedor)

        PoblarComboDistrito()

        var x = intent
        if (x.getStringExtra("FUNCION") == "registrar"){
            LimpiarCampos()
            btnEditarProvForm.isVisible = false
        }else{
            var indice:Int = x.getIntExtra("INDICE",0)
            var dao = ProveedorDAO(this)
            var obj_prov = dao.ListarProveedores().get(indice)
            edtId_proveedor_form.setText(""+obj_prov.idproveedor)
            edtRuc_proveedor_form.setText(""+obj_prov.ruc)
            edtNombre_proveedor_form.setText(""+obj_prov.nombre)
            edtTelefono_proveedor_form.setText(""+obj_prov.telefono)
            spnDistritoProveedorForm.setSelection(obj_prov.iddistrito)
            btnAgregarProvForm.isVisible = false
        }
    }
    fun PoblarComboDistrito(){
        var dao_dist = DistritoDAO(this)
        var adaptador = ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                dao_dist.ListarDistritos_String()
        )
        spnDistritoProveedorForm.adapter = adaptador
    }

    fun FuncionGrabarProveedor(v:View){
        var dao = ProveedorDAO(applicationContext)
        var ruc = edtRuc_proveedor_form.text.toString()
        var nombre = edtNombre_proveedor_form.text.toString()
        var telefono = edtTelefono_proveedor_form.text.toString()
        var distrito = spnDistritoProveedorForm.selectedItemPosition

        // Validar
        if (patron_ruc.matcher(ruc).matches() == false){
            edtRuc_proveedor_form.setError("Formato incorrecto: Debe ingresar mas de 2 caracteres")
            return
        }else{
            edtRuc_proveedor_form.setError(null)
            edtNombre_proveedor_form.requestFocus()
        }
        if (patron_nombre.matcher(nombre).matches() == false){
            edtNombre_proveedor_form.setError("Formato incorrecto: Debe ingresar solo letras")
            return
        }else{
            edtNombre_proveedor_form.setError(null)
            edtTelefono_proveedor_form.requestFocus()
        }
        if (patron_telefono.matcher(telefono).matches() == false){
            edtTelefono_proveedor_form.setError("Formato incorrecto: Debe colocar de después de cada 3 digitos un '-'")
            return
        }else{
            edtTelefono_proveedor_form.setError(null)
        }
        //
        var prov = Proveedor(0,ruc,nombre,telefono,distrito)
        if (prov != null){
            if (spnDistritoProveedorForm.selectedItemPosition == 0){
                Toast.makeText(this,"Debe seleccionar un distrito", Toast.LENGTH_SHORT).show()
            }else{
                dao.GrabarProveedor(prov)
                Toast.makeText(this,"Agrego un nuevo proveedor", Toast.LENGTH_SHORT).show()
                LimpiarCampos()
            }
        }else{
            Toast.makeText(this,"Proveedor no agregado", Toast.LENGTH_SHORT).show()
        }
    }

    fun FuncionEditarProveedor(v:View){
        var dao = ProveedorDAO(applicationContext)
        var idproveedor = edtId_proveedor_form.text.toString().trim().toInt()
        var ruc = edtRuc_proveedor_form.text.toString().trim()
        var nombre = edtNombre_proveedor_form.text.toString()
        var telefono = edtTelefono_proveedor_form.text.toString().trim()
        var distrito = spnDistritoProveedorForm.selectedItemPosition

        // Validar
        if (patron_ruc.matcher(ruc).matches() == false){
            edtRuc_proveedor_form.setError("Formato incorrecto: Debe ingresar mas de 2 caracteres")
            return
        }else{
            edtRuc_proveedor_form.setError(null)
            edtNombre_proveedor_form.requestFocus()
        }
        if (patron_nombre.matcher(nombre).matches() == false){
            edtNombre_proveedor_form.setError("Formato incorrecto: Debe ingresar solo letras")
            return
        }else{
            edtNombre_proveedor_form.setError(null)
            edtTelefono_proveedor_form.requestFocus()
        }
        if (patron_telefono.matcher(telefono).matches() == false){
            edtTelefono_proveedor_form.setError("Formato incorrecto: Debe colocar de después de cada 3 digitos un '-'")
            return
        }else{
            edtTelefono_proveedor_form.setError(null)
        }
        //
        var prov = Proveedor(idproveedor,ruc,nombre,telefono,distrito)
        if (prov != null){
            if (spnDistritoProveedorForm.selectedItemPosition == 0){
                Toast.makeText(this,"Debe seleccionar un distrito", Toast.LENGTH_SHORT).show()
            }else{
                dao.EditarProveedor(prov)
                Toast.makeText(this,"El proveedor ha sido editado", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Error al editar el proveedor", Toast.LENGTH_SHORT).show()
        }
    }

    fun LimpiarCampos(){
        edtId_proveedor_form.setText("")
        edtRuc_proveedor_form.setText("")
        edtNombre_proveedor_form.setText("")
        edtTelefono_proveedor_form.setText("")
        spnDistritoProveedorForm.setSelection(0)
    }
}