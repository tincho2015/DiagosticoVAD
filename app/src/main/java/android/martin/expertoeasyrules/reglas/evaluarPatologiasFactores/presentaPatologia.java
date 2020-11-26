package android.martin.expertoeasyrules.reglas.evaluarPatologiasFactores;


import android.martin.expertoeasyrules.hechos.patologiaFactorAsociada;
import android.martin.expertoeasyrules.hechos.revisionObstruccionVA;
import android.martin.expertoeasyrules.hechos.revisionPatologiaFactor;
import android.martin.expertoeasyrules.hechos.sintomaObstruccionVA;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

@Rule(name = "R_35",description = "El paciente tuvo ancedentes de obstrucción en la VA",priority = 2)
public class presentaPatologia {

    @Condition
    public boolean when(Facts facts){

        patologiaFactorAsociada presente = facts.get("patologiasFactores");
        return presente.isPresenta() && !presente.isEsFactor() && !presente.isRevisado();


    }
    @Action
    public void then(Facts facts) throws Exception{

        patologiaFactorAsociada patologia= facts.get("patologiasFactores");
        String descripcion = patologia.getDescripcion();
        revisionPatologiaFactor revision = facts.get("revisionPatologia");
        revision.getPatologias().add(descripcion);
        patologia.setRevisado(true);
        facts.put("patologiasFactores",patologia);
    }
}
