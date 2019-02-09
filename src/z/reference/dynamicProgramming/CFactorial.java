package z.reference.dynamicProgramming;

public class CFactorial {
    public static void main(String[] args) {
        Int input = new Int(5, 1);
        int output = factorial(input);
        System.out.println(output);

        System.out.println("Normal Method: " + factorial(5));

        System.out.println("Accumulator Method: " + factorialAccumulator(1, 6));
    }

    /* Efficient Approach */
    public static int factorial(int number) {
        if (number == 1) return 1;
        return number * factorial(number - 1);
    }

    /* Using the long approach by not altering the passed value */
    public static int factorial(Int number) {
        if (number.integer == 1) return number.result;
        number.result = number.result * number.integer;
        return factorial(new Int(number.integer - 1, number.result));
    }

    /* More Easier way of implementing the above approach is like accumulator
     * Here the advantage is that unlike the Efficient Approach we discussed above
     * in this approach we will return the result once base case is achieved and calculate
     * the intermediate results in each step right until the base case */
    public static int factorialAccumulator(int accumulator, int number) {
        if (number == 1) return accumulator;
        return factorialAccumulator(accumulator * number, number - 1);
    }
}

class Int {
    protected int integer;
    protected int result;

    public Int(int integer, int result) {
        this.integer = integer;
        this.result = result;
    }
}