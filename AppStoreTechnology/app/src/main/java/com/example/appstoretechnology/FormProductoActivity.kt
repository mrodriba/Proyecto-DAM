package com.example.appstoretechnology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.appstoretechnology.Controlador.CategoriaDAO
import com.example.appstoretechnology.Controlador.ProductoDAO
import com.example.appstoretechnology.Controlador.ProveedorDAO
import com.example.appstoretechnology.Modelo.Producto
import kotlinx.android.synthetic.main.activity_form_producto.*
import java.util.regex.Pattern

class FormProductoActivity : AppCompatActivity() {

    // VALIDACIONES
    var patron_nombre = Pattern.compile("[a-zA-Z-0-9 ]{3,20}")
    var patron_stock = Pattern.compile("[0-9]{1,3}")
    var patron_precio = Pattern.compile("[0-9]{2,4}.[0-9]{2}")
    var patron_descripción = Pattern.compile("[a-zA-Z-0-9 ]{5,400}")

    // COMBO DE ESTADOS
    var v_estado = arrayOf("Seleccione estado","Activo","Vacio")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_producto)

        PoblarComboCategoria()
        PoblarComboProveedor()
        PoblarComboEstado()

        var x = intent
        if (x.getStringExtra("FUNCION") == "registrar"){
            LimpiarCampos()
            btnEditarProductoForm.isVisible = false
        }else{
            var indice:Int = x.getIntExtra("INDICE",0)
            var dao = ProductoDAO(this)
            var obj_prod = dao.ListarProductos().get(indice)
            edtId_producto_form.setText(""+obj_prod.idproducto)
            edtNombre_producto_form.setText(""+obj_prod.nombre)
            spnCategoriaProdForm.setSelection(obj_prod.idcategoria)
            spnProveedorProdForm.setSelection(obj_prod.idproveedor)
            edtStock_producto_form.setText(""+obj_prod.stock)
            edtPrecio_producto_form.setText(""+obj_prod.precio+"0")
            spnEstadoProdForm.setSelection(obj_prod.idestado)
            edtDescripcion_producto_form.setText(""+obj_prod.descripcion)
            btnAgregarProductoForm.isVisible = false
        }
    }

    fun PoblarComboCategoria(){
        var dao_cat = CategoriaDAO(this)
        var adaptador = ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                dao_cat.ListarCategorias_Combo()
        )
        spnCategoriaProdForm.adapter = adaptador
    }
    fun PoblarComboProveedor(){
        var dao_prov = ProveedorDAO(this)
        var adaptador = ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                dao_prov.ListarProveedores_Combo()
        )
        spnProveedorProdForm.adapter = adaptador
    }
    fun PoblarComboEstado(){
        var adaptador_estado = ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                v_estado
        )
        spnEstadoProdForm.adapter = adaptador_estado
    }

    fun FuncionGrabarProducto(v: View){
        var dao = ProductoDAO(applicationContext)
        var nombre = edtNombre_producto_form.text.toString()
        var categoria = spnCategoriaProdForm.selectedItemPosition
        var proveedor = spnProveedorProdForm.selectedItemPosition
        var stock = edtStock_producto_form.text.toString()
        var precio = edtPrecio_producto_form.text.toString()
        var estado = spnEstadoProdForm.selectedItemPosition
        var descripcion = edtDescripcion_producto_form.text.toString()

        // Validar
        if (patron_nombre.matcher(nombre).matches() == false){
            edtNombre_producto_form.setError("Formato incorrecto: El nombre debe contener entre 3 a 20 letras")
            return
        }else{
            edtNombre_producto_form.setError(null)
            edtStock_producto_form.requestFocus()
        }
        if (patron_stock.matcher(stock).matches() == false){
            edtStock_producto_form.setError("Formato incorrecto: El stock no debe sobrepasar los 999")
            return
        }else{
            edtStock_producto_form.setError(null)
            edtPrecio_producto_form.requestFocus()
        }
        if (patron_precio.matcher(precio).matches() == false){
            edtPrecio_producto_form.setError("El precio debe cumplir con el formato '00.00' y no debe ser mayor a 9999.00")
            return
        }else{
            edtPrecio_producto_form.setError(null)
            edtDescripcion_producto_form.requestFocus()
        }
        if (patron_descripción.matcher(descripcion).matches() == false){
            edtDescripcion_producto_form.setError("Debe ingresar mas de 5 letras")
            return
        }else{
            edtDescripcion_producto_form.setError(null)
        }

        //
        var prod = Producto(0,nombre,descripcion,categoria,proveedor,stock.toInt(),precio.toDouble(),estado)
        if (prod != null){
            if (spnEstadoProdForm.selectedItemPosition == 0){
                Toast.makeText(this,"Debe seleccionar un estado", Toast.LENGTH_SHORT).show()
            }else{
                dao.GrabarProducto(prod)
                Toast.makeText(this,"Agrego un nuevo producto", Toast.LENGTH_SHORT).show()
                LimpiarCampos()
            }
        }else{
            Toast.makeText(this,"Producto no agregado", Toast.LENGTH_SHORT).show()
        }
    }

    fun FuncionEditarProducto(v: View){
        var dao = ProductoDAO(applicationContext)
        var idproducto = edtId_producto_form.text.toString().toInt()
        var nombre = edtNombre_producto_form.text.toString()
        var categoria = spnCategoriaProdForm.selectedItemPosition
        var proveedor = spnProveedorProdForm.selectedItemPosition
        var stock = edtStock_producto_form.text.toString()
        var precio = edtPrecio_producto_form.text.toString()
        var estado = spnEstadoProdForm.selectedItemPosition
        var descripcion = edtDescripcion_producto_form.text.toString()

        // Validar
        if (patron_nombre.matcher(nombre).matches() == false){
            edtNombre_producto_form.setError("Formato incorrecto: El nombre debe contener entre 3 a 20 letras")
            return
        }else{
            edtNombre_producto_form.setError(null)
            edtStock_producto_form.requestFocus()
        }
        if (patron_stock.matcher(stock).matches() == false){
            edtStock_producto_form.setError("Formato incorrecto: El stock no debe sobrepasar los 999")
            return
        }else{
            edtStock_producto_form.setError(null)
            edtPrecio_producto_form.requestFocus()
        }
        if (patron_precio.matcher(precio).matches() == false){
            edtPrecio_producto_form.setError("El precio debe cumplir con el formato '00.00' y no debe ser mayor a 9999.00")
            return
        }else{
            edtPrecio_producto_form.setError(null)
            edtDescripcion_producto_form.requestFocus()
        }
        if (patron_descripción.matcher(descripcion).matches() == false){
            edtDescripcion_producto_form.setError("Debe ingresar mas de 5 letras")
            return
        }else{
            edtDescripcion_producto_form.setError(null)
        }

        var prod = Producto(idproducto,nombre,descripcion,categoria,proveedor,stock.toInt(),precio.toDouble(),estado)
        if (prod != null){
            if (spnEstadoProdForm.selectedItemPosition == 0){
                Toast.makeText(this,"Debe seleccionar un estado", Toast.LENGTH_SHORT).show()
            }else{
                dao.EditarProducto(prod)
                Toast.makeText(this,"El producto ha sido editado", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Error al editar el producto", Toast.LENGTH_SHORT).show()
        }
    }

    fun LimpiarCampos(){
        edtId_producto_form.setText("")
        edtNombre_producto_form.setText("")
        spnCategoriaProdForm.setSelection(0)
        spnProveedorProdForm.setSelection(0)
        edtStock_producto_form.setText("")
        edtPrecio_producto_form.setText("")
        spnEstadoProdForm.setSelection(0)
        edtDescripcion_producto_form.setText("")
    }
}