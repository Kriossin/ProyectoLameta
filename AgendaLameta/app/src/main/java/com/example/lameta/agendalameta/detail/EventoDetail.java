package com.example.lameta.agendalameta.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lameta.agendalameta.DAO.EtiquetaDAO;
import com.example.lameta.agendalameta.DAO.EventoDAO;
import com.example.lameta.agendalameta.R;
import com.example.lameta.agendalameta.main.MainActivity;
import com.example.lameta.agendalameta.model.Etiqueta;
import com.example.lameta.agendalameta.model.Evento;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

//import com.example.lameta.agendalameta.R;

/**
 * Created by Bisquert on 11/12/2015.
 */
public class EventoDetail extends AppCompatActivity implements android.view.View.OnClickListener{
    Button btnGuardar, btnBorrar;
    boolean crear = false;
    EditText editEvento, editFecha, editLugar, editHora, editEtiqueta;
    private int Evento_Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);



        btnGuardar = (Button) findViewById(R.id.guardar);
        btnBorrar = (Button) findViewById(R.id.borrar);

        editEvento = (EditText) findViewById(R.id.editText_Evento);
        editFecha = (EditText) findViewById(R.id.editText_Fecha);
        editLugar = (EditText) findViewById(R.id.editText_Lugar);
        editHora = (EditText) findViewById(R.id.editText_hora);
        editEtiqueta = (EditText) findViewById(R.id.editText_Etiqueta);

        btnGuardar.setOnClickListener(this);
        btnBorrar.setOnClickListener(this);

        Evento_Id = 0;
        Intent intent = getIntent();
        Evento_Id=intent.getIntExtra("Evento_Id",0);
        Evento_Id = MainActivity.idBuscado;
        EventoDAO eventoDAO = new EventoDAO(this);
        EtiquetaDAO etiquetaDAO = new EtiquetaDAO((this));
        Evento evento = new Evento();
        Etiqueta etiqueta = new Etiqueta();
        evento = eventoDAO.getListaEventoPorID(MainActivity.idBuscado);
        etiqueta = etiquetaDAO.getEtiquetaListByIDEVENTO(MainActivity.idBuscado);
        Log.v("buscador", Integer.toString(Evento_Id));

        if(evento.nombre != null) {
            editEvento.setText(String.valueOf(evento.nombre));
            crear = false;
        }
        else{
            editEvento.setText("");
            crear = true;
        }

        if(evento.nombre != null)
            editFecha.setText(evento.fecha);
        else
            editFecha.setText(MainActivity.fecha);

            editHora.setText(evento.hora);
            editLugar.setText(evento.lugar);
            editEtiqueta.setText(etiqueta.nombre);

        MainActivity.idBuscado = 0;


    }

    public void onClick(View v) {
        if (v == findViewById(R.id.guardar)) {

            EventoDAO eventoDAO = new EventoDAO(this);
            EtiquetaDAO etiquetaDAO = new EtiquetaDAO(this);
            Evento evento = new Evento();
            Etiqueta etiqueta = new Etiqueta();
            evento.nombre = editEvento.getText().toString();
            evento.fecha = editFecha.getText().toString();
            evento.lugar = editLugar.getText().toString();
            evento.hora = editHora.getText().toString();
            etiqueta.nombre = editEtiqueta.getText().toString();
            evento.evento_ID = Evento_Id;

            if (crear) {
                Evento_Id = eventoDAO.insert(evento);
                etiqueta.id_evento = Evento_Id;
                etiquetaDAO.insert(etiqueta);
                finish();

                Toast.makeText(this, "Nuevo evento creado", Toast.LENGTH_SHORT).show();

            } else {
                eventoDAO.update(evento);
                Log.v("Buscador", Integer.toString(evento.evento_ID));
                Toast.makeText(this, "Evento modificado", Toast.LENGTH_SHORT).show();
                finish();

            }

            etiqueta.id_evento = Evento_Id;
            etiquetaDAO.insert(etiqueta);
            finish();
        } else if (v == findViewById(R.id.borrar)) {

            EventoDAO eventoDAO = new EventoDAO(this);
            eventoDAO.delete(Evento_Id);
            Log.v("Buscador", Integer.toString(Evento_Id));
            Toast.makeText(this, "Evento eliminado", Toast.LENGTH_SHORT).show();
            finish();


        }
    }


}
