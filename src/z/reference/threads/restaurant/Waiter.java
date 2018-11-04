package z.reference.threads.restaurant;

public class Waiter implements Runnable {
    private final String name;
    private Request currentRequest;

    public Waiter(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " has shown up for work today.");
        while (!Restaurant.barClosed || Restaurant.customersInBarCount.get() > 0) {
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
        System.out.println(name + " is waiting for the customer request.");
        synchronized (Restaurant.requests) {
            while (true) {
                currentRequest = Restaurant.requests.poll();
                if (currentRequest != null) {
                    break;
                }
                if (Restaurant.customersInBarCount.get() == 0) {
                    if (Restaurant.barClosed) {
                        break;
                    }
                }
                try {
                    Restaurant.requests.wait();
                } catch (InterruptedException e) {

                }
            }
        }
        if (currentRequest != null) {
            System.out.println(name + " has a request from "
                    + currentRequest.getCustomer().getName()
                    + " : " + currentRequest.getRequestType());
        }
    }

    private void seatCustomer() {
        System.out.println(name + " is seating the customer.");
    }

    private void takeOrder() {
        System.out.println(name + " is taking an order.");
    }

    private void serveCustomer() {
        System.out.println(name + " is serving food.");
    }

    private void getCheque() {
        System.out.println(name + " is getting the cheque.");
    }
}
