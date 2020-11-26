package android.martin.expertoeasyrules.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import db.pacienteBdHelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.martin.expertoeasyrules.R;
import android.martin.expertoeasyrules.adaptadores.adaptadorSpinner;
import android.martin.expertoeasyrules.hechos.paciente;
import android.martin.expertoeasyrules.hechos.predictor;
import android.martin.expertoeasyrules.hechos.predictorAperturaBucal;
import android.martin.expertoeasyrules.hechos.predictorDTM;
import android.martin.expertoeasyrules.hechos.predictorGradoMovilidad;
import android.martin.expertoeasyrules.hechos.predictorMallampati;
import android.martin.expertoeasyrules.hechos.predictorSubluxacionMandibular;
import android.martin.expertoeasyrules.hechos.resultadoPredictorAperturaBucal;
import android.martin.expertoeasyrules.hechos.resultadoPredictorDtm;
import android.martin.expertoeasyrules.hechos.resultadoPredictorGradoMovilidad;
import android.martin.expertoeasyrules.hechos.resultadoPredictorMallampati;
import android.martin.expertoeasyrules.hechos.resultadoPredictorSubMandibular;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores.valoracionPredictorAperturaBucalGrado1;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores.valoracionPredictorAperturaBucalGrado2;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores.valoracionPredictorAperturaBucalGrado3;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores.valoracionPredictorDTMClase1;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores.valoracionPredictorDTMClase2;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores.valoracionPredictorMallampatiClase1;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores.valoracionPredictorMallampatiClase2;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores.valoracionPredictorMallampatiClase3;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores.valoracionPredictorMallampatiClase4;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores.valoracionPredictorMovilidadGrado1;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores.valoracionPredictorMovilidadGrado2;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores.valoracionPredictorMovilidadGrado3;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores.valoracionPredictorSubluxacionMandibularIgual0;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores.valoracionPredictorSubluxacionMandibularMayor0;
import android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores.valoracionPredictorSubluxacionMandibularMenor0;
import android.martin.expertoeasyrules.reglas.pruebasPredictoras.AperturaBucalGrado1;
import android.martin.expertoeasyrules.reglas.pruebasPredictoras.AperturaBucalGrado2;
import android.martin.expertoeasyrules.reglas.pruebasPredictoras.AperturaBucalGrado3;
import android.martin.expertoeasyrules.reglas.pruebasPredictoras.DtmClase1;
import android.martin.expertoeasyrules.reglas.pruebasPredictoras.DtmClase2;
import android.martin.expertoeasyrules.reglas.pruebasPredictoras.MallanpatiClase1;
import android.martin.expertoeasyrules.reglas.pruebasPredictoras.MallanpatiClase2;
import android.martin.expertoeasyrules.reglas.pruebasPredictoras.MallanpatiClase3;
import android.martin.expertoeasyrules.reglas.pruebasPredictoras.MallanpatiClase4;
import android.martin.expertoeasyrules.reglas.pruebasPredictoras.MovilidadCervicalGrado1;
import android.martin.expertoeasyrules.reglas.pruebasPredictoras.MovilidadCervicalGrado2;
import android.martin.expertoeasyrules.reglas.pruebasPredictoras.MovilidadCervicalGrado3;
import android.martin.expertoeasyrules.reglas.pruebasPredictoras.SubMandibularClase1;
import android.martin.expertoeasyrules.reglas.pruebasPredictoras.SubMandibularClase2;
import android.martin.expertoeasyrules.reglas.pruebasPredictoras.SubMandibularClase3;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.InferenceRulesEngine;

import java.util.ArrayList;

public class prueba_predictores extends AppCompatActivity {


    private RadioGroup rgPilares;
    private RadioGroup rgPaladar;

    private Spinner spGradoMovilidad;
    private Spinner spUvula;
    private Spinner spDistanciaInter;
    private Spinner spDistanciaDtm;
    private Button btnFinalizar;
    private Spinner spNivel;



    private RadioButton rbPilaresSi;
    private RadioButton rbPilaresNo;
    private RadioButton rbPaladarBlandoSi;
    private RadioButton rbPaladarBlandoNo;

//    private pruebaClinica pc = null;
    private resultadoPredictorMallampati rpm = null;
    private resultadoPredictorAperturaBucal rpab = null;
    private resultadoPredictorDtm rpdtm = null;
    private resultadoPredictorSubMandibular rpsm = null;
    private resultadoPredictorGradoMovilidad rpgm = null;

    private predictor pr = null;
    private paciente p = null;
    private Bundle b = null;

    private String opcionUvula;
    private boolean pilar = false;
    private boolean paladar;
    private String distanciaInter;
    private String distanciaLaringeo;
    private String opcionNivel;
    private String opcionMovilidad;

    private ArrayList<String> listaNiveles;
    private ArrayList<String>listaGrados;
    private ArrayList<String>listaDistanciaInter;
    private ArrayList<String>listanciaDtm;
    private ArrayList<String>listaUvula;

    private ArrayAdapter<String>adaptadorSpinnerNiveles;
    private ArrayAdapter<String>adaptadorSpinnerGrados;
    private ArrayAdapter<String>adaptadorSpinnerDistanciaInter;
    private ArrayAdapter<String>adaptadorSpinnerDtm;
    private ArrayAdapter<String>adaptadorSpinnerUvula;

    private predictorMallampati pm = null;
    private predictorDTM pDTM = null;
    private predictorGradoMovilidad pGradoMov = null;
    private predictorSubluxacionMandibular pSubMan = null;
    private predictorAperturaBucal pab = null;

    private pacienteBdHelper bd = null;

    @Override
    protected void onDestroy() {
        bd.close();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_clinica);

        getSupportActionBar().setTitle("Predictores");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bd = new pacienteBdHelper(this);

        b = getIntent().getBundleExtra("paciente");

        if(b != null){

            p = b.getParcelable("paciente");
        }

        listaNiveles = new ArrayList<>();
        listaNiveles.add("Seleccione una opción");
        listaNiveles.add("Incisivos inferiores iguales a superiores");
        listaNiveles.add("Incisivos inferiores delante de los superiores");
        listaNiveles.add("Incisivos inferiores detrás de los superiores");

        listaGrados = new ArrayList<>();
        listaGrados.add("Seleccione una opción");
        listaGrados.add("Mov.conservada");
        listaGrados.add("Mov.ligeramente disminuido");
        listaGrados.add("Restricción severa de la movilidad");

        listaDistanciaInter = new ArrayList<>();
        listaDistanciaInter.add("Seleccione una opción");
        listaDistanciaInter.add("Mayor o igual a 5 cm");
        listaDistanciaInter.add("Entre 3,5 cm y 5 cm");
        listaDistanciaInter.add("Menor a 3,5 cm");

        listanciaDtm = new ArrayList<>();
        listanciaDtm.add("Seleccione una opción");
        listanciaDtm.add("Mayor o igual a 6,5 cm");
        listanciaDtm.add("Menor a 6,5 cm");

        listaUvula = new ArrayList<>();
        listaUvula.add("Seleccione una opción");
        listaUvula.add("Completa");
        listaUvula.add("Base");
        listaUvula.add("No");



        rgPaladar = findViewById(R.id.rgPaladar);
        rgPilares = findViewById(R.id.rgPilares);

        rbPilaresSi = findViewById(R.id.rbPilaresSi);
        rbPilaresNo = findViewById(R.id.rbPilaresNo);
        rbPaladarBlandoSi = findViewById(R.id.rbPaladarBlandoSi);
        rbPaladarBlandoNo = findViewById(R.id.rbPaladarBlandoNo);

        spGradoMovilidad = findViewById(R.id.spGradoMov);
        spNivel = findViewById(R.id.spNivel);
        spDistanciaDtm = findViewById(R.id.spDtm);
        spDistanciaInter = findViewById(R.id.spInter);
        spUvula = findViewById(R.id.spUvula);

        adaptadorSpinnerNiveles = new adaptadorSpinner(getApplicationContext(),listaNiveles);
        adaptadorSpinnerNiveles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNivel.setAdapter(adaptadorSpinnerNiveles);


        adaptadorSpinnerGrados = new adaptadorSpinner(getApplicationContext(),listaGrados);
        adaptadorSpinnerGrados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGradoMovilidad.setAdapter(adaptadorSpinnerGrados);

        adaptadorSpinnerDistanciaInter = new adaptadorSpinner(getApplicationContext(),listaDistanciaInter);
        adaptadorSpinnerDistanciaInter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDistanciaInter.setAdapter(adaptadorSpinnerDistanciaInter);

        adaptadorSpinnerDtm = new adaptadorSpinner(getApplicationContext(),listanciaDtm);
        adaptadorSpinnerDtm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDistanciaDtm.setAdapter(adaptadorSpinnerDtm);

        adaptadorSpinnerUvula = new adaptadorSpinner(getApplicationContext(),listaUvula);
        spUvula.setAdapter(adaptadorSpinnerUvula);


        btnFinalizar = findViewById(R.id.btnFinalizar);


        rgPilares.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rbPilaresNo:
                        pilar = !rbPilaresNo.isChecked();
                        rbPilaresNo.setError(null);
                        break;
                    case R.id.rbPilaresSi:
                        pilar = rbPilaresSi.isChecked();
                        rbPilaresSi.setError(null);
                        break;

                }
            }
        });

        spUvula.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (adaptadorSpinnerUvula.getItem(position)){
                    case "Seleccione una opción":
                        break;
                    case "Completa":
                        opcionUvula = "Completa";
                        break;
                    case "Base":
                        opcionUvula = "Base";
                        break;
                    case "No":
                        opcionUvula = "No";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        rgPaladar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rbPaladarBlandoNo:
                        paladar = !rbPaladarBlandoNo.isChecked();
                        rbPaladarBlandoNo.setError(null);
                        break;
                    case R.id.rbPaladarBlandoSi:
                        paladar = rbPaladarBlandoSi.isChecked();
                        rbPaladarBlandoSi.setError(null);
                        break;
                }
            }
        });
        spNivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (adaptadorSpinnerNiveles.getItem(position)){
                    case "Seleccione una opción":
                        break;
                    case "Incisivos inferiores iguales a superiores":
                        opcionNivel ="Igual a 0";
                        break;
                    case "Incisivos inferiores delante de los superiores":
                        opcionNivel ="Mayor a 0";
                        break;
                    case "Incisivos inferiores detrás de los superiores":
                        opcionNivel ="Menor a 0";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spGradoMovilidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (adaptadorSpinnerGrados.getItem(position)){
                    case "Seleccione una opción":
                        break;
                    case "Mov.conservada":
                        opcionMovilidad ="Mov.conservada y sin dolor/parestesia";
                        break;
                    case "Mov.ligeramente disminuido":
                        opcionMovilidad ="Mov.ligeramente disminuido y ligero dolor/parestesia";
                        break;
                    case "Restricción severa de la movilidad":
                        opcionMovilidad ="Restricción severa de la movilidad";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spDistanciaInter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (adaptadorSpinnerDistanciaInter.getItem(position)){

                    case "Seleccione una opción":{
                        break;
                    }
                    case "Mayor o igual a 5 cm":
                        distanciaInter = "Mayor o igual a 5 cm";
                        break;
                    case "Entre 3,5 cm y 5 cm":
                        distanciaInter = "Entre 3,5 cm y 5 cm";
                        break;
                    case "Menor a 3,5 cm":
                        distanciaInter = "Menor a 3,5 cm";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spDistanciaDtm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (adaptadorSpinnerDtm.getItem(position)){

                    case "Seleccione una opción":{
                        break;
                    }
                    case "Mayor o igual a 6,5 cm":
                        distanciaLaringeo = "Mayor o igual a 6,5 cm";
                        break;
                    case "Menor a 6,5 cm":
                        distanciaLaringeo = "Menor a 6,5 cm";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (rgPaladar.getCheckedRadioButtonId()){

                    case -1:{

                        rbPaladarBlandoNo.setError("Seleccione si puede visualizar el paladar blando");
                        rbPaladarBlandoNo.requestFocus();
                        break;
                    }

                }
                switch (rgPilares.getCheckedRadioButtonId()){

                    case -1:{

                        rbPilaresNo.setError("Seleccione si puede visualizar los pilares amigdalinos");
                        rbPilaresNo.requestFocus();
                        break;
                    }

                }
                if (rgPilares.getCheckedRadioButtonId() != -1 && rgPaladar.getCheckedRadioButtonId() != -1 && !spDistanciaDtm.getSelectedItem().equals("Seleccione una opción") && !spDistanciaInter.getSelectedItem().equals("Seleccione una opción") && !spNivel.getSelectedItem().equals("Seleccione una opción") && !spGradoMovilidad.getSelectedItem().equals("Seleccione una opción") &&

                            !spUvula.getSelectedItem().equals("Seleccione una opción")) {

                     pm = new predictorMallampati(opcionUvula,paladar,pilar);
                        validarCampos("Por favor, verificar si los datos ingresados son correctos:",pm);
                    }

            }
        });




    }
    private void validarCampos(String titulo,predictorMallampati pm){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(titulo!=null)builder.setTitle(titulo);
        //if(mensaje!=null)builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar",null);

        View vista = LayoutInflater.from(this).inflate(R.layout.dialog_item,null);
        builder.setView(vista);

        final TextView resultado = vista.findViewById(R.id.txtExplicacion);
        String mensajeTotal = "";
        String mensajePaladar = "";
        String mensajePilares= "";
        String mensajeNivel = "";
        String mensajeGrado = "";
        String mensajeDistanciaInter="";
        String mensajeDistanciaDtm="";
        String mensajeUvula = "";


        if(pilar){

            mensajePilares ="Visualiza los pilares amigdalinos:Si"+"\n";
        }else{
            mensajePilares ="Visualiza los pilares amigdalinos:No"+"\n";
        }
        if(paladar){

            mensajePaladar ="Visualiza el paladar blando:Si"+ "\n";
        }else{

            mensajePaladar ="Visualiza el paladar blando:No"+ "\n";
        }
        mensajeUvula = "Visibilidad de la úvula: "+ "" + opcionUvula + "\n";
        mensajeNivel = "Nivel de subluxación mandibular:" + "" +opcionNivel + "\n";
        mensajeGrado = "Grado de movilidad de la cabeza y el cuello:" + ""+ opcionMovilidad + "\n";
        mensajeDistanciaInter = "Distancia interincisiva:" + distanciaInter + ""+"\n";
        mensajeDistanciaDtm = "Distancia tiromentoniana:"+ distanciaLaringeo + ""+"\n";

        mensajeTotal = mensajePaladar+mensajePilares+mensajeUvula+mensajeNivel+mensajeGrado+mensajeDistanciaInter+mensajeDistanciaDtm;
        resultado.setText(mensajeTotal);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                bd.borrarRevisionPredictores();
                evaluarParametros(distanciaInter,distanciaLaringeo,opcionMovilidad,opcionNivel);
                valoracion(rpab,rpdtm,rpgm,rpm,rpsm);
                p.setResultadoPredictor(true);
                agregarDato(rpab,rpdtm,rpgm,rpm,rpsm,p);
                Intent i = new Intent(getApplicationContext(), menu_principal.class);
                b.putParcelable("paciente",p);
                i.putExtra("paciente",b);
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

    public void evaluarParametros(String distanciaInter, String distanciaLaringeo, String gradoMovilidad,String opcionNivel){

        //pr = new predictor(uvula,paladar,pilar,distanciaInter,distanciaLaringeo,gradoMovilidad,opcionNivel);
        pm = new predictorMallampati(opcionUvula,paladar,pilar);
        pab = new predictorAperturaBucal(distanciaInter);
        pDTM = new predictorDTM(distanciaLaringeo);
        pGradoMov = new predictorGradoMovilidad(gradoMovilidad);
        pSubMan = new predictorSubluxacionMandibular(opcionNivel);

        rpab = new resultadoPredictorAperturaBucal();
        rpdtm = new resultadoPredictorDtm();
        rpgm = new resultadoPredictorGradoMovilidad();
        rpm = new resultadoPredictorMallampati();
        rpsm = new resultadoPredictorSubMandibular();

        //Creo los hechos

        Facts facts = new Facts();
        facts.put("predictorMallampati",pm);
        facts.put("predictorAperturaBocal",pab);
        facts.put("predictorDtm",pDTM);
        facts.put("predictorGradoMovilidad",pGradoMov);
        facts.put("predictorSubMandibular",pSubMan);
        facts.put("resultadoPredictorMallampati",rpm);
        facts.put("resultadoPredictorAperturaBucal",rpab);
        facts.put("resultadoPredictorDtm",rpdtm);
        facts.put("resultadoPredictorGradoMovilidad",rpgm);
        facts.put("resultadoPredictorSubMandibular",rpsm);

        // Crear el set de reglas
       /* Rules rules = new Rules();
        rules.register(new reglaDtmClase1());
        rules.register(new reglaDtmClase2());
        rules.register(new reglaMallanpatiClase1());
        rules.register(new reglaMallanpatiClase2());
        rules.register(new reglaMallanpatiClase3());
        rules.register(new reglaMallanpatiClase4());
        rules.register(new reglaMovilidadCervicalGrado1());
        rules.register(new reglaMovilidadCervicalGrado2());
        rules.register(new reglaMovilidadCervicalGrado3());
        rules.register(new reglaAperturaBucalGrado1());
        rules.register(new reglaAperturaBucalGrado2());
        rules.register(new reglaAperturaBucalGrado3());
        rules.register(new reglaSubMandibularClase1());
        rules.register(new reglaSubMandibularClase2());
        rules.register(new reglaSubMandibularClase3());*/

        Rules rules = new Rules();

        rules.register(new DtmClase1());
        rules.register(new DtmClase2());
        rules.register(new MallanpatiClase1());
        rules.register(new MallanpatiClase2());
        rules.register(new MallanpatiClase3());
        rules.register(new MallanpatiClase4());
        rules.register(new MovilidadCervicalGrado1());
        rules.register(new MovilidadCervicalGrado2());
        rules.register(new MovilidadCervicalGrado3());
        rules.register(new AperturaBucalGrado1());
        rules.register(new AperturaBucalGrado2());
        rules.register(new AperturaBucalGrado3());
        rules.register(new SubMandibularClase1());
        rules.register(new SubMandibularClase2());
        rules.register(new SubMandibularClase3());

        /*RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);*/

        RulesEngine ri = new InferenceRulesEngine();
        ri.fire(rules,facts);


        /*MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());

        try {

            String respath = "/reglas/pruebasC";
            InputStream in = MainActivity.class.getResourceAsStream(respath);
            if (in == null)
                throw new Exception("resource not found: " + respath);

            InputStreamReader ireader = new InputStreamReader(in);

            //Rule pruebasClinicas = ruleFactory.createRule(ireader);
            Rules pruebasClinicas = ruleFactory.createRules(ireader);


            // Crear el set de reglas
            Rules rules = new Rules();
            rules.register(pruebasClinicas);

            //RulesEngineParameters parametros = new RulesEngineParameters()
              //      .skipOnFirstAppliedRule(true);

            // create a rules engine and fire rules on known facts
            RulesEngine rulesEngine = new DefaultRulesEngine();
            rulesEngine.fire(rules, facts);


        } catch (Exception e) {
            e.printStackTrace();
        }*/


    }

    public void agregarDato(resultadoPredictorAperturaBucal revisionAperturaBucal,resultadoPredictorDtm revisionDtm, resultadoPredictorGradoMovilidad revisionGradoMov, resultadoPredictorMallampati revisionMallampati, resultadoPredictorSubMandibular revisionSubMandibular,paciente p){

        boolean esInsertado;
        int valorMallampati = revisionMallampati.getValoracionPredictor();
        int valorSubluxacion = revisionSubMandibular.getValoracionPredictor();
        int valorRango = revisionGradoMov.getValoracionPredictor();
        int valorDtm = revisionDtm.getValoracionPredictor();
        int valorApertura = revisionAperturaBucal.getValoracionPredictor();
        esInsertado = bd.insertarRevisionPredictores(revisionMallampati.getResultado(),revisionGradoMov.getResultado(),revisionSubMandibular.getResultado(),revisionAperturaBucal.getResultado(),revisionDtm.getResultado(),p.getId(),valorMallampati,valorSubluxacion,valorRango,valorDtm,valorApertura,revisionMallampati.getJustificacion(),revisionAperturaBucal.getJustificacion(),revisionDtm.getJustificacion(),revisionGradoMov.getJustificacion(),revisionSubMandibular.getJustificacion());
                /*if(radioMasculino.isChecked()) {
                    //esInsertado= dbhelper.insetarDatos(pacienteNombre.getText().toString(),MASCULINO_OPCION, pacienteEdad.getText().toString());
                    esInsertado= dbhelper.insetarDatos(p.getNombre(),p.getSexo(),String.valueOf(p.getEdad()));
                }else
                    esInsertado= dbhelper.insetarDatos(pacienteNombre.getText().toString(),FEMENINO_OPCION, pacienteEdad.getText().toString());*/

            if(esInsertado == true){

                Toast.makeText(prueba_predictores.this, "Se han insertado correctamente los datos", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(prueba_predictores.this, "No se han insertado correctamente los datos", Toast.LENGTH_SHORT).show();
            }

    }

   /* public void valoracion(resultadoPredictor revision){

        //Creo los hechos

        Facts hechos = new Facts();
        hechos.put("revisionPredictores",revision);

        //Creo el set de reglas
        Rules reglas = new Rules();
        reglas.register(new valoracionPredictores1());
        reglas.register(new valoracionPredictores2());
        reglas.register(new valoracionPredictores3());
        reglas.register(new valoracionPredictores4());
        reglas.register(new valoracionPredictores5());
        reglas.register(new valoracionPredictores6());
        reglas.register(new valoracionPredictores7());
        reglas.register(new valoracionPredictores8());
        reglas.register(new valoracionPredictores9());
        reglas.register(new valoracionPredictores10());
        reglas.register(new valoracionPredictores11());
        reglas.register(new valoracionPredictores12());
        reglas.register(new valoracionPredictores13());
        reglas.register(new valoracionPredictores14());
        reglas.register(new valoracionPredictores15());
        reglas.register(new valoracionPredictores16());
        reglas.register(new valoracionPredictores17());
        reglas.register(new valoracionPredictores18());
        reglas.register(new valoracionPredictores19());
        reglas.register(new valoracionPredictores20());
        reglas.register(new valoracionPredictores21());
        reglas.register(new valoracionPredictores22());
        reglas.register(new valoracionPredictores23());
        reglas.register(new valoracionPredictores24());
        reglas.register(new valoracionPredictores25());
        reglas.register(new valoracionPredictores26());
        reglas.register(new valoracionPredictores27());
        reglas.register(new valoracionPredictores28());
        reglas.register(new valoracionPredictores29());
        reglas.register(new valoracionPredictores30());
        reglas.register(new valoracionPredictores31());
        reglas.register(new valoracionPredictores32());
        reglas.register(new valoracionPredictores33());
        reglas.register(new valoracionPredictores34());
        reglas.register(new valoracionPredictores35());
        reglas.register(new valoracionPredictores36());


        RulesEngine re = new DefaultRulesEngine();
        re.fire(reglas,hechos);
    }*/
    public void valoracion(resultadoPredictorAperturaBucal revisionAperturaBucal,resultadoPredictorDtm revisionDtm, resultadoPredictorGradoMovilidad revisionGradoMov, resultadoPredictorMallampati revisionMallampati, resultadoPredictorSubMandibular revisionSubMandibular){

        //Creo los hechos

        Facts hechos = new Facts();
        hechos.put("resultadoPredictorAperturaBucal",revisionAperturaBucal);
        hechos.put("resultadoPredictorDtm",revisionDtm);
        hechos.put("resultadoPredictorGradoMovilidad",revisionGradoMov);
        hechos.put("resultadoPredictorMallampati",revisionMallampati);
        hechos.put("resultadoPredictorSubMandibular",revisionSubMandibular);

        //Creo el set de reglas
        /*Rules reglas = new Rules();
        reglas.register(new valoracionPredictores1());
        reglas.register(new valoracionPredictores2());
        reglas.register(new valoracionPredictores3());
        reglas.register(new valoracionPredictores4());
        reglas.register(new valoracionPredictores5());
        reglas.register(new valoracionPredictores6());
        reglas.register(new valoracionPredictores7());
        reglas.register(new valoracionPredictores8());
        reglas.register(new valoracionPredictores9());
        reglas.register(new valoracionPredictores10());
        reglas.register(new valoracionPredictores11());
        reglas.register(new valoracionPredictores12());
        reglas.register(new valoracionPredictores13());
        reglas.register(new valoracionPredictores14());
        reglas.register(new valoracionPredictores15());
        reglas.register(new valoracionPredictores16());
        reglas.register(new valoracionPredictores17());
        reglas.register(new valoracionPredictores18());
        reglas.register(new valoracionPredictores19());
        reglas.register(new valoracionPredictores20());
        reglas.register(new valoracionPredictores21());
        reglas.register(new valoracionPredictores22());
        reglas.register(new valoracionPredictores23());
        reglas.register(new valoracionPredictores24());
        reglas.register(new valoracionPredictores25());
        reglas.register(new valoracionPredictores26());
        reglas.register(new valoracionPredictores27());
        reglas.register(new valoracionPredictores28());
        reglas.register(new valoracionPredictores29());
        reglas.register(new valoracionPredictores30());
        reglas.register(new valoracionPredictores31());
        reglas.register(new valoracionPredictores32());
        reglas.register(new valoracionPredictores33());
        reglas.register(new valoracionPredictores34());
        reglas.register(new valoracionPredictores35());
        reglas.register(new valoracionPredictores36());


        RulesEngine re = new DefaultRulesEngine();
        re.fire(reglas,hechos);*/

        Rules reglas = new Rules();
        reglas.register(new valoracionPredictorAperturaBucalGrado1());
        reglas.register(new valoracionPredictorAperturaBucalGrado2());
        reglas.register(new valoracionPredictorAperturaBucalGrado3());
        reglas.register(new valoracionPredictorDTMClase1());
        reglas.register(new valoracionPredictorDTMClase2());
        reglas.register(new valoracionPredictorMallampatiClase1());
        reglas.register(new valoracionPredictorMallampatiClase2());
        reglas.register(new valoracionPredictorMallampatiClase3());
        reglas.register(new valoracionPredictorMallampatiClase4());
        reglas.register(new valoracionPredictorMovilidadGrado1());
        reglas.register(new valoracionPredictorMovilidadGrado2());
        reglas.register(new valoracionPredictorMovilidadGrado3());
        reglas.register(new valoracionPredictorSubluxacionMandibularIgual0());
        reglas.register(new valoracionPredictorSubluxacionMandibularMayor0());
        reglas.register(new valoracionPredictorSubluxacionMandibularMenor0());

        RulesEngine ri = new InferenceRulesEngine();
        ri.fire(reglas,hechos);
    }

}
