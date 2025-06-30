package com.atguigu.streamapi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamApi2 {
    public static void main(String[] args) {

        System.out.println("主线程: " + Thread.currentThread());

        // 流是并发还是不并发？和for有啥区别？
        // 流也是用for循环挨个处理，默认不并发，但是当我们使用parallel方法时可以将流进行并发计算
        // 并发以后，需要自行解决多线程安全问题
        long count = Stream.of(1, 2, 3, 4, 5)
                //.parallel()  // This is an intermediate operation.
                .filter(i -> {
                    System.out.println("filter线程: " + Thread.currentThread());
                    System.out.println("正在filter: " + i);
                    return i > 2;
                })
                .count();
        System.out.println(count);


        // 下面这种写法，当我们并发使用流时，会对最外层的list.add()结果产生并发问题，这种数据叫做有状态的数据，我们需要手动处理并发，通常不推荐这么写
        // 所以当我们使用流时，最好保证流的所有操作都是无状态的。也就是说我的数据仅在此次函数内有效，不溢出至函数外。
        List list = new ArrayList();
        long count1 = Stream.of(1, 2, 3, 4, 5)
                .parallel()  // This is an intermediate operation.
                .filter(i -> {
                    synchronized (Object.class) {
                        System.out.println("filter线程: " + Thread.currentThread());
                        System.out.println("正在filter: " + i);
                        list.add(i);
                        return i > 2;
                    }
                })
                .count();
    }
}
