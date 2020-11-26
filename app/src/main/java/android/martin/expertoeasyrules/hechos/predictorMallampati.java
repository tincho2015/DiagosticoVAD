package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;

public class predictorMallampati extends predictor implements Parcelable {

    private boolean paladarBlando;
    private boolean vePilarAmigdalino;

    public predictorMallampati(String predictor,boolean paladarBlando, boolean vePilarAmigdalino) {
        super(predictor);
        this.paladarBlando = paladarBlando;
        this.vePilarAmigdalino = vePilarAmigdalino;
    }


    protected predictorMallampati(Parcel in) {
        super(in);
        paladarBlando = in.readByte() != 0;
        vePilarAmigdalino = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeByte((byte) (paladarBlando ? 1 : 0));
        dest.writeByte((byte) (vePilarAmigdalino ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<predictorMallampati> CREATOR = new Creator<predictorMallampati>() {
        @Override
        public predictorMallampati createFromParcel(Parcel in) {
            return new predictorMallampati(in);
        }

        @Override
        public predictorMallampati[] newArray(int size) {
            return new predictorMallampati[size];
        }
    };

    public boolean isPaladar() {
        return paladarBlando;
    }

    public void setPaladar(boolean paladar) {
        this.paladarBlando= paladar;
    }

    public boolean isVePilarAmigdalino() {
        return vePilarAmigdalino;
    }

    public void setVePilarAmigdalino(boolean vePilarAmigdalino) {
        this.vePilarAmigdalino = vePilarAmigdalino;
    }
}
