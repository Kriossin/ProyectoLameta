package com.example.lameta.agendalameta.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lameta.agendalameta.DAO.EventoDAO;
import com.example.lameta.agendalameta.R;
import com.example.lameta.agendalameta.model.Etiqueta;
import com.example.lameta.agendalameta.model.Evento;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

//import com.example.lameta.agendalameta.R;

/**
 * Created by Bisquert on 11/12/2015.
 */
public class EventoDetail extends ActionBarActivity implements android.view.View.OnClickListener{
    Button btnGuardar, btnBorrar;
    EditText editEvento, editFecha, editLugar, editHora, editEtiqueta;
    private int Evento_Id=0;

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
        EventoDAO eventoDAO = new EventoDAO(this);
        Evento evento = new Evento();
        Etiqueta etiqueta = new Etiqueta();
        evento = eventoDAO.getListaEventoPorID(Evento_Id);

        if(evento.nombre != null)
            editEvento.setText(String.valueOf(evento.nombre));
        else
            editEvento.setText("");
            editFecha.setText(evento.fecha);
            editHora.setText(evento.hora);
            editLugar.setText(evento.lugar);
            editEtiqueta.setText(etiqueta.nombre);


    }

    public void onClick(View v) {
        if (v == findViewById(R.id.guardar)) {

          EventoDAO eventoDAO = new EventoDAO(this);
          Evento evento = new Evento();
            Etiqueta etiqueta = new Etiqueta();
            evento.nombre = editEvento.getText().toString();
            evento.fecha = editFecha.getText().toString();
            evento.lugar = editLugar.getText().toString();
            evento.hora = editHora.getText().toString();
            etiqueta.nombre = editEtiqueta.getText().toString();
            evento.evento_ID = Evento_Id;

            if(Evento_Id==0){
                Evento_Id = eventoDAO.insert(evento);

                Toast.makeText(this,"Nuevo evento creado",Toast.LENGTH_SHORT).show();
                finish();
            }else{
                eventoDAO.update(evento);
                Toast.makeText(this,"Evento modificado",Toast.LENGTH_SHORT).show();
                finish();
            }
            }else if(v == findViewById(R.id.borrar)) {
            EventoDAO eventoDAO = new EventoDAO(this);
            eventoDAO.delete(Evento_Id);
            Toast.makeText(this, "Evento eliminado", Toast.LENGTH_SHORT).show();
            finish();


        }

    }


}
