package android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores;

import android.martin.expertoeasyrules.hechos.resultadoPredictor;
import android.martin.expertoeasyrules.hechos.resultadoPredictorDtm;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;


@Rule(name = "R_35",description = "Determinar valoración de la revision de los predictores",priority = 4)
public class valoracionPredictorDTMClase1 {

    @Condition
    public boolean when(Facts facts){

        resultadoPredictorDtm resultado = facts.get("resultadoPredictorDtm");
        return !resultado.isValorizado() && resultado.getResultado().equals("Mayor o igual a 6,5 cm");

    }
    @Action
    public void then(Facts facts) throws Exception{

        resultadoPredictorDtm resultado = facts.get("resultadoPredictorDtm");
        resultado.setValoracionPredictor(0);
        resultado.setValorizado(true);
        facts.put("resultadoPredictorDtm",resultado);
    }
}
