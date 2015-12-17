package com.example.bisquert.agendalameta.connection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bisquert.agendalameta.model.Etiqueta;
import com.example.bisquert.agendalameta.model.Evento;

public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "agenda.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_EVENTO = "CREATE TABLE " + Evento.TABLE  + "("
                + Evento.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Evento.KEY_nombre + " TEXT, "
                // + Evento.KEY_fecha + " INTEGER, " AQUI HAY QUE PONER QUE LO GUARDE EN EL DIA COMO CALENDAR, YA LO BUSCARE
                + Evento.KEY_lugar + " TEXT, "
                + Evento.KEY_hora + " TEXT )";

        db.execSQL(CREATE_TABLE_EVENTO);

        String CREATE_TABLE_ETIQUETA = "CREATE TABLE " + Etiqueta.TABLE  + "("
                + Etiqueta.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Etiqueta.KEY_nombre + " TEXT )";

        db.execSQL(CREATE_TABLE_ETIQUETA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Evento.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Etiqueta.TABLE);

        // Create tables again
        onCreate(db);

    }

}