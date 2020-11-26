package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class revisionPatologiaFactor implements Parcelable {

    private int id;
    private ArrayList<String> patologias;
    private ArrayList<String>factores;
    private int valoracionPatologias;
    private int valoracionFactor;
    private boolean patologia;
    private boolean factor;


    public revisionPatologiaFactor() {
        id = 0;
        this.patologias = new ArrayList<>();
        this.factores = new ArrayList<>();
        this.valoracionPatologias = 0;
        this.valoracionFactor = 0;
        this.patologia = false;
        this.factor = false;
    }

    public int getValoracionFactor() {
        return valoracionFactor;
    }

    public void setValoracionFactor(int valoracionFactor) {
        this.valoracionFactor = valoracionFactor;
    }

    protected revisionPatologiaFactor(Parcel in) {
        id = in.readInt();
        patologias = in.createStringArrayList();
        factores = in.createStringArrayList();
        valoracionPatologias = in.readInt();
    }

    public boolean isPatologia() {
        return patologia;
    }

    public void setPatologia(boolean patologia) {
        this.patologia = patologia;
    }

    public boolean isFactor() {
        return factor;
    }

    public void setFactor(boolean factor) {
        this.factor = factor;
    }

    public static final Creator<revisionPatologiaFactor> CREATOR = new Creator<revisionPatologiaFactor>() {
        @Override
        public revisionPatologiaFactor createFromParcel(Parcel in) {
            return new revisionPatologiaFactor(in);
        }

        @Override
        public revisionPatologiaFactor[] newArray(int size) {
            return new revisionPatologiaFactor[size];
        }
    };

    public int getValoracionPatologias() {
        return valoracionPatologias;
    }

    public void setValoracionPatologias(int valoracionPatologias) {
        this.valoracionPatologias = valoracionPatologias;
    }

    public ArrayList<String> getPatologias() {
        return patologias;
    }

    public void setPatologias(ArrayList<String> patologias) {
        this.patologias = patologias;
    }

    public ArrayList<String> getFactores() {
        return factores;
    }

    public void setFactores(ArrayList<String> factores) {
        this.factores = factores;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeStringList(patologias);
        dest.writeStringList(factores);
        dest.writeInt(valoracionPatologias);
    }
}
