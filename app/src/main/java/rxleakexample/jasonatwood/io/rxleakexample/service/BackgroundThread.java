package rxleakexample.jasonatwood.io.rxleakexample.service;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

class BackgroundThread extends Thread {

    private PublishSubject<Integer> publishSubject = PublishSubject.create();

    public void run() {
        int i = 0;

        while (true) {
            publishSubject.onNext(i++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    Observable<Integer> getObservable() {
        return publishSubject;
    }
}