package android.martin.expertoeasyrules.hechos;

import android.content.ContentValues;
import android.martin.expertoeasyrules.app.miAplicacion;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import db.pacienteBd;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class paciente implements Parcelable {

    private int id;
    private String nombre;
    private String sexo;
    private int edad;
    private boolean revisionObstruccion;
    private boolean revisionPatologia;
    private boolean revisionVentilacionDificil;
    private boolean resultadoPredictor;



    public paciente(){

    }
    public paciente(String nombre, String sexo, int edad) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.id = 0;
        this.revisionObstruccion = false;
        this.revisionVentilacionDificil = false;
        this.resultadoPredictor = false;
        this.revisionPatologia = false;

    }

    protected paciente(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        sexo = in.readString();
        edad = in.readInt();
        revisionObstruccion = in.readByte() != 0;
        revisionVentilacionDificil = in.readByte() != 0;
        resultadoPredictor = in.readByte() != 0;
        revisionPatologia = in.readByte() != 0;

    }

    public static final Creator<paciente> CREATOR = new Creator<paciente>() {
        @Override
        public paciente createFromParcel(Parcel in) {
            return new paciente(in);
        }

        @Override
        public paciente[] newArray(int size) {
            return new paciente[size];
        }
    };

    public boolean getRevisionObstruccion() {
        return revisionObstruccion;
    }

    public void setRevisionObstruccion(boolean revisionObstruccion) {
        this.revisionObstruccion = revisionObstruccion;
    }

    public boolean getRevisionPatologia() {
        return revisionPatologia;
    }

    public void setRevisionPatologia(boolean revisionPatologia) {
        this.revisionPatologia = revisionPatologia;
    }

    public boolean getRevisionVentilacionDificil() {
        return revisionVentilacionDificil;
    }

    public void setRevisionVentilacionDificil(boolean revisionVentilacionDificil) {
        this.revisionVentilacionDificil = revisionVentilacionDificil;
    }

    public boolean getResultadoPredictor() {
        return resultadoPredictor;
    }

    public void setResultadoPredictor(boolean resultadoPredictor) {
        this.resultadoPredictor = resultadoPredictor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String toString(){

        return this.getNombre();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(sexo);
        dest.writeInt(edad);
        dest.writeByte((byte) (revisionObstruccion ? 1: 0));
        dest.writeByte((byte) (revisionVentilacionDificil ? 1:0));
        dest.writeByte((byte) (resultadoPredictor ? 1:0));
        dest.writeByte((byte) (revisionPatologia ? 1:0));
    }
}
