package z.reference.threads;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class ZFourConcurrencyWithMultiThreading {
    public static final int TIME_SCALER = 10;
    private static final int numberOfWaiters = 5;
    private static final int numberOfCustomers = 50;
    public static final int openingTime = 360;
    public static volatile boolean closed = true;
    public static final AtomicInteger numberOfCustomersInBar = new AtomicInteger();
    public static final LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Thread[] waiters = new Thread[ numberOfWaiters ];
        Thread[] customers = new Thread[ numberOfCustomers ];
        for (int i = 0; i < numberOfWaiters; i++) {
            Waiter waiter = new Waiter("Waiter " + (i + 1));
            waiters[ i ] = new Thread(waiter, waiter.getName());
        }

        for (int i = 0; i < numberOfCustomers; i++) {
            Guest guest = new Guest("Guest " + (i + 1));
            customers[ i ] = new Thread(guest, guest.getName());
        }

        System.out.println("Restaurant is Opening");
        closed = false;
        for (int i = 0; i < numberOfWaiters; i++) {
            waiters[ i ].start();
        }
        System.out.println("Restaurant is starting to let customers in");
        for (int i = 0; i < numberOfCustomers; i++) {
            customers[ i ].start();
        }

        try {
            Thread.sleep(openingTime * TIME_SCALER);
        } catch (InterruptedException e) {

        }
        System.out.println("Restaurant is closing for new customers");
        closed = true;
        for (int i = 0; i < numberOfCustomers; i++) {
            customers[ i ].interrupt();
        }

        while (numberOfCustomersInBar.get() > 0) {
            try {
                Thread.sleep(20 * TIME_SCALER);
            } catch (InterruptedException e) {

            }
        }

        for (int i = 0; i < numberOfWaiters; i++) {
            waiters[ i ].interrupt();
        }

        System.out.println("Restaurant is closed");
    }
}

class Waiter implements Runnable {
    private final String name;
    private volatile Request currentRequest;

    public Waiter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println(name + " has shown up for work");
        while (!ZFourConcurrencyWithMultiThreading.closed || ZFourConcurrencyWithMultiThreading.numberOfCustomersInBar.get() > 0) {
            waitForCustomerRequest();
            if (currentRequest != null) {
                switch (currentRequest.getRequestType()) {
                    case Request.SEATING_REQUEST:
                        seatCustomer();
                        break;
                    case Request.ORDER_REQUEST:
                        takeOrder();
                        break;
                    case Request.SERVE_REQUEST:
                        serveCustomer();
                        break;
                    case Request.CHEQUE_REQUEST:
                        getCheque();
                        break;
                    default:
                        break;
                }
                synchronized (currentRequest) {
                    currentRequest.setRequestBeenHandled();
                    currentRequest.notify();
                }
            }
        }
        System.out.println(name + " is going home.");
    }

    private void waitForCustomerRequest() {
        currentRequest = null;
        while (true) {
            try {
                currentRequest = ZFourConcurrencyWithMultiThreading.requests.take();
            } catch (InterruptedException e) {

            }
            if (currentRequest != null) {
                break;
            }
            if (ZFourConcurrencyWithMultiThreading.numberOfCustomersInBar.get() == 0) {
                if (ZFourConcurrencyWithMultiThreading.closed) {
                    break;
                }
            }
        }
        if (currentRequest != null) {
            System.out.println(name + " has a request from " + currentRequest.getGuest().getName() + ": " + currentRequest.getRequestType());
        }
    }

    private void seatCustomer() {
        System.out.println(name + " is seating a customer");
    }

    private void takeOrder() {
        System.out.println(name + " is taking an order");
    }

    private void serveCustomer() {
        System.out.println(name + " is serving food");
    }

    private void getCheque() {
        System.out.println(name + " is getting a cheque");
    }
}

class Guest implements Runnable {
    private final String name;

    public Guest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        arrive();
        ZFourConcurrencyWithMultiThreading.numberOfCustomersInBar.incrementAndGet();
        if (Thread.interrupted() || ZFourConcurrencyWithMultiThreading.closed) {
            System.out.println(name + " has arrived late. The restaurant is closed for new comers");
            ZFourConcurrencyWithMultiThreading.numberOfCustomersInBar.decrementAndGet();
            return;
        }
        waitToBeSeated();
        orderFood();
        waitForMeal();
        eatMeal();
        chequeRequest();
        System.out.println(name + " has left.");
        ZFourConcurrencyWithMultiThreading.numberOfCustomersInBar.decrementAndGet();
    }

    private void arrive() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        int timeBeforeArrival = Math.abs(threadLocalRandom.nextInt()) % (ZFourConcurrencyWithMultiThreading.openingTime + 60);
        try {
            Thread.sleep(timeBeforeArrival * ZFourConcurrencyWithMultiThreading.TIME_SCALER);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(this.name + ": has arrived at the restaurant");
    }

    private void waitToBeSeated() {
        CustomerRequestHandler.act(this, Request.SEATING_REQUEST);
    }

    private void orderFood() {
        CustomerRequestHandler.act(this, Request.ORDER_REQUEST);
    }

    private void waitForMeal() {
        CustomerRequestHandler.act(this, Request.SERVE_REQUEST);
    }

    private void eatMeal() {
        System.out.println(name + ": is eating");
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        int eatingTime = threadLocalRandom.nextInt() % 30 + 30;
        long doneEatingTime = System.currentTimeMillis() + eatingTime * ZFourConcurrencyWithMultiThreading.TIME_SCALER;
        long timeLeft;
        while ((timeLeft = doneEatingTime - System.currentTimeMillis()) > 0) {
            try {
                Thread.sleep(timeLeft);
            } catch (InterruptedException e) {

            }
        }
    }

    private void chequeRequest() {
        CustomerRequestHandler.act(this, Request.CHEQUE_REQUEST);
    }

    public String getName() {
        return name;
    }
}

class Request {
    public static final String SEATING_REQUEST = "SEAT";
    public static final String ORDER_REQUEST = "ORDER";
    public static final String SERVE_REQUEST = "SERVE";
    public static final String CHEQUE_REQUEST = "CHEQUE";

    private final Guest guest;
    private final String requestType;
    private boolean requestBeenHandled;

    public Request(Guest guest, String requestType) {
        this.guest = guest;
        this.requestType = requestType;
    }

    public Guest getGuest() {
        return guest;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestBeenHandled() {
        this.requestBeenHandled = true;
    }

    public boolean getRequestBeenHandled() {
        return requestBeenHandled;
    }
}

class CustomerRequestHandler {
    public static void placeRequest(Request request) {
        ZFourConcurrencyWithMultiThreading.requests.offer(request);
    }

    public static String getPreRequestMessage(String requestType) {
        switch (requestType) {
            case Request.SEATING_REQUEST:
                return " is requesting to be seated";
            case Request.ORDER_REQUEST:
                return " is requesting to order";
            case Request.SERVE_REQUEST:
                return " is requesting to be served";
            case Request.CHEQUE_REQUEST:
                return " is requesting for the cheque";
            default:
                return " is... blabbering something";
        }
    }

    public static String postRequestMessage(String requestType) {
        switch (requestType) {
            case Request.SEATING_REQUEST:
                return " has been seated";
            case Request.ORDER_REQUEST:
                return " order has been handled";
            case Request.SERVE_REQUEST:
                return " has been served";
            case Request.CHEQUE_REQUEST:
                return " request for cheque is provided";
            default:
                return " is... blabbering something";
        }
    }

    public static void act(Guest guest, String requestType) {
        Request request = new Request(guest, requestType);
        System.out.println(guest.getName() + getPreRequestMessage(requestType));
        placeRequest(request);
        waitForRequestToBeHandled(request);
        System.out.println(guest.getName() + postRequestMessage(requestType));
    }

    public static void waitForRequestToBeHandled(Request request) {
        synchronized (request) {
            while (!request.getRequestBeenHandled()) {
                try {
                    request.wait();
                } catch (InterruptedException e) {

                }
            }
        }
    }
}