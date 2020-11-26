package android.martin.expertoeasyrules.reglas.pruebasPredictoras;


import android.martin.expertoeasyrules.hechos.predictor;
import android.martin.expertoeasyrules.hechos.predictorGradoMovilidad;
import android.martin.expertoeasyrules.hechos.resultadoPredictor;
import android.martin.expertoeasyrules.hechos.resultadoPredictorGradoMovilidad;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

@Rule(name = "R_19",description = "Clasificar el grado de movilidad cervical del paciente",priority = 10)
public class MovilidadCervicalGrado1 {

    @Condition
    public boolean when(Facts facts){

        predictorGradoMovilidad p = facts.get("predictorGradoMovilidad");
        return !p.isRevisado() && p.getPredictor().equals("Mov.conservada y sin dolor/parestesia");

    }
    @Action
    public void then(Facts facts) throws Exception{

        predictorGradoMovilidad p= facts.get("predictorGradoMovilidad");
        resultadoPredictorGradoMovilidad resultado = facts.get("resultadoPredictorGradoMovilidad");
        resultado.setResultado("Mov.conservada y sin dolor/parestesia");
        resultado.setJustificacion("El dedo índice colocado en el mentón se eleva más que el de la prominencia occipital");
        p.setRevisado(true);
        facts.put("predictorGradoMovilidad",p);
    }
}
