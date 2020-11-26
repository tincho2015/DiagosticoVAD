package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;

public class predictor implements Parcelable {

    public String predictor;
    public boolean revisado;



    public predictor(String predictor) {
        this.predictor = predictor;
        this.revisado = false;
    }

    protected predictor(Parcel in) {
        predictor = in.readString();
        revisado = in.readByte() != 0;
    }

    public static final Creator<predictor> CREATOR = new Creator<predictor>() {
        @Override
        public predictor createFromParcel(Parcel in) {
            return new predictor(in);
        }

        @Override
        public predictor[] newArray(int size) {
            return new predictor[size];
        }
    };

    public String getPredictor() {
        return predictor;
    }

    public void setPredictor(String predictor) {
        this.predictor = predictor;
    }

    public boolean isRevisado() {
        return revisado;
    }

    public void setRevisado(boolean revisado) {
        this.revisado = revisado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(predictor);
        dest.writeByte((byte) (revisado ? 1 : 0));
    }
}
