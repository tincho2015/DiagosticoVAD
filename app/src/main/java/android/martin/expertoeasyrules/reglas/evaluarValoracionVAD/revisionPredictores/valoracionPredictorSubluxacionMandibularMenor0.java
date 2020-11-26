package android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores;

import android.martin.expertoeasyrules.hechos.resultadoPredictor;
import android.martin.expertoeasyrules.hechos.resultadoPredictorSubMandibular;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;


@Rule(name = "R_35",description = "Determinar valoración de la revision de los predictores",priority = 15)
public class valoracionPredictorSubluxacionMandibularMenor0 {

    @Condition
    public boolean when(Facts facts){

        resultadoPredictorSubMandibular resultado = facts.get("resultadoPredictorSubMandibular");
        return !resultado.isValorizado() && resultado.getResultado().equals("Menor a 0");

    }
    @Action
    public void then(Facts facts) throws Exception{

        resultadoPredictorSubMandibular resultado = facts.get("resultadoPredictorSubMandibular");
        resultado.setValoracionPredictor(13);
        resultado.setValorizado(true);
        facts.put("resultadoPredictorSubMandibular",resultado);
    }
}
