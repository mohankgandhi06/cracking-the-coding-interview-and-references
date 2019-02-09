package z.reference.dynamicProgramming;

public class ASumOfNIntegers {
    public static void main(String[] args) {
        System.out.println("Iterative: " + sumIterative(8));
        System.out.println("Recursive: " + sumRecursive(8));
    }

    public static int sumIterative(int n) {
        int result = 0;
        for (int i = 1; i <= n; ++i) {
            result += i;
        }
        return result;
    }

    public static int sumRecursive(int n) {
        if (n == 1) return 1;
        return n + sumRecursive(n - 1);
    }
}
