package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;

public class resultadoPredictorMallampati  extends resultadoPredictor implements Parcelable {



    public resultadoPredictorMallampati() {
       super();

    }


    protected resultadoPredictorMallampati(Parcel in) {
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

    public static final Creator<resultadoPredictorMallampati> CREATOR = new Creator<resultadoPredictorMallampati>() {
        @Override
        public resultadoPredictorMallampati createFromParcel(Parcel in) {
            return new resultadoPredictorMallampati(in);
        }

        @Override
        public resultadoPredictorMallampati[] newArray(int size) {
            return new resultadoPredictorMallampati[size];
        }
    };
}
