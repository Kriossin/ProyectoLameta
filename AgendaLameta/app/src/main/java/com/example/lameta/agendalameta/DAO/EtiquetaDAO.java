package com.example.lameta.agendalameta.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lameta.agendalameta.connection.DBHelper;
import com.example.lameta.agendalameta.model.Etiqueta;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Bisquert on 11/12/2015.
 */
public class EtiquetaDAO {
    private DBHelper dbHelper;

    public EtiquetaDAO(Context context){dbHelper = new DBHelper(context);}

    public int insert(Etiqueta etiqueta){

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Etiqueta.KEY_nombre, etiqueta.nombre);
        values.put(Etiqueta.KEY_ID_evento, etiqueta.id_evento);

        //Inserting Row
        long etiqueta_id = db.insert(Etiqueta.TABLE, null, values);
        db.close(); //Closing database connection
        return (int) etiqueta_id;
    }

    public void delete(int etiqueta_id){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Etiqueta.TABLE, Etiqueta.KEY_ID + "= ?", new String[]{String.valueOf(etiqueta_id)});
        db.close(); //Closing database connection
    }

    public void update(Etiqueta etiqueta) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Etiqueta.KEY_nombre, etiqueta.nombre);
        values.put(Etiqueta.KEY_ID_evento, etiqueta.id_evento);

        //Es bueno para practicar a usar el parametro ?, en vez de concatenar strings
        db.update(Etiqueta.TABLE, values, Etiqueta.KEY_ID + "= ?", new String[]{String.valueOf(etiqueta.etiqueta_ID)});
        db.close(); //Cierra la conexion a la base de datos
    }

    public ArrayList<HashMap<String, String>> getEtiquetaList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Etiqueta.KEY_ID + "," +
                Etiqueta.KEY_nombre + "," +
                Etiqueta.KEY_ID_evento +
                " FROM " + Etiqueta.TABLE;

        //Etiqueta etiqueta = new Etiqueta();
        ArrayList<HashMap<String, String>> etiquetaList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        //looping throug all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> etiqueta = new HashMap<String, String>();
                etiqueta.put("id", cursor.getString(cursor.getColumnIndex(Etiqueta.KEY_ID)));
                etiqueta.put("nombre", cursor.getString(cursor.getColumnIndex(Etiqueta.KEY_nombre)));
                etiquetaList.add(etiqueta);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return etiquetaList;

    }

    public ArrayList<HashMap<String, String>> getEtiquetaListByName(String nameEtiquetaSearch){
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Etiqueta.KEY_ID + "," +
                Etiqueta.KEY_nombre + ","+
                Etiqueta.KEY_ID_evento +
                " FROM " + Etiqueta.TABLE +
                " WHERE " + Etiqueta.KEY_nombre + " LIKE ?";

        //Etiqueta etiqueta = new Etiqueta();
        ArrayList<HashMap<String, String>> etiquetaList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        //looping throug all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> etiqueta = new HashMap<String, String>();
                etiqueta.put("id", cursor.getString(cursor.getColumnIndex(Etiqueta.KEY_ID)));
                etiqueta.put("nombre", cursor.getString(cursor.getColumnIndex(Etiqueta.KEY_nombre)));
                etiquetaList.add(etiqueta);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return etiquetaList;
    }

    public Etiqueta getEtiquetaListByID(int ID) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Etiqueta.KEY_ID + "," +
                Etiqueta.KEY_nombre + ","+
                Etiqueta.KEY_ID_evento +
                " FROM " + Etiqueta.TABLE +
                " WHERE " + Etiqueta.KEY_ID + "=?";

        int iCount = 0;
        Etiqueta etiqueta = new Etiqueta();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(ID)});

        if (cursor.moveToFirst()) {
            do {
                etiqueta.etiqueta_ID = cursor.getInt(cursor.getColumnIndex(Etiqueta.KEY_ID));
                etiqueta.nombre = cursor.getString(cursor.getColumnIndex(Etiqueta.KEY_nombre));
                etiqueta.id_evento = cursor.getInt(cursor.getColumnIndex(Etiqueta.KEY_ID_evento));

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return etiqueta;
    }

    public Etiqueta getEtiquetaListByIDEVENTO(int ID) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Etiqueta.KEY_ID + "," +
                Etiqueta.KEY_nombre + ","+
                Etiqueta.KEY_ID_evento +
                " FROM " + Etiqueta.TABLE +
                " WHERE " + Etiqueta.KEY_ID_evento + "=?";

        int iCount = 0;
        Etiqueta etiqueta = new Etiqueta();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(ID)});

        if (cursor.moveToFirst()) {
            do {
                etiqueta.etiqueta_ID = cursor.getInt(cursor.getColumnIndex(Etiqueta.KEY_ID));
                etiqueta.nombre = cursor.getString(cursor.getColumnIndex(Etiqueta.KEY_nombre));
                etiqueta.id_evento = cursor.getInt(cursor.getColumnIndex(Etiqueta.KEY_ID_evento));

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return etiqueta;
    }
}
