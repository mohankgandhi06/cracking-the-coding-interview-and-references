package q.chapter.mediumDifficulty;

public class IOperations {
    public static void main(String[] args) {
        IOperations math = new IOperations();
        int a = -12;
        int b = -3;
        char operation = '/';
        math.performOperation(a, b, operation);
    }

    private void performOperation(int a, int b, char operation) {
        int result = solve(a, b, operation);
        System.out.println("Mathematical");
        System.out.println(a + " " + operation + " " + b + " : " + result);
    }

    private int solve(int a, int b, char operation) {
        int result = 0;
        switch (operation) {
            case '+':
                result = performAddition(a, b);
                break;
            case '-':
                result = performSubtraction(a, b);
                break;
            case '*':
                result = performMultiplication(a, b);
                break;
            case '/':
                result = performDivision(a, b);
                break;
            default:
                result = -1;
        }
        return result;
    }

    private int performAddition(int a, int b) {
        return a + b;
    }

    private int performSubtraction(int a, int b) {
        /* ((~0) ^ b) + 1 - this equation is called two's complement */
        int flippedB = ((~0) ^ b) + 1;
        return a + flippedB;
    }

    private int performMultiplication(int a, int b) {
        /* Repeated Addition of the number is multiplication */
        int result = 0;
        for (int count = 0; count < b; count++) {
            result += a;
        }
        return result;
    }

    private int performDivision(int a, int b) {
        if (b == 0) {
            return -1;
        }
        /* For Negative numbers we need to perform normal division then add the negative sign if one is negative and plus sign if both are positive */
        int aMsb = (a & (1 << 31)) >>> 31;
        int bMsb = (b & (1 << 31)) >>> 31;
        int flippedA = 0;
        int flippedB = 0;
        char sign = '+';
        if (aMsb == 1 && bMsb == 1) {
            flippedA = ((~0) ^ a) + 1;
            flippedB = ((~0) ^ b) + 1;
        } else if (aMsb == 1) {
            flippedA = ((~0) ^ a) + 1;
            flippedB = b;
            sign = '-';
        } else if (bMsb == 1) {
            flippedA = a;
            flippedB = ((~0) ^ b) + 1;
            sign = '-';
        } else {
            flippedA = a;
            flippedB = b;
        }
        /*System.out.println("A: " + flippedA + " B: " + flippedB + " Sign: " + sign);*/
        /* Repeated Subtraction of the number until we get zero */
        int temp = flippedA;
        int result = 0;
        int tempB = ((~0) ^ flippedB) + 1;
        while (temp > 0) {
            temp = temp + tempB;
            if (temp >= 0) {
                result++;
            }
        }
        if (sign == '-') {
            result = ((~0) ^ result) + 1;;
        }
        return result;
    }
}