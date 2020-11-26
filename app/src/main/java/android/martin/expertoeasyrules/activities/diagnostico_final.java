package android.martin.expertoeasyrules.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.martin.expertoeasyrules.R;
import android.martin.expertoeasyrules.hechos.diagnosticoFinal;
import android.martin.expertoeasyrules.hechos.paciente;
import android.martin.expertoeasyrules.hechos.resultadoPredictor;
import android.martin.expertoeasyrules.hechos.resultadoPredictorAperturaBucal;
import android.martin.expertoeasyrules.hechos.resultadoPredictorDtm;
import android.martin.expertoeasyrules.hechos.resultadoPredictorGradoMovilidad;
import android.martin.expertoeasyrules.hechos.resultadoPredictorMallampati;
import android.martin.expertoeasyrules.hechos.resultadoPredictorSubMandibular;
import android.martin.expertoeasyrules.hechos.revisionObstruccionVA;
import android.martin.expertoeasyrules.hechos.revisionPatologiaFactor;
import android.martin.expertoeasyrules.hechos.revisionVentilacionDificil;
import android.martin.expertoeasyrules.reglas.darDiagnosticoFinal.pacienteNoPresentaVAD;
import android.martin.expertoeasyrules.reglas.darDiagnosticoFinal.pacientePresentaVAD;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.InferenceRulesEngine;

import java.util.Collections;

public class diagnostico_final extends AppCompatActivity {

    private TextView txtNombrePaciente;
    private TextView txtResultadoDiagnostico;
    private TextView txtSintomasObstruccion;
    private TextView txtCantVentilacionDificil;
    private TextView txtPatologiaAsociada;
    private TextView txtHistoriaVAD;
    private TextView txtPruebaAperturaOral;
    private TextView txtPruebaMallampati;
    private TextView txtPruebaSubluxacion;
    private TextView txtPruebaMov;
    private TextView txtDtm;

    private TextView tituloMallampati;
    private TextView tituloSubluaxcion;
    private TextView tituloDTM;
    private TextView tituloMovilidad;
    private TextView tituloApertura;

    private Button btnFinalizar;

    private Bundle b = null;
    private revisionPatologiaFactor revisionPatologia = null;
    private revisionVentilacionDificil revisionVentilacion = null;
    private resultadoPredictor revisionPredictores = null;
    private resultadoPredictorMallampati rpm = null;
    private resultadoPredictorGradoMovilidad rpgm = null;
    private resultadoPredictorDtm rpdtm = null;
    private resultadoPredictorAperturaBucal rpab = null;
    private resultadoPredictorSubMandibular rpsm = null;
    private revisionObstruccionVA revisionSintomas = null;





    private paciente p = null;

    private int cantCriterios = 0;

    private diagnosticoFinal df = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostico_final);

        getSupportActionBar().setTitle("Diagnóstico final");


        txtNombrePaciente = findViewById(R.id.txtNombrePacienteDiagnostico);
        txtResultadoDiagnostico = findViewById(R.id.txtResultadoDiagnostico);
        txtCantVentilacionDificil = findViewById(R.id.cantCriterios);
        txtDtm = findViewById(R.id.dtm);
        txtPruebaAperturaOral = findViewById(R.id.aperturaOral);
        txtPruebaSubluxacion = findViewById(R.id.subMandibular);
        txtPruebaMov = findViewById(R.id.movCabeza);
        txtPruebaMallampati = findViewById(R.id.mallampati);
        txtHistoriaVAD = findViewById(R.id.historiaIntubacion);
        txtPatologiaAsociada = findViewById(R.id.patologiaAsociada);
        txtSintomasObstruccion =  findViewById(R.id.sintomasVA);
        btnFinalizar = findViewById(R.id.btnFinalizarDiag);

        tituloMallampati = findViewById(R.id.txtViewTituloMallampati);
        tituloSubluaxcion = findViewById(R.id.txtViewTituloSubMandibular);
        tituloDTM = findViewById(R.id.txtViewTituloDTM);
        tituloMovilidad = findViewById(R.id.txtViewTituloMovCabeza);
        tituloApertura = findViewById(R.id.txtViewTituloAperturaOral);



        b = new Bundle();
        b = getIntent().getBundleExtra("revisiones");

        if(b != null){

            revisionPatologia = b.getParcelable("revisionPatologias");
            revisionPatologia.getPatologias().removeAll(Collections.singleton(null));
            revisionPatologia.getFactores().removeAll(Collections.singleton(null));
            revisionVentilacion = b.getParcelable("revisionVentilacion");
            //revisionPredictores = b.getParcelable("revisionPredictores");
            rpab = b.getParcelable("revisionPredictorAperturaBucal");
            rpdtm = b.getParcelable("revisionPredictorDtm");
            rpsm = b.getParcelable("revisionPredictorSubMandibular");
            rpm = b.getParcelable("revisionPredictorMallampati");
            rpgm = b.getParcelable("revisionPredictorGradoMovilidad");
            revisionSintomas = b.getParcelable("revisionSintomas");
            p = b.getParcelable("paciente");
        }

        txtNombrePaciente.setText(p.getNombre());

        validarPatologias(revisionPatologia,txtPatologiaAsociada,txtHistoriaVAD);
        validarSintomas(revisionSintomas,txtSintomasObstruccion);
        validarCantCriterios(revisionVentilacion,txtCantVentilacionDificil,cantCriterios);
        //validarPredictores(revisionPredictores,txtDtm,txtPruebaAperturaOral,txtPruebaSubluxacion,txtPruebaMov,txtPruebaMallampati);
        validarPredictores(rpab,rpdtm,rpsm,rpm,rpgm,txtDtm,txtPruebaAperturaOral,txtPruebaSubluxacion,txtPruebaMov,txtPruebaMallampati);


        txtPatologiaAsociada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mostrarAlertDialogPatologias("Patologias encontradas:",revisionPatologia);

            }
        });
        txtCantVentilacionDificil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarAlertDialogCriteriosVentilacion("Criterios de ventilación difícil encontrados:",revisionVentilacion);
            }
        });

        txtPruebaMallampati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mostrarAlertDialog(tituloMallampati.getText().toString(),txtPruebaMallampati.getText().toString(),rpm);
            }
        });

        txtPruebaSubluxacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarAlertDialogSubluxacion(tituloSubluaxcion.getText().toString(),txtPruebaSubluxacion.getText().toString(),rpsm);
            }
        });

        txtDtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mostrarAlertDialogDtm(tituloDTM.getText().toString(),txtDtm.getText().toString(),rpdtm);

            }
        });
        txtPruebaMov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mostrarAlertDialogMovilidad(tituloMovilidad.getText().toString(),txtPruebaMov.getText().toString(),rpgm);
            }
        });
        txtPruebaAperturaOral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mostrarAlertDialogApertura(tituloApertura.getText().toString(),txtPruebaAperturaOral.getText().toString(),rpab);
            }
        });



        //evaluarValoracion(revisionPatologia,revisionVentilacion,revisionPredictores,revisionSintomas);
        evaluarValoracion(revisionPatologia,revisionVentilacion,rpab,rpdtm,rpsm,rpm,rpgm,revisionSintomas);
        if(df.isPresentaVAD()) {
            txtResultadoDiagnostico.setText(df.getDescripcion());
            txtResultadoDiagnostico.setTextColor(getResources().getColor(R.color.vad));
            txtResultadoDiagnostico.setBackground(getResources().getDrawable(R.drawable.stylo_border_textview_vad));
        }else{
            txtResultadoDiagnostico.setText(df.getDescripcion());
            txtResultadoDiagnostico.setTextColor(getResources().getColor(R.color.novad));
            txtResultadoDiagnostico.setBackground(getResources().getDrawable(R.drawable.stylo_border_textview_vad));
        }


        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),menu_principal.class);
                p.setResultadoPredictor(false);
                p.setRevisionObstruccion(false);
                p.setRevisionPatologia(false);
                p.setRevisionVentilacionDificil(false);
                b.putParcelable("paciente",p);
                i.putExtra("paciente",b);
                startActivity(i);
            }
        });

    }

    public void evaluarValoracion(revisionPatologiaFactor revisionPatologia, revisionVentilacionDificil revisionVentilacion,resultadoPredictorAperturaBucal rpab,resultadoPredictorDtm rpdtm, resultadoPredictorSubMandibular rpsm, resultadoPredictorMallampati rpm,resultadoPredictorGradoMovilidad rpgm, revisionObstruccionVA revisionSintomas){

        df = new diagnosticoFinal();
        df.setValoracion(revisionPatologia.getValoracionPatologias()+revisionVentilacion.getValoracionVentilacion()+rpab.getValoracionPredictor()+rpdtm.getValoracionPredictor()+rpsm.getValoracionPredictor()+rpm.getValoracionPredictor()+rpgm.getValoracionPredictor()+revisionSintomas.getValoracionObstruccion());

        /*//Creo los hechos

        Facts hechos = new Facts();
        hechos.put("diagnosticoFinal",df);

        //Creo el set de reglas
        Rules reglas = new Rules();
        reglas.register(new noPresentaVAD());
        reglas.register(new presentaVAD());

        RulesEngine re = new DefaultRulesEngine();
        re.fire(reglas,hechos);*/

        Facts hechos = new Facts();
        hechos.put("diagnosticoFinal",df);

        Rules reglas = new Rules();
        reglas.register(new pacientePresentaVAD());
        reglas.register(new pacienteNoPresentaVAD());

        RulesEngine ri = new InferenceRulesEngine();
        ri.fire(reglas,hechos);





    }
    private void mostrarAlertDialogCriteriosVentilacion(String titulo,revisionVentilacionDificil revisionVentilacionDificil){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(titulo!=null)builder.setTitle(titulo);
        //if(mensaje!=null)builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar",null);

        View vista = LayoutInflater.from(this).inflate(R.layout.dialog_item,null);
        builder.setView(vista);

        final TextView resultado = vista.findViewById(R.id.txtExplicacion);
        for(String criterios: revisionVentilacionDificil.getFactoresVD()){
            criterios+=criterios;
            resultado.setText("-" + criterios + "\n");
        }

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }
    private void mostrarAlertDialogPatologias(String titulo,revisionPatologiaFactor revisionPatologiaFactor){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(titulo!=null)builder.setTitle(titulo);
        //if(mensaje!=null)builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar",null);

        View vista = LayoutInflater.from(this).inflate(R.layout.dialog_item,null);
        builder.setView(vista);

        final TextView resultado = vista.findViewById(R.id.txtExplicacion);
        for(String patologias: revisionPatologiaFactor.getPatologias()){
            patologias+=patologias;
            resultado.setText("-" + patologias + "\n");
        }

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }
    private void mostrarAlertDialog(String titulo, String mensaje,resultadoPredictorMallampati resultadoPredictorMallampati){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(titulo!=null)builder.setTitle(titulo);
        if(mensaje!=null)builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar",null);

        View vista = LayoutInflater.from(this).inflate(R.layout.dialog_item,null);
        builder.setView(vista);

        final TextView resultado = vista.findViewById(R.id.txtExplicacion);
        resultado.setText(resultadoPredictorMallampati.getJustificacion());
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }
    private void mostrarAlertDialogSubluxacion(String titulo, String mensaje,resultadoPredictorSubMandibular resultadoPredictorSubMandibular){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(titulo!=null)builder.setTitle(titulo);
        if(mensaje!=null)builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar",null);

        View vista = LayoutInflater.from(this).inflate(R.layout.dialog_item,null);
        builder.setView(vista);

        final TextView resultado = vista.findViewById(R.id.txtExplicacion);
        resultado.setText(resultadoPredictorSubMandibular.getJustificacion());
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }
    private void mostrarAlertDialogDtm(String titulo, String mensaje,resultadoPredictorDtm resultadoPredictorDtm){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(titulo!=null)builder.setTitle(titulo);
        if(mensaje!=null)builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar",null);

        View vista = LayoutInflater.from(this).inflate(R.layout.dialog_item,null);
        builder.setView(vista);

        final TextView resultado = vista.findViewById(R.id.txtExplicacion);
        resultado.setText(resultadoPredictorDtm.getJustificacion());
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }
    private void mostrarAlertDialogMovilidad(String titulo, String mensaje,resultadoPredictorGradoMovilidad resultadoPredictorGradoMovilidad){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(titulo!=null)builder.setTitle(titulo);
        if(mensaje!=null)builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar",null);

        View vista = LayoutInflater.from(this).inflate(R.layout.dialog_item,null);
        builder.setView(vista);

        final TextView resultado = vista.findViewById(R.id.txtExplicacion);
        resultado.setText(resultadoPredictorGradoMovilidad.getJustificacion());
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }
    private void mostrarAlertDialogApertura(String titulo, String mensaje,resultadoPredictorAperturaBucal resultadoPredictorAperturaBucal){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(titulo!=null)builder.setTitle(titulo);
        if(mensaje!=null)builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar",null);

        View vista = LayoutInflater.from(this).inflate(R.layout.dialog_item,null);
        builder.setView(vista);

        final TextView resultado = vista.findViewById(R.id.txtExplicacion);
        resultado.setText(resultadoPredictorAperturaBucal.getJustificacion());
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }
    public void validarPatologias(revisionPatologiaFactor revisionPatologia,TextView txtPatologiaAsociada,TextView txtHistoriaVAD){

        if(!revisionPatologia.getPatologias().isEmpty()) {

            txtPatologiaAsociada.setText("SI");

        }else{
            txtPatologiaAsociada.setText("NO");
        }
        if(!revisionPatologia.getFactores().isEmpty()){

            txtHistoriaVAD.setText("SI");
        }else {
            txtHistoriaVAD.setText("NO");
        }

    }
    public void validarSintomas(revisionObstruccionVA revisionSintomas,TextView txtSintomasObstruccion){
        if(!revisionSintomas.getSintomas().isEmpty()){
            txtSintomasObstruccion.setText("SI");
        }else{
            txtSintomasObstruccion.setText("NO");
        }
    }
    public void validarCantCriterios(revisionVentilacionDificil revisionVentilacion,TextView txtCantVentilacionDificil,int cantCriterios){

        cantCriterios = revisionVentilacion.getFactoresVD().size();
        txtCantVentilacionDificil.setText(String.valueOf(cantCriterios));
    }
    public void validarPredictores(resultadoPredictorAperturaBucal rpab,resultadoPredictorDtm rpdtm, resultadoPredictorSubMandibular rpsm,resultadoPredictorMallampati rpm,resultadoPredictorGradoMovilidad rpgm,TextView txtDtm, TextView txtPruebaAperturaOral, TextView txtPruebaSubluxacion, TextView txtPruebaMov,TextView txtPruebaMallampati){
        txtDtm.setText(rpdtm.getResultado());
        txtPruebaAperturaOral.setText(rpab.getResultado());
        txtPruebaSubluxacion.setText(rpsm.getResultado());
        txtPruebaMov.setText(rpgm.getResultado());
        txtPruebaMallampati.setText(rpm.getResultado());

    }

}
