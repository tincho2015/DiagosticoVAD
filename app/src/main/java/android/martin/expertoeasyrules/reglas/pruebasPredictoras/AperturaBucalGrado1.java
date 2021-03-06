package android.martin.expertoeasyrules.reglas.pruebasPredictoras;

import android.martin.expertoeasyrules.hechos.predictorAperturaBucal;
import android.martin.expertoeasyrules.hechos.resultadoPredictorAperturaBucal;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

@Rule(name = "R_24",description = "Clasificar el grado de apertura bucal del paciente ",priority = 7)
public class AperturaBucalGrado1 {

    @Condition
    public boolean when(Facts facts){

        predictorAperturaBucal p = facts.get("predictorAperturaBocal");
        return !p.isRevisado() && p.getPredictor().equals("Mayor o igual a 5 cm");

    }
    @Action
    public void then(Facts facts) throws Exception{

        predictorAperturaBucal p= facts.get("predictorAperturaBocal");
        resultadoPredictorAperturaBucal resultado = facts.get("resultadoPredictorAperturaBucal");
        resultado.setResultado("Grado 1");
        resultado.setJustificacion("La distancia interincisiva es mayor o igual a los 5 cm");
        p.setRevisado(true);
        facts.put("predictorAperturaBocal",p);

    }

}
