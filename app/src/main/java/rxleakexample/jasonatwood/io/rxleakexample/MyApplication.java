package rxleakexample.jasonatwood.io.rxleakexample;

import android.app.Application;
import android.os.StrictMode;

import rxleakexample.jasonatwood.io.rxleakexample.service.DataManager;

public class MyApplication extends Application {

    private DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        dataManager = new DataManager();
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
