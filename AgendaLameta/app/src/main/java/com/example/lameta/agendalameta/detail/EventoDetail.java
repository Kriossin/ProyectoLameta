package com.example.lameta.agendalameta.detail;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lameta.agendalameta.DAO.EventoDAO;
import com.example.lameta.agendalameta.R;

//import com.example.lameta.agendalameta.R;

/**
 * Created by Bisquert on 11/12/2015.
 */
public class EventoDetail extends ActionBarActivity {
    Button btnModificar, btnBorrar;

    @Override
protected void onCreate(Bundle savedInstanceState){
super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_evento);

        btnModificar = (Button) findViewById(R.id.modificar);
        btnBorrar = (Button) findViewById(R.id.borrar);

      // btnModificar.setOnClickListener(this);
      // btnBorrar.setOnClickListener(this);




}

    public void onClick(View v){
        if(v == findViewById(R.id.modificar)){
            //EventoDAO eventoDAO = new EventoDAO(this);
        }
    }



}
