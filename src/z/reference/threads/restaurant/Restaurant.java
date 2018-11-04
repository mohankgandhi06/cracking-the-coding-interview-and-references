package z.reference.threads.restaurant;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Restaurant {
    private static final int waiterCount = 5;
    private static final int customerCount = 50;
    static final int TIME_SCALER = 10;
    static final int barOpeningTime = 360;
    static volatile boolean barClosed = true;
    static final AtomicInteger customersInBarCount = new AtomicInteger();
    static final Queue<Request> requests = new ConcurrentLinkedQueue();

    public static void main(String[] args) {
        Thread[] waiters = new Thread[ waiterCount ];
        Thread[] customers = new Thread[ customerCount ];
        for (int i = 0; i < waiterCount; i++) {
            String name = "Waiter " + (i + 1);
            waiters[ i ] = new Thread(new Waiter(name), name);
        }
        for (int i = 0; i < customerCount; i++) {
            String name = "Customer " + (i + 1);
            customers[ i ] = new Thread(new Customer(name), name);
        }

        System.out.println("Restaurant is opening");
        barClosed = false;
        for (int i = 0; i < waiterCount; i++) {
            waiters[ i ].start();
        }

        System.out.println("Restaurant is allowing customers");
        //TODO dont allow the customers to start ordering untill all the waiters have arrived
        for (int i = 0; i < customerCount; i++) {
            customers[ i ].start();
        }
        try {
            Thread.sleep(barOpeningTime * TIME_SCALER);
        } catch (InterruptedException e) {

        }

        System.out.println("Restaurant is closing for new customers");
        barClosed = true;
        for (int i = 0; i < customerCount; i++) {
            customers[ i ].interrupt();
            //Will interrupt only act upon sleeping/waiting customers
        }
        while (customersInBarCount.get() > 0) {
            try {
                Thread.sleep(20 * TIME_SCALER);
            } catch (InterruptedException e) {

            }
        }
        System.out.println("Restaurant is closed for the day");
    }
}
