package rxleakexample.jasonatwood.io.rxleakexample.service;

import android.util.Log;

import java.io.Closeable;
import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class ClosableHelper implements Closeable {

    private final UUID uuid;
    private Disposable disposable;
    private boolean closed;

    ClosableHelper() {
        uuid = UUID.randomUUID();
        Log.d(uuid.toString(), "new ClosableHelper");
    }

    public void setStream(Observable<Integer> observable) {
        if (closed) {
            throw new IllegalStateException("Already closed");
        }
        disposable = observable.subscribe(integer ->
                Log.d(uuid.toString(), "some value: " + integer));
    }

    @Override
    public void close() {
        closed = true;
        disposable.dispose();
    }
}
