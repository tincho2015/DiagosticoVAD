package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class sintomaObstruccionVA implements Parcelable {

    /*private boolean disnea;
    private boolean disfagia;
    private boolean estridor;
    private boolean disfonia;
    private boolean antecedente;*/

    private String descripcion;
    private boolean presente;
    private boolean revisado;

   /* public sintomaObstruccionVA(boolean disnea, boolean disfagia, boolean estridor, boolean disfonia,boolean antecedente) {
        this.disnea = disnea;
        this.disfagia = disfagia;
        this.estridor = estridor;
        this.disfonia = disfonia;
        this.antecedente = antecedente;
    }*/

   public sintomaObstruccionVA(String descripcion,boolean presente) {

        this.descripcion = descripcion;
        this.presente = presente;
        this.revisado = false;
    }
    protected sintomaObstruccionVA(Parcel in) {
        presente = in.readByte() != 0;
        descripcion = in.readString();
    }

    /*protected sintomaObstruccionVA(Parcel in) {
        disnea = in.readByte() != 0;
        disfagia = in.readByte() != 0;
        estridor = in.readByte() != 0;
        disfonia = in.readByte() != 0;
        antecedente = in.readByte() != 0;
    }*/

    public static final Creator<sintomaObstruccionVA> CREATOR = new Creator<sintomaObstruccionVA>() {
        @Override
        public sintomaObstruccionVA createFromParcel(Parcel in) {
            return new sintomaObstruccionVA(in);
        }

        @Override
        public sintomaObstruccionVA[] newArray(int size) {
            return new sintomaObstruccionVA[size];
        }
    };

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isRevisado() {
        return revisado;
    }

    public void setRevisado(boolean revisado) {
        this.revisado = revisado;
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
/*public boolean isDisnea() {
        return disnea;
    }

    public void setDisnea(boolean disnea) {
        this.disnea = disnea;
    }

    public boolean isDisfagia() {
        return disfagia;
    }

    public void setDisfagia(boolean disfagia) {
        this.disfagia = disfagia;
    }

    public boolean isEstridor() {
        return estridor;
    }

    public void setEstridor(boolean estridor) {
        this.estridor = estridor;
    }

    public boolean isDisfonia() {
        return disfonia;
    }

    public void setDisfonia(boolean disfonia) {
        this.disfonia = disfonia;
    }

    public boolean isAntecedente() {
        return antecedente;
    }

    public void setAntecedente(boolean antecedente) {
        this.antecedente = antecedente;
    }*/

    @Override
    public int describeContents() {
        return 0;
    }

    /*@Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (disnea ? 1 : 0));
        dest.writeByte((byte) (disfagia ? 1 : 0));
        dest.writeByte((byte) (estridor ? 1 : 0));
        dest.writeByte((byte) (disfonia ? 1 : 0));
        dest.writeByte((byte) (antecedente ? 1:0));
    }*/

   public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(descripcion);
        dest.writeByte((byte) (presente ? 1 : 0));

    }
}
