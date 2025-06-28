package com.atguigu.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionDemo {
    public static void main(String[] args) {
        // Supplier生成数据
        Supplier<String> supplier = () -> "43";
        // Predicate验证数据
        Predicate<String> predicate = x -> x.matches("-?\\d+(\\.\\d+)?");
        // Function转化数据
        Function<String, Integer> function = Integer::parseInt;
        // Consumer消费数据
        Consumer<Integer> consumer = x -> {
            if (x % 2 == 0) {
                System.out.println(x + "是偶数");
            } else {
                System.out.println(x + "是奇数");
            }
        };

        // 串在一起，实现判断42这个字符是奇数还是偶数
        myMethod(supplier, predicate, function, consumer);

        myMethod(() -> "111a",
                x -> x.matches("-?\\d+(\\.\\d+)?"),
                Integer::parseInt,
                System.out::println);
    }

    private static void myMethod(Supplier<String> supplier,
                                 Predicate<String> predicate,
                                 Function<String, Integer> function,
                                 Consumer<Integer> consumer) {
        if (predicate.test(supplier.get())) {
            consumer.accept(function.apply(supplier.get()));
        } else {
            System.out.println("不是一个数字字符");
        }
    }
}
