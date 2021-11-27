package com.example.appstoretechnology.Controlador

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBhelper(context: Context) : SQLiteOpenHelper(context, "BDSTORETECHNOLOGY",null,1) {

    var tb_categoria = """
        create table if not exists categoria(
            IDCategoria INTEGER PRIMARY KEY AUTOINCREMENT,
            Descripcion TEXT UNIQUE
        );
    """.trimIndent()

    var tb_distrito = """
        create table if not exists distrito(
            IDDistrito INTEGER PRIMARY KEY AUTOINCREMENT,
            Descripcion TEXT TEXT UNIQUE
        );
    """.trimIndent()

    var reg_distrito_0 = """insert into distrito(Descripcion) values('Seleccione distrito');""".trimIndent()
    var reg_distrito_1 = """insert into distrito(Descripcion) values('Miraflores');""".trimIndent()
    var reg_distrito_2 = """insert into distrito(Descripcion) values('Lima');""".trimIndent()
    var reg_distrito_3 = """insert into distrito(Descripcion) values('Surco');""".trimIndent()
    var reg_distrito_4 = """insert into distrito(Descripcion) values('San borja');""".trimIndent()

    var tb_proveedor = """
        create table if not exists proveedor(
            IDProveedor INTEGER PRIMARY KEY AUTOINCREMENT,
            RucProveedor TEXT UNIQUE,
            NomProveedor TEXT UNIQUE,
            TelProveedor TEXT,
            IDDistrito INTEGER,
            FOREIGN KEY(IDDistrito) REFERENCES distrito(IDDistrito)
        );
    """.trimIndent()

    var tb_producto = """
        create table if not exists producto(
            IDProducto INTEGER PRIMARY KEY AUTOINCREMENT,
            Nombre TEXT,
            Descripcion TEXT,
            IDCategoria INTEGER,
            IDProveedor INTEGER,
            Stock INTEGER,
            Precio REAL,
            IDEstado INTEGER,
            FOREIGN KEY(IDCategoria) REFERENCES categoria(IDCategoria),
            FOREIGN KEY(IDProveedor) REFERENCES proveedor(IDProveedor)
        );
    """.trimIndent()

    var tb_venta = """
        create table if not exists venta(
            IDVenta INTEGER PRIMARY KEY AUTOINCREMENT,
            IDUsuario INTEGER,
            IDProducto INTEGER,
            Cantidad INTEGER,
            MontoTotal REAL,
            IDEstado INTEGER,
            FOREIGN KEY(IDProducto) REFERENCES producto(IDProducto)
        );
    """.trimIndent()

    var reg_venta = """insert into venta(IDUsuario,IDProducto,Cantidad,MontoTotal,IDEstado) 
        values(1,1,10,120.00,1);""".trimIndent()

    override fun onCreate(db: SQLiteDatabase) {
        // Crear las tablas
        db.execSQL(tb_categoria);
        db.execSQL(tb_distrito);
        db.execSQL(reg_venta);
        //db.execSQL(reg_distrito_0);
        //db.execSQL(reg_distrito_1);
        //db.execSQL(reg_distrito_2);
        //db.execSQL(reg_distrito_3);
        //db.execSQL(reg_distrito_4);
        db.execSQL(tb_proveedor);
        db.execSQL(tb_producto);
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}