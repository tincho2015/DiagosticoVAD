package android.martin.expertoeasyrules.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import db.pacienteBdHelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.martin.expertoeasyrules.R;
import android.martin.expertoeasyrules.hechos.paciente;
import android.martin.expertoeasyrules.hechos.revisionObstruccionVA;
import android.martin.expertoeasyrules.hechos.sintomaObstruccionVA;
import android.martin.expertoeasyrules.reglas.evaluarSignosObstruccionViaAerea.presentaAntecedenteObstruccion;
import android.martin.expertoeasyrules.reglas.evaluarSignosObstruccionViaAerea.presentaSintoma;
import android.martin.expertoeasyrules.reglas.evaluarSignosObstruccionViaAerea.presentaSintomaDisfagia;
import android.martin.expertoeasyrules.reglas.evaluarSignosObstruccionViaAerea.presentaSintomaDisfonia;
import android.martin.expertoeasyrules.reglas.evaluarSignosObstruccionViaAerea.presentaSintomaDisnea;
import android.martin.expertoeasyrules.reglas.evaluarSignosObstruccionViaAerea.presentaSintomaEstridor;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionObstruccion.valoracionNoSintomaObstruccion;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionObstruccion.valoracionObstruccion;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionObstruccion.valoracionSintomaObstruccion;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.InferenceRulesEngine;

import java.util.ArrayList;

public class sintoma_obstruccion extends AppCompatActivity {

    private RadioGroup rgDisnea;
    private RadioGroup rgDisfonia;
    private RadioGroup rgEstridor;
    private RadioGroup rgDisfagia;
    private RadioGroup rgAntecedente;

    private RadioButton rbDisneaNo;
    private RadioButton rbDisneaSi;
    private RadioButton rbDisfagiaNo;
    private RadioButton rbDisfagiaSi;
    private RadioButton rbDisfoniaNo;
    private RadioButton rbDisfoniaSi;
    private RadioButton rbEstridorNo;
    private RadioButton rbEstridorSi;
    private RadioButton rbAntecedenteSi;
    private RadioButton rbAntecedenteNo;

    private boolean disnea;
    private boolean disfagia;
    private boolean disfonia;
    private boolean estridor;
    private boolean antecedente;

    private ArrayList<sintomaObstruccionVA>listaSintomas = new ArrayList<>();

    private sintomaObstruccionVA sintomas = null;
    private revisionObstruccionVA revision = null;
    private Bundle revisionBundle = null;
    private Button btnFinalizar;

    private pacienteBdHelper bd;

    private paciente p = null;

    //private int pacienteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintoma_obstruccion);


        getSupportActionBar().setTitle("Signos y síntomas de obstrucción");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        revisionBundle = getIntent().getBundleExtra("paciente");

        bd = new pacienteBdHelper(this);


        if(revisionBundle != null) {
            p = (paciente) revisionBundle.getParcelable("paciente");
        }

        rgDisnea = findViewById(R.id.rgDisnea);
        rgDisfonia = findViewById(R.id.rgDisfonia);
        rgEstridor = findViewById(R.id.rgEstridor);
        rgDisfagia = findViewById(R.id.rgDisfagia);
        rgAntecedente = findViewById(R.id.rgAntecedenteObstruccion);

        rbDisneaNo = findViewById(R.id.rbDisneaNo);
        rbDisneaSi = findViewById(R.id.rbDisneaSi);
        rbDisfagiaNo = findViewById(R.id.rbDisfagiaNo);
        rbDisfagiaSi = findViewById(R.id.rbDisfagiaSi);
        rbDisfoniaNo = findViewById(R.id.rbDisfoniaNo);
        rbDisfoniaSi = findViewById(R.id.rbDisfoniaSi);
        rbEstridorNo = findViewById(R.id.rbEstridorNo);
        rbEstridorSi = findViewById(R.id.rbEstridorSi);
        rbAntecedenteNo = findViewById(R.id.rbObstruccionNo);
        rbAntecedenteSi = findViewById(R.id.rbObstruccionSI);

        btnFinalizar = findViewById(R.id.btnFinalizar);

        rgAntecedente.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rbObstruccionSI:
                        antecedente = rbAntecedenteSi.isChecked();
                        sintomas = new sintomaObstruccionVA("Antecedente",antecedente);
                        listaSintomas.add(sintomas);
                        rbAntecedenteNo.setError(null);
                        break;
                    case R.id.rbObstruccionNo:
                        antecedente = !rbAntecedenteNo.isChecked();
                        rbAntecedenteNo.setError(null);
                        sintomas = new sintomaObstruccionVA("Antecedente",antecedente);
                        listaSintomas.add(sintomas);
                        break;
                }
            }
        });

        rgDisnea.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbDisneaNo:
                        disnea = !rbDisneaNo.isChecked();
                        sintomas = new sintomaObstruccionVA("Disnea",disnea);
                        rbDisneaNo.setError(null);
                        listaSintomas.add(sintomas);
                        break;
                    case R.id.rbDisneaSi:
                        disnea = rbDisneaSi.isChecked();
                        rbDisneaNo.setError(null);
                        sintomas = new sintomaObstruccionVA("Disnea",disnea);
                        listaSintomas.add(sintomas);
                        break;
                }
            }
        });
        rgDisfagia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbDisfagiaNo:
                        disfagia = !rbDisfagiaNo.isChecked();
                        sintomas = new sintomaObstruccionVA("Disfagia",disfagia);
                        rbDisfagiaNo.setError(null);
                        listaSintomas.add(sintomas);
                        break;
                    case R.id.rbDisfagiaSi:
                        disfagia = rbDisfagiaSi.isChecked();
                        rbDisfagiaNo.setError(null);
                        sintomas = new sintomaObstruccionVA("Disfagia",disfagia);
                        listaSintomas.add(sintomas);
                        break;
                }
            }
        });
        rgDisfonia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbDisfoniaNo:
                        disfonia = !rbDisfoniaNo.isChecked();
                        sintomas = new sintomaObstruccionVA("Disfonia",disfonia);
                        rbDisfoniaNo.setError(null);
                        listaSintomas.add(sintomas);
                        break;
                    case R.id.rbDisfoniaSi:
                        disfonia = rbDisfoniaSi.isChecked();
                        rbDisfoniaNo.setError(null);
                        sintomas = new sintomaObstruccionVA("Disfonia",disfonia);
                        listaSintomas.add(sintomas);
                        break;
                }
            }
        });
        rgEstridor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbEstridorNo:
                        estridor = !rbEstridorNo.isChecked();
                        sintomas = new sintomaObstruccionVA("Estridor",estridor);
                        rbEstridorNo.setError(null);
                        listaSintomas.add(sintomas);
                        break;
                    case R.id.rbEstridorSi:
                        estridor = rbEstridorSi.isChecked();
                        rbEstridorNo.setError(null);
                        sintomas = new sintomaObstruccionVA("Estridor",estridor);
                        listaSintomas.add(sintomas);
                        break;
                }
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (rgAntecedente.getCheckedRadioButtonId()){

                    case -1:{

                        rbAntecedenteNo.setError("Seleccione si posee antecedentes de obstrucción de la vía aérea");
                        rbAntecedenteNo.requestFocus();
                        break;
                    }
                }
                switch (rgDisfagia.getCheckedRadioButtonId()){

                    case -1:{

                        rbDisfagiaNo.setError("Seleccione si presenta disfagia");
                        rbDisfagiaNo.requestFocus();
                        break;
                    }
                }
                switch (rgDisfonia.getCheckedRadioButtonId()){

                    case -1:{
                        rbDisfoniaNo.setError("Seleccione si presenta disfonia");
                        rbDisfoniaNo.requestFocus();
                        break;
                    }
                }
                switch (rgDisnea.getCheckedRadioButtonId()){

                    case -1:{

                        rbDisneaNo.setError("Seleccione si presenta disnea");
                        rbDisneaNo.requestFocus();
                        break;
                    }
                }
                switch (rgEstridor.getCheckedRadioButtonId()){

                    case -1:
                    {
                        rbEstridorNo.setError("Seleccione si presenta estridor");
                        rbEstridorNo.requestFocus();
                        break;
                    }
                }

            if(rgEstridor.getCheckedRadioButtonId() != -1 && rgDisnea.getCheckedRadioButtonId() != -1 && rgDisfonia.getCheckedRadioButtonId() != -1 && rgAntecedente.getCheckedRadioButtonId() != -1 && rgDisfagia.getCheckedRadioButtonId() != -1) {

                validarCampos("Por favor, verificar si los datos ingresados son correctos:");
            }
            
            }
        });


    }
    private void validarCampos(String titulo){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(titulo!=null)builder.setTitle(titulo);
        //if(mensaje!=null)builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar",null);

        View vista = LayoutInflater.from(this).inflate(R.layout.dialog_item,null);
        builder.setView(vista);

        final TextView resultado = vista.findViewById(R.id.txtExplicacion);
        String mensajeTotal = "";
        String mensajeDisfonia = "";
        String mensajeDisfagia= "";
        String mensajeEstridor= "";
        String mensajeAntecedente= "";
        String mensajeDisnea="";

        if(disfagia){

            mensajeDisfagia ="Presencia de disfagia:Si"+"\n";
        }else{
            mensajeDisfagia ="Presencia de disfagia:No"+"\n";
        }
        if(disfonia){

            mensajeDisfonia ="Presencia de disfonia:Si"+ "\n";
        }else{

            mensajeDisfonia ="Presencia de disfonia:No"+ "\n";
        }
        if(estridor){

            mensajeEstridor = "Presencia de estridor:Si" + "\n";
        }else{
            mensajeEstridor = "Presencia de estridor:No" + "\n";
        }
        if(disnea){

            mensajeDisnea = "Presencia de disnea:Si" + "\n";
        }else{
            mensajeDisnea = "Presencia de disnea:No" + "\n";
        }
        if(antecedente){

            mensajeAntecedente= "Presencia de antecedentes de obstrucción de la vía aérea:Si" + "\n";
        }else{

            mensajeAntecedente= "Presencia de antecedentes de obstrucción de la vía aérea:No" + "\n";
        }

        mensajeTotal = mensajeDisfonia+mensajeDisfagia+mensajeEstridor+mensajeDisnea+mensajeAntecedente;
        resultado.setText(mensajeTotal);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                borrarDato();
                //evaluarParametros(disfagia,disnea,estridor,disfonia,antecedente);
                evaluarParametros(listaSintomas);
                valoracion(revision);
                //actualizarDato(revision,p);
                agregarDato(revision,p);
                p.setRevisionObstruccion(true);
                Intent i = new Intent(getApplicationContext(),menu_principal.class);
                revisionBundle = new Bundle();
                revisionBundle.putParcelable("paciente",p);
                i.putExtra("paciente",revisionBundle);
                startActivity(i);

                //seguir = true;

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //seguir = false;
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        //return seguir;



    }
    public void borrarDato(){
        bd.borrarRevision();
    }
    public void actualizarDato(revisionObstruccionVA revision, paciente p){
        boolean esActualizado;
        for(String descripcion:revision.getSintomas()){

            esActualizado = bd.actualizarRevisionObstruccion(descripcion,p);

            if(esActualizado == true){
                Toast.makeText(sintoma_obstruccion.this, "Se han insertado correctamente los datos", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(sintoma_obstruccion.this, "No se han insertado correctamente los datos", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void agregarDato(revisionObstruccionVA revision,paciente p){

        boolean esInsertado;
        for(String descripcion: revision.getSintomas()) {
            esInsertado = bd.insertarRevisionObstruccion(descripcion,p.getId(),revision.getValoracionObstruccion());
                /*if(radioMasculino.isChecked()) {
                    //esInsertado= dbhelper.insetarDatos(pacienteNombre.getText().toString(),MASCULINO_OPCION, pacienteEdad.getText().toString());
                    esInsertado= dbhelper.insetarDatos(p.getNombre(),p.getSexo(),String.valueOf(p.getEdad()));
                }else
                    esInsertado= dbhelper.insetarDatos(pacienteNombre.getText().toString(),FEMENINO_OPCION, pacienteEdad.getText().toString());*/

        if(esInsertado == true){
            Toast.makeText(sintoma_obstruccion.this, "Se han insertado correctamente los datos", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(sintoma_obstruccion.this, "No se han insertado correctamente los datos", Toast.LENGTH_SHORT).show();
        }
     }
    }

    public void evaluarParametros(ArrayList<sintomaObstruccionVA>listaSintomas) {

        /*sintomas = new sintomaObstruccionVA(disnea,disfagia,estridor,disfonia,antecedente);
        revision = new revisionObstruccionVA();

        //Creo los hechos

        Facts hechos = new Facts();
        hechos.put("sintomas",sintomas);
        hechos.put("revisionSintomas",revision);

        //Creo el set de reglas
        Rules reglas = new Rules();
        reglas.register(new presentaSintomaDisfagia());
        reglas.register(new presentaSintomaDisfonia());
        reglas.register(new presentaSintomaDisnea());
        reglas.register(new presentaSintomaEstridor());
        reglas.register(new presentaAntecedenteObstruccion());

        RulesEngine re = new DefaultRulesEngine();
        re.fire(reglas,hechos);*/
        revision = new revisionObstruccionVA();

       for (sintomaObstruccionVA sintoma : listaSintomas) {
            //Creo los hechos
            Facts hechos = new Facts();
            hechos.put("sintomas", sintoma);
            hechos.put("revisionSintomas",revision);

            // define rules
           /* Rules regla = new Rules();
            regla.register(new presentaSintoma());*/
           Rules reglas = new Rules();
           reglas.register(new presentaSintomaDisfagia());
           reglas.register(new presentaSintomaDisfonia());
           reglas.register(new presentaSintomaDisnea());
           reglas.register(new presentaSintomaEstridor());
           reglas.register(new presentaAntecedenteObstruccion());


            RulesEngine ri = new InferenceRulesEngine();
            ri.fire(reglas, hechos);
      }

    }

    @Override
    protected void onDestroy() {
        bd.close();
        super.onDestroy();
    }

    public void valoracion(revisionObstruccionVA revision){

        //Creo los hechos

        Facts hechos = new Facts();
        hechos.put("revisionSintomas",revision);
/*
        //Creo el set de reglas
        Rules reglas = new Rules();
        reglas.register(new valoracionObstruccion());

        RulesEngine re = new DefaultRulesEngine();
        re.fire(reglas,hechos);*/


        //Creo el set de reglas
        Rules reglas = new Rules();
        reglas.register(new valoracionSintomaObstruccion());
        reglas.register(new valoracionNoSintomaObstruccion());

        RulesEngine ri = new InferenceRulesEngine();
        ri.fire(reglas,hechos);
    }

}
