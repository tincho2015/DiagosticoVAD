package android.martin.expertoeasyrules.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import db.pacienteBdHelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.martin.expertoeasyrules.R;
import android.martin.expertoeasyrules.hechos.criterioVentilacionDificil;
import android.martin.expertoeasyrules.hechos.paciente;
import android.martin.expertoeasyrules.hechos.resultadoPredictor;
import android.martin.expertoeasyrules.hechos.revisionObstruccionVA;
import android.martin.expertoeasyrules.hechos.revisionVentilacionDificil;
import android.martin.expertoeasyrules.reglas.evaluarCriteriosVentilacionDificil.pacientePresentaBarba;
import android.martin.expertoeasyrules.reglas.evaluarCriteriosVentilacionDificil.pacientePresentaEdadMayor55;
import android.martin.expertoeasyrules.reglas.evaluarCriteriosVentilacionDificil.pacientePresentaEdentacion;
import android.martin.expertoeasyrules.reglas.evaluarCriteriosVentilacionDificil.pacientePresentaObesidad;
import android.martin.expertoeasyrules.reglas.evaluarCriteriosVentilacionDificil.pacientePresentaRoncador;
import android.martin.expertoeasyrules.reglas.evaluarCriteriosVentilacionDificil.presentaCriterioVentilacionDificil;
import android.martin.expertoeasyrules.reglas.evaluarSignosObstruccionViaAerea.presentaSintomaDisfagia;
import android.martin.expertoeasyrules.reglas.evaluarSignosObstruccionViaAerea.presentaSintomaDisfonia;
import android.martin.expertoeasyrules.reglas.evaluarSignosObstruccionViaAerea.presentaSintomaDisnea;
import android.martin.expertoeasyrules.reglas.evaluarSignosObstruccionViaAerea.presentaSintomaEstridor;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionVentilacion.valoracionCantCriterios;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionVentilacion.valoracionCantCriteriosVD;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionVentilacion.valoracionNoCantCriteriosVD;
import android.os.Bundle;
import android.text.TextUtils;
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
import java.util.List;

public class criterio_ventilacion_dificil extends AppCompatActivity {

    private RadioGroup rgRoncador;
    private RadioGroup rgEdentacion;
    private RadioGroup rgBarba;
    private RadioGroup rgObesidad;

    private RadioButton rbBarbaSi;
    private RadioButton rbBarbaNo;
    private RadioButton rbObesidadNo;
    private RadioButton rbObesidadSi;
    private RadioButton rbEdentacionNo;
    private RadioButton rbEdentacionSi;
    private RadioButton rbRoncadorNo;
    private RadioButton rbRoncadorSi;

    private TextView txtViewMayoriaEdad;
    private boolean barba;
    private boolean edentacion;
    private boolean mayorEdad;
    private boolean obesidad;
    private boolean roncador;

    private boolean seguir = false;

    private paciente p = null;
    private Bundle b = null;
    private pacienteBdHelper bd;


    private Button btnFinalizar;


    private criterioVentilacionDificil cvd = null;
    private revisionVentilacionDificil revision = null;
    private Bundle cvdBundle = null;
    private ArrayList<criterioVentilacionDificil>listaCriterios = new ArrayList<>();

    @Override
    protected void onDestroy() {
        bd.close();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criterio_ventilacion_dificil);

        getSupportActionBar().setTitle("Criterios de ventilación difícil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        b = getIntent().getBundleExtra("paciente");
        if(b != null){

            p = b.getParcelable("paciente");
        }

        bd = new pacienteBdHelper(this);

        btnFinalizar = findViewById(R.id.btnFinalizar);
        rgBarba = findViewById(R.id.rgBarba);
        rgRoncador = findViewById(R.id.rgRoncador);
        rgEdentacion = findViewById(R.id.rgEdentacion);
        rgObesidad = findViewById(R.id.rgObesidad);

        rbBarbaNo = findViewById(R.id.rbBarbaNo);
        rbBarbaSi = findViewById(R.id.rbBarbaSi);
        rbObesidadNo = findViewById(R.id.rbObesidadNO);
        rbObesidadSi = findViewById(R.id.rbObesidadSi);
        rbRoncadorNo = findViewById(R.id.rbRoncadorNo);
        rbRoncadorSi = findViewById(R.id.rbRoncadorSi);
        rbEdentacionNo = findViewById(R.id.rbEdentacionNo);
        rbEdentacionSi = findViewById(R.id.rbEdentacionSi);
        txtViewMayoriaEdad = findViewById(R.id.txtViewEdadPaciente);


        if(p.getEdad() > 55){

            txtViewMayoriaEdad.setText("Mayor a 55 años");
            mayorEdad = true;
            cvd =new criterioVentilacionDificil(mayorEdad,"Mayor a 55 años");
            listaCriterios.add(cvd);
        }else{

            txtViewMayoriaEdad.setText("Menor o igual a 55 años");
            mayorEdad = false;
            cvd = new criterioVentilacionDificil(mayorEdad,"Menor o igual a 55 años");
            listaCriterios.add(cvd);
        }

        rgBarba.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {

                    case R.id.rbBarbaSi: {
                        barba = rbBarbaSi.isChecked();
                        rbBarbaNo.setError(null);
                        cvd = new criterioVentilacionDificil(barba,"Barba");
                        listaCriterios.add(cvd);
                        break;
                    }
                    case R.id.rbBarbaNo:{
                        barba = !rbBarbaNo.isChecked();
                        rbBarbaNo.setError(null);
                        cvd = new criterioVentilacionDificil(barba,"Barba");
                        listaCriterios.add(cvd);
                        break;
                    }
                }
            }
        });

        rgRoncador.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rbRoncadorNo:{
                        roncador = !rbRoncadorNo.isChecked();
                        rbRoncadorNo.setError(null);
                        cvd = new criterioVentilacionDificil(roncador,"Roncador");
                        listaCriterios.add(cvd);
                        break;
                    }
                    case R.id.rbRoncadorSi:{
                        roncador = rbRoncadorSi.isChecked();
                        rbRoncadorNo.setError(null);
                        cvd = new criterioVentilacionDificil(roncador,"Roncador");
                        listaCriterios.add(cvd);
                        break;
                    }
                }
            }

        });

        rgObesidad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rbObesidadNO:{
                        obesidad = !rbObesidadNo.isChecked();
                        rbObesidadNo.setError(null);
                        cvd = new criterioVentilacionDificil(obesidad,"Obesidad");
                        listaCriterios.add(cvd);
                        break;
                    }
                    case R.id.rbObesidadSi:{
                        obesidad = rbObesidadSi.isChecked();
                        rbObesidadNo.setError(null);
                        cvd = new criterioVentilacionDificil(obesidad,"Obesidad");
                        listaCriterios.add(cvd);
                        break;
                    }
                }
            }
        });

        rgEdentacion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case R.id.rbEdentacionSi:{
                        edentacion = rbEdentacionSi.isChecked();
                        rbEdentacionNo.setError(null);
                        cvd = new criterioVentilacionDificil(edentacion,"Edentacion");
                        listaCriterios.add(cvd);
                        break;
                    }
                    case R.id.rbEdentacionNo:{
                        edentacion = !rbEdentacionNo.isChecked();
                        rbEdentacionNo.setError(null);
                        cvd = new criterioVentilacionDificil(edentacion,"Edentacion");
                        listaCriterios.add(cvd);
                        break;
                    }
                }
            }
        });

    btnFinalizar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String mensajeError = "Seleccione una opción";

            switch (rgBarba.getCheckedRadioButtonId()){

                case -1:{
                    rbBarbaNo.setError(mensajeError);
                    rbBarbaNo.requestFocus();
                    break;
                }

            }
            switch (rgRoncador.getCheckedRadioButtonId()){
                case -1:{
                    rbRoncadorNo.setError(mensajeError);
                    rbRoncadorNo.requestFocus();
                    break;
                }
            }
            switch (rgObesidad.getCheckedRadioButtonId()){

                case -1:{
                    rbObesidadNo.setError(mensajeError);
                    rbObesidadNo.requestFocus();
                    break;
                }
            }
            switch (rgEdentacion.getCheckedRadioButtonId()){

                case -1:{
                    rbEdentacionNo.setError(mensajeError);
                    rbEdentacionNo.requestFocus();
                    break;
                }
            }
            if(rgBarba.getCheckedRadioButtonId() != -1 && rgRoncador.getCheckedRadioButtonId() != -1 && rgObesidad.getCheckedRadioButtonId() != -1 && rgEdentacion.getCheckedRadioButtonId() != -1) {
                validarCampos("Por favor, verificar si los datos ingresados son correctos:",listaCriterios);
                        /*
                        evaluarParametros(barba, obesidad, roncador, edentacion, mayorEdad);
                        valoracion(revision);
                        agregarDato(revision, p);
                        p.setRevisionVentilacionDificil(true);
                        Intent i = new Intent(getApplicationContext(), menu_principal.class);
                        cvdBundle = new Bundle();
                        cvdBundle.putParcelable("paciente", p);
                        i.putExtra("paciente", cvdBundle);
                        startActivity(i);
                        */

            }



        }
    });



    }
    public void borrarDato(){
        bd.borrarRevisionVentilacion();
    }
    public void agregarDato(revisionVentilacionDificil revision, paciente p){

        boolean esInsertado;
        for(String descripcion: revision.getFactoresVD()) {
            esInsertado = bd.insertarRevisionVentilacion(descripcion,p.getId(),revision.getValoracionVentilacion());
            if(esInsertado == true){

                Toast.makeText(criterio_ventilacion_dificil.this, "Se han insertado correctamente los datos", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(criterio_ventilacion_dificil.this, "No se han insertado correctamente los datos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void evaluarParametros(ArrayList<criterioVentilacionDificil>listaCriterios){

//        cvd = new criterioVentilacionDificil(barba,obesidad,roncador,edentacion,mayorEdad);
        revision = new revisionVentilacionDificil();

        //Creo los hechos

        for(criterioVentilacionDificil criterios:listaCriterios) {

            Facts hechos = new Facts();
            hechos.put("criterios", criterios);
            hechos.put("revisionVentilacionDificil", revision);

            //Creo el set de reglas
            /*Rules reglas = new Rules();
            reglas.register(new presentaCriterioVentilacionDificil());*/

            Rules reglas = new Rules();
            reglas.register(new pacientePresentaBarba());
            reglas.register(new pacientePresentaObesidad());
            reglas.register(new pacientePresentaRoncador());
            reglas.register(new pacientePresentaEdentacion());
            reglas.register(new pacientePresentaEdadMayor55());

            /*RulesEngine re = new DefaultRulesEngine();
            re.fire(reglas, hechos);*/

           RulesEngine ri = new InferenceRulesEngine();
            ri.fire(reglas,hechos);


    }


    }

    public void valoracion (revisionVentilacionDificil revision){

        //Creo los hechos

        Facts hechos = new Facts();
        hechos.put("revisionVentilacionDificil",revision);

        //Creo el set de reglas
        /*Rules reglas = new Rules();
        reglas.register(new valoracionCantCriterios());

        RulesEngine re = new DefaultRulesEngine();
        re.fire(reglas,hechos);*/

        Rules reglas = new Rules();
        reglas.register(new valoracionCantCriteriosVD());
        reglas.register(new valoracionNoCantCriteriosVD());

        RulesEngine ri = new InferenceRulesEngine();
        ri.fire(reglas,hechos);

    }
    private void validarCampos(String titulo, final ArrayList<criterioVentilacionDificil>listaCriterios){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(titulo!=null)builder.setTitle(titulo);
        //if(mensaje!=null)builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar",null);

        View vista = LayoutInflater.from(this).inflate(R.layout.dialog_item,null);
        builder.setView(vista);

        final TextView resultado = vista.findViewById(R.id.txtExplicacion);
        String mensajeTotal = "";
        String mensajeBarba = "";
        String mensajeEdentacion= "";
        String mensajeObesidad= "";
        String mensajeRoncador= "";

        if(barba){

            mensajeBarba ="Presencia de barba:Si"+"\n";
        }else{
            mensajeBarba ="Presencia de barba:No"+"\n";
        }
        if(edentacion){

            mensajeEdentacion ="Presencia de edentación:Si"+ "\n";
        }else{

            mensajeEdentacion ="Presencia de edentación:No"+ "\n";
        }
        if(obesidad){

            mensajeObesidad = "Presencia de obesidad:Si" + "\n";
        }else{
            mensajeObesidad = "Presencia de obesidad:No" + "\n";
        }
        if(roncador){

            mensajeRoncador = "Historia de ronquidos:Si" + "\n";
        }else{
            mensajeRoncador = "Historia de ronquidos:No" + "\n";
        }

        mensajeTotal = mensajeBarba+mensajeEdentacion+mensajeObesidad+mensajeRoncador;
        resultado.setText(mensajeTotal);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                borrarDato();
                evaluarParametros(listaCriterios);
                //evaluarParametros(listaCriterios);
                valoracion(revision);
                agregarDato(revision, p);
                p.setRevisionVentilacionDificil(true);
                Intent i = new Intent(getApplicationContext(), menu_principal.class);
                cvdBundle = new Bundle();
                cvdBundle.putParcelable("paciente", p);
                i.putExtra("paciente", cvdBundle);
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

    private boolean mostrarAlertDialog(String titulo, String mensaje, RadioGroup rgBarba, RadioGroup rgEdentacion,RadioGroup rgObesidad, RadioGroup rgRoncador){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(titulo!=null)builder.setTitle(titulo);
        if(mensaje!=null)builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar",null);

        View vista = LayoutInflater.from(this).inflate(R.layout.dialog_item,null);
        builder.setView(vista);

        final TextView resultado = vista.findViewById(R.id.txtExplicacion);
        String mensajeTotal = "";
        String mensajeBarba = "";
        String mensajeEdentacion= "";
        String mensajeObesidad= "";
        String mensajeRoncador= "";
        boolean seguir = false;

        if(rgBarba.getCheckedRadioButtonId() == -1){

            mensajeBarba ="Presencia de barba"+"\n";
        }
        if(rgEdentacion.getCheckedRadioButtonId() == -1){

            mensajeEdentacion ="Presencia de edentación"+ "\n";
        }
        if(rgObesidad.getCheckedRadioButtonId() == -1){

            mensajeObesidad = "Presencia de obesidad" + "\n";
        }
        if(rgRoncador.getCheckedRadioButtonId() == -1){

            mensajeRoncador = "Historia de ronquidos" + "\n";
        }

        mensajeTotal = mensajeBarba+mensajeEdentacion+mensajeObesidad+mensajeRoncador;
        resultado.setText(mensajeTotal);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.cancel();

                }
            });

        AlertDialog dialog = builder.create();
        if(mensajeTotal.isEmpty()) {
            seguir = true;
        }else {
            dialog.show();
        }
        return seguir;



    }
}
