package android.martin.expertoeasyrules.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import db.pacienteBdHelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.martin.expertoeasyrules.DatosListView;
import android.martin.expertoeasyrules.R;
import android.martin.expertoeasyrules.adaptadores.adaptadorMenu;
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
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.InferenceRulesEngine;

import java.util.ArrayList;
import java.util.List;

public class menu_principal extends AppCompatActivity {

    private ListView lViewOpciones;
    private List<DatosListView> listaOpciones;
    private TextView nombrePaciente;
    private TextView edadPaciente;
   //private paciente nuevoPaciente = null;


    private revisionObstruccionVA revisionObstruccion = null;
    private revisionPatologiaFactor revisionPatologia = null;
    private revisionVentilacionDificil revisionVentilacion = null;
    private resultadoPredictor revisionPredictor = null;
    private resultadoPredictorAperturaBucal revisionPredictorAperturaBucal = null;
    private resultadoPredictorGradoMovilidad revisionPredictorGradoMovilidad = null;
    private resultadoPredictorMallampati revisionPredictorMallampati = null;
    private resultadoPredictorSubMandibular revisionPredictorSubMandibular = null;
    private resultadoPredictorDtm revisionPredictorDtm = null;

    private paciente p = null;

    Bundle pacienteEnviado = null;
    Bundle recibidoPaciente = null;
    Bundle revisiones = null;
    Bundle pacienteEnv = null;

    private pacienteBdHelper bd = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        recibidoPaciente = getIntent().getBundleExtra("paciente");

        getSupportActionBar().setTitle("Menú principal");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bd = new pacienteBdHelper(this);
        pacienteEnviado = new Bundle();
        pacienteEnv = new Bundle();

        lViewOpciones = findViewById(R.id.listViewMenu);
        nombrePaciente = findViewById(R.id.txtViewNombrePacienteMenu);
        edadPaciente = findViewById(R.id.txtViewEdadPacienteMenu);

        if(recibidoPaciente != null){

            p = (paciente) recibidoPaciente.getParcelable("paciente");
//            nombrePaciente.setText(p.getNombre());
            //nombrePaciente.setText("caca" + "pedo");
            //edadPaciente.setText(String.valueOf(p.getEdad()));
            limpiarPreferencias();
            guardarPreferencias();
        }
        cargarPreferencias();

        if(p != null) {

            darRevisionObstruccion(p);
            darRevisionVentilacion(p);
            darRevisionPredictorApertura(p);
            darRevisionPredictorDtm(p);
            darRevisionPredictorGrado(p);
            darRevisionPredictorMallampati(p);
            darRevisionPredictorSubMandibular(p);
            //darRevisionPredictor(p);
            darRevisionPatologia(p);
        }

        //pacienteEnviado.putParcelable("paciente", p);

        listaOpciones = new ArrayList<>();
        if(p.getRevisionObstruccion()) {
            listaOpciones.add(new DatosListView(1, "Sintomas y signos de obstrucción de vía aérea", R.mipmap.ic_opcion_1_realizado, false,true));
        }else{
            listaOpciones.add(new DatosListView(1, "Sintomas y signos de obstrucción de vía aérea", R.mipmap.ic_opcion_1, false,false));
        }
        if(p.getRevisionPatologia()) {
            listaOpciones.add(new DatosListView(2, "Patologias y factores asociados", R.mipmap.ic_opcion_2_realizado, false, true));
        }else{
            listaOpciones.add(new DatosListView(2, "Patologias y factores asociados", R.mipmap.ic_opcion_2, false, false));
        }
        if(p.getResultadoPredictor()) {
            listaOpciones.add(new DatosListView(3, "Pruebas predictoras", R.mipmap.ic_opcion_4_realizado, false, true));
        }else{
            listaOpciones.add(new DatosListView(3, "Pruebas predictoras", R.mipmap.ic_opcion_4, false, false));
        }
        if(p.getRevisionVentilacionDificil()) {
            listaOpciones.add(new DatosListView(4, "Factores predictivos de ventilación difícil", R.mipmap.ic_opcion_3_realizado, false, true));
        }else{
            listaOpciones.add(new DatosListView(4, "Factores predictivos de ventilación difícil", R.mipmap.ic_opcion_3, false, false));
        }
        if (darDiagnosticoFinal(p)) {
                listaOpciones.add(new DatosListView(5, "Diagnóstico final", R.mipmap.ic_opcion_5, false,false));
            } else {
                listaOpciones.add(new DatosListView(5, "Diagnóstico final", R.mipmap.ic_diagnostico_final_gris, true,false));
            }
        final adaptadorMenu adaptador = new adaptadorMenu(this, R.layout.lista_item, listaOpciones);
        lViewOpciones.setAdapter(adaptador);


            lViewOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (listaOpciones.get(position).getTitulo()) {

                        case "Diagnóstico final":
                            if(!listaOpciones.get(position).isBloqueado()) {
                                Intent i = new Intent(getApplicationContext(), diagnostico_final.class);
                                revisiones = new Bundle();
                                revisiones.putParcelable("revisionSintomas",revisionObstruccion);
                                revisiones.putParcelable("revisionPatologias",revisionPatologia);
                                //revisiones.putParcelable("revisionPredictores",revisionPredictor);
                                revisiones.putParcelable("revisionPredictorMallampati",revisionPredictorMallampati);
                                revisiones.putParcelable("revisionPredictorGradoMovilidad",revisionPredictorGradoMovilidad);
                                revisiones.putParcelable("revisionPredictorDtm",revisionPredictorDtm);
                                revisiones.putParcelable("revisionPredictorSubMandibular",revisionPredictorSubMandibular);
                                revisiones.putParcelable("revisionPredictorAperturaBucal",revisionPredictorAperturaBucal);
                                revisiones.putParcelable("revisionVentilacion",revisionVentilacion);
                                revisiones.putParcelable("paciente",p);
                                i.putExtra("revisiones", revisiones);
                                startActivity(i);
                            }else{
                                Toast.makeText(getApplicationContext(), "Por favor, ingresar todos los criterios requeridos para dar el diagnóstico final", Toast.LENGTH_SHORT).show();
                            }

                            break;
                        case "Sintomas y signos de obstrucción de vía aérea":
                            Intent i2 = new Intent(getApplicationContext(), sintoma_obstruccion.class);
                            i2.putExtra("paciente", pacienteEnv);
                            startActivity(i2);
                            break;
                        case "Patologias y factores asociados":
                            Intent i3 = new Intent(getApplicationContext(), patologia_asociada.class);
                            i3.putExtra("paciente",pacienteEnv);
                            startActivity(i3);
                            break;
                        case "Pruebas predictoras":
                            Intent i4 = new Intent(getApplicationContext(), prueba_predictores.class);
                            i4.putExtra("paciente",pacienteEnv);
                            startActivity(i4);
                            break;
                        case "Factores predictivos de ventilación difícil":
                            Intent i5 = new Intent(getApplicationContext(), criterio_ventilacion_dificil.class);
                            i5.putExtra("paciente",pacienteEnv);
                            startActivity(i5);
                            break;

                    }
                }
            });




    }

    private void limpiarPreferencias(){

        getApplicationContext().getSharedPreferences("paciente",0).edit().clear().commit();
    }
    private void cargarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("paciente",Context.MODE_PRIVATE);
        String nombre = preferences.getString("nombre","no existe info");
        String edad = preferences.getString("edad","no existe info");
        String sexo = preferences.getString("sexo","no existe info");
        boolean revObstruccion = preferences.getBoolean("revisionObstruccion",false);
        boolean revVentilacion = preferences.getBoolean("revisionVentilacion",false);
        boolean revPatologias = preferences.getBoolean("revisionPatologia",false);
        boolean revPredictores = preferences.getBoolean("revisionPredictores",false);


        nombrePaciente.setText(nombre);
        edadPaciente.setText(edad+ " " + "Años");

        p = new paciente(nombre,sexo,Integer.valueOf(edad));
        p.setRevisionObstruccion(revObstruccion);
        p.setRevisionVentilacionDificil(revVentilacion);
        p.setRevisionPatologia(revPatologias);
        p.setResultadoPredictor(revPredictores);
        pacienteEnv.putParcelable("paciente",p);
    }
    private void guardarPreferencias(){

        SharedPreferences preferencias = getSharedPreferences("paciente", Context.MODE_PRIVATE);
        String nombre = p.getNombre();
        String edad = String.valueOf(p.getEdad());
        String sexo = p.getSexo();
        boolean revObstruccion = p.getRevisionObstruccion();
        boolean revVentilacion = p.getRevisionVentilacionDificil();
        boolean revPatologias = p.getRevisionPatologia();
        boolean revPredictores = p.getResultadoPredictor();

        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("nombre",nombre);
        editor.putString("edad",edad);
        editor.putString("sexo",sexo);
        editor.putBoolean("revisionObstruccion",revObstruccion);
        editor.putBoolean("revisionVentilacion",revVentilacion);
        editor.putBoolean("revisionPatologia",revPatologias);
        editor.putBoolean("revisionPredictores",revPredictores);

        nombrePaciente.setText(nombre);
        edadPaciente.setText(edad + " " + "Años");


        editor.commit();
    }

    public boolean darDiagnosticoFinal(paciente p){

        if(p != null) {
            if (p.getRevisionObstruccion() && p.getRevisionVentilacionDificil() && p.getResultadoPredictor() && p.getRevisionPatologia())
                /*&& (p.getRevisionPatologia() != null
                && !p.getRevisionPatologia().getPatologias().isEmpty()
                && !p.getRevisionPatologia().getFactores().isEmpty())
                && p.getRevisionVentilacionDificil() != null
                && !p.getRevisionVentilacionDificil().getFactoresVD().isEmpty()
                && p.getResultadoPredictor() != null*/ {

                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    public revisionObstruccionVA darRevisionObstruccion (paciente p){
        Cursor cursor = bd.darRevisionObstruccion(p.getId());
        revisionObstruccion = new revisionObstruccionVA();
        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {

                String descripcion = cursor.getString(cursor.getColumnIndex("descripcion_sintoma"));
                int valoracion = cursor.getInt(cursor.getColumnIndex("valoracion"));

                revisionObstruccion.getSintomas().add(descripcion);
                revisionObstruccion.setValoracionObstruccion(valoracion);
                cursor.moveToNext();
            }
        }

        return revisionObstruccion;
    }
    public revisionPatologiaFactor darRevisionPatologia(paciente p){

        Cursor cursor = bd.darRevisionPatologia(p.getId());
        revisionPatologia = new revisionPatologiaFactor();
        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {

                String descripcionPatologia = cursor.getString(cursor.getColumnIndex("descripcion_patologia"));
                String descripcionFactor = cursor.getString(cursor.getColumnIndex("descripcion_factor"));
                int valoracionPatologia = cursor.getInt(cursor.getColumnIndex("valoracion_patologia"));
                int valoracionFactor = cursor.getInt(cursor.getColumnIndex("valoracion_factor"));

                revisionPatologia.getPatologias().add(descripcionPatologia);
                revisionPatologia.getFactores().add(descripcionFactor);
                revisionPatologia.setValoracionPatologias(valoracionPatologia);
                revisionPatologia.setValoracionFactor(valoracionFactor);
                cursor.moveToNext();
            }
        }

        return revisionPatologia;

    }
    public revisionVentilacionDificil darRevisionVentilacion(paciente p){
        Cursor cursor = bd.darRevisionVentilacion(p.getId());
        revisionVentilacion = new revisionVentilacionDificil();
        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {

                String factor = cursor.getString(cursor.getColumnIndex("descripcion_ventilacion"));
                int valoracion = cursor.getInt(cursor.getColumnIndex("valoracion"));

                revisionVentilacion.getFactoresVD().add(factor);
                revisionVentilacion.setValoracionVentilacion(valoracion);
                cursor.moveToNext();
            }
        }

        return revisionVentilacion;

    }
    public resultadoPredictor darRevisionPredictor(paciente p){
        Cursor cursor = bd.darRevisionPredictores(p.getId());
        revisionPredictor = new resultadoPredictor();
        revisionPredictorAperturaBucal = new resultadoPredictorAperturaBucal();
        revisionPredictorGradoMovilidad = new resultadoPredictorGradoMovilidad();
        revisionPredictorMallampati = new resultadoPredictorMallampati();
        revisionPredictorSubMandibular = new resultadoPredictorSubMandibular();
        revisionPredictorDtm = new resultadoPredictorDtm();
        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {

                String mallampati  = cursor.getString(cursor.getColumnIndex("clase_mallampati"));
                String subluxacion = cursor.getString(cursor.getColumnIndex("clase_subluxacion"));
                String movilidad = cursor.getString(cursor.getColumnIndex("grado_movilidad"));
                String dtm = cursor.getString(cursor.getColumnIndex("clase_dtm"));
                String apertura = cursor.getString(cursor.getColumnIndex("grado_apertura"));
                int valoracionMallampati = cursor.getInt(cursor.getColumnIndex("valoracion_mallampati"));
                int valoracionSubluxacion = cursor.getInt(cursor.getColumnIndex("valoracion_subluxacion"));
                int valoracionRango = cursor.getInt(cursor.getColumnIndex("valoracion_grado"));
                int valoracionDtm = cursor.getInt(cursor.getColumnIndex("valoracion_dtm"));
                int valoracionApertura =  cursor.getInt(cursor.getColumnIndex("valoracion_apertura"));
                String justMallampati = cursor.getString(cursor.getColumnIndex("justificacion_mallampati"));
                String justApertura = cursor.getString(cursor.getColumnIndex("justificacion_apertura"));
                String justDtm = cursor.getString(cursor.getColumnIndex("justificacion_dtm"));
                String justMovilidad = cursor.getString(cursor.getColumnIndex("justificacion_rango"));
                String justSubluxacion = cursor.getString(cursor.getColumnIndex("justificacion_subluxacion"));

                revisionPredictorMallampati.setResultado(mallampati);
                revisionPredictorDtm.setResultado(dtm);
                revisionPredictorSubMandibular.setResultado(subluxacion);
                revisionPredictorAperturaBucal.setResultado(apertura);
                revisionPredictorGradoMovilidad.setResultado(movilidad);

                revisionPredictorMallampati.setValoracionPredictor(valoracionMallampati);
                revisionPredictorDtm.setValoracionPredictor(valoracionDtm);
                revisionPredictorSubMandibular.setValoracionPredictor(valoracionSubluxacion);
                revisionPredictorAperturaBucal.setValoracionPredictor(valoracionApertura);
                revisionPredictorGradoMovilidad.setValoracionPredictor(valoracionRango);

                revisionPredictorMallampati.setResultado(justMallampati);
                revisionPredictorSubMandibular.setResultado(justSubluxacion);
                revisionPredictorDtm.setResultado(justDtm);
                revisionPredictorGradoMovilidad.setResultado(justMovilidad);
                revisionPredictorAperturaBucal.setResultado(justApertura);
                cursor.moveToNext();
            }
        }

        return revisionPredictor;

    }
    public resultadoPredictorMallampati darRevisionPredictorMallampati(paciente p){
        Cursor cursor = bd.darRevisionPredictores(p.getId());
        revisionPredictorMallampati = new resultadoPredictorMallampati();
        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {

                String mallampati  = cursor.getString(cursor.getColumnIndex("clase_mallampati"));
                int valoracionMallampati = cursor.getInt(cursor.getColumnIndex("valoracion_mallampati"));
                String justMallampati = cursor.getString(cursor.getColumnIndex("justificacion_mallampati"));

                revisionPredictorMallampati.setResultado(mallampati);
                revisionPredictorMallampati.setValoracionPredictor(valoracionMallampati);
                revisionPredictorMallampati.setJustificacion(justMallampati);
                cursor.moveToNext();
            }
        }

        return revisionPredictorMallampati;

    }
    public resultadoPredictorGradoMovilidad darRevisionPredictorGrado(paciente p){
        Cursor cursor = bd.darRevisionPredictores(p.getId());
        revisionPredictorGradoMovilidad = new resultadoPredictorGradoMovilidad();
        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {

                String movilidad = cursor.getString(cursor.getColumnIndex("grado_movilidad"));
                int valoracionRango = cursor.getInt(cursor.getColumnIndex("valoracion_grado"));
                String justMovilidad = cursor.getString(cursor.getColumnIndex("justificacion_rango"));

                revisionPredictorGradoMovilidad.setResultado(movilidad);
                revisionPredictorGradoMovilidad.setValoracionPredictor(valoracionRango);
                revisionPredictorGradoMovilidad.setJustificacion(justMovilidad);
                cursor.moveToNext();
            }
        }

        return revisionPredictorGradoMovilidad;

    }
    public resultadoPredictorSubMandibular darRevisionPredictorSubMandibular(paciente p){
        Cursor cursor = bd.darRevisionPredictores(p.getId());
        revisionPredictorSubMandibular = new resultadoPredictorSubMandibular();
        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {

                String subluxacion = cursor.getString(cursor.getColumnIndex("clase_subluxacion"));
                int valoracionSubluxacion = cursor.getInt(cursor.getColumnIndex("valoracion_subluxacion"));
                String justSubluxacion = cursor.getString(cursor.getColumnIndex("justificacion_subluxacion"));

                revisionPredictorSubMandibular.setResultado(subluxacion);
                revisionPredictorSubMandibular.setValoracionPredictor(valoracionSubluxacion);
                revisionPredictorSubMandibular.setJustificacion(justSubluxacion);
                cursor.moveToNext();
            }
        }

        return revisionPredictorSubMandibular;

    }
    public resultadoPredictorDtm darRevisionPredictorDtm(paciente p){
        Cursor cursor = bd.darRevisionPredictores(p.getId());
        revisionPredictorDtm = new resultadoPredictorDtm();
        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {

                String dtm = cursor.getString(cursor.getColumnIndex("clase_dtm"));
                int valoracionDtm = cursor.getInt(cursor.getColumnIndex("valoracion_dtm"));
                String justDtm = cursor.getString(cursor.getColumnIndex("justificacion_dtm"));

                revisionPredictorDtm.setResultado(dtm);
                revisionPredictorDtm.setValoracionPredictor(valoracionDtm);
                revisionPredictorDtm.setJustificacion(justDtm);
                cursor.moveToNext();
            }
        }

        return revisionPredictorDtm;

    }
    public resultadoPredictorAperturaBucal darRevisionPredictorApertura(paciente p){
        Cursor cursor = bd.darRevisionPredictores(p.getId());
        revisionPredictorAperturaBucal = new resultadoPredictorAperturaBucal();
        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {

                String apertura = cursor.getString(cursor.getColumnIndex("grado_apertura"));
                int valoracionApertura =  cursor.getInt(cursor.getColumnIndex("valoracion_apertura"));
                String justApertura = cursor.getString(cursor.getColumnIndex("justificacion_apertura"));

                revisionPredictorAperturaBucal.setResultado(apertura);
                revisionPredictorAperturaBucal.setValoracionPredictor(valoracionApertura);
                revisionPredictorAperturaBucal.setJustificacion(justApertura);
                cursor.moveToNext();
            }
        }

        return revisionPredictorAperturaBucal;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()){

            case R.id.delete_paciente:{
                bd.borrarPaciente(p.getId());
                return true;
            }
            default:
            return super.onOptionsItemSelected(item);
    }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle("¿Está seguro que desea eliminar al paciente?" + info.position);
        inflater.inflate(R.menu.context_menu,menu);
    }
}
