package z.reference.threads.barRaceCondition;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class EnglishBarMultipleVisit {
    private static volatile int friendsCount;

    public static void main(String[] args) {
        List<String> friendsList = Arrays.asList(
                "Steve",
                "Reggie",
                "Mac",
                "Donald",
                "Trump",
                "Bobby"
        );


        friendsCount = friendsList.size();
        for (String friend : friendsList) {
            new Thread(new Friend(friend), friend).start();
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
                /*System.out.println();*/
                /*System.out.println("Visit No.: " + visit);*/
                enterBar(visit);
            }
            int percentage = (numRoundsBought.get() * 100) / totalVisitInAMonth;

            System.out.println(name + " Bought " + numRoundsBought + " rounds (" + percentage + "%).");
        }

        private void enterBar(int visit) {
            roundBeingBought.set(false);
            phaser.arriveAndAwaitAdvance();
            System.out.println(this.name + " Entered Bar");
            if (roundBeingBought.compareAndSet(false, true)) {
                buyDrinks(visit);
                numRoundsBought.getAndIncrement();
            } else {
                waitingForDrinks(visit);
            }
            phaser.arriveAndAwaitAdvance();
        }

        private void buyDrinks(int visit) {
            System.out.println("Visit " + visit + " " + this.name + " is buying the Rounds today");
        }

        private void waitingForDrinks(int visit) {
            System.out.println("Visit " + visit + " " + this.name + " is waiting for the Drinks");
        }
    }
}