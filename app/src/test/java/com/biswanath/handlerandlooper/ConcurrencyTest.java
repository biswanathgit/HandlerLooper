package com.biswanath.handlerandlooper;

import android.util.Log;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.Observer;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.schedulers.ExecutorScheduler;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static java.lang.Thread.sleep;

public class ConcurrencyTest {
    /**
     * Scheduler.io;
     * Scheduler.computation();
     * Scheduler.newThread();
     * AndroidSchedulers.mainThread();
     *
     * Schedulers.io() is backed by an unbounded thread pool. It is used for non CPU-intensive I/O type work including
     * interaction with the file system, performing network calls, database interactions, etc. This thread pool is intended
     * to be used for asynchronously performing blocking IO.
     *
     * Schedulers.computation() is backed by a bounded thread pool with size up to the number of available processors.
     * It is used for computational or CPU-intensive work such as resizing images, processing large data sets, etc. Be careful:
     * when you allocate more computation threads than available cores, performance will degrade due to context switching and thread
     * creation overhead as threads vie for processors’ time.
     *
     * Schedulers.newThread() creates a new thread for each unit of work scheduled. This scheduler is expensive as new thread
     * is spawned every time and no reuse happens.
     *
     * Schedulers.from(Executor executor) creates and returns a custom scheduler backed by the specified executor. To limit the number
     * of simultaneous threads in the thread pool, use Scheduler.from(Executors.newFixedThreadPool(n)). This guarantees that if a task is
     * scheduled when all threads are occupied, it will be queued. The threads in the pool will exist until it is explicitly shutdown.
     *
     * Main thread or AndroidSchedulers.mainThread() is provided by the RxAndroid extension library to RxJava. Main thread (also known as UI thread)
     * is where user interaction happens. Care should be taken not to overload this thread to prevent janky non-responsive UI or,
     * worse, Application Not Responding” (ANR) dialog.
     *
     * Schedulers.single() is new in RxJava 2. This scheduler is backed by a single thread executing tasks sequentially in the order requested.
     *
     * Schedulers.trampoline() executes tasks in a FIFO (First In, First Out) manner by one of the participating worker threads.
     * It’s often used when implementing recursion to avoid growing the call stack.
     *
     * ///////////////////////////////////////////////////////
     * /////https://www.baeldung.com/rxjava-schedulers
     * ///////////////////////////////////////////////////////
     */
    private static final String TAG = "ConcurrencyTest";

    @BeforeClass
    public static void setupTest() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

    @Test
    public void _1(){
        Observable<String> observable = Observable.just("Item:1 ", "Item:2 ", "Item:3 ", "Item:4 ", "Item:5 ");

//        observable
//                .subscribeOn(Schedulers.io())
//                .doOnNext(s -> System.out.println("OnNext : " + Thread.currentThread().getName() +" -- "+ s))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(s -> System.out.println("subscribe : " + Thread.currentThread().getName()+" -- "+s));

        observable
                .subscribeOn(Schedulers.io())
                //.doOnNext(e-> Log.d(TAG, "_1: "+Thread.currentThread().getName().toString() +" -- " +e))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        v-> System.out.println(Thread.currentThread().getName() + v),
                        e-> System.out.println(e),
                        ()-> System.out.println(Thread.currentThread().getName() +"completed")
                );

        pause(6000);
    }

    @Test
    public void _2(){
        Scheduler scheduler = Schedulers.newThread();
        long start = System.currentTimeMillis();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(
                () -> System.out.println(System.currentTimeMillis()-start),
                5, TimeUnit.SECONDS);
        worker.schedule(
                () -> System.out.println(System.currentTimeMillis()-start),
                5, TimeUnit.SECONDS);

        pause(10000);
    }

    @Test
    public void _3(){
        Observable.just(1, 2, 3, 4, 5, 6)
                .doOnNext(integer -> System.out.println("Emitting item " + integer + " on: " + Thread.currentThread().getName()))
                .map(integer -> integer * 1)
                .filter(integer -> integer % 2 == 0)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> System.out.println("Consuming item " + integer + " on: " + Thread.currentThread().getName()));

    }

    private static void pause(int duration) {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
