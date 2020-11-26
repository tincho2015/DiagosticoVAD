package android.martin.expertoeasyrules.reglas.evaluarValoracionVAD.revisionPatologias;

import android.martin.expertoeasyrules.hechos.revisionPatologiaFactor;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;


@Rule(name = "R_72",description = "Determinar valoración de vía aérea dificil ")
public class valoracionPatologia2 {

    @Condition
    public boolean when(@Fact("revisionPatologia") revisionPatologiaFactor revisionPatologia){

        return revisionPatologia.getFactores().contains("AntecedenteViaAereaDificil") &&
                (!revisionPatologia.getPatologias().contains("AnginaLudwig") ||
                !revisionPatologia.getPatologias().contains("HipertrofiaAmigdalarLingual") ||
                !revisionPatologia.getPatologias().contains("LesionMandibular") ||
                !revisionPatologia.getPatologias().contains("LesionRaquisCervical") ||
                !revisionPatologia.getPatologias().contains("LesionViaAerea") ||
                !revisionPatologia.getPatologias().contains("Macroglosia") ||
                !revisionPatologia.getPatologias().contains("MasasTiroideas") ||
                !revisionPatologia.getPatologias().contains("LesionCuelloTorax"));

    }
    @Action
    public void then(@Fact("revisionPatologia") revisionPatologiaFactor revisionPatologia) throws Exception{

        revisionPatologia.setValoracionPatologias(10);
    }
}
