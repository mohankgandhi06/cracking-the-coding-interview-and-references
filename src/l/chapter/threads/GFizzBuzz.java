package l.chapter.threads;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class GFizzBuzz {
    /* Question:
     * In the classic problem FizzBuzz, you are told to print the numbers from 1 to n. However, when the number is divisible by 3 Fizz
     * When it is divisible by 5 print Buzz.
     * When it is divisible by 3 and 5 print FizzBuzz. In this problem, you are asked
     * to do this in a multithreaded way. Implement a multithreaded version of FizzBuzz with four threads
     * One thread checks for divisibility of 3 and prints Fizz. Another thread is responsible for divisibility of 5 and prints Buzz
     * A third thread is responsible for divisibility of 3 and 5 and prints FizzBuzz. A fourth thread does the numbers. */

    static AtomicInteger count;
    static final Queue<NumberData> queue = new ConcurrentLinkedQueue();

    public static void main(String[] args) {
        int[] numbers = new int[]{3, 2, 5, 7, 8, 15};
        System.out.println("Number of elements entering the FizzBuzz Game: " + numbers.length);
        count = new AtomicInteger(numbers.length);
        Thread[] divisibilityThreads = new Thread[ 4 ];
        divisibilityThreads[ 0 ] = new Thread(new DivBy3(), "DivBy3");
        divisibilityThreads[ 1 ] = new Thread(new DivBy5(), "DivBy5");
        divisibilityThreads[ 2 ] = new Thread(new DivBy3And5(), "DivBy3And5");
        divisibilityThreads[ 3 ] = new Thread(new NotDivBy3And5(), "NotDivBy3And5");
        divisibilityThreads[ 2 ].start();
        divisibilityThreads[ 3 ].start();
        divisibilityThreads[ 0 ].start();
        divisibilityThreads[ 1 ].start();
        for (int i = 0; i < numbers.length; i++) {
            GFizzBuzz.queue.offer(new NumberData(numbers[ i ]));
        }
    }
}

class NumberData {
    private int number;

    public NumberData(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

class DivBy3 extends Divisibility implements Runnable {
    @Override
    public void run() {
        while (GFizzBuzz.count.get() > 0) {
            waitForNextInteger();
            if (currentInteger.getNumber() % 3 == 0 && currentInteger.getNumber() % 5 != 0) {
                synchronized (currentInteger) {
                    synchronized (GFizzBuzz.count) {
                        if (GFizzBuzz.count.get() >= 0) {
                            System.out.println(currentInteger.getNumber() + ": Fizz");
                            GFizzBuzz.count.decrementAndGet();
                            System.out.println(GFizzBuzz.count);
                            try {
                                TimeUnit.MILLISECONDS.sleep(100);
                            } catch (InterruptedException e) {

                            }
                        }
                    }
                }
            } else {
                GFizzBuzz.queue.offer(currentInteger);
            }
        }
    }
}

class DivBy5 extends Divisibility implements Runnable {
    @Override
    public void run() {
        while (GFizzBuzz.count.get() > 0) {
            waitForNextInteger();
            if (currentInteger.getNumber() % 3 != 0 && currentInteger.getNumber() % 5 == 0) {
                synchronized (currentInteger) {
                    synchronized (GFizzBuzz.count) {
                        if (GFizzBuzz.count.get() >= 0) {
                            System.out.println(currentInteger.getNumber() + ": Buzz");
                            GFizzBuzz.count.decrementAndGet();
                            System.out.println(GFizzBuzz.count);
                            try {
                                TimeUnit.MILLISECONDS.sleep(100);
                            } catch (InterruptedException e) {

                            }
                        }
                    }
                }
            } else {
                GFizzBuzz.queue.offer(currentInteger);
            }
        }
    }
}

class DivBy3And5 extends Divisibility implements Runnable {
    @Override
    public void run() {
        while (GFizzBuzz.count.get() > 0) {
            waitForNextInteger();
            if (currentInteger.getNumber() % 3 == 0 && currentInteger.getNumber() % 5 == 0) {
                synchronized (currentInteger) {
                    synchronized (GFizzBuzz.count) {
                        if (GFizzBuzz.count.get() >= 0) {
                            System.out.println(currentInteger.getNumber() + ": FizzBuzz");
                            GFizzBuzz.count.decrementAndGet();
                            System.out.println(GFizzBuzz.count);
                            try {
                                TimeUnit.MILLISECONDS.sleep(100);
                            } catch (InterruptedException e) {

                            }
                        }
                    }
                }
            } else {
                GFizzBuzz.queue.offer(currentInteger);
            }
        }
    }
}

class NotDivBy3And5 extends Divisibility implements Runnable {
    @Override
    public void run() {
        while (GFizzBuzz.count.get() > 0) {
            waitForNextInteger();
            if (currentInteger.getNumber() % 3 != 0 && currentInteger.getNumber() % 5 != 0) {
                synchronized (currentInteger) {
                    synchronized (GFizzBuzz.count) {
                        if (GFizzBuzz.count.get() >= 0) {
                            System.out.println(currentInteger.getNumber() + " is Neither Divisible by 3 or 5");
                            GFizzBuzz.count.decrementAndGet();
                            System.out.println(GFizzBuzz.count);
                            try {
                                TimeUnit.MILLISECONDS.sleep(100);
                            } catch (InterruptedException e) {

                            }
                        }
                    }
                }
            } else {
                GFizzBuzz.queue.offer(currentInteger);
            }
        }
    }
}

class Divisibility {
    public NumberData currentInteger;

    public void waitForNextInteger() {
        synchronized (GFizzBuzz.queue) {
            while (true) {
                if (!GFizzBuzz.queue.isEmpty()) {
                    currentInteger = GFizzBuzz.queue.poll();
                    if (currentInteger.getNumber() != 0) {
                        break;
                    }
                    try {
                        GFizzBuzz.queue.wait();
                    } catch (InterruptedException e) {

                    }
                }
                if (GFizzBuzz.count.get() <= 0) {
                    break;
                }
            }
        }
    }
}