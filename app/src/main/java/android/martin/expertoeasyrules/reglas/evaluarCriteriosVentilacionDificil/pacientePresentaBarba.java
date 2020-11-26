package android.martin.expertoeasyrules.reglas.evaluarCriteriosVentilacionDificil;


import android.martin.expertoeasyrules.hechos.criterioVentilacionDificil;
import android.martin.expertoeasyrules.hechos.predictor;
import android.martin.expertoeasyrules.hechos.resultadoPredictor;
import android.martin.expertoeasyrules.hechos.revisionVentilacionDificil;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

@Rule(name = "R_15",description = "El paciente pesenta barba")
public class pacientePresentaBarba {

    @Condition
    public boolean when(Facts facts){

        criterioVentilacionDificil presente = facts.get("criterios");
        return presente.getDescripcion().equals("Barba") && presente.isPresente() && !presente.isRevisado();


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
