package rxleakexample.jasonatwood.io.rxleakexample.service;

import android.util.Log;

import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class CleanupHelper {

    private final UUID uuid;
    private Disposable disposable;

    CleanupHelper() {
        uuid = UUID.randomUUID();
        Log.d(uuid.toString(), "new CleanupHelper");
    }

    public void setStream(Observable<Integer> observable) {
        disposable = observable.subscribe(integer ->
                Log.d(uuid.toString(), "some value: " + integer));
    }

    public void cleanup() {
        disposable.dispose();
    }
}
