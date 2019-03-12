package p.chapter.dynamicProgramming;

public class ERecursiveMultiply {
    public static void main(String[] args) {
        ERecursiveMultiply game = new ERecursiveMultiply();
        int first = 31;
        int second = 35;
        int result = game.solve(first, second);
    }

    private int solve(int first, int second) {
        int smaller = first > second ? second : first;
        int larger = first > second ? first : second;
        return multiply(smaller, larger);
    }

    private int multiply(int smaller, int larger) {
        if (smaller == 0) return 0;
        if (smaller == 1) return larger;
        /* When smaller becomes 1 we are multiplying 1 with
         * the larger number i.e. 1*larger = larger so we return the larger directly */
        /* Else we can divide it further by 2. For Diving by 2 we can >> 1 operator */
        int divBy2 = smaller >> 1;
        int sum = multiply(divBy2, larger);
        /* The below logic can be best explained using the example
         * two scenarios we can take multiply
         * Scenario #1: (4, 3) -> (2+2, 3) -> (1+1+1+1, 3). Here when it becomes 1 instead of returning 1 we are going to return the bigger number
         * so (3+3+3+3). Here since the digit 2 was entirely divisible by with a remainder 1 we are just adding sum + sum. otherwise
         * Scenario #2: (5, 6) -> (2+2+1, 6) -> (1+1+1+1+1, 6). Here in the second step we got 2+2+1 since it was a odd number, and since we get an
         * extra 1 we are adding the bigger number to it along with the result of (2,6) + (2,6) */
        if (smaller % 2 == 0) {
            return sum + sum;
        } else {
            return sum + sum + larger;
        }
    }
}