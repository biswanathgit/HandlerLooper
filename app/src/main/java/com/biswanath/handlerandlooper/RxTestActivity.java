package com.biswanath.handlerandlooper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

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

//        Observable.just("Item:1 ", "Item:2 ", "Item:3 ", "Item:4 ", "Item:5 ")
//                .subscribeOn(Schedulers.newThread())
//                .doOnNext(e-> Log.d(TAG, Thread.currentThread().getName().toString() +" -- " +e))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        v-> Log.d(TAG, Thread.currentThread().getName() + " "+ v),
//                        e->  Log.d(TAG, e.getLocalizedMessage()),
//                        ()->  Log.d(TAG, Thread.currentThread().getName() +" completed")
//                );
        _2();
    }


    public void _2(){
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
}
