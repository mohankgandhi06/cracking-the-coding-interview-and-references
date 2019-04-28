package r.chapter.hardDifficulty;

public class FCountOf2s {
    public static void main(String[] args) {
        FCountOf2s game = new FCountOf2s();
        int number = 25;
        System.out.println("Number of 2s in between: 1 and " + number + " is: " + game.countOf2Until(number));
    }

    private int countOf2Until(int number) {
        /* Count the no of 2's occurring in between in the interval 1 to n */
        /* 0,  1,  2,  3,  4,  5,  6,  7,  8,  9,
           10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
           20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
           30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
           40, 41, 42, 43, 44, 45, 46, 47, 48, 49
           ... 99

           If you look at the 2 based on each digit it will be 1/10th
           i.e. in unit's digit 2, 12, 22, 32, 42, 52, 62, 72, 82, 92
           10/100 = 1/10

           If you look at the 2 based on ten's digit 20, 21, 22, 23, 24, 25, 26, 27, 28, 29
           10/100 = 1/10

           100, ... 199

           So now when we are considering the numbers between a range first we should split
           in into the multiples of 10 and we would require to round down and round up
           based on the following three <2, =2, >2

           lets consider for simplicity 1 - 3121
           0 - 99(10), 100 - 999(10), .. 1000 - 1999, 2000 - 2999, 3000 - 3999 (3000 - 3121)
           3121 - (3121 % 10^(0+1)) = 3120 / 10 = 312

           in units place
           <2 = (round down (10^d+1))/10, d = 0
           =2 = round down + right + 1
           >2 = (round up (10^d+1))/10, d = 0

           roundDown = number - (number % nextPower10); 61356 - (61356 % 10000) = 61356 - 1356 = 60000 (i.e., d = 3, power10 = 10^3 = 1000, nextPower10 = 10000)
           roundUp = roundDown + nextPower10; 60000 + 10000 = 70000

           roundDown = 62356 = 62356 - (62356 % 10000); = 60000
           right = number % power10; 62356 % 1000 = 356
           roundDown/10 + right + 1 => 6000 + 357 = 6357
        */
        int total2S = 0;
        String num = String.valueOf(number);
        for (int d = 0; d < num.length(); d++) {
            total2S += countThePower(number, d);
        }
        return total2S;
    }

    private int countThePower(int number, int d) {
        double power10 = Math.pow(10, d);
        double nextPower10 = power10 * 10;
        double right = number % power10;

        int roundDown = (int) (number - (number % nextPower10));
        int roundUp = (int) (roundDown + nextPower10);

        int digit = (int) ((number / power10) % 10);
        if (digit < 2) {
            return roundDown / 10;
        } else if (digit > 2) {
            return roundUp / 10;
        } else {
            return (int) ((roundDown / 10) + right + 1);
        }
    }
}