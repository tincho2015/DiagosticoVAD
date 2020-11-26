package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;

public class resultadoPredictorDtm  extends resultadoPredictor implements Parcelable {



    public resultadoPredictorDtm() {
        super();
    }


    protected resultadoPredictorDtm(Parcel in) {
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

    public static final Creator<resultadoPredictorDtm> CREATOR = new Creator<resultadoPredictorDtm>() {
        @Override
        public resultadoPredictorDtm createFromParcel(Parcel in) {
            return new resultadoPredictorDtm(in);
        }

        @Override
        public resultadoPredictorDtm[] newArray(int size) {
            return new resultadoPredictorDtm[size];
        }
    };
}
