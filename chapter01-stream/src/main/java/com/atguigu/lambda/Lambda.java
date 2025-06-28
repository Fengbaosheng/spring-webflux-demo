package com.atguigu.lambda;


// 函数式接口；只要是函数式接口就可以用Lambda简化
// 函数式接口：接口中有且只有一个未实现的方法，这个接口就叫函数式接口
/*
    interface AAA {
        int a();
        int b();
    }
    像这种就不是一个函数式接口
 */
interface MyInterface {
    int sum(int a, int b);
}

// @FunctionalInterface可以检查该接口是否符合函数式接口，如果不符合代码编写期间idea会检查并提示报错
@FunctionalInterface
interface MyHaha {
    int sum(int x);
}

@FunctionalInterface
interface Myhehe {
    int sum(int x);
}

// 1.自己创建实现类
class MyInterfaceImpl implements MyInterface {
    @Override
    public int sum(int a, int b) {
        return a + b;
    }
}

public class Lambda {
    public static void main(String[] args) {
        // 1.自己创建实现类
        MyInterface myInterface = new MyInterfaceImpl();
        System.out.println(myInterface.sum(1, 2));


        // 2. 创建匿名实现类
        MyInterface myInterface1 = new MyInterface() {
            @Override
            public int sum(int a, int b) {
                return a + b;
            }
        };
        System.out.println(myInterface1.sum(1, 2));
        // 冗余写法

        // 3. lambda表达式：语法糖 参数列表+箭头+方法体  完整写法
        MyInterface myInterface2 = (int x, int y) -> {
            return x + y;
        };
        System.out.println(myInterface2.sum(1, 2));

        // 4. 简化写法
        // 1). 参数类型可以不屑，只写参数名，参数变量名随意定义
        //    参数表最少可以只有一个（），或者只有一个参数名
        Myhehe myhehe = a -> a + a;
        System.out.println(myhehe.sum(1));
        // 2). 方法体如果只有一句话，{}可以省略
        MyHaha myHaha = x -> x + 1;
        System.out.println(myHaha.sum(1));

        // 总结
        // 1. Lambda表达式：(参数表）-> {方法体}
        // 2. 分辨是否是函数式接口。函数式接口就可以使用Lambda表达式简化

    }
}
