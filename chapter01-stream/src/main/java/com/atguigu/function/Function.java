package com.atguigu.function;

import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Function {
    public static void main(String[] args) {
        BiConsumer<String, String> biConsumer = (a, b) -> System.out.println("a:" + a + ",b:" + b);
        biConsumer.accept("hello", "world");

        java.util.function.Function<String, Integer> function = (String x)-> Integer.parseInt(x);
        System.out.println(function.apply("2"));

        new Thread(() -> System.out.println("hello")).start();

        Supplier<String> supplier = () -> "hello";
        System.out.println(supplier.get());

        Predicate<Integer> predicate = (x) -> x > 10;
        System.out.println(predicate.test(10));
    }
}
