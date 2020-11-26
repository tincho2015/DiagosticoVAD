package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class criterioVentilacionDificil implements Parcelable {

    /*private boolean barba;
    private boolean obesidad;
    private boolean roncador;
    private boolean edentacion;
    private boolean edadMayor55;*/

    private String descripcion;
    private boolean presente;
    private boolean revisado;

   /* public criterioVentilacionDificil(boolean barba, boolean obesidad, boolean roncador, boolean edentacion, boolean edadMayor55) {

        this.barba = barba;
        this.obesidad = obesidad;
        this.roncador = roncador;
        this.edentacion = edentacion;
        this.edadMayor55 = edadMayor55;
    }*/
    public criterioVentilacionDificil(boolean presente,String descripcion) {

        this.presente = presente;
        this.descripcion = descripcion;
        this.revisado = false;

    }

    protected criterioVentilacionDificil(Parcel in) {
       /* barba = in.readByte() != 0;
        obesidad = in.readByte() != 0;
        roncador = in.readByte() != 0;
        edentacion = in.readByte() != 0;
        edadMayor55 = in.readByte() != 0;*/
        descripcion = in.readString();
        presente = in.readByte() != 0;
        revisado = in.readByte() != 0;
    }

    public static final Creator<criterioVentilacionDificil> CREATOR = new Creator<criterioVentilacionDificil>() {
        @Override
        public criterioVentilacionDificil createFromParcel(Parcel in) {
            return new criterioVentilacionDificil(in);
        }

        @Override
        public criterioVentilacionDificil[] newArray(int size) {
            return new criterioVentilacionDificil[size];
        }
    };

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    public boolean isRevisado() {
        return revisado;
    }

    public void setRevisado(boolean revisado) {
        this.revisado = revisado;
    }

    /*public boolean isBarba() {
        return barba;
    }

    public void setBarba(boolean barba) {
        this.barba = barba;
    }

    public boolean isObesidad() {
        return obesidad;
    }

    public void setObesidad(boolean obesidad) {
        this.obesidad = obesidad;
    }

    public boolean isRoncador() {
        return roncador;
    }

    public void setRoncador(boolean roncador) {
        this.roncador = roncador;
    }

    public boolean isEdentacion() {
        return edentacion;
    }

    public void setEdentacion(boolean edentacion) {
        this.edentacion = edentacion;
    }

    public boolean isEdadMayor55() {
        return edadMayor55;
    }

    public void setEdadMayor55(boolean edadMayor55) {
        this.edadMayor55 = edadMayor55;
    }*/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        /*dest.writeByte((byte) (barba ? 1 : 0));
        dest.writeByte((byte) (obesidad ? 1 : 0));
        dest.writeByte((byte) (roncador ? 1 : 0));
        dest.writeByte((byte) (edentacion ? 1 : 0));
        dest.writeByte((byte) (edadMayor55 ? 1 : 0));*/
        dest.writeString(descripcion);
        dest.writeByte((byte) (presente ? 1 : 0));
        dest.writeByte((byte) (revisado ? 1 : 0));
    }
}
