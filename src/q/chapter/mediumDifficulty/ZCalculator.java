package q.chapter.mediumDifficulty;

import java.util.HashMap;
import java.util.Stack;

public class ZCalculator {

    public HashMap<Character, Integer> operatorAndPriority;

    public ZCalculator() {
        this.operatorAndPriority = new HashMap<>();
        populateOperatorAndPriority(this.operatorAndPriority);
    }

    public static void main(String[] args) {
        ZCalculator game = new ZCalculator();
        String[] input = new String[]{
                "2", "*", "3", "+", "5", "/", "6", "*", "3", "+", "15"
        };
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : input) {
            stringBuilder.append(s + " ");
        }
        System.out.println("Equation: " + stringBuilder.toString());
        System.out.println(game.solve(input));

        input = new String[]{
                "8", "/", "4", "/", "2", "-", "1", "+", "3", "*", "6", "+", "15"
        };
        stringBuilder = new StringBuilder();
        for (String s : input) {
            stringBuilder.append(s + " ");
        }
        System.out.println("\nEquation: " + stringBuilder.toString());
        System.out.println(game.solve(input));

        input = new String[]{
                "8", "*", "4", "/", "2", "+", "1", "/", "3", "*", "2", "+", "15"
        };
        stringBuilder = new StringBuilder();
        for (String s : input) {
            stringBuilder.append(s + " ");
        }
        System.out.println("\nEquation: " + stringBuilder.toString());
        System.out.println(game.solve(input));

        input = new String[]{
                "10", "/", "5", "*", "2", "-", "1", "*", "3", "+", "6", "-", "15"
        };
        stringBuilder = new StringBuilder();
        for (String s : input) {
            stringBuilder.append(s + " ");
        }
        System.out.println("\nEquation: " + stringBuilder.toString());
        System.out.println(game.solve(input));

        input = new String[]{
                "10", "/", "5", "*", "2", "-", "1", "*", "6", "+", "6", "-", "15"
        };
        stringBuilder = new StringBuilder();
        for (String s : input) {
            stringBuilder.append(s + " ");
        }
        System.out.println("\nEquation: " + stringBuilder.toString());
        System.out.println(game.solve(input));
    }

    private double solve(String[] input) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> sign = new Stack<>();
        int i = 0;
        Character previousOperation = ' ';
        while (i < input.length) {
            if (isNumeric(input[ i ])) {/* Process Digit */
                /*System.out.println("Digit Push: " + input[ i ]);*/
                numbers.push(Double.valueOf(input[ i ]));
                if (i < input.length - 1 && !isCurrentIsLessThanPrevious(input[ i + 1 ].charAt(0), previousOperation)) {/* Sign is not of high priority */
                    i++;
                    continue;
                } else {
                    /* Pop the latest two elements from the number stack and perform the operation in the operation stack and save the value back in the stack
                     * and continue inserting the current operation and proceed to the next */
                    double secondNumber = numbers.pop();
                    double firstNumber = numbers.pop();
                    Character operation = sign.pop();
                    /*System.out.println("Remaining: " + numbers.size());
                    System.out.println("Operation: " + firstNumber + " " + operation + " " + secondNumber);*/
                    double result = performOperation(firstNumber, secondNumber, operation);
                    previousOperation = sign.size() > 0 ? sign.peek() : ' ';/* Updating the previous operation to the previous the current previous which has been popped */
                    numbers.push(result);
                    /*if (previousOperation != ' ') {
                        sign.push(previousOperation);
                    }*/
                    /*System.out.println("Digit Push: " + result);*/
                }

            } else {
                if (previousOperation != ' ' && i < input.length - 1 && isCurrentIsLessThanPrevious(input[ i ].charAt(0), previousOperation)) {/* Sign is not of high priority */
                    int k = 0;
                    double secondNumber = numbers.pop();
                    double firstNumber = numbers.pop();
                    Character operation = sign.pop();
                    /*System.out.println("Remaining Number: " + numbers.size());
                    System.out.println("Remaining Sign: " + sign.size());
                    System.out.println("Operation -: " + firstNumber + " " + operation + " " + secondNumber);*/
                    double result = performOperation(firstNumber, secondNumber, operation);
                    previousOperation = sign.size() > 0 ? sign.peek() : ' ';/* Updating the previous operation to the previous the current previous which has been popped */
                    /*System.out.println("Previous Operation : "+previousOperation);*/
                    numbers.push(result);
                    /*if (previousOperation != ' ') {
                        sign.push(previousOperation);
                    }*/
                    /*System.out.println("Digit Push below: " + result);*/
                    i--;
                } else {
                    /*System.out.println("Sign Push: " + input[ i ]);*/
                    sign.push(input[ i ].charAt(0));
                    previousOperation = input[ i ].charAt(0);
                }
            }
            i++;
        }
        /* Perform Operations for the remaining stack numbers */
        double result = 0;
        while (!numbers.empty() && !sign.empty() && numbers.size() > 1) {
            double secondNumber = numbers.pop();
            double firstNumber = numbers.pop();
            Character operation = sign.pop();
            result = performOperation(firstNumber, secondNumber, operation);
        }
        if (numbers.size() == 1) {
            result = numbers.pop();
        }
        return result;
    }

    private double performOperation(double firstNumber, double secondNumber, Character operation) {
        double total = 0;
        switch (operation) {
            case '+':
                total = firstNumber + secondNumber;
                break;
            case '-':
                total = firstNumber - secondNumber;
                break;
            case '*':
                total = firstNumber * secondNumber;
                break;
            case '/':
                total = firstNumber / secondNumber;
                break;
        }
        return total;
    }

    private boolean isCurrentIsLessThanPrevious(Character currentOperation, Character previousOperation) {
        if (this.operatorAndPriority.get(previousOperation) >= this.operatorAndPriority.get(currentOperation)) {
            return true;
        }
        return false;
    }

    private void populateOperatorAndPriority(HashMap<Character, Integer> operatorAndPriority) {
        operatorAndPriority.put('+', 1);
        operatorAndPriority.put('-', 1);
        operatorAndPriority.put('*', 2);
        operatorAndPriority.put('/', 2);
        operatorAndPriority.put(' ', -1);
    }

    private boolean isNumeric(String input) {
        String string = input;
        boolean numeric = true;
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        return numeric;
    }
}