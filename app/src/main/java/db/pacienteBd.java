package db;

import android.provider.BaseColumns;

public class pacienteBd {

    public static abstract class pacienteEntry implements BaseColumns{

        public static final String TABLE_NAME = "paciente";

        public static final String ID = "id";
        public static final String NOMBRE = "nombre";
        public static final String SEXO = "sexo";
        public static final String EDAD = "edad";
    }
}
