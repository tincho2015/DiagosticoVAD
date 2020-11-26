package android.martin.expertoeasyrules.reglas.darDiagnosticoFinal;


import android.martin.expertoeasyrules.hechos.diagnosticoFinal;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

@Rule(name = "R_77",description = "Determina si el paciente presenta vía aérea difícil",priority = 1)
public class pacienteNoPresentaVAD {

    @Condition
    public boolean when(Facts facts){

        diagnosticoFinal diag = facts.get("diagnosticoFinal");
        return !diag.isRevisado() && !diag.isPresentaVAD() && diag.getValoracion() < 11;

    }
    @Action
    public void then(Facts facts) throws Exception{

        diagnosticoFinal diag = facts.get("diagnosticoFinal");
        diag.setPresentaVAD(false);
        diag.setRevisado(true);
        diag.setDescripcion("No presenta una vía aérea dificultosa");
        facts.put("diagnosticoFinal",diag);
    }

}
