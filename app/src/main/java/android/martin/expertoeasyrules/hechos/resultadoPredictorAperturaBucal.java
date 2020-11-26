package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;

public class resultadoPredictorAperturaBucal extends resultadoPredictor implements Parcelable {


    public resultadoPredictorAperturaBucal() {
        super();

    }


    protected resultadoPredictorAperturaBucal(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<resultadoPredictorAperturaBucal> CREATOR = new Creator<resultadoPredictorAperturaBucal>() {
        @Override
        public resultadoPredictorAperturaBucal createFromParcel(Parcel in) {
            return new resultadoPredictorAperturaBucal(in);
        }

        @Override
        public resultadoPredictorAperturaBucal[] newArray(int size) {
            return new resultadoPredictorAperturaBucal[size];
        }
    };
}
