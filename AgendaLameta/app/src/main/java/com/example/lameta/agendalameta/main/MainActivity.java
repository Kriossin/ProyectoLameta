package com.example.lameta.agendalameta.main;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lameta.agendalameta.DAO.EventoDAO;
import com.example.lameta.agendalameta.R;
import com.example.lameta.agendalameta.detail.EventoDetail;
import com.example.lameta.agendalameta.model.Evento;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {
    Button botonAnyadir;
    TextView evento_ID;
    EditText buscador;
    CalendarView calendar;
    static int dia, mes, anyo;

    public void onClick(View view) {
        if (view== findViewById(R.id.botonAñadir)){

            Intent intent = new Intent(this,EventoDetail.class);
            intent.putExtra("evento_ID",0);
            startActivity(intent);

        }else{
            listaEventos();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = (CalendarView) findViewById(R.id.calendarView1);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth){

                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + (month+1) + "/" + year, Toast.LENGTH_SHORT).show();
                dia = dayOfMonth;
                mes = month;
                anyo = year;

            }
        });





        botonAnyadir = (Button) findViewById(R.id.botonAñadir);
       // botonAnyadir.setOnClickListener(this);


      /*  buscador = (EditText) findViewById(R.id.busqueda);
=======
       /* buscador = (EditText) findViewById(R.id.busqueda);
        buscador.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                listaEventosPorNombre(buscador.getText().toString());
                return true;
            }
        });*/
    }
    //mostrar activity al volver
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        listaEventos();
    }

    public void listaEventos(){

        EventoDAO eventoDAO = new EventoDAO(this);

        ArrayList<HashMap<String, String>> eventoLista =  eventoDAO.getListaEvento();


        if(eventoLista.size()!=0) {

            ListView listView = getListView();
            getListView().setVisibility(View.VISIBLE);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                    evento_ID = (TextView) view.findViewById(R.id.evento_Id);
                    String eventoId = evento_ID.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(),EventoDetail.class);
                    objIndent.putExtra("evento_Id", Integer.parseInt(eventoId));
                    startActivity(objIndent);
                }
            });
//id y nombre llamados igual que en evento ´DAO REcordar!!
            ListAdapter adapter = new SimpleAdapter(MainActivity.this,eventoLista, R.layout.activity_evento, new String[] { "id","name"}, new int[] {R.id.evento_Id, R.id.nombre_evento});
            setListAdapter(adapter);


        }else{

            getListView().setVisibility(View.GONE);

            Toast.makeText(this, "No hay eventos", Toast.LENGTH_SHORT).show();
        }
    }
    public void listaEventosPorNombre(String nombre){

        EventoDAO eventoDAO = new EventoDAO(this);

        ArrayList<HashMap<String, String>> eventoLista =  eventoDAO.getListaEventoPorNombre(nombre);


        if(eventoLista.size()!=0) {

            ListView listView = getListView();
            getListView().setVisibility(View.VISIBLE);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                    evento_ID = (TextView) view.findViewById(R.id.evento_Id);
                    String eventoId = evento_ID.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(),EventoDetail.class);
                    objIndent.putExtra("evento_Id", Integer.parseInt(eventoId));
                    startActivity(objIndent);
                }
            });
//id y nombre llamados igual que en evento ´DAO REcordar!!
            ListAdapter adapter = new SimpleAdapter(MainActivity.this,eventoLista, R.layout.activity_evento, new String[] { "id","name"}, new int[] {R.id.evento_Id, R.id.nombre_evento});
            setListAdapter(adapter);


        }else{

            getListView().setVisibility(View.GONE);

            Toast.makeText(this, "No hay eventos con ese nombre", Toast.LENGTH_SHORT).show();
        }
    }
    public void listaEventosPorLugar(String lugar){

        EventoDAO eventoDAO = new EventoDAO(this);

        ArrayList<HashMap<String, String>> eventoLista =  eventoDAO.getListaEventoPorLugar(lugar);


        if(eventoLista.size()!=0) {

            ListView listView = getListView();
            getListView().setVisibility(View.VISIBLE);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                    evento_ID = (TextView) view.findViewById(R.id.evento_Id);
                    String eventoId = evento_ID.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(),EventoDetail.class);
                    objIndent.putExtra("evento_Id", Integer.parseInt(eventoId));
                    startActivity(objIndent);
                }
            });

            //id y nombre llamados igual que en evento ´DAO REcordar!!
            ListAdapter adapter = new SimpleAdapter(MainActivity.this,eventoLista, R.layout.activity_evento, new String[] { "id","lugar"}, new int[] {R.id.evento_Id, R.id.lugar});
            setListAdapter(adapter);


        }else{

            getListView().setVisibility(View.GONE);

            Toast.makeText(this, "No hay eventos con ese lugar", Toast.LENGTH_SHORT).show();
        }
    }
    public void listaEventosPorEtiqueta(String etiqueta){

        EventoDAO eventoDAO = new EventoDAO(this);

        ArrayList<HashMap<String, String>> eventoLista =  eventoDAO.getListaEventoPorEtiqueta(etiqueta);


        if(eventoLista.size()!=0) {

            ListView listView = getListView();
            getListView().setVisibility(View.VISIBLE);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                    evento_ID = (TextView) view.findViewById(R.id.evento_Id);
                    String eventoId = evento_ID.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(),EventoDetail.class);
                    objIndent.putExtra("evento_Id", Integer.parseInt(eventoId));
                    startActivity(objIndent);
                }
            });

        //id y nombre llamados igual que en evento ´DAO REcordar!!
            ListAdapter adapter = new SimpleAdapter(MainActivity.this,eventoLista, R.layout.activity_evento, new String[] { "id","etiqueta"}, new int[] {R.id.evento_Id, R.id.etiqueta});
            setListAdapter(adapter);


        }else{

            getListView().setVisibility(View.GONE);

            Toast.makeText(this, "No hay eventos con esa etiqueta", Toast.LENGTH_SHORT).show();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
