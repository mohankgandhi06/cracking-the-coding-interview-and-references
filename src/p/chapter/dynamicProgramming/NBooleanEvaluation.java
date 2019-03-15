package p.chapter.dynamicProgramming;

public class NBooleanEvaluation {
    public static void main(String[] args) {
        NBooleanEvaluation game = new NBooleanEvaluation();
        String operation = "1^0|0|1";
        boolean expectedResult = false;
        System.out.println("Expression: " + operation + " Expected Result: " + expectedResult);
        game.solve(operation, expectedResult);
        game.solveWithMemoization(operation, expectedResult);

        operation = "0&0&0&1^1|0";
        expectedResult = true;
        System.out.println();
        System.out.println("Expression: " + operation + " Expected Result: " + expectedResult);
        game.solve(operation, expectedResult);
        game.solveWithMemoization(operation, expectedResult);
    }

    private void solve(String operation, boolean expectedResult) {
        int count = countEval(operation, expectedResult);
        System.out.println("(Brute Force) count of ways we could parenthesis the expression: " + count);
        return;
    }

    private int countEval(String expression, boolean expectedResult) {
        if (expression.length() == 0) return 0;
        if (expression.length() == 1) return evaluate(expression) == expectedResult ? 1 : 0;
        int ways = 0;
        for (int i = 1; i < expression.length(); i = i + 2) {
            /* Splitting the Expression before and after the operator */
            char operator = expression.charAt(i);
            String left = expression.substring(0, i);
            String right = expression.substring(i + 1, expression.length());
            int leftTrue = countEval(left, true);
            int leftFalse = countEval(left, false);
            int rightTrue = countEval(right, true);
            int rightFalse = countEval(right, false);
            int totalTrueAndFalseCombination = (leftTrue + leftFalse) * (rightTrue + rightFalse);

            int totalTrueCombination = 0;
            if (operator == '^') {//EXOR Operation
                totalTrueCombination = performEXOR(leftTrue, leftFalse, rightTrue, rightFalse);
            } else if (operator == '&') {//AND Operation
                totalTrueCombination = performAND(leftTrue, rightTrue);
            } else if (operator == '|') {//OR Operation
                totalTrueCombination = performOR(leftTrue, leftFalse, rightTrue, rightFalse);
            }

            /* Here we are checking what we need to return. The method's expected outcome can be true or false, and if it is true
             * we could include both the true and false combination of the subsequent calls that we have made above. But if it is false
             * we need to segregate the false alone so we minus the totalTrue from the totalTrueAndFalse combination */
            int subways = expectedResult ? totalTrueCombination : (totalTrueAndFalseCombination - totalTrueCombination);
            ways = ways + subways;
        }
        return ways;
    }

    private void solveWithMemoization(String operation, boolean expectedResult) {
        int[][] memo = new int[ operation.length() + 1 ][ 2 ];
        int count = countEvalMemoized(operation, expectedResult, memo);
        System.out.println("(Memoization) count of ways we could parenthesis the expression: " + count);
        return;
    }

    private int countEvalMemoized(String expression, boolean expectedResult, int[][] memo) {
        if (expression.length() == 0) return 0;
        if (expression.length() == 1) return evaluate(expression) == expectedResult ? 1 : 0;
        if (memo[ expression.length() ][ expectedResult ? 1 : 0 ] == 0) {
            int ways = 0;
            for (int i = 1; i < expression.length(); i = i + 2) {
                int k = 0;
                /* Splitting the Expression before and after the operator */
                char operator = expression.charAt(i);
                String left = expression.substring(0, i);
                String right = expression.substring(i + 1, expression.length());
                int leftTrue = countEval(left, true);
                int leftFalse = countEval(left, false);
                int rightTrue = countEval(right, true);
                int rightFalse = countEval(right, false);
                int totalTrueAndFalseCombination = (leftTrue + leftFalse) * (rightTrue + rightFalse);

                int totalTrueCombination = 0;
                if (operator == '^') {//EXOR Operation
                    totalTrueCombination = performEXOR(leftTrue, leftFalse, rightTrue, rightFalse);
                } else if (operator == '&') {//AND Operation
                    totalTrueCombination = performAND(leftTrue, rightTrue);
                } else if (operator == '|') {//OR Operation
                    totalTrueCombination = performOR(leftTrue, leftFalse, rightTrue, rightFalse);
                }

                /* Here we are checking what we need to return. The method's expected outcome can be true or false, and if it is true
                 * we could include both the true and false combination of the subsequent calls that we have made above. But if it is false
                 * we need to segregate the false alone so we minus the totalTrue from the totalTrueAndFalse combination */
                int subways = expectedResult ? totalTrueCombination : (totalTrueAndFalseCombination - totalTrueCombination);
                ways = ways + subways;
            }
            memo[ expression.length() ][ expectedResult ? 1 : 0 ] = ways;
        }
        return memo[ expression.length() ][ expectedResult ? 1 : 0 ];
    }

    private boolean evaluate(String character) {
        return character.equals("1") ? true : false;
    }

    private int performOR(int leftTrue, int leftFalse, int rightTrue, int rightFalse) {
        /* Since we have to take the combination for a OR operation we consider the state which will lead to true i.e 1 1, 1 0, 0 1 */
        int combination = (leftTrue * rightTrue) + (leftFalse * rightTrue) + (leftTrue * rightFalse);
        return combination;
    }

    private int performAND(int leftTrue, int rightTrue) {
        /* Since we have to take the combination of the leftTrue 1 and rightTrue 1 alone since that only will return true for a AND operation */
        int combination = leftTrue * rightTrue;
        return combination;
    }

    private int performEXOR(int leftTrue, int leftFalse, int rightTrue, int rightFalse) {
        /* Since we have to take the combination for a XOR operation we consider the state which will lead to true i.e 1 0, 0 1 */
        int combination = (leftTrue * rightFalse) + (leftFalse * rightTrue);
        return combination;
    }
}