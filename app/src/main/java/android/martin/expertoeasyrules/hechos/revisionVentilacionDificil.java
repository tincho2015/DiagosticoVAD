package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class revisionVentilacionDificil implements Parcelable {

    private int id;
    private ArrayList<String> factoresVD;
    private int valoracionVentilacion;
    private boolean valorizado;
    public revisionVentilacionDificil() {
        id = 0;
        this.factoresVD = new ArrayList<>();
        this.valoracionVentilacion = 0;
        this.valorizado = false;
    }


    protected revisionVentilacionDificil(Parcel in) {
        id = in.readInt();
        factoresVD = in.createStringArrayList();
        valoracionVentilacion = in.readInt();
    }

    public static final Creator<revisionVentilacionDificil> CREATOR = new Creator<revisionVentilacionDificil>() {
        @Override
        public revisionVentilacionDificil createFromParcel(Parcel in) {
            return new revisionVentilacionDificil(in);
        }

        @Override
        public revisionVentilacionDificil[] newArray(int size) {
            return new revisionVentilacionDificil[size];
        }
    };

    public boolean isValorizado() {
        return valorizado;
    }

    public void setValorizado(boolean valorizado) {
        this.valorizado = valorizado;
    }

    public ArrayList<String> getFactoresVD() {
        return factoresVD;
    }

    public void setFactoresVD(ArrayList<String> factoresVD) {
        this.factoresVD = factoresVD;
    }

    public int getValoracionVentilacion() {
        return valoracionVentilacion;
    }

    public void setValoracionVentilacion(int valoracionVentilacion) {
        this.valoracionVentilacion = valoracionVentilacion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeStringList(factoresVD);
        dest.writeInt(valoracionVentilacion);
    }
}
