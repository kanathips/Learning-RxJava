package com.kanathips.learning.rxjava;

import rx.Observable;

/**
 * Created by new_z on 27/03/2017.
 */
public class RxMain {
    public static void main(String... args){
        hello("kanathip", "salinee", "albert");
    }

    public static void hello(String... names) {
        Observable.from(names).subscribe(name -> System.out.println("Hello " + name + "!"));
    }
}
