package android.martin.expertoeasyrules.reglas.pruebasPredictoras;

import android.martin.expertoeasyrules.hechos.predictor;
import android.martin.expertoeasyrules.hechos.predictorDTM;
import android.martin.expertoeasyrules.hechos.resultadoPredictor;
import android.martin.expertoeasyrules.hechos.resultadoPredictorDtm;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

@Rule(name = "R_23", description = "Clasificar DTM del paciente",priority = 6)
public class DtmClase1 {

    @Condition
    public boolean when(Facts facts){

        predictorDTM p = facts.get("predictorDtm");
        return !p.isRevisado() && p.getPredictor().equals("Mayor o igual a 6,5 cm");

    }
    @Action
    public void then(Facts facts) throws Exception{

        predictorDTM p= facts.get("predictorDtm");
        resultadoPredictorDtm resultado = facts.get("resultadoPredictorDtm");
        resultado.setResultado("Mayor o igual a 6,5 cm");
        resultado.setJustificacion("La distancia tiromentoniana es mayor o igual a los 6,5 cm");
        p.setRevisado(true);
        facts.put("predictorDtm",p);
    }


}
