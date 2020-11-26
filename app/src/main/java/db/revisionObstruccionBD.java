package db;

import android.provider.BaseColumns;

public class revisionObstruccionBD {


    public static abstract class revisionObstruccionEntry implements BaseColumns {

        public static final String TABLE_NAME = "revisionObstruccion";
        public static final String ID = "id";
        public static final String ID_PACIENTE = "id_paciente";
        public static final String DESCRIPCION = "descripcion_sintoma";
        public static final String VALORACION = "valoracion";
    }


}
