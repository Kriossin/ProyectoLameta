package com.example.lameta.agendalameta.detail;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lameta.agendalameta.DAO.EventoDAO;
import com.example.lameta.agendalameta.R;
import com.example.lameta.agendalameta.main.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;


public class DiaDetail extends ListActivity {

    Button btnAnyadir;
    TextView tituloDia, evento_ID;
    String sDia, fecha;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia);
        btnAnyadir = (Button) findViewById(R.id.botonCrear);


        btnAnyadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EventoDetail.class);
                startActivity(intent);
                intent.putExtra("fecha", fecha);
            }
        });


        tituloDia = (TextView) findViewById(R.id.dia);
        tituloDia.setText(MainActivity.fecha);

        Bundle extra = getIntent().getExtras();
        if(extra!=null){
            fecha = extra.getString("fecha");
        }

        listaEventos();

    }

    @Override
    public void onResume(){
        super.onResume();
        listaEventos();
    }

    public void listaEventos(){

        EventoDAO eventoDAO = new EventoDAO(this);
        ArrayList<HashMap<String, String>> eventoLista;
        //sDia = tvDia_id.getText().toString();
        if(MainActivity.fecha != "") {
            eventoLista = eventoDAO.getListaEventoPorFecha(MainActivity.fecha);
        }
        else {
            eventoLista = eventoDAO.getListaEventoPorBuscador(MainActivity.buscador);

        }


        if(eventoLista.size()!=0) {

            ListView listView = getListView();
            getListView().setVisibility(View.VISIBLE);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                    evento_ID = (TextView) view.findViewById(R.id.evento_Id);

                    String eventoId = evento_ID.getText().toString();
                    //Log.v("mirarID", eventoId);

                    Intent objIndent = new Intent(getApplicationContext(),EventoDetail.class);
                    objIndent.putExtra("evento_Id", Integer.parseInt(eventoId));//ME ACUERDO
                    MainActivity.idBuscado = Integer.parseInt(eventoId);

                    startActivity(objIndent);
                }
            });

            ListAdapter adapter = new SimpleAdapter(DiaDetail.this,eventoLista, R.layout.listiem_dia, new String[] { "id","name"}, new int[] {R.id.evento_Id, R.id.evento});
            setListAdapter(adapter);


        }else{

            getListView().setVisibility(View.GONE);

            Toast.makeText(this, "No hay eventos", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
