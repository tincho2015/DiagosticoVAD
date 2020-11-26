package android.martin.expertoeasyrules.reglas.pruebasPredictoras;


import android.martin.expertoeasyrules.hechos.predictor;
import android.martin.expertoeasyrules.hechos.predictorSubluxacionMandibular;
import android.martin.expertoeasyrules.hechos.resultadoPredictor;
import android.martin.expertoeasyrules.hechos.resultadoPredictorSubMandibular;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

@Rule(name = "R_16", description = "Clasificar clase subluxación mandibular del paciente",priority = 15)
public class SubMandibularClase3 {


    @Condition
    public boolean when(Facts facts){

        predictorSubluxacionMandibular p = facts.get("predictorSubMandibular");
        return !p.isRevisado() && p.getPredictor().equals("Menor a 0");

    }
    @Action
    public void then(Facts facts) throws Exception{

        predictorSubluxacionMandibular p= facts.get("predictorSubMandibular");
        resultadoPredictorSubMandibular resultado = facts.get("resultadoPredictorSubMandibular");
        resultado.setResultado("Menor a 0");
        resultado.setJustificacion("Los incisivos inferiores se quedan por detrás de los superiores");
        p.setRevisado(true);
        facts.put("predictorSubMandibular",p);
    }
}
