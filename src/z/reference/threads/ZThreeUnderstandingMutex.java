package z.reference.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ZThreeUnderstandingMutex {

    public static void main(String[] args) {
        /*Friend adam = new Friend("Adam");
        Friend john = new Friend("John");

        Thread adamThread = new Thread(adam, adam.getName());
        Thread johnThread = new Thread(john, john.getName());
        adamThread.start();
        johnThread.start();*/
        FriendMulti adam = new FriendMulti("Adam");
        FriendMulti john = new FriendMulti("John");

        Thread adamThread = new Thread(adam, adam.getName());
        Thread johnThread = new Thread(john, john.getName());
        adamThread.start();
        johnThread.start();
    }
}

class Friend implements Runnable {
    private String name;
    private static volatile boolean roundBeingBought = false;
    private static volatile CountDownLatch latch = new CountDownLatch(2);
    private static AtomicBoolean roundBought = new AtomicBoolean();

    public Friend(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        visit();
        buyOrWaitForDrink();
    }

    public void visit() {
        System.out.println(name + ": entered the bar");
    }

    public void buyOrWaitForDrink() {
        /* Normal Implementations */
        /*if (!roundBeingBought) {
         *//*try {
                latch.countDown();
                latch.await();
            } catch (InterruptedException e) {

            }*//*
         *//* Since using the above count down latch will make the thread to wait for others and then proceed once the
         * latch has become zero here the if check and setting of the flag to true are two lines which could be
         * executed by two threads will make one thread stop at if check and allow the other thread to catch up
         * and both of them to enter the buy method. This whole section is deemed critical *//*
            roundBeingBought = true;
            buy();
        } else {
            waitForDrinks();
        }*/

        /* Atomic Integer Implementation */
        if (roundBought.compareAndSet(false, true)) {
            buy();
        } else {
            waitForDrinks();
        }
    }

    private void buy() {
        System.out.println(name + ": Buying Drinks");
    }

    private void waitForDrinks() {
        System.out.println(name + ": Waiting for Drinks");
    }

    public String getName() {
        return name;
    }
}

class FriendMulti implements Runnable {

    private String name;
    private static final int numberOfVisits = 50;
    private static final AtomicBoolean roundBeingBought = new AtomicBoolean();
    private final AtomicInteger numberOfRounds = new AtomicInteger();
    private static final Phaser phaser = new Phaser(2);

    public FriendMulti(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i <= numberOfVisits; i++) {
            enterBarAndOrderDrinks(i);
        }
        int percentage = numberOfRounds.get() * 100 / numberOfVisits;
        System.out.println(name + ": bought " + numberOfRounds + " rounds (" + percentage + "%) ");
    }

    private void enterBarAndOrderDrinks(int visit) {
        roundBeingBought.set(false);
        phaser.arriveAndAwaitAdvance();
        if (roundBeingBought.compareAndSet(false, true)) {
            buy(visit);
            numberOfRounds.getAndIncrement();
        } else {
            waitForDrinks(visit);
        }
        phaser.arriveAndAwaitAdvance();
    }

    private void buy(int visit) {
        System.out.println("Visit Number: " + visit + ": " + name + " is buying the drinks");
    }

    private void waitForDrinks(int visit) {
        System.out.println("Visit Number: " + visit + ": " + name + " is waiting for the drinks");
    }

    public String getName() {
        return name;
    }
}