package l.chapter.threads;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CDiningPhilosophers {
    public static void main(String[] args) {
        /*Restaurant restaurant = new Restaurant();
        new Philosopher("Luke", restaurant);
        new Philosopher("Annakin", restaurant);
        new Philosopher("Obi Wan", restaurant);
        new Philosopher("Shi Fu", restaurant);
        new Philosopher("Darth Vader", restaurant);*/
        Chopstick[] chopsticks = new Chopstick[ 5 ];
        PhilosopherOptimal[] philosophers = new PhilosopherOptimal[ 5 ];
        for (int i = 0; i < 5; i++) {
            chopsticks[ i ] = new Chopstick(i);
        }
        for (int j = 0; j < 5; j++) {
            philosophers[ j ] = new PhilosopherOptimal(j, chopsticks[ j ], chopsticks[ (j + 1) % 5 ]);
        }

        for (int k = 0; k < 5; k++) {
            philosophers[ k ].start();
        }
    }
}

/* Optimal Implementation - Even though this is considered optimal it is actually favouring one thread */
class PhilosopherOptimal extends Thread {
    private AtomicInteger foodQuantity = new AtomicInteger();
    private Chopstick lower, higher;
    private int index;

    public PhilosopherOptimal(int index, Chopstick left, Chopstick right) {
        this.index = index;
        if (left.getNumber() < right.getNumber()) {
            this.lower = left;
            this.higher = right;
        } else {
            this.lower = right;
            this.higher = left;
        }
    }

    @Override
    public void run() {
        while (foodQuantity.get() != 100) {
            eat();
        }
        System.out.println(Thread.currentThread().getName() + " has finished eating....");
    }

    public void eat() {
        pickup();
        chew();
        putdown();
    }

    public void pickup() {
        lower.pickup();
        higher.pickup();
    }

    public void chew() {
        foodQuantity.addAndGet(25);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {

        }
        System.out.println(Thread.currentThread().getName() + " : has finished " + foodQuantity.get() + "% of food...");
    }

    public void putdown() {
        higher.putdown();
        lower.putdown();
    }
}

class Chopstick {
    private Lock lock;
    private int number;

    public Chopstick(int number) {
        this.lock = new ReentrantLock();
        this.number = number;
    }

    public void pickup() {
        this.lock.lock();
    }

    public void putdown() {
        this.lock.unlock();
    }

    public int getNumber() {
        return number;
    }
}

/* Earlier Implementations */
class Restaurant {
    public static Semaphore leftChopstickToken = new Semaphore(2);
    public static Semaphore rightChopstickToken = new Semaphore(2);
}

class Philosopher implements Runnable {
    private AtomicBoolean hasLeftChopstick = new AtomicBoolean();
    private AtomicInteger foodAmount = new AtomicInteger();

    public Philosopher(String name, Restaurant restaurant) {
        this.hasLeftChopstick.set(false);
        this.foodAmount.set(0);
        new Thread(this, name).start();
    }

    @Override
    public void run() {
        try {
            while (foodAmount.get() != 100) {
                Restaurant.leftChopstickToken.acquire();
                hasLeftChopstick.set(true);
                if (hasLeftChopstick.get()) {
                    Restaurant.rightChopstickToken.acquire();
                }
                TimeUnit.SECONDS.sleep(2);
                foodAmount.addAndGet(25);
                System.out.println(Thread.currentThread().getName() + " : has finished " + foodAmount + "% of food...");
                Restaurant.leftChopstickToken.release();
                Restaurant.rightChopstickToken.release();
            }
            System.out.println(Thread.currentThread().getName() + " has finished eating....");
        } catch (InterruptedException e) {

        }
    }
}