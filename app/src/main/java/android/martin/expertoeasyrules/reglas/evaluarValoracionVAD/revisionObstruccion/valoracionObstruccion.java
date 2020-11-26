package android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionObstruccion;

import android.martin.expertoeasyrules.hechos.diagnosticoFinal;
import android.martin.expertoeasyrules.hechos.resultadoPredictor;
import android.martin.expertoeasyrules.hechos.revisionObstruccionVA;
import android.martin.expertoeasyrules.hechos.revisionPatologiaFactor;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;


@Rule(name = "R_74",description = "Determinar valoración de vía aérea dificil ")
public class valoracionObstruccion {

    @Condition
    public boolean when(@Fact("revisionSintomas") revisionObstruccionVA revisionSintoma){

        return revisionSintoma.getSintomas().contains("Disfagia") ||
                revisionSintoma.getSintomas().contains("Disfonia") ||
                revisionSintoma.getSintomas().contains("Disnea") ||
                revisionSintoma.getSintomas().contains("Estridor") ||
                revisionSintoma.getSintomas().contains("AntecedenteObstruccion");


    }
    @Action
    public void then(@Fact("revisionSintomas") revisionObstruccionVA revisionSintoma) throws Exception{

        revisionSintoma.setValoracionObstruccion(3);
    }
}
