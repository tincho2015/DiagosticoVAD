package android.martin.expertoeasyrules.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import db.pacienteBdHelper;
import io.realm.Realm;

import android.content.Intent;
import android.database.Cursor;
import android.martin.expertoeasyrules.R;
import android.martin.expertoeasyrules.hechos.paciente;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class ingresar_paciente extends AppCompatActivity {


    //variables

    private static final Logger logger = LoggerFactory.getLogger(ingresar_paciente.class);
    EditText pacienteNombre;
    EditText pacienteEdad;
    RadioButton radioMasculino;
    RadioButton radioFemenino;
    private paciente p = null;
    pacienteBdHelper dbhelper;
    private List<paciente> pacientes;
    private Realm realm;


    private static final String MASCULINO_OPCION = "Masculino";
    private static final String FEMENINO_OPCION = "Femenino";

    Button insert;
    Button regla;
    Button listarPaciente;
    Button actualizar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbhelper = new pacienteBdHelper(this);

        pacienteNombre = findViewById(R.id.inNombre);
        pacienteEdad = findViewById(R.id.inEdad);
        radioFemenino = findViewById(R.id.rbFemenino);
        radioMasculino = findViewById(R.id.rbMasculino);
        insert = findViewById(R.id.btnInsertar);

        pacientes = new ArrayList<>();

        getSupportActionBar().setTitle("Datos del paciente");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



/*
        //validating the inputs
       if (TextUtils.isEmpty(pacienteNombre.getText().toString())) {
            this.pacienteNombre.setError("Por favor, ingrese el nombre");
            this.pacienteNombre.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(pacienteEdad.getText().toString())) {
            this.pacienteEdad.setError("Por favor, ingrese la edad");
            this.pacienteEdad.requestFocus();
            return;
        }*/


        //listarPacientes();
        //actualizarDatos();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(TextUtils.isEmpty(pacienteNombre.getText().toString())){

                    pacienteNombre.setError("Ingrese el nombre");
                    pacienteNombre.requestFocus();
                    return;

                }
                if(TextUtils.isEmpty(pacienteEdad.getText().toString())){

                    pacienteEdad.setError("Ingrese la edad");
                    pacienteEdad.requestFocus();
                    return;
                }

                else {

                    if (radioMasculino.isChecked()) {
                        agregarDato(p);
                        //agregarPaciente(pacienteNombre.getText().toString(), MASCULINO_OPCION, Integer.parseInt(pacienteEdad.getText().toString()));
                        Intent i = new Intent(getApplicationContext(), listar_pacientes.class);
                        startActivity(i);
                    }
                    if(radioFemenino.isChecked()){
                        agregarDato(p);
                        //agregarPaciente(pacienteNombre.getText().toString(), FEMENINO_OPCION, Integer.parseInt(pacienteEdad.getText().toString()));
                        Intent i = new Intent(getApplicationContext(), listar_pacientes.class);
                        startActivity(i);

                    }
                }
                //agregarDato(p);
            }
        });

    }
    /*private void agregarPaciente(String pacienteNombre, String pacienteSexo, int pacienteEdad){

        realm.beginTransaction();
        paciente p = new paciente(pacienteNombre,pacienteSexo,pacienteEdad);
        realm.copyToRealm(p);
        realm.commitTransaction();


    }*/

   public void agregarDato(paciente p){

       if(radioMasculino.isChecked())
           p = new paciente(pacienteNombre.getText().toString(),MASCULINO_OPCION,Integer.valueOf(pacienteEdad.getText().toString()));
       else{
           p = new paciente(pacienteNombre.getText().toString(),FEMENINO_OPCION,Integer.valueOf(pacienteEdad.getText().toString()));
       }

       //pacientes.add(p);
       boolean esInsertado;
       esInsertado= dbhelper.insertarPaciente(p.getNombre(),p.getSexo(),String.valueOf(p.getEdad()));
                /*if(radioMasculino.isChecked()) {
                    //esInsertado= dbhelper.insetarDatos(pacienteNombre.getText().toString(),MASCULINO_OPCION, pacienteEdad.getText().toString());
                    esInsertado= dbhelper.insetarDatos(p.getNombre(),p.getSexo(),String.valueOf(p.getEdad()));
                }else
                    esInsertado= dbhelper.insetarDatos(pacienteNombre.getText().toString(),FEMENINO_OPCION, pacienteEdad.getText().toString());*/

       if(esInsertado == true){

           Toast.makeText(ingresar_paciente.this, "Se han insertado correctamente los datos", Toast.LENGTH_SHORT).show();
       }else{
           Toast.makeText(ingresar_paciente.this, "No se han insertado correctamente los datos", Toast.LENGTH_SHORT).show();
       }

       Intent i = new Intent(getApplicationContext(), listar_pacientes.class);
       //i.putExtra("pacientes", (Serializable) pacientes);
       startActivity(i);

    }

   public void listarPacientes(){

        listarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = dbhelper.darDatos();
                if(res.getCount() == 0){ // No hay datos que mostrar
                    mostrarMensaje("Error","No se han encontrado registros");
                    return;
                }else{

                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append("Id:" + res.getString(0)+ "\n");
                        buffer.append("Nombre:"+ "" + res.getString(1)+ "\n");
                        buffer.append("Sexo:"+ "" + res.getString(2)+ "\n");
                        buffer.append("Edad:"+ "" + res.getString(3) + "\n\n");
                    }
                    mostrarMensaje("Pacientes:",buffer.toString());
                }
            }
        });
   }


   public void mostrarMensaje(String titulo, String mensaje){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.show();
    }

}
