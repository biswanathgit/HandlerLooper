package com.biswanath.handlerandlooper;

import android.util.Log;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import static java.lang.Thread.sleep;

/**
 * Imperative programming
 * 1.Describe how programme operates
 * 2.Use Statement to change state of the program
 * 3.conditional statement,loops, inheritance
 * exp: if(value%2 == 0){
 *     //do something
 * }
 * Functional Programming
 * 1.describe what program should accomplish
 * 2.Ideally stateless
 * 3.Evaluate with functions and expression instead of statement
 * exp: boolean filter(int v){
 *     return v%2 == 0;
 * }
 *
 *
 * Reactive Programming
 * 1.Data Streams
 * 2.Functions Programming
 * 3.Asynchronous
 *
 */

public class ObservableTest {

    @Test
    public void _1() {

        //Observable
        //Operator
        //Observer

        Observable<String> observable = Observable.just("Item:1 ", "Item:2 ", "Item:3 ", "Item:4 ", "Item:5 ");
        observable.subscribe(s -> System.out.println(s),e-> System.out.println(e),()-> System.out.println("completed"));
//        observable.subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Throwable {
//                System.out.println(s);
//            }
//        });


    }

    @Test
    public void _2() {
        Observable<String> observable = Observable.just("Item:1 ", "Item:2 ", "Item:3 ", "Item:4 ", "Item:5 ");


        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("IsDisposed : " + d.isDisposed());
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("Error : " + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Completed");
            }
        };

        observable.subscribe(observer);
    }

    @Test
    public void _3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Observable<Integer> observable = Observable.fromIterable(list);

        observable.subscribe(System.out::println);
    }
    @Test
    public void _4() {
        Observable<Integer> observable = Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onNext(4);
            emitter.onNext(5);
            //emitter.onNext(null);
            emitter.onComplete();
        });

        observable.subscribe(item -> System.out.println(item),
                error -> System.out.println("There was error: " + error.getLocalizedMessage()),
                () -> System.out.println("Completed"));
    }

    //Hot observable
    @Test
    public void _5() {
        ConnectableObservable<Integer> observable = Observable.just(1, 2, 3, 4, 5).publish();

        observable.subscribe(item -> System.out.println("Observer 1: " + item));
        //observable.connect();
        // observable.subscribe(item -> System.out.println("Observer 2: " + item));
    }

    @Test
    public void _6() {
        int start = 5, count = 2;
        Observable<Integer> observable = Observable.range(start, count);

        observable.subscribe(System.out::println);
    }
    int start = 5;
    int count = 2;
    @Test
    public void _7() {

        Observable<Integer> observable = Observable.defer(() -> {
            //System.out.println("New Observable is created with start = " + start + " and count = " + count);
            return Observable.range(start, count);
        });
        observable.subscribe(item -> System.out.println("Observer 1: " + item));
        count = 3;
        observable.subscribe(item -> System.out.println("Observer 2: " + item));
    }

    @Test
    public void _8() {

        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        observable
                .map(item -> item * 2)
                .subscribe(System.out::println);
    }

    @Test
    public void _9() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        observable
                .filter(item -> item % 2 == 0)
                .map(item -> item * 2)
                .subscribe(System.out::println);
    }

    @Test
    public void _10() {
        Observable.just(1,2,3,4,5,1,2,3,4,5)
                .takeWhile(item -> item <= 3)
                .subscribe(System.out::println);
    }

    @Test
    public void _11() {
        Observable.just(1, 1, 2, 2, 3, 3, 4, 5, 1, 2)
                .distinct()
                .subscribe(System.out::println);

//        Observable.just("foo", "fool", "super", "foss", "foil")
//                .distinct(String::length)
//                .subscribe(System.out::println);
    }
    @Test
    public void _12() {
//        Observable.just(1, 2, 3, 4, 5)
//                .repeat(2)
//                .subscribe(System.out::println);

        Observable.just(1, 2, 3, 4, 5)
                .scan((accumulator, next) -> accumulator + next)
                .subscribe(System.out::println);
    }
    @Test
    public void _13() {
        Observable.just(3, 5, 2, 4, 1)
                .sorted()
                .sorted(Comparator.reverseOrder())
                .subscribe(System.out::println);
    }

    @Test
    public void _14() {
        Observable.just("foo", "john", "bar")
                .sorted((first, second) -> Integer.compare(first.length(), second.length()))
                .subscribe(System.out::println);
    }
    @Test
    public void _15() {
        Observable.error(new Exception("Error @@@"))
                .delay(3, TimeUnit.SECONDS, true)
                .subscribe(System.out::println,
                        error -> System.out.println(error.getLocalizedMessage()),
                        () -> System.out.println("Completed"));
        pause(5000);
    }
    @Test
    public void _16() {
        Observable<Integer> oneToFive = Observable.just(1, 2, 3, 4, 5);
        Observable<Integer> sixToTen = Observable.just(6, 7, 8, 9, 10);
        Observable<Integer> elevenToFifteen = Observable.just(11, 12, 13, 14, 15);
        Observable<Integer> sixteenToTwenty = Observable.just(16, 17, 18, 19, 20);
        Observable<Integer> twentyOneToTwentyFive = Observable.just(21, 22, 23, 24, 25);

        Observable.mergeArray(oneToFive, sixToTen, elevenToFifteen, sixteenToTwenty, twentyOneToTwentyFive)
                .subscribe(System.out::println);
    }
    @Test
    public void _17() {
        Observable<Integer> oneToFive = Observable.just(1, 2, 3, 4, 5);
        Observable<Integer> sixToTen = Observable.range(6, 5);
        Observable<Integer> elevenToFifteen = Observable.fromIterable(Arrays.asList(11, 12, 13, 14, 15));

        Observable.zip(oneToFive, sixToTen, elevenToFifteen, (a, b, c) -> a + b + c)
                .subscribe(System.out::println);
    }
    @Test
    public void _18() {
        Observable<String> observable = Observable.just("foo", "bar", "jam");
        observable.flatMap((string) -> {
            if (string.equals("bar")){
                return Observable.empty();
            }
            return Observable.fromArray(string.split(""));
        }).subscribe(System.out::println);
    }


    /**
     * Flowable is used when you need to handle lots of data.
     * It supports backpressure. We’ll discuss it at length in another tutorial.
     * For now, a Flowable Observable needs a Subscriber class as the Observer since RxJava2.
     */
    @Test
    public void _20() {
        Flowable<Integer> integerFlowable = Flowable.range(1, 500000);

        integerFlowable.reduce(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        });

        Subscriber<Integer> integerSubscriber = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("onSubscribe");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext: " + integer);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        //integerFlowable.subscribe(integerSubscriber);

        Flowable.range(1, 500000)
                .reduce((a, b) -> {
                    //System.out.println("a : "+a +"  b : "+b);
                    return a + b;
                })
                .subscribe(integer -> System.out.println(integer)

                );
    }

    private static void pause(int duration) {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
