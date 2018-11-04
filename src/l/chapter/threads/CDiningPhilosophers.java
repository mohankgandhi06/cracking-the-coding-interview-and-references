package l.chapter.threads;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CDiningPhilosophers {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        new Philosopher("Luke", restaurant);
        new Philosopher("Annakin", restaurant);
        new Philosopher("Obi Wan", restaurant);
        new Philosopher("Shi Fu", restaurant);
        new Philosopher("Darth Vader", restaurant);
    }
}

class Restaurant {
    public static Semaphore leftChopstickToken = new Semaphore(2);
    public static Semaphore rightChopstickToken = new Semaphore(2);
}

class Philosopher implements Runnable {
    private Restaurant restaurant;
    private AtomicBoolean hasLeftChopstick = new AtomicBoolean();
    private AtomicInteger foodAmount = new AtomicInteger();

    public Philosopher(String name, Restaurant restaurant) {
        this.restaurant = restaurant;
        this.hasLeftChopstick.set(false);
        this.foodAmount.set(0);
        new Thread(this, name).start();
    }

    @Override
    public void run() {
        try {
            //Left Chopstick
            while (foodAmount.get() != 100) {
                Restaurant.leftChopstickToken.acquire();
                hasLeftChopstick.set(true);
                //System.out.println(Thread.currentThread().getName() + " : Got the left chopstick...");
                if (hasLeftChopstick.get()) {
                    Restaurant.rightChopstickToken.acquire();
                    //System.out.println(Thread.currentThread().getName() + " : Got the right chopstick...");
                }

                TimeUnit.SECONDS.sleep(2);
                /*synchronized (foodAmount) {*/
                    foodAmount.addAndGet(25);
                /*}*/
                System.out.println(Thread.currentThread().getName() + " : has finished " + foodAmount + "% of food...");
                Restaurant.leftChopstickToken.release();
                Restaurant.rightChopstickToken.release();
            }
            System.out.println(Thread.currentThread().getName() + " has finished eating....");
        } catch (InterruptedException e) {

        }
    }
}

class Chopstick {
    private final String leftChopstick = "leftChopstick";
    private final String rightChopstick = "rightChopstick";
}