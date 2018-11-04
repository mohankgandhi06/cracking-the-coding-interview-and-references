package z.reference.threads;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BBar {
    private static List<Customer> customerList;

    public static void main(String[] args) {
        System.out.println("Sleepy Bartender: ");
        Bartender bartender = new Bartender();
        bartender.start();
        customerList = Arrays.asList(
                new Customer(bartender, "Steve", (int) (Math.random() * 5)),
                new Customer(bartender, "Roger", (int) (Math.random() * 5)),
                new Customer(bartender, "Sam", (int) (Math.random() * 5)),
                new Customer(bartender, "Nick", (int) (Math.random() * 5)),
                new Customer(bartender, "Adam", (int) (Math.random() * 5))
        );

        for (Customer customer : customerList) {
            customer.start();
        }

        try {
            bartender.join();
        } catch (InterruptedException e) {

        }
        System.out.println();
        System.out.println("Voice at the distance: Why Bartender is leaving?");
        for (int i = 1; i <= customerList.size(); i++) {
            customerList.get(i - 1).interrupt();
        }
    }
}

class Bartender extends Thread {
    private int customerAttended;

    public Bartender() {
        super("Bartender Thread");
    }

    @Override
    public void run() {
        System.out.println("My Boss is not around today. Let's get some sleep");
        while (true) {
            if (Thread.interrupted()) {
                System.out.println();
                System.out.println(Thread.currentThread().getName());
                System.out.println("Some Guy is waiting. Better get going");
            }
            if (customerAttended == 3) {
                System.out.println("I had enough for Today");
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                customerAttended++;
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Customer extends Thread {
    private Bartender bartender;
    private String customerName;
    private int waitTime;

    public Customer(Bartender bartender, String customerName, int waitTime) {
        super(customerName + " Thread");
        this.bartender = bartender;
        this.customerName = customerName;
        this.waitTime = waitTime;
    }


    @Override
    public void run() {
        System.out.println("Register Entry: " + customerName + "Came in");
        try {
            TimeUnit.SECONDS.sleep(waitTime);
        } catch (InterruptedException e) {
            System.out.println(customerName + ": Bartender. Why are you leaving?");
            return;
        }
        System.out.println(customerName + ": I'll get the bartender");
        bartender.interrupt();
    }
}