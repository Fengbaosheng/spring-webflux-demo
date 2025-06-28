在java中，函数式接口是只包含一个抽象方法的接口。它们是支持Lambda表达式的基础，因此Lambda表达式需要一个目标类型，这个目标类型必须是一个函数式接口.

### 函数式接口的出入参定义
- 有入参，无出参[消费者]: 只拿取东西，然后进行业务计算，算完就完事
```java
    BiConsumer<String, String> biConsumer = (a, b) -> System.out.println("a:" + a + ",b:" + b);
    biConsumer.accept("hello", "world");
```
- 有入参，有出参[多功能函数]: 既要拿东西，还能返回一些东西
```java
    Function<String, Integer> function = (String x)-> Integer.parseInt(x);
    System.out.println(function.apply("2"));
```
- 无入参，无出参
```java
    new Thread(() -> System.out.println("hello")).start();
```
- 无入参，有出参[提供者]: 不接东西，还可以提供东西
```java
    Supplier<String> supplier = () -> "hello";
    System.out.println(supplier.get());
```
java.util.function包下所有的function定义：
- Consumer: 消费者
- Supplier: 提供者
- Predicate: 断言
  
get/test/apply/accept调用的函数方法