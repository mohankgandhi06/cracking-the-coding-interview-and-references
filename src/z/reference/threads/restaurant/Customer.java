package z.reference.threads.restaurant;

import java.util.concurrent.ThreadLocalRandom;

public class Customer implements Runnable {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        arrive();
        Restaurant.customersInBarCount.incrementAndGet();
        if (Thread.interrupted() || Restaurant.barClosed) {
            System.out.println(name + " has arrived after closing time - Too Late!");
            Restaurant.customersInBarCount.decrementAndGet();
            return;
        }
        System.out.println(name + " has arrived.");
        waitToBeSeated();
        orderMeal();
        waitForMeal();
        eatMeal();
        payCheque();
        System.out.println(name + " has left.");
        Restaurant.customersInBarCount.decrementAndGet();
    }

    public static void arrive() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        int timeBeforeArrival = Math.abs(threadLocalRandom.nextInt()) % (Restaurant.barOpeningTime + 60);
        try {
            Thread.sleep(timeBeforeArrival * Restaurant.TIME_SCALER);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void waitToBeSeated() {
        CustomerRequestHandler.act(this, Request.SEATING_REQUEST);
    }

    public void orderMeal() {
        CustomerRequestHandler.act(this, Request.ORDER_REQUEST);
    }

    public void waitForMeal() {
        CustomerRequestHandler.act(this, Request.SERVE_REQUEST);
    }

    public void eatMeal() {
        System.out.println(getName() + " is eating.");
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        int eatingTime = threadLocalRandom.nextInt() % 30 + 30;
        long doneEatingTime = System.currentTimeMillis() + eatingTime * Restaurant.TIME_SCALER;
        long timeLeft;
        while ((timeLeft = doneEatingTime - System.currentTimeMillis()) > 0) {
            try {
                Thread.sleep(timeLeft);
            } catch (InterruptedException e) {

            }
        }
        System.out.println(name + " has eaten.");
    }

    public void payCheque() {
        CustomerRequestHandler.act(this, Request.CHEQUE_REQUEST);
    }

    public String getName() {
        return name;
    }
}
