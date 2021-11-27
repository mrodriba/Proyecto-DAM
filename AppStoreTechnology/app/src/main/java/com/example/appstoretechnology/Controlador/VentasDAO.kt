package com.example.appstoretechnology.Controlador

import android.content.Context
import android.database.Cursor
import com.example.appstoretechnology.Modelo.Venta

class VentasDAO(context: Context) {
    var helper = DBhelper(context)
    fun ListarVentas_String():ArrayList<String>{
        var cadena = "select v.IDVenta,p.Nombre,v.Cantidad,v.MontoTotal from venta AS v INNER JOIN producto AS p ON v.IDProducto = p.IDProducto WHERE v.IDEstado = 2"
        var lista = ArrayList<String>()
        var cad = ""
        var db = helper.readableDatabase
        var cur: Cursor = db.rawQuery(cadena, null)
        if (cur.count > 0){
            while (cur.moveToNext()){
                cad = "Nro: "+cur.getInt(0) + " - Producto: "+cur.getString(1) + " - Cantidad: " + cur.getInt(2) + " - Monto: " + cur.getDouble(3)
                lista.add(cad)
            }
        }
        cur.close()
        db.close()
        return lista
    }
    fun ListarPedidos_String(idusu:Int):ArrayList<String>{
        var cadena = "select v.IDVenta,p.Nombre,v.Cantidad,v.MontoTotal from venta AS v INNER JOIN producto AS p ON v.IDProducto = p.IDProducto WHERE v.IDEstado = 2 AND v.IDUsuario=${idusu}"
        var lista = ArrayList<String>()
        var cad = ""
        var db = helper.readableDatabase
        var cur: Cursor = db.rawQuery(cadena, null)
        if (cur.count > 0){
            while (cur.moveToNext()){
                cad = "Nro: "+cur.getInt(0) + " - Producto: "+cur.getString(1) + " - Cantidad: " + cur.getInt(2) + " - Monto: " + cur.getDouble(3)
                lista.add(cad)
            }
        }
        cur.close()
        db.close()
        return lista
    }
    fun ListarCarrito_String(idusu:Int):ArrayList<String>{
        var cadena = "select p.Nombre,v.Cantidad,v.MontoTotal from venta AS v INNER JOIN producto AS p ON v.IDProducto = p.IDProducto WHERE v.IDEstado = 1 AND IDUsuario=${idusu}"
        var lista = ArrayList<String>()
        var cad = ""
        var db = helper.readableDatabase
        var cur: Cursor = db.rawQuery(cadena, null)
        if (cur.count > 0){
            while (cur.moveToNext()){
                cad = "Producto: "+cur.getString(0) + " - Cantidad: " + cur.getInt(1) + " - Monto total: " + cur.getDouble(2)
                lista.add(cad)
            }
        }
        cur.close()
        db.close()
        return lista
    }
    /*fun ReducirStock(cant:Int, id:Int){
        var cad_sql = """update producto set Stock -= ${cant} where IDProducto = ${id}""".trimMargin()
    }*/
    fun GrabarVenta(obj_venta : Venta){
        var cad_sql = """
            insert into venta(IDUsuario,IDProducto,Cantidad,MontoTotal,IDEstado) values(
            '${obj_venta.idusuario}',
            '${obj_venta.idproducto}',
            '${obj_venta.cantidad}',
            '${obj_venta.montototal}',
            '${obj_venta.idestado}')
        """.trimMargin()
        var cad_stock = """update producto set Stock = Stock - ${obj_venta.cantidad} where IDProducto = ${obj_venta.idproducto}""".trimMargin()
        var db = helper.writableDatabase
        db.execSQL(cad_sql)
        db.execSQL(cad_stock)
        db.close()
    }
    fun ComprarVentaDeUsuario(idusuario:Int){
        var cad_sql = """update venta set IDEstado=2 WHERE IDUsuario=${idusuario}""".trimMargin()
        var db = helper.writableDatabase
        db.execSQL(cad_sql)
        db.close()
    }
}