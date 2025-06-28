package com.atguigu.lambda;

import java.util.ArrayList;
import java.util.Collections;


public class Lambda2 {
    public static void main(String[] args) {
        var list = new ArrayList<Integer>();
        list.add(3);
        list.add(1);
        list.add(2);

        // 比较器 原生写法
//        Collections.sort(list,new Comparator<Integer>(){
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1-o2;
//            }
//        });
//        System.out.println(list);

        // 直接写函数式接口简化
        Collections.sort(list, (o1, o2) -> o1 - o2);
        System.out.println(list);

        // 类::方法 方法引用，使用引用类中的实例方法，但是这个只能从小到大排，不能从大到小，所以从大到小还是需要上述方法自己定义
        Collections.sort(list, Integer::compareTo);
        System.out.println(list);
    }
}
