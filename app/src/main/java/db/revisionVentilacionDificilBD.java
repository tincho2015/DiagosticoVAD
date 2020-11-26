package db;

import android.provider.BaseColumns;

public class revisionVentilacionDificilBD {

    public static abstract class revisionVentilacionEntry implements BaseColumns {

        public static final String TABLE_NAME = "revisionVentilacion";
        public static final String ID = "id";
        public static final String ID_PACIENTE = "id_paciente";
        public static final String DESCRIPCION = "descripcion_ventilacion";
        public static final String VALORACION = "valoracion";
    }
}
