package android.martin.expertoeasyrules.activities;

import androidx.appcompat.app.AppCompatActivity;
import db.pacienteBdHelper;

import android.content.Intent;
import android.database.Cursor;
import android.martin.expertoeasyrules.R;
import android.martin.expertoeasyrules.hechos.paciente;
import android.martin.expertoeasyrules.hechos.resultadoPredictor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class resultado_predictores extends AppCompatActivity {

    private TextView viewMallanpati;
    private TextView viewAperturaBucal;
    private TextView viewDTM;
    private TextView viewMovRangoCuello;
    private TextView viewSubluxacion;
    private Button btnMenuPrincipal;

    private resultadoPredictor rcp = null;
    private Bundle b = null;
    private paciente p = null;

    private pacienteBdHelper bd = null;

    private ArrayList<String>predictores = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_prueba_predictores);

        getSupportActionBar().setTitle("Resultados");

        bd = new pacienteBdHelper(this);

        b = new Bundle();
        b = getIntent().getBundleExtra("paciente");

        if(b != null){

            p = b.getParcelable("paciente");
        }

        viewAperturaBucal = findViewById(R.id.txtViewAperturaBucal);
        viewMallanpati = findViewById(R.id.txtViewMallanpati);
        viewDTM = findViewById(R.id.txtViewDTM);
        viewMovRangoCuello = findViewById(R.id.txtViewNivelMovilidad);
        viewSubluxacion = findViewById(R.id.txtViewSubluxacion);
        btnMenuPrincipal = findViewById(R.id.btnVolver);

        viewAperturaBucal.setText("");
        viewDTM.setText("");
        viewMovRangoCuello.setText("");
        viewSubluxacion.setText("");
        viewMallanpati.setText("");

        darPredictores(p);

        for(String predictor: predictores) {

            if(predictor.equals("Clase 1")){
               viewMallanpati.setText(predictor);
            }
            if(predictor.equals("Clase 2")){
                viewMallanpati.setText(predictor);
            }
            if(predictor.equals("Clase 3")){
                viewMallanpati.setText(predictor);
            }
            if(predictor.equals("Clase 4")){
                viewMallanpati.setText(predictor);
            }
            if(predictor.equals("Mov.conservada y sin dolor/parestesia")){
                viewMovRangoCuello.setText(predictor);
            }
            if(predictor.equals("Mov.ligeramente disminuido y ligero dolor/parestesia")){
                viewMovRangoCuello.setText(predictor);
            }
            if(predictor.equals("Restricción severa de la movilidad")){
                viewMovRangoCuello.setText(predictor);
            }
            if(predictor.equals("Mayor a 0")){
                viewSubluxacion.setText(predictor);
            }
            if(predictor.equals("Igual a 0")){
                viewSubluxacion.setText(predictor);
            }
            if(predictor.equals("Menor a 0")){
                viewSubluxacion.setText(predictor);
            }
            if(predictor.equals("Mayor a 6.5")){
                viewDTM.setText(predictor);
            }
            if(predictor.equals("Menor a 6.5")){
                viewDTM.setText(predictor);
            }
            if(predictor.equals("Grado 1")){
                viewAperturaBucal.setText(predictor);
            }
            if(predictor.equals("Grado 2")){
                viewAperturaBucal.setText(predictor);
            }
            if(predictor.equals("Grado 3")){
                viewAperturaBucal.setText(predictor);
            }

        }

        btnMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),menu_principal.class);
                b.putParcelable("paciente",p);
                i.putExtra("paciente",b);
                startActivity(i);
            }
        });

    }

    public ArrayList<String> darPredictores(paciente p) {

        Cursor cursor = bd.darRevisionPredictores(p.getId());
        predictores = new ArrayList<>();
        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {

                String claseMallampati = cursor.getString(cursor.getColumnIndex("clase_mallampati"));
                String clasDtm = cursor.getString(cursor.getColumnIndex("clase_dtm"));
                String gradoMovilidad = cursor.getString(cursor.getColumnIndex("grado_movilidad"));
                String gradoApertura = cursor.getString(cursor.getColumnIndex("grado_apertura"));
                String claseSubluxacion = cursor.getString(cursor.getColumnIndex("clase_subluxacion"));

                predictores.add(claseMallampati);
                predictores.add(clasDtm);
                predictores.add(gradoMovilidad);
                predictores.add(gradoApertura);
                predictores.add(claseSubluxacion);
                cursor.moveToNext();
            }
        }

        return predictores;
    }
}
