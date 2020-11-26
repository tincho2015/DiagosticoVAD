package android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPatologias;

import android.martin.expertoeasyrules.hechos.revisionPatologiaFactor;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;


@Rule(name = "R_71",description = "Determinar valoración de vía aérea dificil ",priority = 4)
public class valoracionPatologia {

    @Condition
    public boolean when(Facts facts){

        revisionPatologiaFactor revision = facts.get("revisionPatologia");
        return !revision.isPatologia() && revision.getPatologias().size() > 0;

    }
    @Action
    public void then(Facts facts) throws Exception{

        revisionPatologiaFactor revision = facts.get("revisionPatologia");
        revision.setValoracionPatologias(5);
        revision.setPatologia(true);
        facts.put("revisionPatologia",revision);
    }
}
