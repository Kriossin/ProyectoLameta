package com.example.lameta.agendalameta.detail;

<<<<<<< HEAD
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


=======
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
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

/**
 * Created by Roxas on 12/01/2016.
 */
public class DiaDetail extends ListActivity implements android.view.View.OnClickListener{

    Button btnAnyadir;
    TextView tvDia_id, evento_ID;
    String sDia, fecha;

    @Override
    public void onClick(View v) {

        if(v == findViewById(R.id.botonAñadir)){
            Intent intent = new Intent(this, EventoDetail.class);
            intent.putExtra("tvDia_id", 0);
            startActivity(intent);
        }else{
            listaEventos();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia);
        btnAnyadir = (Button) findViewById(R.id.botonAñadir);
        btnAnyadir.setOnClickListener(this);
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

        //sDia = tvDia_id.getText().toString();
        ArrayList<HashMap<String, String>> eventoLista =  eventoDAO.getListaEventoPorFecha(fecha);


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

            ListAdapter adapter = new SimpleAdapter(DiaDetail.this,eventoLista, R.layout.activity_dia, new String[] { "id","name"}, new int[] {R.id.evento_Id, R.id.nombre_evento});
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
>>>>>>> 9274ae974624e1ee74465e095d2870d943a350c2
    }
}
