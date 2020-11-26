package android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores;

import android.martin.expertoeasyrules.hechos.resultadoPredictor;
import android.martin.expertoeasyrules.hechos.resultadoPredictorMallampati;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;


@Rule(name = "R_35",description = "Determinar valoración de la revision de los predictores",priority = 8)
public class valoracionPredictorMallampatiClase3 {

    @Condition
    public boolean when(Facts facts){

        resultadoPredictorMallampati rp = facts.get("resultadoPredictorMallampati");
        return !rp.isValorizado() && rp.getResultado().equals("Clase 3");

    }
    @Action
    public void then(Facts facts) throws Exception{

        resultadoPredictorMallampati rp = facts.get("resultadoPredictorMallampati");
        rp.setValoracionPredictor(6);
        rp.setValorizado(true);
        facts.put("resultadoPredictorMallampati",rp);
    }
}
