package android.martin.expertoeasyrules.reglas.evaluarSignosObstruccionViaAerea;


import android.martin.expertoeasyrules.hechos.revisionObstruccionVA;
import android.martin.expertoeasyrules.hechos.sintomaObstruccionVA;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

@Rule(name = "R_35",description = "El paciente tuvo ancedentes de obstrucción en la VA",priority = 1)
public class presentaSintoma {

    @Condition
    public boolean when(Facts facts){

        sintomaObstruccionVA presente = facts.get("sintomas");
        return presente.isPresente() && !presente.isRevisado();


    }
    @Action
    public void then(Facts facts) throws Exception{

        sintomaObstruccionVA sintoma= facts.get("sintomas");
        String descripcion = sintoma.getDescripcion();
        revisionObstruccionVA revision = facts.get("revisionSintomas");
        revision.getSintomas().add(descripcion);
        sintoma.setRevisado(true);
        facts.put("sintomas",sintoma);
    }
}
