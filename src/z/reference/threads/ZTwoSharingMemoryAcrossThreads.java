package z.reference.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZTwoSharingMemoryAcrossThreads {
    public static void main(String[] args) {
        /* Normal Implementation - Here there is no guarantee that the latest produce will always be shown
         * When the thread producer is producing the value and at the same time consumer thread access the latest
         * produce there is a possibility of invariant data */
        /*Producer producer = new Producer();
        Consumer consumer = new Consumer(producer);
        Thread producerThread = new Thread(producer, "Producer Thread");
        Thread consumerThread = new Thread(consumer, "Consumer Thread");

        producerThread.start();
        consumerThread.start();*/

        /* To Overcome this we are using volatile keyword along with builder pattern to implement in a clean manner */
        ProducerOptimal producer = new ProducerOptimal();
        ConsumerOptimal consumer = new ConsumerOptimal();

        producer.registerObserver(consumer);

        Thread producerThread = new Thread(producer, "Producer Thread");
        Thread consumetThread = new Thread(consumer, "Consumer Thread");

        producerThread.start();
        consumetThread.start();
    }
}

/* Normal Producer and Consumer Implementations */
class Producer implements Runnable {

    private Produce latestProduce = null;

    public Produce getLatestProduce() {
        return latestProduce;
    }

    @Override
    public void run() {
        System.out.println("Producer Starting");
        Produce produce = new Produce();
        for (int i = 1; i <= 10; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(600);
            } catch (InterruptedException e) {

            }
            produce.setInstance(i);
            produce.setColor(Produce.Color.values()[ i % Produce.Color.values().length ]);
            latestProduce = produce;
        }
        System.out.println("Producer Terminating");
    }
}

class Consumer implements Runnable {

    public Producer producer;

    public Consumer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        System.out.println("Consumer Starting");
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(600);
            } catch (InterruptedException e) {

            }
            Produce produce = producer.getLatestProduce();
            if (produce != null) {
                int productInstance = produce.getInstance();
                Produce.Color color = produce.getColor();

                System.out.println("Last Produce Instance: " + productInstance);
                System.out.println("Last Produce Color: " + color.name());

                if (productInstance == 10) {
                    break;
                }
            }
        }
    }
}

class Produce {
    enum Color {Red, Blue, Green, Yellow}

    private int instance = 0;
    private Color color;

    public void setInstance(int instance) {
        this.instance = instance;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getInstance() {
        return instance;
    }

    public Color getColor() {
        return color;
    }
}

/* Using Design Patterns - Observer and Builder Patterns */
class ProducerOptimal implements Runnable {

    private volatile List<ProduceObserver> observers = new ArrayList<>();

    public void registerObserver(ConsumerOptimal consumer) {
        observers.add(consumer);
    }

    @Override
    public void run() {
        System.out.println("Producer Starting");
        for (int i = 1; i <= 10; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {

            }
            ProduceOptimal.ProduceBuilder builder = new ProduceOptimal.ProduceBuilder();
            builder.withInstance(i);
            builder.withColor(ProduceOptimal.Color.values()[ i % ProduceOptimal.Color.values().length ]);
            ProduceOptimal latestProduce = builder.build();
            observers.forEach(observer -> observer.onProduction(latestProduce));
        }
        System.out.println("Producer Terminating: Production Complete");
    }
}

class ConsumerOptimal implements Runnable, ProduceObserver {
    private volatile ProduceOptimal produce = null;

    @Override
    public void onProduction(ProduceOptimal produce) {
        this.produce = produce;
    }

    @Override
    public void run() {
        System.out.println("Consumer Starting");
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                //Do Nothing
            }
            if (produce != null) {
                int produceInstance = produce.getInstance();
                ProduceOptimal.Color produceColor = produce.getColor();

                System.out.println("Latest Produce Instance: " + produceInstance);
                System.out.println("Latest Produce Color: " + produceColor);

                if (produceInstance == 10) {
                    break;
                }
            }
        }
        System.out.println("Consumer Terminated: Production Queue is full");
    }
}

interface ProduceObserver {
    void onProduction(ProduceOptimal produce);
}

class ProduceOptimal {
    enum Color {red, blue, green, yellow, gray, orange, purple, pink, black, white}

    private final int instance;
    private final Color color;

    private ProduceOptimal(int instance, Color color) {
        this.instance = instance;
        this.color = color;
    }

    public static class ProduceBuilder {
        private int instance;
        private Color color;

        public void withInstance(int instance) {
            this.instance = instance;
        }

        public void withColor(Color color) {
            this.color = color;
        }

        public ProduceOptimal build() {
            return new ProduceOptimal(this.instance, this.color);
        }
    }

    public int getInstance() {
        return instance;
    }

    public Color getColor() {
        return color;
    }
}