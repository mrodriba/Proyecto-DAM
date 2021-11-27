package com.example.appstoretechnology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.appstoretechnology.Controlador.CategoriaDAO
import com.example.appstoretechnology.Modelo.Categoria
import kotlinx.android.synthetic.main.activity_form_categoria.*
import java.util.regex.Pattern

class FormCategoriaActivity : AppCompatActivity() {

    // VALIDACIONES
    var patron_nomcategoria = Pattern.compile("[a-zA-z ]{3,30}")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_categoria)

        var x = intent
        if (x.getStringExtra("FUNCION") == "registrar"){
            edtId_categoria_form.setText("")
            edtNombreCateForm.setText("")
            btnEditarCateForm.isVisible = false
        }else{
            var indice:Int = x.getIntExtra("INDICE",0)
            var dao = CategoriaDAO(this)
            var obj_cate = dao.ListarCategorias().get(indice)
            edtId_categoria_form.setText(""+obj_cate.idcategoria)
            edtNombreCateForm.setText(""+obj_cate.descripcion)
            btnAgregarCategoriaForm.isVisible = false
        }
    }
    fun FuncionGrabarCategoria(v:View){
        var nombre = edtNombreCateForm.text.toString()

        // VALIDAR
        if (patron_nomcategoria.matcher(nombre).matches() == false){
            edtNombreCateForm.setError("Solo ingrese letras y como maximo 30 caracteres")
            return
        }else{
            edtNombreCateForm.setError(null)
        }
        var dao = CategoriaDAO(applicationContext)
        var cate = Categoria(0,nombre)
        if (cate != null){
            dao.GrabarCategoria(cate)
            Toast.makeText(this,"Agrego una nueva categoria", Toast.LENGTH_SHORT).show()
            edtNombreCateForm.setText("")
        }else{
            Toast.makeText(this,"Categoria no agregada", Toast.LENGTH_SHORT).show()
        }
    }

    fun FuncionEditarCategoria(v:View){
        var codigo = edtId_categoria_form.text.toString().trim().toInt()
        var nombre = edtNombreCateForm.text.toString()

        // VALIDAR
        if (patron_nomcategoria.matcher(nombre).matches() == false){
            edtNombreCateForm.setError("Solo ingrese letras y como maximo 30 caracteres")
            return
        }else{
            edtNombreCateForm.setError(null)
        }
        var dao = CategoriaDAO(applicationContext)
        var cate = Categoria(codigo,nombre)
        if (cate != null){
            dao.EditarCategoria(cate)
            Toast.makeText(this,"La categoria ha sido editada", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Error al editar la categoria", Toast.LENGTH_SHORT).show()
        }
    }
}