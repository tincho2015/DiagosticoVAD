package android.martin.expertoeasyrules.reglas.evaluarCriteriosVentilacionDificil;


import android.martin.expertoeasyrules.hechos.criterioVentilacionDificil;
import android.martin.expertoeasyrules.hechos.patologiaFactorAsociada;
import android.martin.expertoeasyrules.hechos.revisionPatologiaFactor;
import android.martin.expertoeasyrules.hechos.revisionVentilacionDificil;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

@Rule(name = "R_35",description = "El paciente tuvo ancedentes de obstrucción en la VA",priority = 1)
public class presentaCriterioVentilacionDificil {

    @Condition
    public boolean when(Facts facts){

        criterioVentilacionDificil presente = facts.get("criterios");
        return presente.isPresente() && !presente.isRevisado();


    }
    @Action
    public void then(Facts facts) throws Exception{

        criterioVentilacionDificil criterio= facts.get("criterios");
        String descripcion = criterio.getDescripcion();
        revisionVentilacionDificil revision = facts.get("revisionVentilacionDificil");
        revision.getFactoresVD().add(descripcion);
        criterio.setRevisado(true);
        facts.put("criterios",criterio);
    }
}
