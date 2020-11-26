package android.martin.expertoeasyrules.reglas.pruebasPredictoras;

import android.martin.expertoeasyrules.hechos.predictor;
import android.martin.expertoeasyrules.hechos.predictorMallampati;
import android.martin.expertoeasyrules.hechos.resultadoPredictor;
import android.martin.expertoeasyrules.hechos.resultadoPredictorMallampati;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

@Rule(name = "R_27", description = "Clasificar la clase de Mallanpati del paciente",priority = 2)
public class MallanpatiClase2 {

    @Condition
    public boolean when(Facts facts){

        predictorMallampati p = facts.get("predictorMallampati");
        return !p.isRevisado() && p.getPredictor().equals("Completa") && !p.isVePilarAmigdalino() && p.isPaladar();

    }
    @Action
    public void then(Facts facts) throws Exception{

        predictorMallampati p= facts.get("predictorMallampati");
        resultadoPredictorMallampati resultado = facts.get("resultadoPredictorMallampati");
        resultado.setResultado("Clase 2");
        resultado.setJustificacion("Visión completa de la úvula y paladar blando, pero no de los pilares amigdalinos");
        p.setRevisado(true);
        facts.put("predictorMallampati",p);
    }
}
