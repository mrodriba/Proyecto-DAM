package com.example.appstoretechnology.Controlador

import android.content.Context
import android.database.Cursor
import com.example.appstoretechnology.Modelo.Producto

class ProductoDAO(context: Context) {
    var helper = DBhelper(context)

    fun ListarProductos_String():ArrayList<String>{
        var cadena = "select * from producto"
        var lista = ArrayList<String>()
        var cad = ""
        var db = helper.readableDatabase
        var cur: Cursor = db.rawQuery(cadena, null)
        if (cur.count > 0){
            while (cur.moveToNext()){
                cad = "ID: "+cur.getInt(0) + " Producto: "+cur.getString(1) + " Stock: "+ cur.getInt(5)
                lista.add(cad)
            }
        }
        cur.close()
        db.close()
        return lista
    }
    fun ListarProductos():ArrayList<Producto>{
        var cadena = "select * from producto"
        var lista = ArrayList<Producto>()
        var cad = Producto(0,"","",0,0,0,0.00,0)
        var db = helper.readableDatabase
        var cur:Cursor = db.rawQuery(cadena, null)
        if (cur.count > 0){
            while (cur.moveToNext()){
                cad = Producto(cur.getInt(0),cur.getString(1),cur.getString(2),cur.getInt(3),cur.getInt(4),cur.getInt(5),cur.getDouble(6),cur.getInt(7))
                lista.add(cad)
            }
        }
        cur.close()
        db.close()
        return lista
    }
    fun GrabarProducto(ob_prod : Producto){
        var cad_sql = """
            insert into producto(Nombre,Descripcion,IDCategoria,IDProveedor,Stock,Precio,IDEstado) values(
            '${ob_prod.nombre}',
            '${ob_prod.descripcion}',
            '${ob_prod.idcategoria}',
            '${ob_prod.idproveedor}',
            '${ob_prod.stock}',
            '${ob_prod.precio}',
            '${ob_prod.idestado}')
        """.trimMargin()
        var db = helper.writableDatabase
        db.execSQL(cad_sql)
        db.close()
    }
    fun EditarProducto(ob_prod : Producto){
        var cad_sql = """
            update producto set Nombre='${ob_prod.nombre}', Descripcion='${ob_prod.descripcion}',
            IDCategoria=${ob_prod.idcategoria}, IDProveedor=${ob_prod.idproveedor},
            Stock=${ob_prod.stock}, Precio=${ob_prod.precio}, IDEstado=${ob_prod.idestado}
            where IDProducto=${ob_prod.idproducto}
        """.trimMargin()
        var db = helper.writableDatabase
        db.execSQL(cad_sql)
        db.close()
    }

}