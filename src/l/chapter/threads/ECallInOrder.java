package l.chapter.threads;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ECallInOrder {
    public static void main(String[] args) {
        Foo instance = new Foo();
        new ThirdThread(instance);
        new SecondThread(instance);
        new FirstThread(instance);
    }
}

class FirstThread implements Runnable {
    private Foo foo;

    public FirstThread(Foo foo) {
        this.foo = foo;
        new Thread(this, "threadA").start();
    }

    @Override
    public void run() {
        foo.first();
    }
}

class SecondThread implements Runnable {
    private Foo foo;

    public SecondThread(Foo foo) {
        this.foo = foo;
        new Thread(this, "threadB").start();
    }

    @Override
    public void run() {
        foo.second();
    }
}

class ThirdThread implements Runnable {
    private Foo foo;

    public ThirdThread(Foo foo) {
        this.foo = foo;
        new Thread(this, "threadC").start();
    }

    @Override
    public void run() {
        foo.third();
    }
}

class Foo {
    private Semaphore semaphoreFirst = new Semaphore(1);
    private Semaphore semaphoreSecond = new Semaphore(0);
    private Semaphore semaphoreThird = new Semaphore(0);

    public Foo() {

    }

    public void first() {
        try {
            semaphoreFirst.acquire();
            System.out.println("First Method");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {

        }
        semaphoreSecond.release();
    }

    public void second() {
        try {
            semaphoreSecond.acquire();
            System.out.println("Second Method");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {

        }
        semaphoreThird.release();
    }

    public void third() {
        try {
            semaphoreThird.acquire();
            System.out.println("Third Method");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {

        }
    }
}