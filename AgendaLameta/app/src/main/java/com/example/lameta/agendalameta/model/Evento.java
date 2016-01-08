package com.example.lameta.agendalameta.model;

/**
 * Created by Bisquert on 11/12/2015.
 */
public class Evento {
    // Labels table name
    public static final String TABLE = "Evento";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_nombre = "nombre";
    //public static final String KEY_fecha = "fecha";
    public static final String KEY_lugar = "lugar";
    public static final String KEY_hora = "hora";

    // property help us to keep data
    public int evento_ID;
    public String nombre;
    //public String fecha;
    public String lugar;
    public String hora;

}
