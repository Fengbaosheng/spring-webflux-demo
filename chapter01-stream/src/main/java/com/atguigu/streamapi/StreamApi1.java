package com.atguigu.streamapi;

import java.util.List;

public class StreamApi1 {
    public static void main(String[] args) {
        // 获取最大偶数
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        /*
           StringAPI
           1. 把数据封装成流，获取到流数据
           2. 定义流式操作
           3. 获取最终结果

           流管道的组成：
           - 一个数据源（可以是一个数组，集合，生成器函数，IO管道）
           - 零或多个中间操作（将一个流变成另一个流）
           - 一个终止操作（产生最终结果）
           流的特性:
           1. 流是惰性的，只有在调用终止操作方法时才会对源数据进行计算，而且只在需要时才会消耗元素
         */
        Integer i = list.stream()
                // This is an intermediate operation.  中间操作
                .filter(x -> x % 2 == 0)   // 过滤出我们想要的值，如果断言返回true就是我们想要的
                // This is a terminal operation.  终止操作
                .max(Integer::compareTo)
                .get();
        System.out.println(i);
    }
}
