package q.chapter.mediumDifficulty;

public class EFactorialZero {
    public static void main(String[] args) {
        EFactorialZero findTrailingZeros = new EFactorialZero();
        int factorial = 25;
        int trailingZeros = findTrailingZeros.numberOfTrailingZeros(factorial);
        System.out.println("Factorial: " + factorial);
        System.out.println("Number of Trailing Zero's: " + trailingZeros);
        trailingZeros = findTrailingZeros.countFactorialOf5Efficient(factorial);
        System.out.println("Number of Trailing Zero's Efficient: " + trailingZeros);

        System.out.println();
        factorial = 5;
        trailingZeros = findTrailingZeros.numberOfTrailingZeros(factorial);
        System.out.println("Factorial: " + factorial);
        System.out.println("Number of Trailing Zero's: " + trailingZeros);
        trailingZeros = findTrailingZeros.countFactorialOf5Efficient(factorial);
        System.out.println("Number of Trailing Zero's Efficient: " + trailingZeros);

        System.out.println();
        factorial = 20;
        trailingZeros = findTrailingZeros.numberOfTrailingZeros(factorial);
        System.out.println("Factorial: " + factorial);
        System.out.println("Number of Trailing Zero's: " + trailingZeros);
        trailingZeros = findTrailingZeros.countFactorialOf5Efficient(factorial);
        System.out.println("Number of Trailing Zero's Efficient: " + trailingZeros);

        System.out.println();
        factorial = 100;
        trailingZeros = findTrailingZeros.numberOfTrailingZeros(factorial);
        System.out.println("Factorial: " + factorial);
        System.out.println("Number of Trailing Zero's: " + trailingZeros);
        trailingZeros = findTrailingZeros.countFactorialOf5Efficient(factorial);
        System.out.println("Number of Trailing Zero's Efficient: " + trailingZeros);
    }

    private int numberOfTrailingZeros(int factorial) {
        int count = 0;
        for (int n = 5; n <= factorial; n++) {
            count += countFactorialsOf5(n);
        }
        return count;
    }

    private int countFactorialsOf5(int n) {
        int count = 0;
        while (n % 5 == 0) {
            count++;
            n = n / 5;
        }
        return count;
    }

    private int countFactorialOf5Efficient(int factorial) {
        int count = 0;
        /* Factorial/i(i=multiple of five) only 5 and 25 and 125 and 625 and so on are factors of five */
        for (int i = 5; factorial / i > 0; i = i * 5) {
            count += factorial / i;
        }
        return count;
    }
}