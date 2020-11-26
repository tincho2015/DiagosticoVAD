package android.martin.expertoeasyrules.reglas.darDiagnosticoFinal;


import android.martin.expertoeasyrules.hechos.diagnosticoFinal;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

@Rule(name = "R_77",description = "Determina si el paciente presenta vía aérea difícil",priority = 2)
public class pacientePresentaVAD {

    @Condition
    public boolean when(Facts facts){

        diagnosticoFinal diag = facts.get("diagnosticoFinal");
        return !diag.isRevisado() && diag.isPresentaVAD() && diag.getValoracion() >= 11;

    }
    @Action
    public void then(Facts facts) throws Exception{

        diagnosticoFinal diag = facts.get("diagnosticoFinal");
        diag.setPresentaVAD(true);
        diag.setRevisado(true);
        diag.setDescripcion("Vía aérea dificultosa");
        facts.put("diagnosticoFinal",diag);
    }

}
