package com.example.appstoretechnology.Controlador

import android.content.Context
import android.database.Cursor

class DistritoDAO(context: Context) {
    var helper = DBhelper(context)

    fun ListarDistritos_String():ArrayList<String>{
        var cadena = "select * from distrito"
        var lista = ArrayList<String>()
        var cad = ""
        var db = helper.readableDatabase
        var cur: Cursor = db.rawQuery(cadena, null)
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
}