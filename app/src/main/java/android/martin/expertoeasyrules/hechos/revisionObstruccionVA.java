package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class revisionObstruccionVA implements Parcelable {

    private int id;
    private ArrayList<String>sintomas;
    private int valoracionObstruccion;
    private boolean valorizado;

    public revisionObstruccionVA() {
        id = 0;
        this.sintomas = new ArrayList<>();
        this.valoracionObstruccion = 0;
        this.valorizado = false;
    }

    protected revisionObstruccionVA(Parcel in) {
        id = in.readInt();
        sintomas = in.createStringArrayList();
        valoracionObstruccion = in.readInt();
    }

    public static final Creator<revisionObstruccionVA> CREATOR = new Creator<revisionObstruccionVA>() {
        @Override
        public revisionObstruccionVA createFromParcel(Parcel in) {
            return new revisionObstruccionVA(in);
        }

        @Override
        public revisionObstruccionVA[] newArray(int size) {
            return new revisionObstruccionVA[size];
        }
    };

    public boolean isValorizado() {
        return valorizado;
    }

    public void setValorizado(boolean valorizado) {
        this.valorizado = valorizado;
    }

    public ArrayList<String> getSintomas() {
        return sintomas;
    }

    public void setSintomas(ArrayList<String> sintomas) {
        this.sintomas = sintomas;
    }

    public int getValoracionObstruccion() {
        return valoracionObstruccion;
    }

    public void setValoracionObstruccion(int valoracionObstruccion) {
        this.valoracionObstruccion = valoracionObstruccion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeStringList(sintomas);
        dest.writeInt(valoracionObstruccion);
    }
}
