package Rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableCreate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class RxJavaTest {
    public static void main(String[] args) {
        RxJavaTest test = new RxJavaTest();
        test.learnSwitchThread1();
    }

    private void sumTwoRequests() {
        Observable<Integer> first = request("第一次请求");
        Observable<Integer> second = request("第二次请求");

        try {
            Observable.zip(first, second, Integer::sum)
                    .blockingSubscribe(sum -> System.out.println("求和结果: " + sum));
        } finally {
            Schedulers.shutdown();
        }
    }

    private Observable<Integer> request(String name) {
        return Observable.fromCallable(() -> {
            long costMillis = ThreadLocalRandom.current().nextLong(1000, 3001);
            System.out.printf("%s 开始，模拟耗时 %.1f 秒%n", name, costMillis / 1000.0);
            Thread.sleep(costMillis);
            int value = ThreadLocalRandom.current().nextInt(1, 101);
            System.out.println(name + " 返回: " + value);
            return value;
        }).subscribeOn(Schedulers.io());
    }

    /**
     * 没有背压，只看切线程。subscribeOn 决定源头和它上面的 map 在哪个线程跑，
     * observeOn 决定终端 onNext 在哪个线程跑。
     * 断点：Observable.subscribe、ObservableSubscribeOn、ObservableCreate、ObservableObserveOn。
     */
    private void learnSwitchThread1() {
        ObservableOnSubscribe oos = new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                for (int i = 1; i <= 3; i++) {
                    System.out.println("源头发射 " + i + "，线程 " + threadName());
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        };


        ObservableCreate oc = new ObservableCreate<Integer>(oos);
        oc.subscribe(new BaseObserver());
        Observable.<Integer>create(emitter -> {
                    System.out.println("源头开始，线程 " + threadName());
                    for (int i = 1; i <= 3; i++) {
                        System.out.println("源头发射 " + i + "，线程 " + threadName());
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                })                                    //返回ObservableCreate对                .subscribeOn(Schedulers.io())         //返回ObservableSubscribeOn对象， source 是ObservableCreate对象
//                .map(value -> {
//                    int mapped = value * 10;
//                    System.out.println("map " + value + " -> " + mapped + "，线程 " + threadName());
//                    return mapped;
//                }) 象   source 是 ObservableEmitter <Integer> 对象
//                                    //返回ObservableMap 实例， source是ObservableSubscribeOn 实例
                //.observeOn(Schedulers.computation())   //返回ObservableObserveOn  source是返回ObservableMap 实例
                .subscribe(new BaseObserver());
    }

    /**
     * 没有背压，只看切线程。subscribeOn 决定源头和它上面的 map 在哪个线程跑，
     * observeOn 决定终端 onNext 在哪个线程跑。
     * 断点：Observable.subscribe、ObservableSubscribeOn、ObservableCreate、ObservableObserveOn。
     */
    private void learnSwitchThread() {
        CountDownLatch done = new CountDownLatch(1);
        Observable.<Integer>create(emitter -> {
                    System.out.println("源头开始，线程 " + threadName());
                    for (int i = 1; i <= 3; i++) {
                        System.out.println("源头发射 " + i + "，线程 " + threadName());
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                })                                    //返回ObservableCreate对象   source 是 ObservableEmitter <Integer> 对象
                .subscribeOn(Schedulers.io())         //返回ObservableSubscribeOn对象， source 是ObservableCreate对象
                .map(value -> {
                    int mapped = value * 10;
                    System.out.println("map " + value + " -> " + mapped + "，线程 " + threadName());
                    return mapped;
                })                                     //返回ObservableMap 实例， source是ObservableSubscribeOn 实例
                .observeOn(Schedulers.computation())   //返回ObservableObserveOn  source是返回ObservableMap 实例
                .subscribe(new ThreadSwitchObserver(done));

        try {
            done.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            Schedulers.shutdown();
        }
    }

    /**
     * 先看这条。全程在 main 线程：源头发射，中间只包一层 map，终端每次 request(1)。
     * 断点顺序：Flowable.subscribe → FlowableMap.subscribeActual → FlowableCreate.subscribeActual。
     */
    private void learnSimpleCallChain() {
        Flowable.<Integer>create(emitter -> {
                    for (int i = 1; i <= 3 && !emitter.isCancelled(); i++) {
                        System.out.println("源头发射 " + i + "，剩余 request=" + emitter.requested());
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                }, BackpressureStrategy.BUFFER)
                .map(value -> {
                    int mapped = value * 10;
                    System.out.println("map " + value + " -> " + mapped);
                    return mapped;
                })
                .subscribe(new SimpleSubscriber());
    }

    /**
     * 跟源码时看两个方向：
     * subscribe 从下游走到上游，每一层都把自己包装成上游的观察者；
     * onNext 从上游走到下游，request 再从下游走回上游。
     * 建议断点：Flowable.subscribe、FlowableCreate、FlowableSubscribeOn、
     * FlowableMap、FlowableLift、FlowableFilter、FlowableObserveOn。
     */
    private void learnCallChain() {
        CountDownLatch done = new CountDownLatch(1);
        Flowable.<Integer>create(emitter -> {
                    System.out.println("create 在线程 " + threadName() + " 执行，这里才真正订阅到源头");
                    for (int i = 1; i <= 8 && !emitter.isCancelled(); i++) {
                        System.out.println("源头准备发射 " + i + "，尚未投递的 request=" + emitter.requested());
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                }, BackpressureStrategy.BUFFER)
                .doOnRequest(n -> System.out.println("源头 Subscription 收到 request(" + n + ")，线程 " + threadName()))
                .subscribeOn(Schedulers.io())
                .map(value -> {
                    int mapped = value * 10;
                    System.out.println("map " + value + " -> " + mapped + "，线程 " + threadName());
                    return mapped;
                })
                .lift(new PassThroughOperator())
                .filter(value -> {
                    boolean pass = value % 20 == 0;
                    System.out.println("filter " + value + (pass ? " 通过" : " 丢弃，FilterSubscriber 会再向上游 request(1)")
                            + "，线程 " + threadName());
                    return pass;
                })
                .observeOn(Schedulers.computation(), false, 2)
                .subscribe(new StepByStepSubscriber(done));

        try {
            done.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            Schedulers.shutdown();
        }
    }

    private static String threadName() {
        return Thread.currentThread().getName();
    }

    private static final class BaseObserver implements Observer<Integer> {

        @Override
        public void onSubscribe(@NonNull Disposable d) {
            System.out.println("终端 onSubscribe，线程 " + threadName());
        }

        @Override
        public void onNext(@NonNull Integer integer) {
            System.out.println("终端 onNext " + integer + "，线程 " + threadName());
        }

        @Override
        public void onError(@NonNull Throwable e) {
            e.printStackTrace(System.out);
        }

        @Override
        public void onComplete() {
            System.out.println("终端 onComplete，线程 " + threadName());
        }
    }

    private static final class ThreadSwitchObserver implements Observer<Integer> {
        private final CountDownLatch done;

        private ThreadSwitchObserver(CountDownLatch done) {
            this.done = done;
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            System.out.println("终端 onSubscribe，线程 " + threadName());
        }

        @Override
        public void onNext(Integer value) {
            System.out.println("终端 onNext " + value + "，线程 " + threadName());
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace(System.out);
            done.countDown();
        }

        @Override
        public void onComplete() {
            System.out.println("终端 onComplete，线程 " + threadName());
            done.countDown();
        }
    }

    private static final class SimpleSubscriber implements FlowableSubscriber<Integer> {
        private Subscription upstream;

        @Override
        public void onSubscribe(Subscription subscription) {
            this.upstream = subscription;
            System.out.println("终端 onSubscribe，request(1)");
            subscription.request(1);
        }

        @Override
        public void onNext(Integer value) {
            System.out.println("终端 onNext " + value);
            upstream.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace(System.out);
        }

        @Override
        public void onComplete() {
            System.out.println("终端 onComplete");
        }
    }

    /**
     * lift 插入的一层观察者。apply 的参数是下游观察者，返回值是交给上游的观察者。
     * 对应源码 FlowableLift。
     */
    private static final class PassThroughOperator implements FlowableOperator<Integer, Integer> {
        @Override
        public Subscriber<? super Integer> apply(Subscriber<? super Integer> downstream) {
            return new FlowableSubscriber<Integer>() {
                private Subscription upstream;

                @Override
                public void onSubscribe(Subscription subscription) {
                    this.upstream = subscription;
                    downstream.onSubscribe(new Subscription() {
                        @Override
                        public void request(long n) {
                            System.out.println("自定义层收到下游 request(" + n + ")，原样向上游传递");
                            upstream.request(n);
                        }

                        @Override
                        public void cancel() {
                            upstream.cancel();
                        }
                    });
                }

                @Override
                public void onNext(Integer value) {
                    System.out.println("自定义层 onNext " + value + "，线程 " + threadName());
                    downstream.onNext(value);
                }

                @Override
                public void onError(Throwable throwable) {
                    downstream.onError(throwable);
                }

                @Override
                public void onComplete() {
                    downstream.onComplete();
                }
            };
        }
    }

    /**
     * 终端观察者每次只 request(1)。observeOn 预取大小是 2，所以源头先看到的是 request(2)。
     */
    private static final class StepByStepSubscriber implements FlowableSubscriber<Integer> {
        private final CountDownLatch done;
        private Subscription upstream;

        private StepByStepSubscriber(CountDownLatch done) {
            this.done = done;
        }

        @Override
        public void onSubscribe(Subscription subscription) {
            this.upstream = subscription;
            System.out.println("终端 onSubscribe，先 request(1)，线程 " + threadName());
            subscription.request(1);
        }

        @Override
        public void onNext(Integer value) {
            System.out.println("终端 onNext " + value + "，线程 " + threadName());
            upstream.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace(System.out);
            done.countDown();
        }

        @Override
        public void onComplete() {
            System.out.println("终端 onComplete，线程 " + threadName());
            done.countDown();
        }
    }
}
