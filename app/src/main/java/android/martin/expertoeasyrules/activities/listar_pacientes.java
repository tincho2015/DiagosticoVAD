package android.martin.expertoeasyrules.activities;

import androidx.appcompat.app.AppCompatActivity;
import db.pacienteBdHelper;

import android.content.Intent;
import android.database.Cursor;
import android.martin.expertoeasyrules.R;
import android.martin.expertoeasyrules.adaptadores.adaptadorPaciente;
import android.martin.expertoeasyrules.hechos.paciente;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class listar_pacientes extends AppCompatActivity {

    private ListView listaPacientes;
    private FloatingActionButton btnMenuAgregarPaciente;
    private paciente p = null;
    private Bundle pacienteEnviado = null;
    private ArrayList<paciente> pacientesBase;
    private pacienteBdHelper pacienteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pacientes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(R.string.titulo_layout_listar_pacientes);

        pacienteHelper = new pacienteBdHelper(this);

        listaPacientes = findViewById(R.id.listViewPaciente);
        listaPacientes.setEmptyView(findViewById(R.id.txtListaVacia));

        btnMenuAgregarPaciente = findViewById(R.id.btnAgregarPaciente);

        darPacientes();

        btnMenuAgregarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), ingresar_paciente.class);
                startActivity(i);
            }
        });

        adaptadorPaciente adaptador = new adaptadorPaciente(this,R.layout.lista_item_paciente, pacientesBase);
        listaPacientes.setAdapter(adaptador);

        listaPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getApplicationContext(),menu_principal.class);
                p = new paciente(pacientesBase.get(position).getNombre(),pacientesBase.get(position).getSexo(),pacientesBase.get(position).getEdad());
                pacienteEnviado = new Bundle();
                pacienteEnviado.putParcelable("paciente",p);
                i.putExtra("paciente",pacienteEnviado);
                startActivity(i);
            }
        });

    }
    public ArrayList<paciente>darPacientes(){

        Cursor cursor = pacienteHelper.darDatos();
        pacientesBase = new ArrayList<>();
        if(cursor.moveToFirst()){

            while(cursor.isAfterLast() == false){

                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                String edad = cursor.getString(cursor.getColumnIndex("edad"));
                String sexo = cursor.getString(cursor.getColumnIndex("sexo"));

                pacientesBase.add(new paciente(nombre,sexo,Integer.valueOf(edad)));
                cursor.moveToNext();
            }
        }

        return pacientesBase;

    }


//    public void listarPacientes(){
//
//        listarPaciente.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Cursor res = dbhelper.darDatos();
//                if(res.getCount() == 0){ // No hay datos que mostrar
//                    mostrarMensaje("Error","No se han encontrado registros");
//                    return;
//                }else{
//
//                    StringBuffer buffer = new StringBuffer();
//                    while (res.moveToNext()){
//                        buffer.append("Id:" + res.getString(0)+ "\n");
//                        buffer.append("Nombre:"+ "" + res.getString(1)+ "\n");
//                        buffer.append("Sexo:"+ "" + res.getString(2)+ "\n");
//                        buffer.append("Edad:"+ "" + res.getString(3) + "\n\n");
//                    }
//                    mostrarMensaje("Pacientes:",buffer.toString());
//                }
//            }
//        });
//    }

}
