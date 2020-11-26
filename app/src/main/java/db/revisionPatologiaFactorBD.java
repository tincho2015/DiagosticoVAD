package db;

import android.provider.BaseColumns;

public class revisionPatologiaFactorBD {


    public static abstract class revisionPatologiaEntry implements BaseColumns {

        public static final String TABLE_NAME = "revisionPatologia";

        public static final String ID = "id";
        public static final String ID_PACIENTE = "id_paciente";
        public static final String DESCRIPCION_PATOLOGIA = "descripcion_patologia";
        public static final String DESCRIPCION_FACTOR = "descripcion_factor";
        public static final String VALORACION_PATOLOGIA = "valoracion_patologia";
        public static final String VALORACION_FACTOR = "valoracion_factor";
    }
}
