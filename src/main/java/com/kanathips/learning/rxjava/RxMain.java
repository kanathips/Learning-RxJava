package com.kanathips.learning.rxjava;


import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import java.util.Arrays;
import java.util.List;

/**
 * Created by new_z on 27/03/2017.
 */
public class RxMain {
    public static void main(String... args) {

        System.out.println("Hello World\n");
        hello();
        System.out.println("\n--------------------");


        System.out.println("Example 1\n");
        exampleOne();
        System.out.println("\n--------------------");

        System.out.println("Example 2\n");
        exampleTwo();
        System.out.println("\n--------------------");
    }

    private static void exampleTwo() {
        Observable<String> observable1 = Observable.create(emitter -> {
            try {
                List<String> names = getNameList();
                if (!names.isEmpty()) {
                    names.forEach(emitter::onNext);
                }
            }catch (Exception e){
                emitter.onError(e);
            }
        });

        Observable<Integer> observable2 = Observable.create(emitter -> {
            try {
                List<Integer> numbers = getNumber();
                if (!numbers.isEmpty()) {
                    numbers.forEach(emitter::onNext);
                }
            }catch (Exception e){
                emitter.onError(e);
            }
        });

        Consumer<Object> consumer1 = o -> {
            Thread.sleep(500);
            System.out.println(o.toString());
        };

        Consumer<Object> consumer2 = o -> {
            Thread.sleep(500);
            System.out.println(o.toString());
        };

        CompositeDisposable compositeDisposable = new CompositeDisposable();


        Disposable disposable1 = observable1.subscribe(consumer1);
        Disposable disposable2 = observable2.subscribe(consumer2);
        compositeDisposable.add(disposable1);
        compositeDisposable.add(disposable2);

        if (!compositeDisposable.isDisposed()){
            System.out.println("\nCompositeDisposable is Not Dispose Yet");
            compositeDisposable.dispose();
        }

        if (compositeDisposable.isDisposed()){
            System.out.println("CompositeDisposable is Disposed");
        }

        System.out.println("disposable1 is " + disposable1);
        System.out.println("disposable2 is " + disposable2);

    }

    private static void exampleOne(){

        Observable<String> observable1 = Observable.create(emitter -> {
            try {
                List<String> names = getNameList();
                if (!names.isEmpty()) {
                    names.forEach(name -> {
                        try {
                            Thread.sleep(500);
                            emitter.onNext(name);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }catch (Exception e){
                emitter.onError(e);
            }
        });
        observable1.subscribe(System.out::println).dispose();

        Observable<Integer> observable2 = Observable.create(emitter -> {
            try {
                List<Integer> numbers = getNumber();
                if (!numbers.isEmpty()) {
                    numbers.forEach(number -> {
                        try {
                            Thread.sleep(500);
                            emitter.onNext(number);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }catch (Exception e){
                emitter.onError(e);
            }
        });
        observable2.subscribe(System.out::println).dispose();
    }

    private static List<String> getNameList() {
        return Arrays.asList("Kanathip", "Salinee", "Albert");
    }


    private static List<Integer> getNumber() {
        return Arrays.asList(1, 2, 3, 4, 5, 6);
    }

    private static void hello() {
        Observable.fromArray(getNameList().toArray()).subscribe(name -> System.out.println("Hello " + name + "!")).dispose();
    }
}
