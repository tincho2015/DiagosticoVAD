package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class patologiaFactorAsociada implements Parcelable {

    /*private Boolean presentaLesionVA;
    private Boolean presentaMasasTiroideas;
    private Boolean presentaAnginaLudwig;
    private Boolean presentaLesionCuelloTorax;
    private Boolean presentaLesionRaquisCervical;
    private Boolean presentaMacroglosias;
    private Boolean presentaHipertrofiaAmigdalar;
    private Boolean presentaLesionMandibular;
    private Boolean presentaHistoriaVAD;*/

    private boolean presenta;
    private String descripcion;
    private boolean revisado;
    private boolean esFactor;



    /*public patologiaFactorAsociada(Boolean presentaLesionVA, Boolean presentaMasasTiroideas, Boolean presentaAnginaLudwig, Boolean presentaLesionCuelloTorax, Boolean presentaLesionRaquisCervical, Boolean presentaMacroglosias, Boolean presentaHipertrofiaAmigdalar, Boolean presentaLesionMandibular,Boolean presentaHistoriaVAD) {

        this.presentaLesionVA = presentaLesionVA;
        this.presentaMasasTiroideas = presentaMasasTiroideas;
        this.presentaAnginaLudwig = presentaAnginaLudwig;
        this.presentaLesionCuelloTorax = presentaLesionCuelloTorax;
        this.presentaLesionRaquisCervical = presentaLesionRaquisCervical;
        this.presentaMacroglosias = presentaMacroglosias;
        this.presentaHipertrofiaAmigdalar = presentaHipertrofiaAmigdalar;
        this.presentaLesionMandibular = presentaLesionMandibular;
        this.presentaHistoriaVAD = presentaHistoriaVAD;
    }*/
    public patologiaFactorAsociada(boolean presenta,String descripcion,boolean esFactor) {

        this.presenta = presenta;
        this.descripcion = descripcion;
        this.revisado = false;
        this.esFactor = esFactor;
    }

    protected patologiaFactorAsociada(Parcel in) {
        /*byte tmpPresentaLesionVA = in.readByte();
        presentaLesionVA = tmpPresentaLesionVA == 0 ? null : tmpPresentaLesionVA == 1;
        byte tmpPresentaMasasTiroideas = in.readByte();
        presentaMasasTiroideas = tmpPresentaMasasTiroideas == 0 ? null : tmpPresentaMasasTiroideas == 1;
        byte tmpPresentaAnginaLudwig = in.readByte();
        presentaAnginaLudwig = tmpPresentaAnginaLudwig == 0 ? null : tmpPresentaAnginaLudwig == 1;
        byte tmpPresentaLesionCuelloTorax = in.readByte();
        presentaLesionCuelloTorax = tmpPresentaLesionCuelloTorax == 0 ? null : tmpPresentaLesionCuelloTorax == 1;
        byte tmpPresentaLesionRaquisCervical = in.readByte();
        presentaLesionRaquisCervical = tmpPresentaLesionRaquisCervical == 0 ? null : tmpPresentaLesionRaquisCervical == 1;
        byte tmpPresentaMacroglosias = in.readByte();
        presentaMacroglosias = tmpPresentaMacroglosias == 0 ? null : tmpPresentaMacroglosias == 1;
        byte tmpPresentaHipertrofiaAmigdalar = in.readByte();
        presentaHipertrofiaAmigdalar = tmpPresentaHipertrofiaAmigdalar == 0 ? null : tmpPresentaHipertrofiaAmigdalar == 1;
        byte tmpPresentaLesionMandibular = in.readByte();
        presentaLesionMandibular = tmpPresentaLesionMandibular == 0 ? null : tmpPresentaLesionMandibular == 1;
        byte tmpPresentaHistoriaVAD = in.readByte();
        presentaHistoriaVAD = tmpPresentaHistoriaVAD == 0 ? null : tmpPresentaHistoriaVAD == 1;*/
        presenta = in.readByte() != 0;
        descripcion = in.readString();
        revisado = in.readByte() != 0;
    }

    public static final Creator<patologiaFactorAsociada> CREATOR = new Creator<patologiaFactorAsociada>() {
        @Override
        public patologiaFactorAsociada createFromParcel(Parcel in) {
            return new patologiaFactorAsociada(in);
        }

        @Override
        public patologiaFactorAsociada[] newArray(int size) {
            return new patologiaFactorAsociada[size];
        }
    };

    public boolean isEsFactor() {
        return esFactor;
    }

    public void setEsFactor(boolean esFactor) {
        this.esFactor = esFactor;
    }

    /*public Boolean getPresentaHistoriaVAD() {
        return presentaHistoriaVAD;
    }

    public void setPresentaHistoriaVAD(Boolean presentaHistoriaVAD) {
        this.presentaHistoriaVAD = presentaHistoriaVAD;
    }

    public Boolean getPresentaLesionVA() {
        return presentaLesionVA;
    }

    public void setPresentaLesionVA(Boolean presentaLesionVA) {
        this.presentaLesionVA = presentaLesionVA;
    }

    public Boolean getPresentaLesionMandibular() {
        return presentaLesionMandibular;
    }

    public void setPresentaLesionMandibular(Boolean presentaLesionMandibular) {
        this.presentaLesionMandibular = presentaLesionMandibular;
    }*/

    public boolean isPresenta() {
        return presenta;
    }

    public void setPresenta(boolean presenta) {
        this.presenta = presenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isRevisado() {
        return revisado;
    }

    public void setRevisado(boolean revisado) {
        this.revisado = revisado;
    }

    /*public Boolean getPresentaMasasTiroideas() {
        return presentaMasasTiroideas;
    }

    public void setPresentaMasasTiroideas(Boolean presentaMasasTiroideas) {
        this.presentaMasasTiroideas = presentaMasasTiroideas;
    }

    public Boolean getPresentaAnginaLudwig() {
        return presentaAnginaLudwig;
    }

    public void setPresentaAnginaLudwig(Boolean presentaAnginaLudwig) {
        this.presentaAnginaLudwig = presentaAnginaLudwig;
    }

    public Boolean getpresentaLesionCuelloTorax() {
        return presentaLesionCuelloTorax;
    }

    public void setpresentaLesionCuelloTorax(Boolean presentaLesionCuelloTorax) {
        this.presentaLesionCuelloTorax = presentaLesionCuelloTorax;
    }

    public Boolean getPresentaLesionRaquisCervical() {
        return presentaLesionRaquisCervical;
    }

    public void setPresentaLesionRaquisCervical(Boolean presentaLesionRaquisCervical) {
        this.presentaLesionRaquisCervical = presentaLesionRaquisCervical;
    }

    public Boolean getPresentaMacroglosias() {
        return presentaMacroglosias;
    }

    public void setPresentaMacroglosias(Boolean presentaMacroglosias) {
        this.presentaMacroglosias = presentaMacroglosias;
    }

    public Boolean getPresentaHipertrofiaAmigdalar() {
        return presentaHipertrofiaAmigdalar;
    }

    public void setPresentaHipertrofiaAmigdalar(Boolean presentaHipertrofiaAmigdalar) {
        this.presentaHipertrofiaAmigdalar = presentaHipertrofiaAmigdalar;
    }*/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        /*dest.writeByte((byte) (presentaLesionVA == null ? 0 : presentaLesionVA ? 1 : 2));
        dest.writeByte((byte) (presentaMasasTiroideas == null ? 0 : presentaMasasTiroideas ? 1 : 2));
        dest.writeByte((byte) (presentaAnginaLudwig == null ? 0 : presentaAnginaLudwig ? 1 : 2));
        dest.writeByte((byte) (presentaLesionCuelloTorax == null ? 0 : presentaLesionCuelloTorax ? 1 : 2));
        dest.writeByte((byte) (presentaLesionRaquisCervical == null ? 0 : presentaLesionRaquisCervical ? 1 : 2));
        dest.writeByte((byte) (presentaMacroglosias == null ? 0 : presentaMacroglosias ? 1 : 2));
        dest.writeByte((byte) (presentaHipertrofiaAmigdalar == null ? 0 : presentaHipertrofiaAmigdalar ? 1 : 2));
        dest.writeByte((byte) (presentaLesionMandibular == null ? 0 : presentaLesionMandibular ? 1 : 2));
        dest.writeByte((byte) (presentaHistoriaVAD == null ? 0 : presentaHistoriaVAD ? 1 : 2));*/
        dest.writeByte((byte) (presenta ? 1 : 0));
        dest.writeString(descripcion);
        dest.writeByte((byte) (revisado ? 1 : 0));
    }
}
