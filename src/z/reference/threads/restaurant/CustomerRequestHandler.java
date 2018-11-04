package z.reference.threads.restaurant;

class CustomerRequestHandler {
    private static void placeRequest(Request request) {
        synchronized (Restaurant.requests) {
            Restaurant.requests.offer(request);
            Restaurant.requests.notify();
        }
    }

    private static void waitForRequestToBeHandled(Request request) {
        synchronized (request) {
            while (!request.isRequestBeenHandled()) {
                try {
                    request.wait();
                } catch (InterruptedException e) {

                }
            }
        }
    }

    private static String getPreRequestMessage(String requestType) {
        switch (requestType) {
            case Request.SEATING_REQUEST:
                return " is requesting to be seated";
            case Request.ORDER_REQUEST:
                return " is requesting to order";
            case Request.SERVE_REQUEST:
                return " is requesting to be served";
            case Request.CHEQUE_REQUEST:
                return " is requesting the bill";
            default:
                return " is... requesting something which I could not comprehend!";
        }
    }

    private static String getPostRequestMessage(String requestType) {
        switch (requestType) {
            case Request.SEATING_REQUEST:
                return "'s and accompanies have been seated";
            case Request.ORDER_REQUEST:
                return "'s orders have been taken";
            case Request.SERVE_REQUEST:
                return "'s orders have been served";
            case Request.CHEQUE_REQUEST:
                return "'s bill has been provided";
            default:
                return "'s request could not be processed";
        }
    }

    public static void act(Customer customer, String requestType) {
        Request request = new Request(customer, requestType);
        System.out.println(customer.getName() + getPreRequestMessage(requestType));
        placeRequest(request);
        waitForRequestToBeHandled(request);
        System.out.println(customer.getName() + getPostRequestMessage(requestType));
    }
}