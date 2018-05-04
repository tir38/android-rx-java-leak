package rxleakexample.jasonatwood.io.rxleakexample.service;

import android.util.Log;

import java.util.UUID;

import io.reactivex.Observable;

public class Helper {

    private final UUID uuid;

    Helper() {
        uuid = UUID.randomUUID();
        Log.d(uuid.toString(), "new Helper");
    }

    public void setStream(Observable<Integer> observable) {
        observable.subscribe(integer ->
                Log.d(uuid.toString(), "some value: " + integer));
    }
}
