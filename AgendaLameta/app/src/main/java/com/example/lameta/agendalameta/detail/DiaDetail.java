package com.example.lameta.agendalameta.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.lameta.agendalameta.R;

/**
 * Created by Usuario on 12/01/2016.
 */
public class DiaDetail extends ActionBarActivity implements android.view.View.OnClickListener{

    ListView listaEventos;
    Button btnCrear;
    private int Evento_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia);

        listaEventos = (ListView) findViewById(R.id.ListaEventos);
        btnCrear = (Button) findViewById(R.id.botonCrear);

        btnCrear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


    }
}
