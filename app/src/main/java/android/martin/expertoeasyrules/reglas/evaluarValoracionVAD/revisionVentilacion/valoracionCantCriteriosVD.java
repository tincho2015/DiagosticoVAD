package android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionVentilacion;

import android.martin.expertoeasyrules.hechos.revisionVentilacionDificil;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;


@Rule(name = "R_75",description = "Determinar valoración de vía aérea dificil ",priority = 1)
public class valoracionCantCriteriosVD {

    @Condition
    public boolean when(Facts facts){

        revisionVentilacionDificil revision = facts.get("revisionVentilacionDificil");
        return !revision.isValorizado() && revision.getFactoresVD().size() >= 2;


    }
    @Action
    public void then(Facts facts) throws Exception{

        revisionVentilacionDificil revision = facts.get("revisionVentilacionDificil");
        revision.setValoracionVentilacion(3);
        revision.setValorizado(true);
        facts.put("revisionVentilacionDificil",revision);
    }
}
