package z.reference.threads.barRaceCondition;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class EnglishBar {
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

        public static void main(String[] args) {

        }

        public Friend(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            enterBar();
        }

        private void enterBar() {
            System.out.println(this.name + " Entered Bar");
            /*if (!alreadyBought) {
                *//* TODO Only for testing out the racing condition.
                 * Have to remove the try and catch block *//*
                try {
                    countDownLatch.countDown();
                    countDownLatch.await();
                } catch (InterruptedException e) {

                }
                alreadyBought = true;
                buyDrinks();
            }*/

            /*if (!alreadyBought) {
                alreadyBought = true;
                buyDrinks();
            } else {
                waitingForDrinks();
            }*/

            /* Using Mutex */
            /*boolean shouldDrinksBeBought = false;

            synchronized (mutex) {
                if (!alreadyBought) {
                    alreadyBought = true;
                    shouldDrinksBeBought = true;
                }
            }

            if (shouldDrinksBeBought) {
                buyDrinks();
            } else {
                waitingForDrinks();
            }*/

            try {
                countDownLatch.countDown();
                countDownLatch.await();
            } catch (InterruptedException e) {

            }

            if (roundBeingBought.compareAndSet(false, true)) {
                buyDrinks();
            } else {
                waitingForDrinks();
            }
        }

        private void buyDrinks() {
            System.out.println(this.name + " is buying the Rounds today");
        }

        private void waitingForDrinks() {
            System.out.println(this.name + " is waiting for the Drinks");
        }
    }
}