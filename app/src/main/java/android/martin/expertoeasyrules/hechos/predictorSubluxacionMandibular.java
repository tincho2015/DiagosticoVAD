package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;

public class predictorSubluxacionMandibular extends predictor implements Parcelable {


    public predictorSubluxacionMandibular(String predictor) {

        super(predictor);

    }


    protected predictorSubluxacionMandibular(Parcel in) {
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

    public static final Creator<predictorSubluxacionMandibular> CREATOR = new Creator<predictorSubluxacionMandibular>() {
        @Override
        public predictorSubluxacionMandibular createFromParcel(Parcel in) {
            return new predictorSubluxacionMandibular(in);
        }

        @Override
        public predictorSubluxacionMandibular[] newArray(int size) {
            return new predictorSubluxacionMandibular[size];
        }
    };
}
