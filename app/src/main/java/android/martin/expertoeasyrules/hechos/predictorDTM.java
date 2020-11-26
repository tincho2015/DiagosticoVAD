package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;

public class predictorDTM extends predictor implements Parcelable {


    public predictorDTM(String predictor) {

        super(predictor);

    }


    protected predictorDTM(Parcel in) {
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

    public static final Creator<predictorDTM> CREATOR = new Creator<predictorDTM>() {
        @Override
        public predictorDTM createFromParcel(Parcel in) {
            return new predictorDTM(in);
        }

        @Override
        public predictorDTM[] newArray(int size) {
            return new predictorDTM[size];
        }
    };
}
