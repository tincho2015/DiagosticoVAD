package android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPatologias;

import android.martin.expertoeasyrules.hechos.revisionPatologiaFactor;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;


@Rule(name = "R_71",description = "Determinar valoración de vía aérea dificil ",priority = 3)
public class valoracionNoPatologia {

    @Condition
    public boolean when(Facts facts){

        revisionPatologiaFactor revision = facts.get("revisionPatologia");
        return !revision.isPatologia() && revision.getPatologias().isEmpty();

    }
    @Action
    public void then(Facts facts) throws Exception{

        revisionPatologiaFactor revision = facts.get("revisionPatologia");
        revision.setValoracionPatologias(0);
        revision.setPatologia(true);
        facts.put("revisionPatologia",revision);
    }
}
