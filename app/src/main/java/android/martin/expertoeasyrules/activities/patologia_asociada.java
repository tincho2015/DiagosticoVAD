package android.martin.expertoeasyrules.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import db.pacienteBdHelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.martin.expertoeasyrules.R;
import android.martin.expertoeasyrules.hechos.paciente;
import android.martin.expertoeasyrules.hechos.patologiaFactorAsociada;
import android.martin.expertoeasyrules.hechos.revisionPatologiaFactor;
import android.martin.expertoeasyrules.reglas.evaluarPatologiasFactores.pacientePresentaAnginaLudwig;
import android.martin.expertoeasyrules.reglas.evaluarPatologiasFactores.pacientePresentaAntecedenteVad;
import android.martin.expertoeasyrules.reglas.evaluarPatologiasFactores.pacientePresentaHipertrofiaAmigdalarLingual;
import android.martin.expertoeasyrules.reglas.evaluarPatologiasFactores.pacientePresentaLesionCuelloTorax;
import android.martin.expertoeasyrules.reglas.evaluarPatologiasFactores.pacientePresentaLesionMandibular;
import android.martin.expertoeasyrules.reglas.evaluarPatologiasFactores.pacientePresentaLesionRaquisCervical;
import android.martin.expertoeasyrules.reglas.evaluarPatologiasFactores.pacientePresentaLesionVA;
import android.martin.expertoeasyrules.reglas.evaluarPatologiasFactores.pacientePresentaMacroglosias;
import android.martin.expertoeasyrules.reglas.evaluarPatologiasFactores.pacientePresentaMasasTiroideas;
import android.martin.expertoeasyrules.reglas.evaluarPatologiasFactores.presentaFactor;
import android.martin.expertoeasyrules.reglas.evaluarPatologiasFactores.presentaPatologia;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPatologias.valoracionFactor;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPatologias.valoracionNoFactor;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPatologias.valoracionNoPatologia;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPatologias.valoracionPatologia;
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
import org.jeasy.rules.core.InferenceRulesEngine;

import java.util.ArrayList;


public class patologia_asociada extends AppCompatActivity {

    private RadioGroup rgLesionVA;
    private RadioGroup rgMasaTiroide;
    private RadioGroup rgLesionCuelloTorax;
    private RadioGroup rgLesionRaquisCervical;
    private RadioGroup rgAnginaLudwig;
    private RadioGroup rgMacroglosia;
    private RadioGroup rgLesionMandibular;
    private RadioGroup rgHipertrofia;
    private RadioGroup rgAntecedente;


    private RadioButton rbMacroSi;
    private RadioButton rbMacroNo;
    private RadioButton rbLesionMandibularSi;
    private RadioButton rbLesionMandibularNo;
    private RadioButton rbHipertrofiaSi;
    private RadioButton rbHipertrofiaNo;
    private RadioButton rbAntecedenteSi;
    private RadioButton rbAntecedenteNo;
    private RadioButton rbLesionNo;
    private RadioButton rbLesionSi;
    private RadioButton rbMasaTiroideSi;
    private RadioButton rbMasaTiroideNo;
    private RadioButton rbLesionCuelloToraxSi;
    private RadioButton rbLesionCuelloToraxNo;
    private RadioButton rbLesionRaquisNo;
    private RadioButton rbLesionRaquisSi;
    private RadioButton rbAnginaLudwigSi;
    private RadioButton rbAnginaLudwigNo;

    private Button btnFinalizar;

    private boolean lesionVa;
    private boolean masaTiroide;
    private boolean lesionCuelloTorax;
    private boolean lesionRaquis;
    private boolean anginaLudwig;
    private boolean antecedenteVAD;
    private boolean hipertrofia;
    private boolean macroglosia;
    private boolean lesionMandibular;

    private Bundle b = null;
    private patologiaFactorAsociada pfa = null;
    private revisionPatologiaFactor revision = null;
    private paciente p = null;
    private ArrayList<patologiaFactorAsociada>listaPatologiasFactores = new ArrayList<>();

    private pacienteBdHelper bd;


    @Override
    protected void onDestroy() {
        bd.close();
        super.onDestroy();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patologia_asociada);

        getSupportActionBar().setTitle("Patologías y factores asociados");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bd = new pacienteBdHelper(this);

        b = new Bundle();
        b = getIntent().getBundleExtra("paciente");

        if(b != null){

            p = b.getParcelable("paciente");
        }

        rgLesionVA= findViewById(R.id.rgLesionVA);
        rgLesionRaquisCervical = findViewById(R.id.rgLesionRaquisCervical);
        rgMasaTiroide = findViewById(R.id.rgMasas);
        rgLesionCuelloTorax = findViewById(R.id.rgLesionCuelloTorax);
        rgAnginaLudwig = findViewById(R.id.rgAnginaLudwig);
        rgMacroglosia = findViewById(R.id.rgMacroglosias);
        rgLesionMandibular = findViewById(R.id.rgLesionMandibular);
        rgHipertrofia = findViewById(R.id.rgHipertrofia);
        rgAntecedente = findViewById(R.id.rgAntecedenteVad);



        btnFinalizar = findViewById(R.id.btnFinalizar);


        rbLesionNo = findViewById(R.id.rbLesionNo);
        rbLesionSi = findViewById(R.id.rbLesionSi);
        rbLesionRaquisNo = findViewById(R.id.rbLesionCervicalNo);
        rbLesionRaquisSi = findViewById(R.id.rbLesionCervicalSi);
        rbMasaTiroideNo = findViewById(R.id.rbMasasNo);
        rbMasaTiroideSi = findViewById(R.id.rbMasasSi);
        rbLesionCuelloToraxSi = findViewById(R.id.rbLesionCuelloToraxSi);
        rbLesionCuelloToraxNo = findViewById(R.id.rbLesionCuelloToraxNo);
        rbAnginaLudwigNo = findViewById(R.id.rbAnginaLudwigNo);
        rbAnginaLudwigSi = findViewById(R.id.rbAnginaLudwigSi);
        rbMacroSi = findViewById(R.id.rbMacroglosiasSi);
        rbMacroNo = findViewById(R.id.rbMacroglosiasNo);
        rbLesionMandibularSi = findViewById(R.id.rbLesionMandibularSi);
        rbLesionMandibularNo = findViewById(R.id.rbLesionMandibularNo);
        rbHipertrofiaSi = findViewById(R.id.rbHipertrofiaSi);
        rbHipertrofiaNo = findViewById(R.id.rbHipertrofiaNo);
        rbAntecedenteSi = findViewById(R.id.rbAntecedenteVadSi);
        rbAntecedenteNo = findViewById(R.id.rbAntecedenteVaNo);


        b = new Bundle();

        rgLesionVA.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbLesionSi:
                        lesionVa = rbLesionSi.isChecked();
                        rbLesionNo.setError(null);
                        pfa = new patologiaFactorAsociada(lesionVa,"Lesión sobre el cuello y el mediastino",false);
                        listaPatologiasFactores.add(pfa);
                        break;
                    case R.id.rbLesionNo:
                        lesionVa = !rbLesionNo.isChecked();
                        rbLesionNo.setError(null);
                        pfa = new patologiaFactorAsociada(lesionVa,"Lesión sobre el cuello y el mediastino",false);
                        listaPatologiasFactores.add(pfa);
                        break;
                }
            }
        });
        rgLesionCuelloTorax.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbLesionCuelloToraxNo:
                        lesionCuelloTorax = !rbLesionCuelloToraxNo.isChecked();
                        rbLesionCuelloToraxNo.setError(null);
                        pfa = new patologiaFactorAsociada(lesionCuelloTorax,"Lesión macroscópica sobre el cuello y/o Tórax",false);
                        listaPatologiasFactores.add(pfa);
                        break;
                    case R.id.rbLesionCuelloToraxSi:
                        lesionCuelloTorax = rbLesionCuelloToraxSi.isChecked();
                        rbLesionCuelloToraxNo.setError(null);
                        pfa = new patologiaFactorAsociada(lesionCuelloTorax,"Lesión macroscópica sobre el cuello y/o Tórax",false);
                        listaPatologiasFactores.add(pfa);
                        break;
                }
            }
        });
        rgMasaTiroide.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbMasasNo:
                        masaTiroide = !rbMasaTiroideNo.isChecked();
                        rbMasaTiroideNo.setError(null);
                        pfa = new patologiaFactorAsociada(masaTiroide,"Masas tiroideas",false);
                        listaPatologiasFactores.add(pfa);
                        break;
                    case R.id.rbMasasSi:
                        masaTiroide = rbMasaTiroideSi.isChecked();
                        rbMasaTiroideNo.setError(null);
                        pfa = new patologiaFactorAsociada(masaTiroide,"Masas tiroideas",false);
                        listaPatologiasFactores.add(pfa);
                        break;
                }
            }
        });
        rgLesionRaquisCervical.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbLesionCervicalNo:
                        lesionRaquis = !rbLesionRaquisNo.isChecked();
                        rbLesionRaquisNo.setError(null);
                        pfa = new patologiaFactorAsociada(lesionRaquis,"Lesión sobre raquis cervical",false);
                        listaPatologiasFactores.add(pfa);
                        break;
                    case R.id.rbLesionCervicalSi:
                        lesionRaquis = rbLesionRaquisSi.isChecked();
                        rbLesionRaquisNo.setError(null);
                        pfa = new patologiaFactorAsociada(lesionRaquis,"Lesión sobre raquis cervical",false);
                        listaPatologiasFactores.add(pfa);
                        break;
                }
            }
        });
        rgAnginaLudwig.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbAnginaLudwigNo:
                    {
                        anginaLudwig = !rbAnginaLudwigNo.isChecked();
                        rbAnginaLudwigNo.setError(null);
                        pfa = new patologiaFactorAsociada(anginaLudwig,"Angina de Ludwig",false);
                        listaPatologiasFactores.add(pfa);
                        break;
                    }
                    case R.id.rbAnginaLudwigSi:{
                        anginaLudwig = rbAnginaLudwigSi.isChecked();
                        rbAnginaLudwigNo.setError(null);
                        pfa = new patologiaFactorAsociada(anginaLudwig,"Angina de Ludwig",false);
                        listaPatologiasFactores.add(pfa);
                        break;
                    }
                }
            }
        });
        rgMacroglosia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rbMacroglosiasSi:{
                        macroglosia = rbMacroSi.isChecked();
                        rbMacroNo.setError(null);
                        pfa = new patologiaFactorAsociada(macroglosia,"Macroglosia",false);
                        listaPatologiasFactores.add(pfa);
                        break;
                    }
                    case R.id.rbMacroglosiasNo:{
                        macroglosia = !rbMacroNo.isChecked();
                        rbMacroNo.setError(null);
                        pfa = new patologiaFactorAsociada(macroglosia,"Macroglosia",false);
                        listaPatologiasFactores.add(pfa);
                        break;
                    }
                }
            }
        });
        rgLesionMandibular.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbLesionMandibularNo:{
                        lesionMandibular = !rbLesionMandibularNo.isChecked();
                        rbLesionMandibularNo.setError(null);
                        pfa = new patologiaFactorAsociada(lesionMandibular,"Lesión mandibular",false);
                        listaPatologiasFactores.add(pfa);
                        break;
                    }
                    case R.id.rbLesionMandibularSi:{
                        lesionMandibular = rbLesionMandibularSi.isChecked();
                        rbLesionMandibularNo.setError(null);
                        pfa = new patologiaFactorAsociada(lesionMandibular,"Lesión mandibular",false);
                        listaPatologiasFactores.add(pfa);
                        break;
                    }
                }
            }
        });
        rgHipertrofia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbHipertrofiaNo:
                    {
                        hipertrofia = !rbHipertrofiaNo.isChecked();
                        rbHipertrofiaNo.setError(null);
                        pfa = new patologiaFactorAsociada(hipertrofia,"Hipertrofia amigdalar lingual",false);
                        listaPatologiasFactores.add(pfa);
                        break;
                    }
                    case R.id.rbHipertrofiaSi:{
                        hipertrofia = rbHipertrofiaSi.isChecked();
                        rbHipertrofiaNo.setError(null);
                        pfa = new patologiaFactorAsociada(hipertrofia,"Hipertrofia amigdalar lingual",false);
                        listaPatologiasFactores.add(pfa);
                        break;
                    }
                }
            }
        });
        rgAntecedente.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rbAntecedenteVaNo:
                    {
                        antecedenteVAD = !rbAntecedenteNo.isChecked();
                        rbAntecedenteNo.setError(null);
                        pfa = new patologiaFactorAsociada(antecedenteVAD,"Posee antecedentes de VAD",true);
                        listaPatologiasFactores.add(pfa);
                        break;
                    }
                    case R.id.rbAntecedenteVadSi:
                    {
                        antecedenteVAD = rbAntecedenteSi.isChecked();
                        rbAntecedenteNo.setError(null);
                        pfa = new patologiaFactorAsociada(antecedenteVAD,"Posee antecedentes de VAD",true);
                        listaPatologiasFactores.add(pfa);
                        break;
                    }
                }
            }
        });


        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (rgLesionVA.getCheckedRadioButtonId()) {

                    case -1: {
                        rbLesionNo.setError("Seleccione si presenta lesión sobre el cuello y el mediastino");
                        rbLesionNo.requestFocus();
                        break;
                    }
                }
                switch (rgLesionCuelloTorax.getCheckedRadioButtonId()) {

                    case -1: {
                        rbLesionCuelloToraxNo.setError("Seleccione si presenta lesión macroscopica en el cuello y/o tórax");
                        rbLesionCuelloToraxNo.requestFocus();
                        break;
                    }
                }
                switch (rgMasaTiroide.getCheckedRadioButtonId()) {

                    case -1: {
                        rbMasaTiroideNo.setError("Seleccione si presenta Masas tiroideas");
                        rbMasaTiroideNo.requestFocus();
                        break;
                    }

                }
                switch (rgLesionRaquisCervical.getCheckedRadioButtonId()) {

                    case -1: {

                        rbLesionRaquisNo.setError("Seleccione si presenta lesión sobre el raquis cervical");
                        rbLesionRaquisNo.requestFocus();
                        break;
                    }
                }
                switch (rgAnginaLudwig.getCheckedRadioButtonId()) {

                    case -1: {

                        rbAnginaLudwigNo.setError("Seleccione si presenta Angina de Ludwig");
                        rbAnginaLudwigNo.requestFocus();
                        break;
                    }
                }
                switch (rgMacroglosia.getCheckedRadioButtonId()) {

                    case -1: {

                        rbMacroNo.setError("Seleccione si presenta macroglosia");
                        rbMacroNo.requestFocus();
                        break;
                    }
                }
                switch (rgLesionMandibular.getCheckedRadioButtonId()) {

                    case -1: {

                        rbLesionMandibularNo.setError("Seleccione si presenta lesión mandibular");
                        rbLesionMandibularNo.requestFocus();
                        break;
                    }
                }
                switch (rgHipertrofia.getCheckedRadioButtonId()) {

                    case -1: {

                        rbHipertrofiaNo.setError("Seleccione si presenta hipetrofia amigdalar");
                        rbHipertrofiaNo.requestFocus();
                        break;
                    }
                }
                switch (rgAntecedente.getCheckedRadioButtonId()) {

                    case -1: {

                        rbAntecedenteNo.setError("Seleccione si presenta antecedentes de vía aérea difícil");
                        rbAntecedenteNo.requestFocus();
                        break;
                    }
                }

                if (rgLesionVA.getCheckedRadioButtonId() != -1 && rgLesionCuelloTorax.getCheckedRadioButtonId() != -1 && rgMasaTiroide.getCheckedRadioButtonId() != -1 && rgLesionRaquisCervical.getCheckedRadioButtonId() != -1 && rgAnginaLudwig.getCheckedRadioButtonId() != -1
                        && rgMacroglosia.getCheckedRadioButtonId() != -1 && rgLesionMandibular.getCheckedRadioButtonId() != -1 && rgHipertrofia.getCheckedRadioButtonId() != -1 && rgAntecedente.getCheckedRadioButtonId() != -1) {

                    validarCampos("Por favor, verificar si los datos ingresados son correctos:",listaPatologiasFactores);

                }
            }
        });


    }
    private void validarCampos(String titulo,final ArrayList<patologiaFactorAsociada>listaPatologiasFactores){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(titulo!=null)builder.setTitle(titulo);
        //if(mensaje!=null)builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar",null);

        View vista = LayoutInflater.from(this).inflate(R.layout.dialog_item,null);
        builder.setView(vista);

        final TextView resultado = vista.findViewById(R.id.txtExplicacion);
        String mensajeTotal = "";
        String mensajeLesionVa = "";
        String mensajeMasaTiroide= "";
        String mensajeAnginaLudwig= "";
        String mensajeLesionCuelloTorax= "";
        String mensajeLesionRaquis = "";
        String mensajeMacroglosia = "";
        String mensajeHipertrofia = "";
        String mensajeLesionMandibular = "";
        String mensajeAntecedenteVAD ="";

        if(lesionVa){

            mensajeLesionVa ="Presencia de barba:Si"+"\n";
        }else{
            mensajeLesionVa ="Presencia de barba:No"+"\n";
        }
        if(masaTiroide){

            mensajeMasaTiroide ="Presencia de edentación:Si"+ "\n";
        }else{

            mensajeMasaTiroide ="Presencia de edentación:No"+ "\n";
        }
        if(anginaLudwig){

            mensajeAnginaLudwig = "Presencia de obesidad:Si" + "\n";
        }else{
            mensajeAnginaLudwig = "Presencia de obesidad:No" + "\n";
        }
        if(lesionCuelloTorax){

            mensajeLesionCuelloTorax = "Historia de ronquidos:Si" + "\n";
        }else{
            mensajeLesionCuelloTorax = "Historia de ronquidos:No" + "\n";
        }
        if(lesionRaquis){

            mensajeLesionRaquis= "Presencia de raquis cervical:Si" + "\n";
        }else{

            mensajeLesionRaquis= "Presencia de raquis cervical:No" + "\n";
        }
        if(macroglosia){
            mensajeMacroglosia = "Presencia de macroglosia:Si" + "\n";
        }else{

            mensajeMacroglosia = "Presencia de macroglosia:No" + "\n";
        }
        if(hipertrofia){

            mensajeHipertrofia = "Presencia de hipertrofia amigdalar lingual:Si" + "\n";
        }else{

            mensajeHipertrofia = "Presencia de hipertrofia amigdalar lingual:No" + "\n";
        }
        if(lesionMandibular){

            mensajeLesionMandibular = "Presencia de alguna lesión mandibular:Si" + "\n";
        }else{

            mensajeLesionMandibular = "Presencia de alguna lesión mandibular:No" + "\n";
        }
        if(antecedenteVAD){

            mensajeAntecedenteVAD = "Presencia de antecedentes de vía aérea difícil:Si" + "\n";
        }else{
            mensajeAntecedenteVAD = "Presencia de antecedentes de vía aérea difícil:No" + "\n";
        }


        mensajeTotal = mensajeLesionVa+mensajeMasaTiroide+mensajeAnginaLudwig+mensajeLesionCuelloTorax+mensajeLesionRaquis+mensajeMacroglosia+mensajeHipertrofia+mensajeLesionMandibular+mensajeAntecedenteVAD ;
        resultado.setText(mensajeTotal);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                bd.borrarRevisionPatologia();
                //evaluarParametros(lesionVa, masaTiroide, anginaLudwig, lesionCuelloTorax, lesionRaquis, macroglosia, hipertrofia, lesionMandibular, antecedenteVAD);
                evaluarParametros(listaPatologiasFactores);
                valoracion(revision);
                p.setRevisionPatologia(true);
                agregarDato(revision, p);
                Intent i = new Intent(getApplicationContext(), menu_principal.class);
                b.putParcelable("paciente", p);
                i.putExtra("paciente", b);
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

    private boolean mostrarDialogPatologia(String titulo, String mensaje, RadioGroup rgAnginaLudwig,RadioGroup rgAntecedente, RadioGroup rgHipertrofia, RadioGroup rgLesionMandibular, RadioGroup rgLesionRaquisCervical, RadioGroup rgLesionVA, RadioGroup rgMasaTiroide, RadioGroup rgRadiodermitisCervical){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(titulo!=null)builder.setTitle(titulo);
        if(mensaje!=null)builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar",null);

        View vista = LayoutInflater.from(this).inflate(R.layout.dialog_item,null);
        builder.setView(vista);

        final TextView resultado = vista.findViewById(R.id.txtExplicacion);
        String mensajeTotal = "";
        String mensajeAnginaLudwig = "";
        String mensajeAntecedente= "";
        String mensajeHiperotrofia= "";
        String mensajeLensionMandibular= "";
        String mensajeLesionRaquisCervical= "";
        String mensajeLesionVA = "";
        String mensajeMasasTiroide="";
        String mensajeRadioDermitisCervical="";
        boolean seguir = false;

        if(rgAnginaLudwig.getCheckedRadioButtonId() == -1){

            mensajeAnginaLudwig ="Presencia de angina de Ludwig"+"\n";
        }
        if(rgAntecedente.getCheckedRadioButtonId() == -1){

            mensajeAntecedente ="Antecedentes de vía aérea difícil"+ "\n";
        }
        if(rgHipertrofia.getCheckedRadioButtonId() == -1){

            mensajeHiperotrofia = "Presencia de Hipertrofia" + "\n";
        }
        if(rgLesionMandibular.getCheckedRadioButtonId() == -1){

            mensajeLensionMandibular = "Presencia de lesiones mandibular" + "\n";
        }
        if(rgLesionRaquisCervical.getCheckedRadioButtonId() == -1){

            mensajeLesionRaquisCervical = "Historia de ronquidos" + "\n";
        }
        if(rgLesionVA.getCheckedRadioButtonId() == -1){

            mensajeLesionVA = "Presencia de lesiones sobre la vía aérea" + "\n";
        }
        if(rgMasaTiroide.getCheckedRadioButtonId() == -1){

            mensajeMasasTiroide = "Presencia de masas en la glándula tiroides" + "\n";
        }
        if(rgRadiodermitisCervical.getCheckedRadioButtonId() == -1){

            mensajeRadioDermitisCervical= "Presencia de radiodermitis cervical" + "\n";
        }


        mensajeTotal = mensajeAnginaLudwig+mensajeAntecedente+mensajeHiperotrofia+mensajeLensionMandibular+mensajeLesionRaquisCervical+mensajeLesionVA+mensajeMasasTiroide+mensajeRadioDermitisCervical;
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
    public void agregarDato(revisionPatologiaFactor revision, paciente p){

        boolean esInsertado;
        for(String descripcion: revision.getPatologias()) {
            esInsertado = bd.insertarRevisionPatologia(descripcion,p.getId(),revision.getValoracionPatologias());
            if(esInsertado == true){

                Toast.makeText(patologia_asociada.this, "Se han insertado correctamente los datos", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(patologia_asociada.this, "No se han insertado correctamente los datos", Toast.LENGTH_SHORT).show();
            }

        }
        for(String factor: revision.getFactores()){
            esInsertado = bd.insertarRevisionFactor(factor,p.getId(),revision.getValoracionPatologias());
            if(esInsertado == true){

                Toast.makeText(patologia_asociada.this, "Se han insertado correctamente los datos", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(patologia_asociada.this, "No se han insertado correctamente los datos", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void evaluarParametros(ArrayList<patologiaFactorAsociada>listaPatologiasFactores){

        //pfa = new patologiaFactorAsociada(lesionVa,masaTiroide,anginaLudwig,lesionCuelloTorax,lesionRaquis,macroglosia,hipertrofia,lesionMandibular,antecedenteVAD);
        revision = new revisionPatologiaFactor();

        //Creo los hechos

      for(patologiaFactorAsociada patologiaFactor:listaPatologiasFactores) {

            Facts hechos = new Facts();
            hechos.put("patologiasFactores", patologiaFactor);
            hechos.put("revisionPatologia", revision);

            //Creo el set de reglas
             Rules reglas = new Rules();
          reglas.register(new pacientePresentaAnginaLudwig());
          reglas.register(new pacientePresentaAntecedenteVad());
          reglas.register(new pacientePresentaHipertrofiaAmigdalarLingual());
          reglas.register(new pacientePresentaLesionCuelloTorax());
          reglas.register(new pacientePresentaLesionMandibular());
          reglas.register(new pacientePresentaLesionRaquisCervical());
          reglas.register(new pacientePresentaLesionVA());
          reglas.register(new pacientePresentaMacroglosias());
          reglas.register(new pacientePresentaMasasTiroideas());
       /*
        RulesEngine re = new DefaultRulesEngine();
        re.fire(reglas,hechos);*/

           /* Rules reglas = new Rules();
            reglas.register(new presentaPatologia());
            reglas.register(new presentaFactor());*/

            RulesEngine ri = new InferenceRulesEngine();
            ri.fire(reglas, hechos);
    }

    }

    public void valoracion(revisionPatologiaFactor revision){

        //Creo los hechos

        Facts hechos = new Facts();
        hechos.put("revisionPatologia",revision);
/*
        //Creo el set de reglas
        Rules reglas = new Rules();
        reglas.register(new valoracionPatologia1());
        reglas.register(new valoracionPatologia2());
        reglas.register(new valoracionPatologia3());

        RulesEngine re = new DefaultRulesEngine();
        re.fire(reglas,hechos);*/

        //Creo el set de reglas
        Rules reglas = new Rules();
        reglas.register(new valoracionPatologia());
        reglas.register(new valoracionNoPatologia());
        reglas.register(new valoracionFactor());
        reglas.register(new valoracionNoFactor());

        RulesEngine ri = new InferenceRulesEngine();
        ri.fire(reglas,hechos);

    }
}
