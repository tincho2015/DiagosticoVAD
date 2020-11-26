package android.martin.expertoeasyrules.reglas.evaluarPatologiasFactores;

import android.martin.expertoeasyrules.hechos.patologiaFactorAsociada;
import android.martin.expertoeasyrules.hechos.revisionPatologiaFactor;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;


@Rule(name = "R_01",description = "el paciente presenta lesión sobre la vía aérea y el mediastino anterior ")
public class pacientePresentaLesionVA {

    @Condition
    public boolean when(Facts facts){

        patologiaFactorAsociada presente = facts.get("patologiasFactores");
        return presente.getDescripcion().equals("Lesión sobre el cuello y el mediastino") && presente.isPresenta() && presente.isEsFactor() && !presente.isRevisado();


    }
    @Action
    public void then(Facts facts) throws Exception{

        patologiaFactorAsociada patologia= facts.get("patologiasFactores");
        String descripcion = patologia.getDescripcion();
        revisionPatologiaFactor revision = facts.get("revisionPatologia");
        revision.getFactores().add(descripcion);
        patologia.setRevisado(true);
        facts.put("patologiasFactores",patologia);
    }
    }

