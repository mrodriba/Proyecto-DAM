package com.example.appstoretechnology.Controlador

import android.content.Context
import android.database.Cursor
import com.example.appstoretechnology.Modelo.Proveedor

class ProveedorDAO(context: Context) {
    var helper = DBhelper(context)

    fun ListarProveedores_String():ArrayList<String>{
        var cadena = "select * from proveedor"
        var lista = ArrayList<String>()
        var cad = ""
        var db = helper.readableDatabase
        var cur:Cursor = db.rawQuery(cadena, null)
        if (cur.count > 0){
            while (cur.moveToNext()){
                cad = "ID: "+cur.getInt(0) + " Proveedor: "+cur.getString(2)
                lista.add(cad)
            }
        }
        cur.close()
        db.close()
        return lista
    }
    fun ListarProveedores_Combo():ArrayList<String>{
        var cadena = "select * from proveedor"
        var lista = ArrayList<String>()
        var cad = ""
        var db = helper.readableDatabase
        var cur:Cursor = db.rawQuery(cadena, null)
        if (cur.count > 0){
            while (cur.moveToNext()){
                cad = cur.getString(2)
                lista.add(cad)
            }
        }
        cur.close()
        db.close()
        return lista
    }
    fun ListarProveedores():ArrayList<Proveedor>{
        var cadena = "select * from proveedor"
        var lista = ArrayList<Proveedor>()
        var cad = Proveedor(0,"","","",0)
        var db = helper.readableDatabase
        var cur:Cursor = db.rawQuery(cadena, null)
        if (cur.count > 0){
            while (cur.moveToNext()){
                cad = Proveedor(cur.getInt(0),cur.getString(1),cur.getString(2),cur.getString(3),cur.getInt(4))
                lista.add(cad)
            }
        }
        cur.close()
        db.close()
        return lista
    }
    fun GrabarProveedor(ob_prov : Proveedor){
        var cad_sql = """
            insert into proveedor(RucProveedor,NomProveedor,TelProveedor,IDDistrito) values(
            '${ob_prov.ruc}',
            '${ob_prov.nombre}',
            '${ob_prov.telefono}',
            '${ob_prov.iddistrito}')
        """.trimMargin()
        var db = helper.writableDatabase
        db.execSQL(cad_sql)
        db.close()
    }
    fun EditarProveedor(ob_prov : Proveedor){
        var cad_sql = """
            update proveedor set RucProveedor='${ob_prov.ruc}', NomProveedor='${ob_prov.nombre}', 
            TelProveedor='${ob_prov.telefono}', IDDistrito=${ob_prov.iddistrito}
            where IDProveedor=${ob_prov.idproveedor}
        """.trimMargin()
        var db = helper.writableDatabase
        db.execSQL(cad_sql)
        db.close()
    }
}