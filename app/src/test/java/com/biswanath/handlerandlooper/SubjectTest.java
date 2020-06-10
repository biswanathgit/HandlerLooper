package com.biswanath.handlerandlooper;

import org.junit.Test;

import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class SubjectTest {
    @Test
    public void _1() {
        PublishSubject<Integer> subject = PublishSubject.create();
        subject.onNext(1);
        subject.subscribe(System.out::println);
        subject.onNext(2);
        subject.onNext(3);
        subject.onNext(4);
    }
    @Test
    public void _2() {
        ReplaySubject<Integer> s = ReplaySubject.create();
        s.subscribe(v -> System.out.println("Early:" + v));
        s.onNext(0);
        s.onNext(1);

        s.subscribe(v -> System.out.println("Late: " + v));
        s.onNext(2);
    }
    @Test
    public void _3() {

    }@Test
    public void _4() {

    }
}
