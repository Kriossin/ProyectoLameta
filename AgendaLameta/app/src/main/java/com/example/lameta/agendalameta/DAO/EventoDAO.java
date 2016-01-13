package com.example.lameta.agendalameta.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lameta.agendalameta.connection.DBHelper;
import com.example.lameta.agendalameta.model.Etiqueta;
import com.example.lameta.agendalameta.model.Evento;

import java.util.ArrayList;
import java.util.HashMap;

public class EventoDAO {
    private DBHelper dbHelper;

    public EventoDAO(Context context) { dbHelper = new DBHelper(context);}

    public int insert(Evento evento) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Evento.KEY_nombre, evento.nombre);
        values.put(Evento.KEY_lugar, evento.lugar);
        values.put(Evento.KEY_fecha, evento.fecha);
        values.put(Evento.KEY_hora, evento.hora);

        long evento_ID = db.insert(Evento.TABLE, null, values);
        db.close();;
        return (int) evento_ID;
    }

    public void delete(int evento_ID){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Evento.TABLE, Evento.KEY_ID + "= ?", new String[]{String.valueOf(evento_ID)});
        db.close();

    }

    public void update(Evento evento){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Evento.KEY_nombre, evento.nombre);
        values.put(Evento.KEY_lugar, evento.lugar);
        values.put(Evento.KEY_fecha, evento.fecha);
        values.put(Evento.KEY_hora, evento.hora);

        db.update(Evento.TABLE, values, Evento.KEY_ID + "= ?", new String[]{String.valueOf(evento.evento_ID)});
        db.close();

    }

    public ArrayList<HashMap<String, String>> getListaEvento() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Evento.KEY_ID + "," +
                Evento.KEY_nombre + "," +
                Evento.KEY_lugar + "," +
                Evento.KEY_fecha + "," +
                Evento.KEY_hora +
                " FROM " + Evento.TABLE;

        ArrayList<HashMap<String, String>> eventoList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> evento = new HashMap<String, String>();
                evento.put("id", cursor.getString(cursor.getColumnIndex(Evento.KEY_ID)));
                evento.put("name", cursor.getString(cursor.getColumnIndex(Evento.KEY_nombre)));
                eventoList.add(evento);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return eventoList;

    }

    public ArrayList<HashMap<String, String>>  getListaEventoPorNombre(String nameEventoSearch) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT " +
                Evento.KEY_ID + "," +
                Evento.KEY_nombre + "," +
                Evento.KEY_lugar + "," +
                Evento.KEY_fecha + "," +
                Evento.KEY_hora +
                " FROM " + Evento.TABLE +
                " WHERE " + Evento.KEY_nombre + " LIKE ?";

        ArrayList<HashMap<String, String>> eventoList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {"%" + nameEventoSearch + "%"});
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> evento = new HashMap<String, String>();
                evento.put("id", cursor.getString(cursor.getColumnIndex(Evento.KEY_ID)));
                evento.put("name", cursor.getString(cursor.getColumnIndex(Evento.KEY_nombre)));
                eventoList.add(evento);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return eventoList;

    }

    public Evento getListaEventoPorID(int ID){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Evento.KEY_ID + "," +
                Evento.KEY_nombre + "," +
                Evento.KEY_lugar + "," +
                Evento.KEY_fecha + "," +
                Evento.KEY_hora +
                " FROM " + Evento.TABLE
                + " WHERE " +
                Evento.KEY_ID + "=?";

        int iCount =0;
        Evento evento = new Evento();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(ID) } );

        if (cursor.moveToFirst()) {
            do {
                evento.evento_ID =cursor.getInt(cursor.getColumnIndex(Evento.KEY_ID));
                evento.nombre =cursor.getString(cursor.getColumnIndex(Evento.KEY_nombre));
                evento.lugar  =cursor.getString(cursor.getColumnIndex(Evento.KEY_lugar));
                evento.fecha =cursor.getString(cursor.getColumnIndex(Evento.KEY_fecha));
                evento.hora =cursor.getString(cursor.getColumnIndex(Evento.KEY_hora));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return evento;
    }

    public ArrayList<HashMap<String, String>>  getListaEventoPorLugar(String nameEventoSearch) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Evento.KEY_ID + "," +
                Evento.KEY_nombre + "," +
                Evento.KEY_lugar + "," +
                Evento.KEY_fecha + "," +
                Evento.KEY_hora +
                " FROM " + Evento.TABLE +
                " WHERE " + Evento.KEY_lugar + " LIKE ?";

        ArrayList<HashMap<String, String>> eventoList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {"%" + nameEventoSearch + "%"});
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> evento = new HashMap<String, String>();
                evento.put("id", cursor.getString(cursor.getColumnIndex(Evento.KEY_ID)));
                evento.put("name", cursor.getString(cursor.getColumnIndex(Evento.KEY_nombre)));
                eventoList.add(evento);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return eventoList;

    }

    public ArrayList<HashMap<String, String>>  getListaEventoPorEtiqueta(String nameEventoSearch) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Evento.KEY_ID + "," +
                Evento.KEY_nombre + "," +
                Evento.KEY_lugar + "," +
                Evento.KEY_fecha + "," +
                Evento.KEY_hora +
                " FROM " + Evento.TABLE +
                " WHERE " + Evento.KEY_ID + " IN(SELECT " + Etiqueta.KEY_ID_evento +
                " FROM " + Etiqueta.TABLE +
                " WHERE " +
                Etiqueta.KEY_nombre + " LIKE ?)";

        ArrayList<HashMap<String, String>> eventoList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {"%" + nameEventoSearch + "%"});
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> evento = new HashMap<String, String>();
                evento.put("id", cursor.getString(cursor.getColumnIndex(Evento.KEY_ID)));
                evento.put("name", cursor.getString(cursor.getColumnIndex(Evento.KEY_nombre)));
                eventoList.add(evento);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return eventoList;

    }

    public ArrayList<HashMap<String, String>>  getListaEventoPorFecha(String nameFechaSearch) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Evento.KEY_ID + "," +
                Evento.KEY_nombre + "," +
                Evento.KEY_lugar + "," +
                Evento.KEY_fecha + "," +
                Evento.KEY_hora +
                " FROM " + Evento.TABLE +
                " WHERE " + Evento.KEY_fecha + " LIKE ?";

        ArrayList<HashMap<String, String>> eventoList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {"%" + nameFechaSearch + "%"});
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> evento = new HashMap<String, String>();
                evento.put("id", cursor.getString(cursor.getColumnIndex(Evento.KEY_ID))); //HACER UN LOG
                //Log.v("mirarID", evento.put("id", cursor.getString(cursor.getColumnIndex(Evento.KEY_ID))));
                evento.put("name", cursor.getString(cursor.getColumnIndex(Evento.KEY_nombre)));
                eventoList.add(evento);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return eventoList;

    }

    public ArrayList<HashMap<String, String>>  getListaEventoPorBuscador(String buscar) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Evento.KEY_ID + "," +
                Evento.KEY_nombre + "," +
                Evento.KEY_lugar + "," +
                Evento.KEY_fecha + "," +
                Evento.KEY_hora +
                " FROM " + Evento.TABLE +
                " WHERE " + Evento.KEY_nombre + " LIKE ?" +
                " OR " + Evento.KEY_lugar + " LIKE ?" +
                " OR " + Evento.KEY_ID + " IN(SELECT " + Etiqueta.KEY_ID_evento +
                " FROM " + Etiqueta.TABLE +
                " WHERE " + Etiqueta.KEY_nombre + " LIKE ?)";

        ArrayList<HashMap<String, String>> eventoList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {"%" + buscar + "%", "%" + buscar + "%", "%" + buscar + "%"});
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> evento = new HashMap<String, String>();
                evento.put("id", cursor.getString(cursor.getColumnIndex(Evento.KEY_ID)));
                evento.put("name", cursor.getString(cursor.getColumnIndex(Evento.KEY_nombre)));
                eventoList.add(evento);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return eventoList;

    }

}
