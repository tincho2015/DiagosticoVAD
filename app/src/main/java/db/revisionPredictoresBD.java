package db;

import android.provider.BaseColumns;

public class revisionPredictoresBD {

    public static abstract class revisionPredictoresEntry implements BaseColumns {

        public static final String TABLE_NAME = "revisionPredictores";
        public static final String ID = "id";
        public static final String ID_PACIENTE = "id_paciente";
        public static final String CLASE_MALLAMPATI = "clase_mallampati";
        public static final String CLASE_SUBLUXACION = "clase_subluxacion";
        public static final String RANGO_MOVILIDAD = "grado_movilidad";
        public static final String CLASE_DTM = "clase_dtm";
        public static final String GRADO_APERTURA = "grado_apertura";
        public static final String VALORACION_MALLAMPATI = "valoracion_mallampati";
        public static final String VALORACION_SUBLUXACION = "valoracion_subluxacion";
        public static final String VALORACION_RANGO = "valoracion_grado";
        public static final String VALORACION_DTM = "valoracion_dtm";
        public static final String VALORACION_APERTURA ="valoracion_apertura";
        public static final String JUSTIFICACION_MALLAMPATI = "justificacion_mallampati";
        public static final String JUSTIFICACION_SUBLUXACION = "justificacion_subluxacion";
        public static final String JUSTIFICACION_RANGO = "justificacion_rango";
        public static final String JUSTIFICACION_DTM = "justificacion_dtm";
        public static final String JUSTIFICACION_APERTURA = "justificacion_apertura";

    }
}
