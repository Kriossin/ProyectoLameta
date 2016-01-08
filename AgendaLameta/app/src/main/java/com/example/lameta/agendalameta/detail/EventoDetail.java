package com.example.lameta.agendalameta.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lameta.agendalameta.DAO.EventoDAO;
import com.example.lameta.agendalameta.R;
import com.example.lameta.agendalameta.model.Evento;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

//import com.example.lameta.agendalameta.R;

/**
 * Created by Bisquert on 11/12/2015.
 */
public class EventoDetail extends ActionBarActivity {
    Button btnModificar, btnBorrar;
    private int Evento_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        btnModificar = (Button) findViewById(R.id.modificar);
        btnBorrar = (Button) findViewById(R.id.borrar);

      //  btnModificar.setOnClickListener(this);
      //  btnBorrar.setOnClickListener(this);

        Evento_Id = 0;
        Intent intent = getIntent();
        Evento_Id=intent.getIntExtra("Evento_Id",0);
       // EventoDAO eventoDAO = new EventoDAO(this);
        Evento evento = new Evento();
      //  evento = eventoDAO.getEventoById(Evento_Id);





    }

    public void onClick(View v) {
        if (v == findViewById(R.id.modificar)) {
          //  EventoDAO eventoDAO = new EventoDAO(this);
            Evento evento = new Evento();

        }
    }


}
