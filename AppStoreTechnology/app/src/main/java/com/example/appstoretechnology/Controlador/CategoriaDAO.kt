package com.example.appstoretechnology.Controlador

import android.content.Context
import android.database.Cursor
import com.example.appstoretechnology.Modelo.Categoria

class CategoriaDAO(context: Context) {
    var helper = DBhelper(context)

    fun ListarCategorias_String():ArrayList<String>{
        var cadena = "SELECT * FROM categoria"
        var lista = ArrayList<String>()
        var cad = ""
        var db = helper.readableDatabase
        var cur:Cursor = db.rawQuery(cadena, null)
        if (cur.count > 0){
            while (cur.moveToNext()){
                cad = "ID: "+cur.getInt(0) + " - Nombre: "+cur.getString(1)
                lista.add(cad)
            }
        }
        cur.close()
        db.close()
        return lista
    }

    fun ListarCategorias_Combo():ArrayList<String>{
        var cadena = "SELECT * FROM categoria"
        var lista = ArrayList<String>()
        var cad = ""
        var db = helper.readableDatabase
        var cur:Cursor = db.rawQuery(cadena, null)
        if (cur.count > 0){
            while (cur.moveToNext()){
                cad = cur.getString(1)
                lista.add(cad)
            }
        }
        cur.close()
        db.close()
        return lista
    }

    fun ListarCategorias():ArrayList<Categoria>{
        var cadena = "select * from categoria"
        var lista = ArrayList<Categoria>()
        var cad = Categoria(0,"")
        var db = helper.readableDatabase
        var cur:Cursor = db.rawQuery(cadena, null)
        if (cur.count > 0){
            while (cur.moveToNext()){
                cad = Categoria(cur.getInt(0),cur.getString(1))
                lista.add(cad)
            }
        }
        cur.close()
        db.close()
        return lista
    }

    fun GrabarCategoria(ob_cate : Categoria){
        var cad_sql = """
            insert into categoria(Descripcion) values(
            '${ob_cate.descripcion}')
        """.trimMargin()
        var db = helper.writableDatabase
        db.execSQL(cad_sql)
        db.close()
    }
    fun EditarCategoria(ob_cate : Categoria){
        var cad_sql = """
            update categoria set Descripcion='${ob_cate.descripcion}'
            where IDCategoria=${ob_cate.idcategoria}
        """.trimMargin()
        var db = helper.writableDatabase
        db.execSQL(cad_sql)
        db.close()
    }
}