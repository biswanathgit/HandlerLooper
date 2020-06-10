package com.biswanath.handlerandlooper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxTestActivity extends AppCompatActivity {
    private static final String TAG = "RxTestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test);

        Observable.just("Item:1 ", "Item:2 ", "Item:3 ", "Item:4 ", "Item:5 ")
                .subscribeOn(Schedulers.io())
                .doOnNext(e-> Log.d(TAG, Thread.currentThread().getName().toString() +" -- " +e))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        v-> Log.d(TAG, Thread.currentThread().getName() + " "+ v),
                        e->  Log.d(TAG, e.getLocalizedMessage()),
                        ()->  Log.d(TAG, " completed")
                );
       // _2();
       // _3();
       // _4();
    }


    public void _2(){
        //http://reactivex.io/documentation/observable.html
        //https://www.androidhive.info/RxJava/rxjava-operators-buffer-debounce/
        Observable<Integer> integerObservable = Observable.just(1, 2, 3, 4,
                5, 6, 7, 8, 9);

        integerObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .buffer(3)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.d(TAG, "onNext");
                        for (Integer integer : integers) {
                            Log.d(TAG, "Item: " + integer);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "All items emitted!");
                    }
                });
    }
    public void _3(){
        Observable.just(1, 2, 3, 4, 5, 6)
                .doOnNext(integer -> Log.d(TAG, "Emitting item " + integer + " on: " + Thread.currentThread().getName()))
                .map(integer -> integer * 1)
                .filter(integer -> integer % 2 == 0)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> Log.d(TAG, "Consuming item " + integer + " on: " + Thread.currentThread().getName()));

    }
    public void _4(){
        Disposable disposable = Observable.timer(3000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .repeat()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t -> Log.d(TAG, "_4: "+ t));

    }
}
