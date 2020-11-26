package android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores;

import android.martin.expertoeasyrules.hechos.predictorAperturaBucal;
import android.martin.expertoeasyrules.hechos.resultadoPredictor;
import android.martin.expertoeasyrules.hechos.resultadoPredictorAperturaBucal;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;


@Rule(name = "R_35",description = "Determinar valoración de la revision de los predictores",priority = 1)
public class valoracionPredictorAperturaBucalGrado1 {

    @Condition
    public boolean when(Facts facts){

        resultadoPredictorAperturaBucal resultado = facts.get("resultadoPredictorAperturaBucal");
        return !resultado.isValorizado() && resultado.getResultado().equals("Grado 1");


    }
    @Action
    public void then(Facts facts) throws Exception{

        resultadoPredictorAperturaBucal resultado = facts.get("resultadoPredictorAperturaBucal");
        resultado.setValoracionPredictor(0);
        resultado.setValorizado(true);
        facts.put("resultadoPredictorAperturaBucal",resultado);
    }
}
