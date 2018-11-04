package z.reference.threads.barRaceCondition;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class EnglishBarArbiter {
    private static volatile int friendsCount;
    private static Arbiter arbiter = new Arbiter();
    private static volatile List<String> friendsList;

    public static void main(String[] args) {
        friendsList = Arrays.asList(
                "Steve",
                "Reggie",
                "Mac",
                "Donald",
                "Trump",
                "Bobby"
        );


        friendsCount = friendsList.size();
        Thread thread = null;
        for (String friend : friendsList) {
            arbiter.register(friend);
            thread = new Thread(new Friend(friend), friend);
            thread.start();
        }

        // Since they exit the bar together, calling isAlive on just one is safe
        while (thread.isAlive()) {
            //displayStatistics(friendsList);
            try {
                TimeUnit.MICROSECONDS.sleep(1000000);
            } catch (InterruptedException e) {
                // Safe to ignore
            }
        }

        System.out.println("Final results:");

        displayStatistics(friendsList);
    }

    public static void displayStatistics(List<String> friendsList) {
        for (int i = 0; i < friendsCount; i++) {
            String name = friendsList.get(i);
            int[] result = arbiter.getNumBoughtAndNumRoundsSoFar(name);
            int roundsBought = result[ 0 ];
            int roundsSoFar = result[ 1 ];
            int percentage = 0;
            if (roundsBought > 0) {
                percentage = (roundsBought * 100) / roundsSoFar;
            }
            System.out.println("After " + roundsSoFar + " rounds, " + name + " has bought " + roundsBought + " rounds( " + percentage + "%).");
        }
    }

    public static class Friend implements Runnable {
        private final String name;
        private static volatile boolean alreadyBought = false;
        //This is to achieve the race condition. i.e. when there is a critical
        // section and there is possibility of thread to work in undesirable
        // manner. In our example when friends are buying at the same time
        // It is undesirable and so we are using only to check if the condition
        // can occur. countDownLatch will wait for the other threads as well to
        // meet at a some point and proceed further.
        private static final CountDownLatch countDownLatch = new CountDownLatch(friendsCount);

        private static final Object mutex = new Object();

        private static final AtomicBoolean roundBeingBought = new AtomicBoolean();

        private final AtomicInteger numRoundsBought = new AtomicInteger();

        private static final Phaser phaser = new Phaser(friendsCount);

        private static final int totalVisitInAMonth = 20;

        public Friend(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int visit = 1; visit <= totalVisitInAMonth; visit++) {
                enterBar();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // Safe to ignore
                }
            }
        }

        private void enterBar() {
            roundBeingBought.set(false);
            phaser.arriveAndAwaitAdvance();
            if (roundBeingBought.compareAndSet(false, true)) {
                buyDrinks(this.name);
                numRoundsBought.getAndIncrement();
            } else {
                waitingForDrinks(this.name);
            }
            phaser.arriveAndAwaitAdvance();
        }

        private void buyDrinks(String name) {
            arbiter.roundsBought(name);
        }

        private void waitingForDrinks(String name) {

        }
    }

    public static class Arbiter {
        private final Hashtable<String, Integer> roundsBought = new Hashtable<>();
        private final AtomicInteger roundsSoFar = new AtomicInteger();

        public void register(String name) {
            roundsBought.put(name, 0);
        }

        public synchronized void roundsBought(String name) {
            int numBought = roundsBought.get(name);
            roundsBought.put(name, numBought + 1);
            roundsSoFar.getAndIncrement();
        }

        public synchronized int[] getNumBoughtAndNumRoundsSoFar(String name) {
            return new int[]{roundsBought.get(name), roundsSoFar.get()};
        }
    }
}