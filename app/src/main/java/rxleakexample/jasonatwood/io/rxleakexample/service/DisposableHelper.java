package rxleakexample.jasonatwood.io.rxleakexample.service;

import android.util.Log;

import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class DisposableHelper {
    private final UUID uuid;

    DisposableHelper() {
        uuid = UUID.randomUUID();
        Log.d(uuid.toString(), "new DisposableHelper");
    }

    public Disposable setStream(Observable<Integer> observable) {
        return observable.subscribe(integer -> Log.d(uuid.toString(), "some value: " + integer));
    }
}
