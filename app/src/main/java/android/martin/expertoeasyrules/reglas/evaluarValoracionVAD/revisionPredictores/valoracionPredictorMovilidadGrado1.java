package android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPredictores;

import android.martin.expertoeasyrules.hechos.resultadoPredictor;
import android.martin.expertoeasyrules.hechos.resultadoPredictorGradoMovilidad;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;


@Rule(name = "R_35",description = "Determinar valoración de la revision de los predictores",priority = 10)
public class valoracionPredictorMovilidadGrado1 {

    @Condition
    public boolean when(Facts facts){

        resultadoPredictorGradoMovilidad resultado = facts.get("resultadoPredictorGradoMovilidad");
        return !resultado.isValorizado() && resultado.getResultado().equals("Mov.conservada y sin dolor/parestesia");

    }
    @Action
    public void then(Facts facts) throws Exception{

        resultadoPredictorGradoMovilidad resultado = facts.get("resultadoPredictorGradoMovilidad");
        resultado.setValoracionPredictor(0);
        resultado.setValorizado(true);
        facts.put("resultadoPredictorGradoMovilidad",resultado);
    }
}
