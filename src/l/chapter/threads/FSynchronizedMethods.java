package l.chapter.threads;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class FSynchronizedMethods {

    public static void main(String[] args) {
        FSynchronizedMethods instance = new FSynchronizedMethods();
        MethodThread methodThreadOne = new MethodThread(instance, "One");
        MethodThread methodThreadTwo = new MethodThread(instance, "Two");
        methodThreadOne.start();
        methodThreadTwo.start();

        /* When the method is synchronized then its caller thread, if it is of same instance as that of the second
        * Thread then the method will not be called simultaneously. Inorder to understand better, try commenting
        * the synchronized keyword in the below methodA. Both the threads methodA will get executed simultaneously.
        * However if you execute the methodB first in the same instance, it can execute since they are not synchronized */
    }

    public synchronized void methodA() {
        System.out.println(Thread.currentThread().getName() + "'s Method A: is executed");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException ex) {

        }
    }

    public void methodB() {
        System.out.println(Thread.currentThread().getName() + "'s Method B: is executed");
    }
}

class MethodThread extends Thread {
    private final FSynchronizedMethods instance;
    private static final Phaser phaser = new Phaser(2);

    public MethodThread(FSynchronizedMethods instance, String name) {
        super(name);
        this.instance = instance;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is waiting...");
        phaser.arriveAndAwaitAdvance();
        //System.out.println(Thread.currentThread().getName() + " both of them arrived");
        instance.methodA();
        instance.methodB();
        // This is to test the second part of the question
        /*instance.methodB();
        instance.methodA();*/
    }
}