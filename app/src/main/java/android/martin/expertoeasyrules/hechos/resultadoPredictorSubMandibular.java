package android.martin.expertoeasyrules.hechos;

import android.os.Parcel;
import android.os.Parcelable;

public class resultadoPredictorSubMandibular  extends resultadoPredictor implements Parcelable {



    public resultadoPredictorSubMandibular() {
       super();
    }


    protected resultadoPredictorSubMandibular(Parcel in) {
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

    public static final Creator<resultadoPredictorSubMandibular> CREATOR = new Creator<resultadoPredictorSubMandibular>() {
        @Override
        public resultadoPredictorSubMandibular createFromParcel(Parcel in) {
            return new resultadoPredictorSubMandibular(in);
        }

        @Override
        public resultadoPredictorSubMandibular[] newArray(int size) {
            return new resultadoPredictorSubMandibular[size];
        }
    };
}
