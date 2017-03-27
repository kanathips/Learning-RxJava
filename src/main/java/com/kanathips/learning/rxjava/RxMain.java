package com.kanathips.learning.rxjava;


import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by new_z on 27/03/2017.
 */
public class RxMain {
    public static void main(String... args) {

        System.out.println("Hello World");
        hello(getNameList());
        System.out.println("--------------------");


        System.out.println("Example 1");
        exampleOne(getNameList());
        System.out.println("--------------------");
    }

    private static void exampleOne(List<String> names){

        Observable<String> observable = Observable.create(emitter -> {
            try {
                if (names != null && !names.isEmpty()) {
                    names.forEach(emitter::onNext);
                }
            }catch (Exception e){
                emitter.onError(e);
            }
        });
        observable.subscribe(System.out::println);
    }

    private static List<String> getNameList() {
        return Arrays.asList("Kanathip", "Salinee", "Albert");
    }


    public List<Integer> getNumber() {
        return Arrays.asList(1, 2, 3, 4, 5, 6);
    }

    private static void hello(List<String> names) {
        Observable.fromArray(names).subscribe(name -> System.out.println("Hello " + name + "!"));
    }
}
