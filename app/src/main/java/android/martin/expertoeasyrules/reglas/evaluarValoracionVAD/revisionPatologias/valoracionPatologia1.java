package android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPatologias;

import android.martin.expertoeasyrules.hechos.diagnosticoFinal;
import android.martin.expertoeasyrules.hechos.resultadoPredictor;
import android.martin.expertoeasyrules.hechos.revisionObstruccionVA;
import android.martin.expertoeasyrules.hechos.revisionPatologiaFactor;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;


@Rule(name = "R_71",description = "Determinar valoración de vía aérea dificil ")
public class valoracionPatologia1 {

    @Condition
    public boolean when(Facts facts){

        revisionPatologiaFactor revision = facts.get("");
        return !revision.isPatologia() && revision.getPatologias().size() > 0;

    }
    @Action
    public void then(Facts facts) throws Exception{

        revisionPatologiaFactor revision = facts.get("");
        revision.setValoracionPatologias(5);
        revision.setPatologia(true);
        facts.put("","");
    }
}
