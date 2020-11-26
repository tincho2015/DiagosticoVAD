package android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPatologias;

import android.martin.expertoeasyrules.hechos.revisionPatologiaFactor;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;


@Rule(name = "R_71",description = "Determinar valoración de vía aérea dificil ",priority = 1)
public class valoracionFactor {

    @Condition
    public boolean when(Facts facts){

        revisionPatologiaFactor revision = facts.get("revisionPatologia");
        return !revision.isFactor() && revision.getFactores().contains("AntecedentesViaAereaDificil");

    }
    @Action
    public void then(Facts facts) throws Exception{

        revisionPatologiaFactor revision = facts.get("revisionPatologia");
        revision.setValoracionFactor(10);
        revision.setFactor(true);
        facts.put("revisionPatologia",revision);
    }
}
