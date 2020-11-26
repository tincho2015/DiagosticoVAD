package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;

public class predictorAperturaBucal extends predictor implements Parcelable {

    public predictorAperturaBucal(String predictor) {

        super(predictor);

    }


    protected predictorAperturaBucal(Parcel in) {
        super(in);
    }

    public static final Creator<predictorAperturaBucal> CREATOR = new Creator<predictorAperturaBucal>() {
        @Override
        public predictorAperturaBucal createFromParcel(Parcel in) {
            return new predictorAperturaBucal(in);
        }

        @Override
        public predictorAperturaBucal[] newArray(int size) {
            return new predictorAperturaBucal[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest,flags);

    }
}
