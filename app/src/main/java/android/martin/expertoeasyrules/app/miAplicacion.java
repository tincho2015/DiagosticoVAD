package android.martin.expertoeasyrules.app;

import android.app.Application;
import android.martin.expertoeasyrules.hechos.paciente;
import android.martin.expertoeasyrules.hechos.resultadoPredictor;
import android.martin.expertoeasyrules.hechos.revisionPatologiaFactor;
import android.martin.expertoeasyrules.hechos.revisionVentilacionDificil;
import android.martin.expertoeasyrules.hechos.sintomaObstruccionVA;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class miAplicacion extends Application {

    public static AtomicInteger PacienteId = new AtomicInteger();
    public static AtomicInteger RevisionPatologiaId = new AtomicInteger();
    public static AtomicInteger revisionSintomasId = new AtomicInteger();
    public static AtomicInteger revisionVentilacionId = new AtomicInteger();
    public static AtomicInteger revisionPredictoresId = new AtomicInteger();

    @Override
    public void onCreate() {

        setUpRealmConfig();
        super.onCreate();
        Realm realm = Realm.getDefaultInstance();
        //PacienteId = getPacienteId(realm, paciente.class);
        //RevisionPatologiaId = getPacienteId(realm, revisionPatologiaFactor.class);
        //revisionSintomasId = getPacienteId(realm, sintomaObstruccionVA.class);
        //revisionPredictoresId = getPacienteId(realm, resultadoPredictor.class);
        //revisionVentilacionId = getPacienteId(realm, revisionVentilacionDificil.class);
        realm.close();


    }
    private void setUpRealmConfig(){

        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }


    //Metodo utilizado para autoincrementar ID

    private <T extends RealmObject> AtomicInteger getPacienteId(Realm realm, Class<T>anyClass){

        RealmResults <T> results = realm.where(anyClass).findAll();
        return (results.size()>0) ? new AtomicInteger(results.max("id").intValue()): new AtomicInteger();

    }
}
