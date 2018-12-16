package f.chapter.linkedList;

public class ESumLists {
    /* Question: You have two numbers represented by a linked list, where each node contains
     * a single digit. The digits are stored in reverse order, such that the 1's digit is at
     * the head of the list. Write a function that adds the two numbers and returns the sum
     * as a linked list
     * EXAMPLE:
     * Input    (7  -> 1 -> 6) + (5-> 9 -> 2). That is 617 + 295 = 912
     * Output   (2 -> 1 -> 9)
     * FOLLOW UP:
     * Suppose the digits are stored in the forward order.Repeat the above problem.
     * Input    (6 -> 1 -> 7) + (2 -> 9 -> 5). That is 617 + 295 = 912
     * Output   (9 -> 1 -> 2) */

    public static void main(String[] args) {
        LinkedList numberOne = new LinkedList(new int[]{1, 7, 1, 6});
        LinkedList numberTwo = new LinkedList(new int[]{2, 3, 5, 8, 2});

        /*LinkedList result = add(numberOne, numberTwo);*/
        /*Node node = result.head;*/
        /*while (node.getNext() != null) {
            System.out.println("" + node.getNext().getValue() + " -> ");
            node = node.getNext();
        }*/
        /*Node result = addOptimal(numberOne, numberTwo);
        Node node = result;
        while (node != null) {
            System.out.println("" + node.getValue() + " -> ");
            node = node.getNext();
        }*/
        Node result = addOptimalFollowUp(numberOne, numberTwo);
        Node node = result.getNext();//This is to remove the excess zero appended to the head
        while (node != null) {
            System.out.println("" + node.getValue() + " -> ");
            node = node.getNext();
        }
    }

    /* Optimal Implementations */
    private static Node addOptimal(LinkedList numberOne, LinkedList numberTwo) {
        /* Recursive Way
         * Take the numberOne's digit and add with numberTwo's digit and save the
         * sum's primary digit and carry over the 10's digit to the next addition
         * In this way repeat by adding the digits and their carry each stage */
        return addOptimalRecursive(numberOne.head.getNext(), numberTwo.head.getNext(), 0);
    }

    private static Node addOptimalRecursive(Node numberOne, Node numberTwo, int carry) {
        if (numberOne == null && numberTwo == null && carry == 0) {
            return null;
        }
        Node result = new Node();
        int value = carry;
        if (numberOne != null) {
            value = value + numberOne.getValue();
        }
        if (numberTwo != null) {
            value = value + numberTwo.getValue();
        }
        result.setValue(value % 10);
        carry = value / 10;
        if (numberOne != null || numberTwo != null) {
            Node more = addOptimalRecursive(numberOne == null ? null : numberOne.getNext(), numberTwo == null ? null : numberTwo.getNext(), carry);
            result.setNext(more);
        }
        return result;
    }

    private static Node addOptimalFollowUp(LinkedList numberOne, LinkedList numberTwo) {
        int numberOneLength = getLength(numberOne.head.getNext());
        int numberTwoLength = getLength(numberTwo.head.getNext());
        int paddingZeroCount = Math.abs(numberOneLength - numberTwoLength);
        if (numberOneLength != numberTwoLength) {
            if (numberOneLength > numberTwoLength) {
                padZero(paddingZeroCount, numberTwo);
            } else {
                padZero(paddingZeroCount, numberOne);
            }
        }

        System.out.println("1's Length: " + numberOneLength + " 2's Length: " + numberTwoLength);
        return addOptimalFollowUpRecursive(numberOne.head.getNext(), numberTwo.head.getNext(), new Carry(), new Node());
    }

    private static LinkedList padZero(int paddingZeroCount, LinkedList number) {
        while (paddingZeroCount > 0) {
            Node currentFirstNode = number.head.getNext();
            Node zeroNode = new Node(0);
            zeroNode.setNext(currentFirstNode);
            number.head.setNext(zeroNode);
            paddingZeroCount--;
        }
        return null;
    }

    private static int getLength(Node number) {
        int count = 0;
        while (number != null) {
            count++;
            number = number.getNext();
        }
        return count;
    }

    private static Node addOptimalFollowUpRecursive(Node numberOne, Node numberTwo, Carry carry, Node result) {
        //Base Condition
        if (numberOne == null && numberTwo == null && carry.value == 0) {
            return null;
        }
        addOptimalFollowUpRecursive(numberOne.getNext(), numberTwo.getNext(), carry, result);
        int value = carry.value;
        value = value + numberOne.getValue() + numberTwo.getValue();
        carry.value = value / 10;
        Node currentNext = result.getNext();
        Node newNode = new Node(value % 10);
        newNode.setNext(currentNext);
        result.setNext(newNode);
        return result;
    }

    /* Earlier Implementations */
    public static LinkedList add(LinkedList numberOne, LinkedList numberTwo) {
        /* Here we are taking the digit and converting them to their digit position
         * i.e. if the 619 is used the 9 * 10^0 = 9, 1 * 10^1 = 10,
         * 9 * 10^2 = 900 and then adding the other value. This works properly
         * for the reversed digits currently. however it will not work without
         * slight modifications for a forward (FOLLOW UP) approach */
        long tenPower = 0;
        double one = 0;
        double two = 0;

        Node headOne = numberOne.head.getNext();
        Node headTwo = numberTwo.head.getNext();

        while (headOne != null || headTwo != null) {
            if (headOne != null) {
                one = one + (headOne.getValue() * Math.pow(10, tenPower));
                headOne = headOne.getNext();
            }
            if (headTwo != null) {
                two = two + (headTwo.getValue() * Math.pow(10, tenPower));
                headTwo = headTwo.getNext();
            }
            tenPower++;
        }

        int result = (int) Math.round(one + two);
        LinkedList list = new LinkedList();
        while (result > 0) {
            int digit = result % 10;
            list.insert(digit, list.head);
            result = result / 10;
        }
        return list;
    }
}

class Carry {
    public int value = 0;
}