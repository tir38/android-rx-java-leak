package rxleakexample.jasonatwood.io.rxleakexample.service;

import android.annotation.SuppressLint;
import android.util.Log;

import io.reactivex.disposables.Disposable;

/**
 * This class is not designed to be instantiated often. Be careful recreating instances of this class.
 */
public class DataManager {

    private static final String TAG = DataManager.class.getSimpleName();

    private Helper leakedHelper;
    private Disposable disposable;
    private DisposableHelper disposableHelper;
    private final BackgroundThread backgroundThread;
    private ClosableHelper closableHelper;
    private CleanupHelper cleanupHelper;

    @SuppressLint("CheckResult")  // suppress since this class is designed to be long lived.
    public DataManager() {
        backgroundThread = new BackgroundThread();
        backgroundThread.start();
        backgroundThread.getObservable()
                .subscribe(integer ->
                        Log.d(TAG, "some value: " + integer));
    }

    public void start() {
        // 1. the problem; setting null (or assigning) doesn't ensure GC
        leakedHelper = new Helper();
        leakedHelper.setStream(backgroundThread.getObservable());

        // 2. cleanup solution
        // cleanup previous helper before creating a new one
        if (cleanupHelper != null) {
            cleanupHelper.cleanup();
        }
        cleanupHelper = new CleanupHelper();
        cleanupHelper.setStream(backgroundThread.getObservable());

        // 3. "disposable" solution
        // dispose old disposable before creating a new one
        if (disposable != null) {
            disposable.dispose();
        }
        disposableHelper = new DisposableHelper();
        disposable = disposableHelper.setStream(backgroundThread.getObservable());

        // 4. closeable solution
        // close old helper before creating new one
        if (closableHelper != null) {
            closableHelper.close();
        }
        closableHelper = new ClosableHelper();
        closableHelper.setStream(backgroundThread.getObservable());
    }
}
