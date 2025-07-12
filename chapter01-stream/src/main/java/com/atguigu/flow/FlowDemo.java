package com.atguigu.flow;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class FlowDemo {
    public static void main(String[] args) throws InterruptedException {

        // 1. 定义一个发布者，发布数据
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        // 2. 定义一个订阅者，订阅者感兴趣发布者的数据
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<String>() {
            private Flow.Subscription subscription;

            @Override   //在订阅时 onXxx：在xxx事件发生时，执行这个回调
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println(Thread.currentThread() + "订阅开始..." + subscription);
                this.subscription = subscription;

                // 从上游请求一个数据，背压模式
                this.subscription.request(1);
            }

            @Override   //在下一个元素到达时，执行这个回调    接收到了新数据
            public void onNext(String item) {
                System.out.println(Thread.currentThread() + "订阅者，接收到数据" + item);
                // 取消订阅
                if (item.equals("p-7")) {
                    subscription.cancel();
                }
                this.subscription.request(1);
            }

            @Override   //在错误发生时
            public void onError(Throwable throwable) {
                System.out.println(Thread.currentThread() + "d订阅者，接收到错误信号" + throwable);
            }

            @Override   //在完成时
            public void onComplete() {
                System.out.println(Thread.currentThread() + "订阅者，接收到完成信号");
            }
        };

        // 3.绑定发布者和订阅者
        publisher.subscribe(subscriber);

        for (int i = 0; i < 10; i++) {
            // 发布者发布10条消息  publisher发布的所有数据在它的buffer区
            if (i == 5) {
                publisher.closeExceptionally(new NullPointerException("空指针异常了"));
            } else {
                publisher.submit("p-" + i);
            }
        }

        // 4. 发布者有数据，订阅者就会拿到

        // jvm底层对于整个发布订阅关系做好了 异步 + 缓存区处理 = 响应式编程;

        // 5. 关闭
        //publisher.close();

        // 此处睡眠防止主线程提前结束
        Thread.sleep(2000);
    }
}
