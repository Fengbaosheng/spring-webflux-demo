package com.atguigu.streamapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
    private String name;
    private String sex;
    private int age;
}


/***
 * 流的中间操作
 */
public class StreamApi3 {
    static List<Person> personList = new ArrayList<>();

    static {
        Person person1 = new Person("张 三", "man", 18);
        Person person2 = new Person("王 四", "woman", 48);
        Person person3 = new Person("王 五", "woman", 31);
        Person person4 = new Person("赵 六", "man", 8);
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
    }

    public static void main(String[] args) {
        // filter 挑出年龄大于18的人
        personList.stream()
                .filter(person -> person.getAge() > 18)   // 挑出大于18
                .forEach(System.out::println);
        System.out.println("-------------------------------------");

        // map 挑出年龄大于18的人的姓名
        personList.stream()
                .filter(person -> person.getAge() > 18)
                .map(person -> person.getName())
                .forEach(System.out::println);
        System.out.println("-------------------------------------");

        // flatMap 挑出年龄大于18的人，并且把他们的性和名分别打印
        personList.stream()
                .filter(person -> person.getAge() > 18)
                .map(person -> person.getName())
                .flatMap(name -> {
                    String[] s = name.split(" ");
                    return Arrays.stream(s);
                })
                .forEach(System.out::println);
        System.out.println("-------------------------------------");

        // distinct,sorted 挑出年龄大于18的人，并且把他们的性和名分别打印，并且去重，排序
        personList.stream()
                .filter(person -> person.getAge() > 18)
                .map(person -> person.getName())
                .flatMap(name -> {
                    String[] s = name.split(" ");
                    return Arrays.stream(s);
                })
                .distinct()
                .sorted(String::compareTo)
                .forEach(System.out::println);
        System.out.println("-------------------------------------");

        // peek 我上一步处理完，我想看一下处理后的数据可以使用peek.  检查上一步处理好的流
        // peek是中间操作，允许后面再继续处理其他任务，foreach是结束操作，操作完不能再进行执行了
        personList.stream()
                .filter(person -> person.getAge() > 18)
                .peek(x -> System.out.println("filter peek: " + x))
                .map(person -> person.getName())
                .peek(y -> System.out.println("map peek: " + y))
                .flatMap(name -> {
                    String[] s = name.split(" ");
                    return Arrays.stream(s);
                })
                .distinct()
                .sorted(String::compareTo)
                .forEach(System.out::println);
        System.out.println("-------------------------------------");

        // takeWhile 当满足条件，拿到这个元素，不满足直接结束流的操作
        List<Integer> collect = List.of(1, 2, 3, 4, 5, 6)
                .stream()
                .filter(i -> i > 2)
                .collect(Collectors.toList());
        System.out.println(collect);

        List<Integer> collect1 = List.of(1, 2, 3, 4, 5, 6)
                .stream()
                //.takeWhile(i -> i > 2)      // 当1进来后，不满足1>2，所以直接结束就，最后获得的集合是个空
                .takeWhile(i -> i < 2)       // 只有1进来后满足1<2的要求，所以最后输出的集合中只有1一个元素
                .collect(Collectors.toList());
        System.out.println(collect1);

        System.out.println("-------------------------------------");
        // group by
        Map<String, List<Person>> collect2 = personList.stream()
                .filter(person -> person.getAge() > 20)
                .collect(Collectors.groupingBy(person -> person.getSex()));
        System.out.println(collect2);
    }

}


