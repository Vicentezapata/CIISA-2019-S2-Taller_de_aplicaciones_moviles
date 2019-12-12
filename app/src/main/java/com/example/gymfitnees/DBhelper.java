package com.example.gymfitnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    String tbl_evaluaciones = "CREATE TABLE tbl_evaluaciones (id integer PRIMARY KEY AUTOINCREMENT,fecha string,imc decimal,id_usuario decimal);";
    String tbl_usuarios = "CREATE TABLE tbl_usuarios (id integer PRIMARY KEY AUTOINCREMENT,nomUser string, nombre string,apellido string,fechNac date,estatura decimal,clave string);";
    public DBhelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    //METODO QUE NOS PERMITE CREAR LAS CONSULTAS DE CREACION DE TABLAS
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tbl_evaluaciones);
        db.execSQL(tbl_usuarios);
    }
    //METODO QUE NOS PERMITE ACTUALIZAR LA BD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
