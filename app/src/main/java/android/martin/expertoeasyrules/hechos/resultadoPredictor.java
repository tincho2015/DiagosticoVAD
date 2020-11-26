package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import io.realm.RealmObject;

public class resultadoPredictor implements Parcelable{


    private String resultado;
    private String justificacion;
    private boolean valorizado;
    private int valoracionPredictor;


    public resultadoPredictor() {
        this.resultado ="";
        this.justificacion ="";
        this.valoracionPredictor = 0;
        this.valorizado = false;

    }

    public boolean isValorizado() {
        return valorizado;
    }

    public void setValorizado(boolean valorizado) {
        this.valorizado = valorizado;
    }

    protected resultadoPredictor(Parcel in) {
        resultado = in.readString();
        justificacion = in.readString();
        valoracionPredictor = in.readInt();
        valorizado = in.readByte() != 0;
    }

    public static final Creator<resultadoPredictor> CREATOR = new Creator<resultadoPredictor>() {
        @Override
        public resultadoPredictor createFromParcel(Parcel in) {
            return new resultadoPredictor(in);
        }

        @Override
        public resultadoPredictor[] newArray(int size) {
            return new resultadoPredictor[size];
        }
    };

    public int getValoracionPredictor() {
        return valoracionPredictor;
    }

    public void setValoracionPredictor(int valoracionPredictor) {
        this.valoracionPredictor = valoracionPredictor;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(resultado);
        dest.writeString(justificacion);
        dest.writeInt(valoracionPredictor);
        dest.writeByte((byte) (valorizado ? 1 : 0));
    }
}
