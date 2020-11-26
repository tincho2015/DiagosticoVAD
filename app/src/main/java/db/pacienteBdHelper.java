package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.martin.expertoeasyrules.hechos.paciente;

public class pacienteBdHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 8;
    public static final String DATABASE_NAME = "pacientes.db";


    public pacienteBdHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db= this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + pacienteBd.pacienteEntry.TABLE_NAME + " ("
                + pacienteBd.pacienteEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + pacienteBd.pacienteEntry.NOMBRE + " TEXT NOT NULL,"
                + pacienteBd.pacienteEntry.SEXO + " TEXT NOT NULL,"
                + pacienteBd.pacienteEntry.EDAD + " INTEGER NOT NULL,"
                + "UNIQUE (" + pacienteBd.pacienteEntry.ID + "))");

        db.execSQL("CREATE TABLE " + revisionObstruccionBD.revisionObstruccionEntry.TABLE_NAME + " ("
        + revisionObstruccionBD.revisionObstruccionEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        + revisionObstruccionBD.revisionObstruccionEntry.DESCRIPCION + " TEXT, "
        + revisionObstruccionBD.revisionObstruccionEntry.ID_PACIENTE + " INTEGER, "
        + revisionObstruccionBD.revisionObstruccionEntry.VALORACION + " INTEGER, "
        + "UNIQUE (" + revisionObstruccionBD.revisionObstruccionEntry.ID + "))");

        db.execSQL("CREATE TABLE " + revisionVentilacionDificilBD.revisionVentilacionEntry.TABLE_NAME + " ("
                + revisionVentilacionDificilBD.revisionVentilacionEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + revisionVentilacionDificilBD.revisionVentilacionEntry.DESCRIPCION + " TEXT, "
                + revisionVentilacionDificilBD.revisionVentilacionEntry.ID_PACIENTE + " INTEGER, "
                + revisionVentilacionDificilBD.revisionVentilacionEntry.VALORACION + " INTEGER, "
                + "UNIQUE (" + revisionVentilacionDificilBD.revisionVentilacionEntry.ID + "))");

        db.execSQL("CREATE TABLE " + revisionPatologiaFactorBD.revisionPatologiaEntry.TABLE_NAME + " ("
                + revisionPatologiaFactorBD.revisionPatologiaEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + revisionPatologiaFactorBD.revisionPatologiaEntry.DESCRIPCION_PATOLOGIA + " TEXT, "
                + revisionPatologiaFactorBD.revisionPatologiaEntry.DESCRIPCION_FACTOR + " TEXT, "
                + revisionPatologiaFactorBD.revisionPatologiaEntry.ID_PACIENTE + " INTEGER, "
                + revisionPatologiaFactorBD.revisionPatologiaEntry.VALORACION_PATOLOGIA + " INTEGER, "
                + revisionPatologiaFactorBD.revisionPatologiaEntry.VALORACION_FACTOR + " INTEGER, "
                + "UNIQUE (" + revisionPatologiaFactorBD.revisionPatologiaEntry.ID + "))");

        db.execSQL("CREATE TABLE " + revisionPredictoresBD.revisionPredictoresEntry.TABLE_NAME + " ("
                + revisionPredictoresBD.revisionPredictoresEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + revisionPredictoresBD.revisionPredictoresEntry.CLASE_MALLAMPATI + " TEXT, "
                + revisionPredictoresBD.revisionPredictoresEntry.CLASE_DTM + " TEXT , "
                + revisionPredictoresBD.revisionPredictoresEntry.CLASE_SUBLUXACION + " TEXT , "
                + revisionPredictoresBD.revisionPredictoresEntry.GRADO_APERTURA + " TEXT , "
                + revisionPredictoresBD.revisionPredictoresEntry.RANGO_MOVILIDAD + " TEXT , "
                + revisionPredictoresBD.revisionPredictoresEntry.ID_PACIENTE + " INTEGER, "
                + revisionPredictoresBD.revisionPredictoresEntry.VALORACION_MALLAMPATI + " INTEGER, "
                + revisionPredictoresBD.revisionPredictoresEntry.VALORACION_DTM + " INTEGER, "
                + revisionPredictoresBD.revisionPredictoresEntry.VALORACION_APERTURA + " INTEGER, "
                + revisionPredictoresBD.revisionPredictoresEntry.VALORACION_SUBLUXACION + " INTEGER, "
                + revisionPredictoresBD.revisionPredictoresEntry.VALORACION_RANGO + " INTEGER, "
                + revisionPredictoresBD.revisionPredictoresEntry.JUSTIFICACION_MALLAMPATI + " TEXT, "
                + revisionPredictoresBD.revisionPredictoresEntry.JUSTIFICACION_APERTURA + " TEXT, "
                + revisionPredictoresBD.revisionPredictoresEntry.JUSTIFICACION_DTM + " TEXT, "
                + revisionPredictoresBD.revisionPredictoresEntry.JUSTIFICACION_RANGO + " TEXT, "
                + revisionPredictoresBD.revisionPredictoresEntry.JUSTIFICACION_SUBLUXACION + " TEXT, "
                + "UNIQUE (" + revisionPredictoresBD.revisionPredictoresEntry.ID + "))");

    }
    public void borrarRevision(){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(revisionObstruccionBD.revisionObstruccionEntry.TABLE_NAME,"",null);
    }
    public void borrarRevisionVentilacion(){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(revisionVentilacionDificilBD.revisionVentilacionEntry.TABLE_NAME,"",null);
    }
    public void borrarRevisionPatologia(){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(revisionPatologiaFactorBD.revisionPatologiaEntry.TABLE_NAME,"",null);
    }
    public void borrarRevisionPredictores(){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(revisionPredictoresBD.revisionPredictoresEntry.TABLE_NAME,"",null);
    }
    public void borrarPaciente(int id){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(pacienteBd.pacienteEntry.TABLE_NAME,"id = ?",new String[]{String.valueOf(id)});
    }
    public boolean actualizarRevisionObstruccion(String descripcion,paciente p){
        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(revisionObstruccionBD.revisionObstruccionEntry.DESCRIPCION,descripcion);

        long result = db.update(revisionObstruccionBD.revisionObstruccionEntry.TABLE_NAME,values,revisionObstruccionBD.revisionObstruccionEntry.ID_PACIENTE + "= ?",new String[] { String.valueOf(p.getId())});

        if(result == -1){
            return false;
        }else{

            return true;
        }

    }
    public boolean insertarRevisionObstruccion(String descripcion,int idPaciente,int valoracion){

        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(revisionObstruccionBD.revisionObstruccionEntry.DESCRIPCION, descripcion);
        values.put(revisionObstruccionBD.revisionObstruccionEntry.ID_PACIENTE, idPaciente);
        values.put(revisionObstruccionBD.revisionObstruccionEntry.VALORACION,valoracion);

        long result = db.insert(revisionObstruccionBD.revisionObstruccionEntry.TABLE_NAME,null,values);

        if( result == -1 ){

            return false;

        }
        return true;

    }
    public boolean insertarRevisionPatologia(String descripcion,int idPaciente,int valoracion){

        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(revisionPatologiaFactorBD.revisionPatologiaEntry.DESCRIPCION_PATOLOGIA, descripcion);
        values.put(revisionPatologiaFactorBD.revisionPatologiaEntry.ID_PACIENTE, idPaciente);
        values.put(revisionPatologiaFactorBD.revisionPatologiaEntry.VALORACION_PATOLOGIA,valoracion);

        long result = db.insert(revisionPatologiaFactorBD.revisionPatologiaEntry.TABLE_NAME,null,values);

        if( result == -1 ){

            return false;

        }
        return true;
    }
    public boolean insertarRevisionFactor(String factor, int idPaciente, int valoracion){

        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(revisionPatologiaFactorBD.revisionPatologiaEntry.DESCRIPCION_FACTOR,factor);
        values.put(revisionPatologiaFactorBD.revisionPatologiaEntry.ID_PACIENTE, idPaciente);
        values.put(revisionPatologiaFactorBD.revisionPatologiaEntry.VALORACION_FACTOR,valoracion);

        long result = db.insert(revisionPatologiaFactorBD.revisionPatologiaEntry.TABLE_NAME,null,values);

        if( result == -1 ){

            return false;

        }
        return true;
    }
    public boolean insertarRevisionVentilacion(String descripcion,int idPaciente,int valoracion){

        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(revisionVentilacionDificilBD.revisionVentilacionEntry.DESCRIPCION, descripcion);
        values.put(revisionVentilacionDificilBD.revisionVentilacionEntry.ID_PACIENTE, idPaciente);
        values.put(revisionVentilacionDificilBD.revisionVentilacionEntry.VALORACION, valoracion);

        long result = db.insert(revisionVentilacionDificilBD.revisionVentilacionEntry.TABLE_NAME,null,values);

        if( result == -1 ){

            return false;

        }
        return true;
    }
    public boolean insertarRevisionPredictores(String claseMallampati,String gradoMovilidad, String claseSubluxacion, String gradoApertura,String claseDtm,int idPaciente, int valoracionMallampati,int valoracionSubluxacion,int valoracionRango, int valoracionDtm,int valoracionApertura,String justMallampati,String justApertura,String justDtm, String justMovilidad,String justSubluxacion){

        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(revisionPredictoresBD.revisionPredictoresEntry.CLASE_MALLAMPATI, claseMallampati);
        values.put(revisionPredictoresBD.revisionPredictoresEntry.RANGO_MOVILIDAD, gradoMovilidad);
        values.put(revisionPredictoresBD.revisionPredictoresEntry.CLASE_SUBLUXACION, claseSubluxacion);
        values.put(revisionPredictoresBD.revisionPredictoresEntry.GRADO_APERTURA, gradoApertura);
        values.put(revisionPredictoresBD.revisionPredictoresEntry.CLASE_DTM, claseDtm);
        values.put(revisionPredictoresBD.revisionPredictoresEntry.ID_PACIENTE, idPaciente);
        values.put(revisionPredictoresBD.revisionPredictoresEntry.VALORACION_MALLAMPATI,valoracionMallampati);
        values.put(revisionPredictoresBD.revisionPredictoresEntry.VALORACION_SUBLUXACION,valoracionSubluxacion);
        values.put(revisionPredictoresBD.revisionPredictoresEntry.VALORACION_RANGO,valoracionRango);
        values.put(revisionPredictoresBD.revisionPredictoresEntry.VALORACION_DTM,valoracionDtm);
        values.put(revisionPredictoresBD.revisionPredictoresEntry.VALORACION_APERTURA,valoracionApertura);
        values.put(revisionPredictoresBD.revisionPredictoresEntry.JUSTIFICACION_MALLAMPATI,justMallampati);
        values.put(revisionPredictoresBD.revisionPredictoresEntry.JUSTIFICACION_APERTURA,justApertura);
        values.put(revisionPredictoresBD.revisionPredictoresEntry.JUSTIFICACION_DTM,justDtm);
        values.put(revisionPredictoresBD.revisionPredictoresEntry.JUSTIFICACION_RANGO,justMovilidad);
        values.put(revisionPredictoresBD.revisionPredictoresEntry.JUSTIFICACION_SUBLUXACION,justSubluxacion);

        long result = db.insert(revisionPredictoresBD.revisionPredictoresEntry.TABLE_NAME,null,values);

        if( result == -1 ){

            return false;

        }
        return true;
    }

    public boolean insertarPaciente(String nombre, String sexo, String edad){

        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // Pares clave-valor
        values.put(pacienteBd.pacienteEntry.NOMBRE, nombre);
        values.put(pacienteBd.pacienteEntry.SEXO, sexo);
        values.put(pacienteBd.pacienteEntry.EDAD, edad);

        long result = db.insert(pacienteBd.pacienteEntry.TABLE_NAME,null,values);

        if( result == -1 ){

            return false;

        }
        return true;

    }
    public Cursor darDatos(){

        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ pacienteBd.pacienteEntry.TABLE_NAME,null);
        return  res;

    }
    public Cursor darRevisionObstruccion(int idpaciente){

        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ revisionObstruccionBD.revisionObstruccionEntry.TABLE_NAME + " WHERE " + revisionObstruccionBD.revisionObstruccionEntry.ID_PACIENTE + " = " + idpaciente,null );
        return  res;

    }
    public Cursor darRevisionPatologia (int idpaciente){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ revisionPatologiaFactorBD.revisionPatologiaEntry.TABLE_NAME + " WHERE " + revisionPatologiaFactorBD.revisionPatologiaEntry.ID_PACIENTE + " = " + idpaciente,null );
        return  res;
    }
    public Cursor darRevisionVentilacion(int idpaciente){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ revisionVentilacionDificilBD.revisionVentilacionEntry.TABLE_NAME + " WHERE " + revisionVentilacionDificilBD.revisionVentilacionEntry.ID_PACIENTE + " = " + idpaciente,null );
        return  res;
    }
    public Cursor darRevisionPredictores(int idpaciente){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ revisionPredictoresBD.revisionPredictoresEntry.TABLE_NAME + " WHERE " + revisionPredictoresBD.revisionPredictoresEntry.ID_PACIENTE + " = " + idpaciente,null );
        return  res;
    }
    /*public boolean actualizarDatos(String id, String nombre, String sexo, String edad){

        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(pacienteBd.pacienteEntry.ID, id);
        values.put(pacienteBd.pacienteEntry.NOMBRE, nombre);
        values.put(pacienteBd.pacienteEntry.SEXO, sexo);
        values.put(pacienteBd.pacienteEntry.EDAD, edad);

        db.update(pacienteBd.pacienteEntry.TABLE_NAME,values,"id = ?",new String[]{id});
        return  true;


    }*/
//    public long savePaciente(paciente p) {
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//
//        return sqLiteDatabase.insert(
//                pacienteBd.pacienteEntry.TABLE_NAME,
//                null,
//                p.toContentValues());
//
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ pacienteBd.pacienteEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + revisionObstruccionBD.revisionObstruccionEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + revisionPredictoresBD.revisionPredictoresEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + revisionVentilacionDificilBD.revisionVentilacionEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + revisionPatologiaFactorBD.revisionPatologiaEntry.TABLE_NAME);
        onCreate(db);


    }
}
