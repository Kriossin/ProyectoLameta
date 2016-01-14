package com.example.lameta.agendalameta.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.example.lameta.agendalameta.R;
import com.example.lameta.agendalameta.detail.DiaDetail;

public class MainActivity extends AppCompatActivity {

    Button botonir;
    CalendarView calendar;
    public static String fecha, buscador;
    SearchView search;
    public static int idBuscado=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*metodo capturar el dia del calendario*/
        calendar = (CalendarView) findViewById(R.id.calendarView1);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                fecha = String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year);
                Toast.makeText(getApplicationContext(), fecha, Toast.LENGTH_SHORT).show();


            }
        });

        /*Ir al activity dia*/
        botonir= (Button) findViewById(R.id.ir);
        botonir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DiaDetail.class);
                startActivity(intent);
                intent.putExtra("fecha",fecha);
            }
        });


        /*buscador*/
        search=(SearchView) findViewById(R.id.busqueda);
        search.setQueryHint("Buscar...");

        /*buscador: metodos capturar la busqueda*/


        //*** setOnQueryTextFocusChangeListener ***
        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub //true false

              //  Toast.makeText(getBaseContext(), String.valueOf(hasFocus),Toast.LENGTH_SHORT).show();
            }
        });

        //*** setOnQueryTextListener ***
        search.setOnQueryTextListener(new OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub

                fecha = "";
                buscador = query;

                Intent intent = new Intent(getApplicationContext(), DiaDetail.class);
                startActivity(intent);
                intent.putExtra("fecha",fecha);

                //Toast.makeText(getBaseContext(), query, Toast.LENGTH_SHORT).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub

                //	Toast.makeText(getBaseContext(), newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }
    //mostrar activity al volver
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
    }


    /*
    *
    * metodos para listar*/
/*
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
            //id y nombre llamados igual que en eventoDAO Recordar!!
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

            ListAdapter adapter = new SimpleAdapter(MainActivity.this,eventoLista, R.layout.activity_evento, new String[] { "id","etiqueta"}, new int[] {R.id.evento_Id, R.id.etiqueta});
            setListAdapter(adapter);


        }else{

            getListView().setVisibility(View.GONE);

            Toast.makeText(this, "No hay eventos con esa etiqueta", Toast.LENGTH_SHORT).show();
        }
    }

*/

















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
