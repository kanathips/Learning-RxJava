package com.kanathips.learning.rxjava;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by new_z on 27/03/2017.
 */
public class RxMain {
    public static void main(String... args){
        hello("kanathip", "salinee", "albert");
    }

    public static void hello(String... names) {
        Observable.from(names).subscribe(new Action1<String>() {

            public void call(String s) {
                System.out.println("Hello " + s + "!");
            }
        });
    }
}
