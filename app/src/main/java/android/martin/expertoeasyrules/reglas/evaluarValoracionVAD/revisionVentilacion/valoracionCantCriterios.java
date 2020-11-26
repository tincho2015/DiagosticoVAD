package android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionVentilacion;

import android.martin.expertoeasyrules.hechos.revisionObstruccionVA;
import android.martin.expertoeasyrules.hechos.revisionVentilacionDificil;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;


@Rule(name = "R_75",description = "Determinar valoración de vía aérea dificil ")
public class valoracionCantCriterios {

    @Condition
    public boolean when(@Fact("revisionVentilacionDificil") revisionVentilacionDificil revision){

        return revision.getFactoresVD().size() >= 2;


    }
    @Action
    public void then(@Fact("revisionVentilacionDificil") revisionVentilacionDificil revision) throws Exception{

        revision.setValoracionVentilacion(3);
    }
}
