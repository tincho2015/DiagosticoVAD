package android.martin.expertoeasyrules.reglas.pruebasPredictoras;


import android.martin.expertoeasyrules.hechos.predictor;
import android.martin.expertoeasyrules.hechos.predictorGradoMovilidad;
import android.martin.expertoeasyrules.hechos.resultadoPredictor;
import android.martin.expertoeasyrules.hechos.resultadoPredictorGradoMovilidad;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

@Rule(name = "R_19",description = "Clasificar el grado de movilidad cervical del paciente",priority = 11)
public class MovilidadCervicalGrado2 {

    @Condition
    public boolean when(Facts facts){

        predictorGradoMovilidad p = facts.get("predictorGradoMovilidad");
        return !p.isRevisado() && p.getPredictor().equals("Mov.ligeramente disminuido y ligero dolor/parestesia");

    }
    @Action
    public void then(Facts facts) throws Exception{

        predictorGradoMovilidad p= facts.get("predictorGradoMovilidad");
        resultadoPredictorGradoMovilidad resultado = facts.get("resultadoPredictorGradoMovilidad");
        resultado.setResultado("Mov.ligeramente disminuido y ligero dolor/parestesia");
        resultado.setJustificacion("Los dos dedos índices quedan situados en el mismo plano");
        p.setRevisado(true);
        facts.put("predictorGradoMovilidad",p);
    }
}
