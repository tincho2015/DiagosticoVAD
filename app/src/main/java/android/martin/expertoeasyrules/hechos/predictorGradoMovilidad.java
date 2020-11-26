package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;

public class predictorGradoMovilidad extends predictor implements Parcelable {



    public predictorGradoMovilidad(String predictor) {

        super(predictor);

    }


    protected predictorGradoMovilidad(Parcel in) {
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

    public static final Creator<predictorGradoMovilidad> CREATOR = new Creator<predictorGradoMovilidad>() {
        @Override
        public predictorGradoMovilidad createFromParcel(Parcel in) {
            return new predictorGradoMovilidad(in);
        }

        @Override
        public predictorGradoMovilidad[] newArray(int size) {
            return new predictorGradoMovilidad[size];
        }
    };
}
