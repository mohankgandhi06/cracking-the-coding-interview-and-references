package z.reference.threads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ZOneCreatingAndManagingThreads {

    public static DateFormatterThreadLocal dateFormatterThreadLocal = new DateFormatterThreadLocal();

    public static void main(String[] args) {
        /* Basic Runnable Thread */
        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        });
        thread.start();*/

        /* Alternate Way */
        /*String[] countries = new String[]{"India", "Nepal", "Bhutan", "China", "Pakistan", "Bangladesh", "Myanmar", "Srilanka"};

        for (String country : countries) {
            new Greetings(country).start();
            //new Greetings(country).run();//Try uncommenting this part where we are
            // using run in the place of start. here the same thread single thread will be
            // calling all the Greetings by itself and not different threads
            //System.out.println(" " + country);
        }*/

        /* Using Runnable */
        /*System.out.println(" Using Runnable ");
        for (String country : countries) {
            Message message = new Message(country);
            new Thread(message, country + " Thread").start();
        }*/

        /* Sleep Example Bartender */
        /*String bartenderName = "Bartender";
        BartenderZ bartender = new BartenderZ(bartenderName);
        Thread bartenderThread = new Thread(bartender, bartenderName);
        bartenderThread.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.println("In Here.... Main Thread");
        }
        int customers = 5;
        Thread[] threads = new Thread[ customers ];
        for (int i = 1; i <= customers; i++) {
            String customerName = "Customer " + i;
            CustomerZ customer = new CustomerZ(bartenderThread, customerName, (int) (Math.random() * 10));
            threads[ i - 1 ] = new Thread(customer, customerName);
            threads[ i - 1 ].start();
        }
        try {
            bartenderThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main Thread");
        }
        System.out.println("A Voice: Hey! Isn't that the bartender leaving for the day?");
        for (int i = 0; i < threads.length; i++) {
            threads[ i ].interrupt();
        }*/

        /* Uncaught Exception Handler */
        /*Kenny kenny = new Kenny();
        Thread kennyThread = new Thread(kenny, "Kenny Thread");
        ThreadExceptionHandler exceptionHandler = new ThreadExceptionHandler();
        kennyThread.setUncaughtExceptionHandler(exceptionHandler);
        kennyThread.start();

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {

        }
        kennyThread.interrupt();

        try {
            kennyThread.join();
        } catch (InterruptedException e) {

        }

        ExecutionException exception = exceptionHandler.getException();

        System.out.println("They killed Kenny!");

        if (exception != null) {
            System.out.println("Thread caught an exception");
            exception.printStackTrace();
        }*/

        /* Thread Local */
        //Declared a Global Variable above the main method dateFormatterThreadLocal
        Thread threadOne = new Thread(new DatePrinter("Format One"), "Format One");
        Thread threadTwo = new Thread(new DatePrinter("Format Two"), "Format Two");

        threadOne.start();
        threadTwo.start();
    }

    public static class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
        private ExecutionException exception;

        public ExecutionException getException() {
            return exception;
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            exception = new ExecutionException(e);
        }
    }
}

class Greetings extends Thread {
    private String country;

    public Greetings(String country) {
        super(country + " Thread");//If we are using a thread we can leverage super
        // but usually extending means we cannot extend other class in Java as
        // multiple inheritence is not allowed in Java Language.
        this.country = country;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {

        }
        System.out.println(Thread.currentThread().getId() + " - " + this.country);
    }
}

class Message implements Runnable {

    public String country;

    public Message(String country) {
        this.country = country;
    }

    @Override
    public void run() {
        System.out.println("* " + this.country);
    }
}

class BartenderZ implements Runnable {
    public String bartenderName;

    public BartenderZ(String name) {
        this.bartenderName = name;
    }

    @Override
    public void run() {

        int numberOfTimesWoken = 0;
        System.out.println(bartenderName + ": Nobody is around, I should get some sleep.");
        while (true) {
            if (Thread.interrupted()) {
                System.out.println(bartenderName + ": Someone is waiting. I better go over there...");
            }
            if (numberOfTimesWoken == 2) {
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                numberOfTimesWoken++;
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("I had enough for the day. I am going home");
    }
}

class CustomerZ extends Thread {

    public Thread bartenderThread;
    public String customerName;
    public int waitTime;

    public CustomerZ(Thread bartenderThread, String customerName, int waitTime) {
        this.bartenderThread = bartenderThread;
        this.customerName = customerName;
        this.waitTime = waitTime;
    }

    @Override
    public void run() {
        System.out.println(this.customerName + ": seems like nobody is here. I'll wait for sometime");
        try {
            TimeUnit.SECONDS.sleep(waitTime);
        } catch (InterruptedException e) {
            System.out.println(this.customerName + ": I didn't get my service... Where are you going?");
            return;
        }
        System.out.println(this.customerName + ": Oh there is a bell. Can I get someone to tend bar service to me?");
        bartenderThread.interrupt();
    }
}

class Kenny implements Runnable {
    @Override
    public void run() {
        System.out.println("Hi I am Kenny");
        Thread currentThread = Thread.currentThread();
        while (!currentThread.isInterrupted()) {
            System.out.println("I am still Alive in this world");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                currentThread.interrupt();//When this is made the next time while loop is checked it will come out of it
            }
        }
        throw new RuntimeException("Goodbye Cruel World");
    }
}

class DateFormatterThreadLocal extends ThreadLocal<SimpleDateFormat> {
    protected SimpleDateFormat initialValue() {
        return new SimpleDateFormat("EEE MMM d, hh:mm:ss");
    }
}

class DatePrinter implements Runnable {
    public String formatType;

    public DatePrinter(String formatType) {
        this.formatType = formatType;
    }

    @Override
    public void run() {
        if (formatType.equalsIgnoreCase("Format One")) {
            System.out.println("Changing the format to show only time");
            ZOneCreatingAndManagingThreads.dateFormatterThreadLocal.get().applyPattern("hh:mm:ss");
        }
        int counter = 0;
        while (true) {
            try {
                if (counter == 5) {
                    TimeUnit.SECONDS.sleep(2);
                    break;
                }
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {

            }
            Date now = new Date();
            System.out.println(formatType + ": " + ZOneCreatingAndManagingThreads.dateFormatterThreadLocal.get().format(now));
            counter++;
        }
    }
}