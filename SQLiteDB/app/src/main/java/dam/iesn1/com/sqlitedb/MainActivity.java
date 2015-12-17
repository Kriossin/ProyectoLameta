package dam.iesn1.com.sqlitedb;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ListActivity  implements android.view.View.OnClickListener{

    Button btnAdd;
    TextView student_Id;
    EditText searchTxt;

    @Override
    public void onClick(View view) {
        if (view== findViewById(R.id.btnAdd)){

            Intent intent = new Intent(this,StudentDetail.class);
            intent.putExtra("student_Id",0);
            startActivity(intent);

        }else{
            listStudents();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        searchTxt = (EditText) findViewById(R.id.searchText);
        searchTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                 listStudentsByName(searchTxt.getText().toString());
                 return true;
                }
        });

        listStudents();

    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        listStudents();
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

    public void listStudents(){
        StudentDAO studentDAO = new StudentDAO(this);

        ArrayList<HashMap<String, String>> studentList =  studentDAO.getStudentList();

        if(studentList.size()!=0) {
            ListView lv = getListView();
            getListView().setVisibility(View.VISIBLE);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                    student_Id = (TextView) view.findViewById(R.id.student_Id);
                    String studentId = student_Id.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(),StudentDetail.class);
                    objIndent.putExtra("student_Id", Integer.parseInt( studentId));
                    startActivity(objIndent);
                }
            });
            ListAdapter adapter = new SimpleAdapter( MainActivity.this,studentList, R.layout.view_student_detail, new String[] { "id","name"}, new int[] {R.id.student_Id, R.id.student_name});
            setListAdapter(adapter);
        }else{
            getListView().setVisibility(View.GONE);
            Toast.makeText(this,"No hay estudiantes!",Toast.LENGTH_SHORT).show();
        }
    }

    public void listStudentsByName(String name){
        StudentDAO studentDAO = new StudentDAO(this);
        ArrayList<HashMap<String, String>> studentList =  studentDAO.getStudentListByName(name);

        if(studentList.size()!=0) {
            ListView lv = getListView();
            getListView().setVisibility(View.VISIBLE);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                    student_Id = (TextView) view.findViewById(R.id.student_Id);
                    String studentId = student_Id.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(),StudentDetail.class);
                    objIndent.putExtra("student_Id", Integer.parseInt( studentId));
                    startActivity(objIndent);
                }
            });
            ListAdapter adapter = new SimpleAdapter( MainActivity.this,studentList, R.layout.view_student_detail, new String[] { "id","name"}, new int[] {R.id.student_Id, R.id.student_name});
            setListAdapter(adapter);
        }else{
            getListView().setVisibility(View.GONE);
            Toast.makeText(this,"No hay estudiantes!",Toast.LENGTH_SHORT).show();
        }
    }

}