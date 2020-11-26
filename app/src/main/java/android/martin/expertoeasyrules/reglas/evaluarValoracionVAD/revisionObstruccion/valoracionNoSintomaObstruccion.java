package android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionObstruccion;

import android.martin.expertoeasyrules.hechos.revisionObstruccionVA;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;


@Rule(name = "R_74",description = "Determinar valoración de vía aérea dificil ",priority = 1)
public class valoracionNoSintomaObstruccion {

    @Condition
    public boolean when(Facts facts){

        revisionObstruccionVA revision = facts.get("revisionSintomas");
        return !revision.isValorizado() && revision.getSintomas().isEmpty();

    }
    @Action
    public void then(Facts facts) throws Exception{

        revisionObstruccionVA revision = facts.get("revisionSintomas");
        revision.setValoracionObstruccion(0);
        revision.setValorizado(true);
        facts.put("revisionSintomas",revision);

    }
}
