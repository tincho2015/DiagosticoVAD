package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;

public class resultadoPredictorGradoMovilidad extends resultadoPredictor implements Parcelable {


    public resultadoPredictorGradoMovilidad() {

        super();
    }


    protected resultadoPredictorGradoMovilidad(Parcel in) {
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

    public static final Creator<resultadoPredictorGradoMovilidad> CREATOR = new Creator<resultadoPredictorGradoMovilidad>() {
        @Override
        public resultadoPredictorGradoMovilidad createFromParcel(Parcel in) {
            return new resultadoPredictorGradoMovilidad(in);
        }

        @Override
        public resultadoPredictorGradoMovilidad[] newArray(int size) {
            return new resultadoPredictorGradoMovilidad[size];
        }
    };
}
